package Classes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    Board board;
    @Before
    public void setUp() throws Exception {
        board = new Board();
    }


    @Test
    public void testGetSquare() throws Exception {
            for(int i=0; i<40; i++) {
                if(i == 10)
                    assertTrue("False Jail", board.getSquare(i).getClass().getName().equals("JailSquare"));
                else if(i == 4)
                    assertTrue("False Income Tax", board.getSquare(i).getClass().getName().equals("IncomeTaxSquare"));
                else if(i == 0)
                    assertTrue("False Go", board.getSquare(i).getClass().getName().equals("GoSquare"));
                else if(i == 20)
                    assertTrue("False Parking", board.getSquare(i).getClass().getName().equals("FreeParkingSquare"));
                else if(i == 30)
                    assertTrue("False Go To Jail", board.getSquare(i).getClass().getName().equals("GoToJailSquare"));
                else if(i == 38)
                    assertTrue("False Luxury Tax", board.getSquare(i).getClass().getName().equals("LuxuryTaxSquare"));
                else
                    assertTrue("False Square", board.getSquare(i).getClass().getName().equals("RegularSquare"));
            }
    }
}

