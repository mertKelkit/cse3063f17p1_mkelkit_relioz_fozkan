package Classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Board {

    //Classes.Board constants which are square number and size times squares
    public static final int SIZE = 40;
    public static final Square[] squares = getSquaresFromCsvFile();

    //read square names from a text file
    public static Square[] getSquaresFromCsvFile() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        Square[] squares = new Square[SIZE];
        ArrayList<String> lines = new ArrayList<>();
        boolean control = false;
        try {
            fileReader = new FileReader("Monopoly/src/Resources/Monopoly-Lots.csv");
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine()) != null) {
                if (control)
                    lines.add(line);
                control = true;
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
        int listCounter = 0;
        for(int i=0; i<SIZE; i++) {
            if(i == 0)
                squares[i] = new GoSquare("GoSquare", i);
            else if(i == 2 || i == 17 || i == 33)
                squares[i] = new RegularSquare("CommunityChest", i);
            else if(i == 7 || i == 22 || i == 36)
                squares[i] = new RegularSquare("Chance", i);
            else if(i == 5 || i == 15 || i == 25 || i == 35) {
                RailRoadSquare s = new RailRoadSquare("RailRoad" + railRoadCtr++, i);
                s.setPrice(200);
                squares[i] = s;
            }
            else if(i == 12) {
                UtilitySquare s = new UtilitySquare("ElectricUtility", i);
                s.setPrice(150);
                squares[i] = s;
            }
            else if(i == 28) {
                UtilitySquare s = new UtilitySquare("WaterUtility", i);
                s.setPrice(150);
                squares[i] = s;
            }
            else if(i == 4)
                squares[i] = new IncomeTaxSquare("IncomeTaxSquare", i);
            else if(i == 10)
                squares[i] = new JailSquare("JailSquare", i);
            else if(i == 20)
                squares[i] = new FreeParkingSquare("FreeParkingSquare", i);
            else if(i == 30)
                squares[i] = new GoToJailSquare("GoToJailSquare", i);
            else if(i == 38)
                squares[i] = new LuxuryTaxSquare("LuxuryTaxSquare", i);
            else {
                LotSquare s = new LotSquare("Square" + squareCtr++, i);
                squares[i] = s;
                String[] splitted = lines.get(listCounter).split(";");
                s.setIndex(Integer.parseInt(splitted[0]) - 1);
                s.setPrice(Integer.parseInt(splitted[1]));
                s.setRent(Integer.parseInt(splitted[2]));
            }
        }
        return squares;
    }

    //get method for Square
    public static Square getSquare(int index) {
        return squares[index%SIZE];
    }

    public void print() {
        for(int i=0; i<40; i++) {
            System.out.println(squares[i]);
        }
    }
}
