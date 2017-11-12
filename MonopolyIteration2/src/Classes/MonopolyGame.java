package Classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


//The class which includes everything about the game
public class MonopolyGame {

    //Every Monopoly Game has 8 pieces as constant
    public static final int NUM_OF_PIECES = 8;
    //Number of players in the current game
    //Dice
    private Die die1;
    private Die die2;
    //The board which the game is on it
    private Board mainBoard;
    //Players
    private ArrayList<Player> players;


    public MonopolyGame(ArrayList<Player> players) {
        this.players = players;
        mainBoard = new Board();
        die1 = new Die();
        die2 = new Die();
    }

    //Setting up some instances
    public void startGame(int numOfIterations) {
        int choice;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        //Getting simulation type
        choice = getMenuChoice();
        rollDiceForTurn();
        //Run simulation with given parameters
        runSimulation(cal, sdf, numOfIterations, choice);
    }


    //prints given messages with delay
    public void printWithDelay(String str, long delay) {
        System.out.println(str);
        try {
            TimeUnit.MILLISECONDS.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //for appearance
    public void printBorder(int iteration, int delay) {
        if(iteration != 0)
            printWithDelay("\n----------------------- Iteration " + iteration + " -----------------------\n", delay);
        else
            printWithDelay("-----------------------------------------------------------", delay);
    }

    public void runSimulation(Calendar cal, SimpleDateFormat sdf, int numOfIterations, int choice) {
        printPieceOwners();
        System.out.println("Game starts...");
        //simulation types
        switch(choice) {
            //if simulation runs with delay
            case 1: {
                int doubleCounter = 0;
                for(int i=0; i<numOfIterations; i++) {
                    for(int j=0; j<players.size(); j++) {
                        printBorder(i+1, 100);
                        printWithDelay("Player " + players.get(j).getTurn() + " (" + players.get(j).getPiece().getShape() + ") is on " + players.get(j).getPiece().getSquare() + " square right now.", 100);
                        printWithDelay("Player " + players.get(j).getTurn() + " (a.k.a. " + players.get(j) + ") has " + players.get(j).getCash() + ".", 50);
                        printWithDelay("Player " + players.get(j).getTurn() + " (a.k.a. " + players.get(j) + ") is rolling dice...", 150);
                        int total = players.get(j).rollDice(die1, die2);
                        players.get(j).getPiece().moveTo(die1.getFaceValue() + die2.getFaceValue());
                        printWithDelay("Player " + players.get(j).getTurn() + " dice values: " + die1.getFaceValue() + "-" + die2.getFaceValue() + " Sum of dice: " + total, 400);
                        System.out.println();
                        printWithDelay("Player " + players.get(j).getTurn() + " is moving to "
                                + players.get(j).getPiece().getSquare(), 50);
                        printWithDelay("Player " + players.get(j).getTurn() + " (" + players.get(j).getPiece().getShape() + ") is on " +
                                players.get(j).getPiece().getSquare() + " sqaure at " + sdf.format(cal.getTime()), 500);
                        if(die1.getFaceValue() == die2.getFaceValue()) {
                            if(doubleCounter == 3) {
                                doubleCounter++;
                                printWithDelay("Player " + players.get(j).getTurn() + " rolled double three times in a row. " + players.get(j) + " is going to jail right now!", 75);
                                players.get(j).getPiece().moveTo(Board.squares[10]);
                                players.get(j).setSuspended(true);
                                break;
                            }
                            printWithDelay("Player " + players.get(j).getTurn() + " rolled a double. " + players.get(j) + " has the turn again...", 75);
                            j--;
                        }
                    }
                }
                System.out.println();
                printBorder(0, 100);
                break;
            }
            //if simulation ends instantly
            case 2: {
                int doubleDiceCounter = 0;
                for(int i=0; i<numOfIterations; i++) {
                    for(int j=0; j<players.size(); j++) {
                        printBorder(i+1, 0);
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
                    }
                }
                System.out.println();
                printBorder(0, 0);
                break;
            }
        }
        //end message
        System.out.printf("\n%d iterations completed.\nGame ends...\n", numOfIterations);
    }


    //print pieces and their ownner players
    public void printPieceOwners() {
        printBorder(0, 100);
        for(int i=0; i<players.size(); i++)
            System.out.println("Player " + players.get(i).getTurn() + " (a.k.a. " + players.get(i) + ") picks "
                    + players.get(i).getPiece().getShape() + "!");
        printBorder(0, 100);
    }


    //get a choice from menu and return it
    private int getMenuChoice() {
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("1. Continue with delay.");
        System.out.println("2. End instantly. Quick result.");
        System.out.print("Please choose simulation type: ");
        choice = sc.nextInt();
        while(choice < 1 || choice > 2) {
            System.out.print("Please enter a choice between 1 and 2: ");
            choice = sc.nextInt();
        }
        return choice;
    }

    public void rollDiceForTurn() {
        ArrayList<Integer> diceValues = new ArrayList<>();
        System.out.println("Players are rolling dice for turns...\n");
        for(int i=0; i<players.size(); i++) {
            int total = players.get(i).rollDice(die1, die2);
            System.out.println("-" + players.get(i) + " rolled " + die1.getFaceValue() + "-" + die2.getFaceValue() + " Sum of dice: " + total);
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

    public void sort(ArrayList<Integer> diceValues) {
        for(int i=0; i<players.size()-1; i++) {
            for(int j=i+1; j<players.size(); j++) {
                if(diceValues.get(j) > diceValues.get(i)) {
                    Collections.swap(diceValues, i, j);
                    Collections.swap(players, i, j);
                }
            }
        }
    }
}