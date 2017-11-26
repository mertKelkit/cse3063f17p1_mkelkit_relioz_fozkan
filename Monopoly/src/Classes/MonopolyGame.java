package Classes;


import java.text.SimpleDateFormat;
import java.util.*;


//The class which includes everything about the game
public class MonopolyGame {

    //Every Monopoly Game has 8 pieces as constant
    public static final int NUM_OF_PIECES = 8;
    //Number of players in the current game
    //Dice
    private static Die die1;
    private static Die die2;
    //The board which the game is on it
    private Board mainBoard;
    //Players
    private ArrayList<Player> players;


    public MonopolyGame(ArrayList<Player> players) {
        this.players = players;
        this.mainBoard = new Board();
        this.die1 = new Die();
        this.die2 = new Die();
    }

    //Setting up some instances
    public void startGame() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        //Getting simulation type
        rollDiceForTurn();
        //Run simulation with given parameters
        runSimulation(cal, sdf);
    }

    //for appearance
    private void printBorder(int iteration) {
        if(iteration != 0) {
            System.out.println("\n----------------------- Iteration " + iteration + " -----------------------\n");
        }
        else {
            System.out.println("-----------------------------------------------------------");
        }
    }

    private void runSimulation(Calendar cal, SimpleDateFormat sdf) {
        printPieceOwners();
        System.out.println("\nGame starts...");
        int ctr = 0;
        for(int i=0; ; i++) {
            for(int j=0; j<players.size(); j++) {
                printBorder(i + 1);
                //if current player is not in the jail
                if(!players.get(j).isSuspended()) {
                    //print status
                    System.out.println("Player " + players.get(j).getTurn() + " (" + players.get(j).getPiece().getShape() + ") is on " + players.get(j).getPiece().getSquare() + " square right now.");
                    System.out.println("Player " + players.get(j).getTurn() + " (a.k.a. " + players.get(j) + ") has " + players.get(j).getCash() + ".");
                    System.out.println("Player " + players.get(j).getTurn() + " (a.k.a. " + players.get(j) + ") is rolling dice...");
                    int total = players.get(j).rollDice(die1, die2);
                    players.get(j).getPiece().moveTo(die1.getFaceValue() + die2.getFaceValue());
                    System.out.println("Player " + players.get(j).getTurn() + " dice values: " + die1.getFaceValue() + "-" + die2.getFaceValue() + " Sum of dice: " + total);
                    System.out.println();
                    System.out.println("Player " + players.get(j).getTurn() + " is moving to "
                            + players.get(j).getPiece().getSquare());
                    System.out.println("Player " + players.get(j).getTurn() + " (" + players.get(j).getPiece().getShape() + ") is on " +
                            players.get(j).getPiece().getSquare() + " sqaure at " + sdf.format(cal.getTime()));
                    //store the die value because this die will be rolled again for paying rents of utility and railroad squares
                    int firstDieValue = die1.getFaceValue();
                    //call the polymorphic action method of square class
                    players.get(j).getPiece().getSquare().action(players.get(j));
                    //if player has money
                    if(players.get(j).isBankrupt()) {
                        //if a player has no money, empty its owned squares
                        for(int a=0; a<players.get(j).getOwnedSquares().size(); a++) {
                            players.get(j).getOwnedSquares().get(a).setHasOwner(false);
                            players.get(j).getOwnedSquares().get(a).setOwner(null);
                        }
                        //remove the player from the game
                        players.remove(j);
                        //if only one player left, game ends
                        if(players.size() == 1)
                            break;
                        //shift the player turns
                        for(int l=0; l<players.size(); l++) {
                            players.get(l).setTurn(players.indexOf(players.get(l))+1);
                        }
                        j--;
                        continue;
                    }
                    //if player rolled a double
                    if (firstDieValue == die2.getFaceValue()) {
                        players.get(j).incrementDoubleCounter();
                        //if player rolled 3 times in a row double, it goes to the jail
                        if (players.get(j).getDoubleCounter() == 3) {
                            System.out.println("Player " + players.get(j).getTurn() + " rolled double three times in a row. " + players.get(j) + " is going to jail right now!");
                            players.get(j).getPiece().moveTo(mainBoard.squares[10]);
                            //suspend player from the game
                            players.get(j).setSuspended(true);
                            continue;
                        }
                        System.out.println("Player " + players.get(j).getTurn() + " rolled a double. " + players.get(j) + " has the turn again...");
                        j--;
                        continue;
                    }
                    else
                        players.get(j).resetDoubleCounter();
                }
                //if player is in the jail
                else {
                    players.get(j).incrementSuspensionCounter();
                    if(players.get(j).getSuspensionCounter() == 3) {
                        //if it's players last turn in the jail, it must pay 50$
                        System.out.println("This is Player " + players.get(j).getTurn() + "'s last turn in the jail...");
                        System.out.println("Player " + players.get(j).getTurn() + " must pay 50$ to get out!");
                        players.get(j).getCash().dropCash(50l);
                        //if doesn't have enough money, player is removed from the game
                        if(players.get(j).getCash().getAmount() <= 0) {
                            System.out.println("Player " + players.get(j).getTurn() + " (a.k.a. " + players.get(j) + ") doesn't have enough money to pay it :(");
                            System.out.println("Player " + players.get(j).getTurn() + " goes bankruptcy !");
                            for(int a=0; a<players.get(j).getOwnedSquares().size(); a++) {
                                players.get(j).getOwnedSquares().get(a).setHasOwner(false);
                                players.get(j).getOwnedSquares().get(a).setOwner(null);
                            }
                            players.remove(j);
                            //if one player left, game ends
                            if(players.size() == 1) {
                                break;
                            }
                            //shift turns
                            for(int l=0; l<players.size(); l++) {
                                players.get(l).setTurn(players.indexOf(players.get(l))+1);
                            }
                            j--;
                            continue;
                        }
                        //remove suspension
                        players.get(j).resetSuspensionCounter();
                        players.get(j).setSuspended(false);
                        j--;
                        continue;
                    }
                    //player's jail decision case
                    else {
                        System.out.println("Player " + players.get(j).getTurn() + " is on " + players.get(j).getPiece().getSquare() + " right now.");
                        System.out.println("Player " + players.get(j).getTurn() + " has two decisions. S/he can pay 50$ to get out or can try rolling dice to double !");
                        //if player chooses to pay 50$
                        if(randomDecision(players.get(j))) {
                            System.out.println("Now Player " + players.get(j).getTurn() + " has " + players.get(j).getCash() + ".");
                            players.get(j).setSuspended(false);
                            players.get(j).resetSuspensionCounter();
                            j--;
                            continue;
                        }
                        //chooses to roll doubles
                        else {
                            System.out.println("Player " + players.get(j).getTurn() + " chose rolling dice for getting out !");
                            System.out.println("Player " + players.get(j).getTurn() + " is rolling dice...");
                            int total = players.get(j).rollDice(die1, die2);
                            System.out.println("Player " + players.get(j).getTurn() + " (a.k.a. " + players.get(j) + ") rolled " +
                                die1.getFaceValue() + "-" + die2.getFaceValue() + ". Total dice: " + total);
                            //if can roll double, player gets out from the jail and continues the game
                            if(die1.getFaceValue() == die2.getFaceValue()) {
                                players.get(j).setSuspended(false);
                                players.get(j).resetSuspensionCounter();
                                players.get(j).getPiece().moveTo(total);
                                System.out.println("Player " + players.get(j).getTurn() + " (" + players.get(j).getPiece().getShape() + ") is on" +
                                        players.get(j).getPiece().getSquare() + " right now. " );
                                System.out.println("Player " + players.get(j).getTurn() + " has " + players.get(j).getCash() + ".");
                                continue;
                            }
                            else {
                                System.out.println("Player " + players.get(j).getTurn() + " (a.k.a. " + players.get(j) + ") failed rolling doubles :(");
                                continue;
                            }
                        }
                    }
                }
            }
            ctr++;
            //if one player left, game ends
            if(players.size() == 1)
                break;
        }
        System.out.println();
        printBorder(0);
        //end message
        if(players.size() == 1)
            System.out.printf("\nPlayer %d (a.k.a. %s) is the winner in %d iterations with %d$ on the %s.\nGame ends...\n",
                    players.get(0).getTurn(), players.get(0), ctr, players.get(0).getCash().getAmount(), players.get(0).getPiece().getSquare());
    }


    //print pieces and their ownner players
    private void printPieceOwners() {
        printBorder(0);
        for(int i=0; i<players.size(); i++) {
            System.out.println("Player " + players.get(i).getTurn() + " (a.k.a. " + players.get(i) + ") picks "
                    + players.get(i).getPiece().getShape() + "!");
        }
        printBorder(0);
    }

    private void rollDiceForTurn() {
        ArrayList<Integer> diceValues = new ArrayList<>();
        System.out.println("\nPlayers are rolling dice for turns...\n");
        for(int i=0; i<players.size(); i++) {
            int total = players.get(i).rollDice(die1, die2);
            System.out.println("-> " + players.get(i) + " rolled " + die1.getFaceValue() + "-" + die2.getFaceValue() + ". Sum of dice: " + total);
            System.out.println();
            diceValues.add(total);
        }
        sort(diceValues);
        for(int i=0; i<players.size()-1; i++) {
            for(int j=i+1; j<players.size(); j++) {
                while(diceValues.get(j) == diceValues.get(i)) {
                    System.out.println(players.get(i) + " and " + players.get(j) + " rolled the same dice value. They are rolling again...");
                    int total = players.get(i).rollDice(die1, die2);
                    System.out.println("-" + players.get(i) + " rolled " + die1.getFaceValue() + "-" + die2.getFaceValue() + " Sum of dice: " + total);
                    System.out.println();
                    diceValues.set(i, total);
                    total = players.get(j).rollDice(die1, die2);
                    System.out.println("-" + players.get(j) + " rolled " + die1.getFaceValue() + "-" + die2.getFaceValue() + " Sum of dice: " + total);
                    System.out.println();
                    diceValues.set(j, total);
                    if(diceValues.get(i) < diceValues.get(j)) {
                        Collections.swap(diceValues, i, j);
                        Collections.swap(players, i, j);
                    }
                }
            }
        }
        for(int i=0; i<players.size(); i++) {
            players.get(i).setTurn(i+1);
            System.out.println(players.get(i) + " is the Player " + players.get(i).getTurn() + "!\n");
        }
    }

    private void sort(ArrayList<Integer> diceValues) {
        for(int i=0; i<players.size()-1; i++) {
            for(int j=i+1; j<players.size(); j++) {
                if(diceValues.get(j) > diceValues.get(i)) {
                    Collections.swap(diceValues, i, j);
                    Collections.swap(players, i, j);
                }
            }
        }
    }

    private boolean randomDecision(Player p) {
        Random rand = new Random();
        int dec = rand.nextInt(100);
        if((p.getSuspensionCounter() < 2 && dec <= 40 && p.getCash().getAmount() >= 51) || (p.getSuspensionCounter() >= 2 && dec <= 20 && p.getCash().getAmount() >= 51)) {
            System.out.println("Player " + p.getTurn() + " chose to pay for going out.");
            p.getCash().dropCash(50l);
            return true;
        }
        return false;
    }

    public static Die getDie1() {
        return die1;
    }
}