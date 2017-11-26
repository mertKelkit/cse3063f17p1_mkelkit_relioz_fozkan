package Classes;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//Basic test class in order to run game
public class Main {

    static FileOutputStream fout = null;
    static MultiOutputStream multiOut = null;
    static PrintStream stdout = null;

    public static void main(String[] args) {
        try {
            fout = new FileOutputStream("Monopoly/output.txt");
            multiOut = new MultiOutputStream(System.out, fout);
            stdout = new PrintStream(multiOut);
            System.setOut(stdout);
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        int numOfPlayers = getNumberOfPlayers();
        String[] nameOfPlayers = getPlayerNames(numOfPlayers);
        String[] shapeNames = getShapeNames();
        int initialCash = getInitialCash();
        ArrayList<Player> players = initializePlayers(nameOfPlayers, shapeNames, numOfPlayers, initialCash);
        int numOfIterations = getNumOfIterations();
        MonopolyGame game = new MonopolyGame(players);
        game.startGame(numOfIterations);

    }

    public static int getNumberOfPlayers() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of players must be between 2 and 8. Enter number of players: ");
        int numOfPlayers = sc.nextInt();
        System.out.println("Observer entered " + numOfPlayers);
        //controlling input, at least 2 - at most 8 players can play the game
        while(numOfPlayers < 2 || numOfPlayers > 8) {
            System.out.print("Please enter a number between 2 and 8: ");
            numOfPlayers = sc.nextInt();
            System.out.println("Observer entered " + numOfPlayers);
        }
        return numOfPlayers;
    }

    public static String[] getPlayerNames(int numOfPlayers) {
        String[] playerNames = new String[numOfPlayers];
        Scanner sc = new Scanner(System.in);
        String temp;
        //Getting names of players
        for(int i=0; i<numOfPlayers; i++) {
            System.out.print((i+1) + "- Enter a name for player: ");
            temp = sc.nextLine();
            System.out.println("Observer entered " + temp);
            //Checking current name with other names. If the name is already taken, program prints a warning message
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
        //If a name is already taken, return false
        for(String str : playerNames)
            if(name.equals(str)) return false;
        return true;
    }

    public static ArrayList<Player> initializePlayers(String[] playerNames, String[] shapeNames, int numOfPlayers, long initialCash) {
        ArrayList<Player> players = new ArrayList<>();
        for(int i=0; i<numOfPlayers; i++) {
            //Take a random piece shape for each player
            PieceShape shape = randomShape(shapeNames);
            //Then create Player instances with given names, turns, pieces with piece shapes and default starting squares
            players.add(new Player(playerNames[i], i+1, new Piece(shape, Board.getSquare(0)), new Cash((long)initialCash)));
        }
        return players;
    }

    public static PieceShape randomShape(String[] shapeNames) {
        Random rand = new Random();
        int random;
        String temp;
        do {
            //get a random shape from random created index of shape names array
            random = rand.nextInt(MonopolyGame.NUM_OF_PIECES);
            temp = shapeNames[random];
        } while(temp == null);
        //and set chosen piece shapes to null in order to prevent piece duplication
        shapeNames[random] = null;
        return new PieceShape(temp);
    }

    //getting piece shapes from a text file
    public static String[] getShapeNames() {
        String[] ret = new String[MonopolyGame.NUM_OF_PIECES];
        try {
            FileReader fileReader = new FileReader("Monopoly/src/Resources/piece_shapes.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for(int i=0; i<MonopolyGame.NUM_OF_PIECES; i++) {
                ret[i] = bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch(FileNotFoundException e) {
            System.err.println("Unable to find text file. -class MonopolyGame-");
        }catch(IOException e) {
            System.err.println("Error reading file.");
        }
        return ret;
    }

    public static int getNumOfIterations() {
        Scanner sc = new Scanner(System.in);
        int numOfIterations;
        System.out.print("Enter the number of iterations: ");
        //Getting number of iterations
        numOfIterations = sc.nextInt();
        System.out.println("Observer entered " + numOfIterations);
        //Control iteration number. Cannot be less or equal than 0.
        while(numOfIterations <= 0) {
            System.out.print("Please enter a bigger iteration number than 0: ");
            numOfIterations = sc.nextInt();
            System.out.println("Observer entered " + numOfIterations);
        }
        return numOfIterations;
    }

    public static int getInitialCash() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter initial cash for players: ");
        int initialCash = sc.nextInt();
        System.out.println("Observer entered " + initialCash);
        while(initialCash <= 0) {
            System.out.print("Please enter an amount larger than 0: ");
            initialCash = sc.nextInt();
            System.out.println("Observer entered " + initialCash);
        }
        return initialCash;
    }
}
