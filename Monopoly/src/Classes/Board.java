package Classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Board {

    //Classes.Board constants which are square number and size times squares
    public static final int SIZE = 40;
    public static final Square[] squares = getSquaresFromCsvFile();

    //read square names from a text file
    public static Square[] getSquaresFromCsvFile() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        String[] squareNames = new String[SIZE];
        Square[] squares = new Square[SIZE];
        String[] lotSquareInfo = new String[];
        try {
            fileReader = new FileReader("Monopoly/src/Resources/Monopoly-Lots.txt");
            bufferedReader = new BufferedReader(fileReader);
            for(int i=0; i<SIZE; i++) {
                squareNames[i] = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch(FileNotFoundException e) {
            System.err.println("Unable to find csv file. -class Board");
        } catch(IOException e) {
            System.err.println("Error reading csv file.");
        }
        //creating each square objects
        int railRoadCtr = 1;
        int squareCtr = 1;
        for(int i=0; i<40; i++) {
            if(i == 0)
                squares[i] = new GoSquare("GoSquare");
            else if(i == 2 || i == 17 || i == 33)
                squares[i] = new RegularSquare("CommunityChest");
            else if(i == 7 || i == 22 || i == 36)
                squares[i] = new RegularSquare("Chance");
            else if(i == 5 || i == 15 || i == 25 || i == 35)
                squares[i] = new RailRoadSquare("RailRoad" + railRoadCtr++);
            else if(i == 12)
                squares[i] = new UtilitySquare("ElectricUtility");
            else if(i == 28)
                squares[i] = new UtilitySquare("WaterUtility");
            else if(i == 4)
                squares[i] = new IncomeTaxSquare("IncomeTaxSquare");
            else if(i == 10)
                squares[i] = new JailSquare("JailSquare");
            else if(i == 20)
                squares[i] = new FreeParkingSquare("FreeParkingSquare");
            else if(i == 30)
                squares[i] = new GoToJailSquare("GoToJailSquare");
            else if(i == 38)
                squares[i] = new LuxuryTaxSquare("LuxuryTaxSquare");
            else
                squares[i] = new LotSquare("Square" + squareCtr++);
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
