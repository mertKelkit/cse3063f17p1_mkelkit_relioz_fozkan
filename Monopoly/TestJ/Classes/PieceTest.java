package Classes;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class PieceTest {
    @Test
    public void getShape() throws Exception {
        FileReader fileReader = new FileReader("Classes/piece_shapes.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Piece p = new Piece();
        assertTrue("no", p.getShape().equals("Cat"));

    }

    @Test
    public void moveTo() throws Exception {
    }

    @Test
    public void moveTo1() throws Exception {
    }

    @Test
    public void getSquare() throws Exception {
    }

}