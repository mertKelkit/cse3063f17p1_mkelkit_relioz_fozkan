package Classes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testrollDice() throws Exception {
        Die die1 = new Die();
        Die die2 = new Die();
        die1.setFaceValue();
        die2.setFaceValue();
        assertEquals(5, die1.getFaceValue()+die2.getFaceValue());


    }

    @Test
    public void testGetPiece() throws Exception {
        Piece p = new Piece();
        Player player = new Player(null, 0,
                p,null);
        assertEquals(p, player.getPiece());
    }

    @Test
    public void testGetTurn() throws Exception {
        Player player = new Player(null, 1,
                null,null);
        assertEquals(1,player.getTurn());
    }


    @Test
    public void TestgetCash() throws Exception {
        Cash c= new Cash(200);
        Player player = new Player(null, 1,
                null,c);

        assertEquals(c,player.getCash());
    }


}