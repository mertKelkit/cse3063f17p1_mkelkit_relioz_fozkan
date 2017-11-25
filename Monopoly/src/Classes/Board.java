package Classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Board {

    //Classes.Board constants which are square number and size times squares
    public static final int SIZE = 40;
    public static final Square[] squares = getSquaresFromTextFile();

    //read square names from a text file
    public static Square[] getSquaresFromTextFile() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        String[] squareNames = new String[SIZE];
        Square[] squares = new Square[SIZE];
        try {
            fileReader = new FileReader("Monopoly/src/Resources/square_names.txt");
            bufferedReader = new BufferedReader(fileReader);
            for(int i=0; i<SIZE; i++) {
                squareNames[i] = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch(FileNotFoundException e) {
            System.err.println("Unable to find text file. -class Classes.Board");
        } catch(IOException e) {
            System.err.println("Error reading file.");
        }
        //creating each square objects
        int i = 0;
        for(String squareName : squareNames) {
            if(i == 0)
                squares[i] = new GoSquare(squareName);
            else if(i == 4)
                squares[i] = new IncomeTaxSquare(squareName);
            else if(i == 10)
                squares[i] = new JailSquare(squareName);
            else if(i == 20)
                squares[i] = new FreeParkingSquare(squareName);
            else if(i == 30)
                squares[i] = new GoToJailSquare(squareName);
            else if(i == 38)
                squares[i] = new LuxuryTaxSquare(squareName);
            else
                squares[i] = new RegularSquare(squareName);
            squares[i].setIndex(i);
            i++;
        }
        return squares;
    }

    //get method for Square
    public static Square getSquare(int index) {
        return squares[index%SIZE];
    }
}
