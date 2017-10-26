package Classes;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class MonopolyGame {

    private final int NUM_OF_PIECES = 8;
    private int numOfPlayers;
    private Die die1;
    private Die die2;
    private Board mainBoard;
    private Player[] players;


    public MonopolyGame() {
        numOfPlayers = getNumberOfPlayers();
        die1 = new Die();
        die2 = new Die();
        mainBoard = new Board();
        players = new Player[numOfPlayers];
        startGame();
    }

    public static int getNumberOfPlayers() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of players must be between 2 and 8. Enter number of players: ");
        int numOfPlayers = sc.nextInt();
        while(numOfPlayers < 2 || numOfPlayers > 8) {
            System.out.print("Please enter a number between 2 and 8: ");
            numOfPlayers = sc.nextInt();
        }
        return numOfPlayers;
    }

    public static String[] getPlayerNames(int numOfPlayers) {
        String[] playerNames = new String[numOfPlayers];
        Scanner sc = new Scanner(System.in);
        String temp;
        for(int i=0; i<numOfPlayers; i++) {
            System.out.print("Enter a name for Player " + (i+1) + ": ");
            temp = sc.nextLine();
            if(check(playerNames, temp))
                playerNames[i] = temp;
            else {
                i--;
                System.out.print("This name is already taken. ");
            }
        }
        return playerNames;
    }

    public static boolean check(String[] playerNames, String name) {
        for(String str : playerNames)
            if(name.equals(str)) return false;
        return true;
    }

    public void initializePlayers(String[] playerNames, String[] shapeNames) {
        for(int i=0; i<numOfPlayers; i++) {
            PieceShape shape = randomShape(shapeNames);
            players[i] = new Player(playerNames[i], i+1, new Piece(shape, mainBoard.getSquare(0)));
        }
    }

    public void startGame() {
        Scanner sc = new Scanner(System.in);
        int iterationNumber;
        int choice;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String[] shapeNames = getShapeNames();
        String[] playerNames = getPlayerNames(numOfPlayers);
        initializePlayers(playerNames, shapeNames);
        System.out.print("Enter the number of iterations: ");
        iterationNumber = sc.nextInt();
        while(iterationNumber <= 0) {
            System.out.print("Please enter a bigger iteration number than 0: ");
            iterationNumber = sc.nextInt();
        }
        choice = getMenuChoice();

        runSimulation(cal, sdf, iterationNumber, choice);
    }

    public PieceShape randomShape(String[] shapeNames) {
        Random rand = new Random();
        int random;
        String temp;
        do {
            random = rand.nextInt(NUM_OF_PIECES);
            temp = shapeNames[random];
        } while(temp == null);
        shapeNames[random] = null;
        return new PieceShape(temp);
    }

    public String[] getShapeNames() {
        String[] ret = new String[NUM_OF_PIECES];
        try {
            FileReader fileReader = new FileReader("src/Resources/piece_shapes.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for(int i=0; i<NUM_OF_PIECES; i++) {
                ret[i] = bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch(FileNotFoundException e) {
            System.err.println("Unable to find text file.");
        }catch(IOException e) {
            System.err.println("Error reading file.");
        }
        return ret;
    }

    public void printWithDelay(String str, long delay) {
        System.out.println(str);
        try {
            TimeUnit.MILLISECONDS.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printBorder(int iteration, int delay) {
        if(iteration != 0)
            printWithDelay("\n----------------------- Iteration " + iteration + " -----------------------\n", delay);
        else
            printWithDelay("-----------------------------------------------------------", delay);
    }

    public void runSimulation(Calendar cal, SimpleDateFormat sdf, int iterationNumber, int choice) {
        printPieceOwners();
        System.out.println("Game starts...");
        switch(choice) {
            case 1: {
                Scanner sc = new Scanner(System.in);
                System.out.println("Press enter to continue...");
                for (int i = 0; i < iterationNumber; i++) {
                    for (int j = 0; j < numOfPlayers; j++) {
                        if(sc.nextLine().isEmpty()) {
                            printBorder(i + 1, 0);
                            System.out.println("Player " + players[j].getTurn() + " (" + players[j].getPiece().getShape() + ") is on " + players[j].getPiece().getSquare() + " square right now.");
                            System.out.println("Player " + players[j].getTurn() + " (a.k.a. " + players[j].getName() + ") is rolling dice...");
                            die1.rollDie();
                            die2.rollDie();
                            players[j].getPiece().moveTo(die1.getFaceValue() + die2.getFaceValue());
                            System.out.println("Player " + players[j].getTurn() + " dice values: " + die1.getFaceValue() + "-" + die2.getFaceValue() + " Sum of dice: " + (die1.getFaceValue() + die2.getFaceValue()));
                            System.out.println();
                            System.out.println("Player " + players[j].getTurn() + " is moving to "
                                    + players[j].getPiece().getSquare());
                            System.out.println("Player " + players[j].getTurn() + " (" + players[j].getPiece().getShape() + ") is on " +
                                    players[j].getPiece().getSquare() + " sqaure at " + sdf.format(cal.getTime()));
                            System.out.println("\nPress enter to continue...");
                        }
                        else {
                            j--;
                            System.out.println("\nInvalid input. Press enter to continue...");
                        }
                    }
                }
                System.out.println();
                printBorder(0, 0);
                break;
            }
            case 2: {
                for(int i=0; i<iterationNumber; i++) {
                    for(int j=0; j<numOfPlayers; j++) {
                        printBorder(i+1, 100);
                        printWithDelay("Player " + players[j].getTurn() + " (" + players[j].getPiece().getShape() + ") is on " + players[j].getPiece().getSquare() + " square right now.", 100);
                        printWithDelay("Player " + players[j].getTurn() + " (a.k.a. " + players[j].getName() + ") is rolling dice...", 150);
                        die1.rollDie();
                        die2.rollDie();
                        players[j].getPiece().moveTo(die1.getFaceValue() + die2.getFaceValue());
                        printWithDelay("Player " + players[j].getTurn() + " dice values: " + die1.getFaceValue() + "-" + die2.getFaceValue() + " Sum of dice: " + (die1.getFaceValue()+die2.getFaceValue()), 400);
                        System.out.println();
                        printWithDelay("Player " + players[j].getTurn() + " is moving to "
                                + players[j].getPiece().getSquare(), 50);
                        printWithDelay("Player " + players[j].getTurn() + " (" + players[j].getPiece().getShape() + ") is on " +
                                players[j].getPiece().getSquare() + " sqaure at " + sdf.format(cal.getTime()), 500);
                    }
                }
                System.out.println();
                printBorder(0, 100);
                break;
            }
            case 3: {
                for(int i=0; i<iterationNumber; i++) {
                    for(int j=0; j<numOfPlayers; j++) {
                        printBorder(i+1, 0);
                        System.out.println("Player " + players[j].getTurn() + " (" + players[j].getPiece().getShape() + ") is on " + players[j].getPiece().getSquare() + " square right now.");
                        System.out.println("Player " + players[j].getTurn() + " (a.k.a. " + players[j].getName() + ") is rolling dice...");
                        die1.rollDie();
                        die2.rollDie();
                        players[j].getPiece().moveTo(die1.getFaceValue() + die2.getFaceValue());
                        System.out.println("Player " + players[j].getTurn() + " dice values: " + die1.getFaceValue() + "-" + die2.getFaceValue() + " Sum of dice: " + (die1.getFaceValue()+die2.getFaceValue()));
                        System.out.println();
                        System.out.println("Player " + players[j].getTurn() + " is moving to "
                                + players[j].getPiece().getSquare());
                        System.out.println("Player " + players[j].getTurn() + " (" + players[j].getPiece().getShape() + ") is on " +
                                players[j].getPiece().getSquare() + " sqaure at " + sdf.format(cal.getTime()));
                    }
                }
                System.out.println();
                printBorder(0, 0);
                break;
            }
        }
        System.out.printf("\n%d iterations completed.\nGame ends...\n", iterationNumber);
    }

    public void printPieceOwners() {
        printBorder(0, 100);
        for(int i=0; i<numOfPlayers; i++)
            System.out.println("Player " + players[i].getTurn() + " (a.k.a. " + players[i].getName() + ") picks "
                    + players[i].getPiece().getShape() + "!");
        printBorder(0, 100);
    }

    private int getMenuChoice() {
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("1. Continue with pressing enter key.");
        System.out.println("2. Continue with delay.");
        System.out.println("3. End instantly. Quick result.");
        System.out.print("Please choose simulation type: ");
        choice = sc.nextInt();
        while(choice < 1 || choice > 3) {
            System.out.print("Please enter a choice between 1 and 3: ");
            choice = sc.nextInt();
        }
        return choice;
    }

}