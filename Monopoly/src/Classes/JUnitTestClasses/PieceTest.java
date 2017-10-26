package Classes.JUnitTestClasses;

import Classes.Piece;
import Classes.PieceShape;
import Classes.Square;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PieceTest extends Piece {
    Piece p1;
    @Before
    public void setUp() throws Exception {
        p1 = new Piece(new PieceShape("Top Hat"), new Square("Go To Jail"));
    }

    @Test
    public void getSquareTest() {
        String squareName = p1.getSquare().toString();
        assertTrue(squareName.equals("Go To Jail"));
    }
}