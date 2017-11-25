package Classes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DieTest {
    Die die1,die2;
    Player p;
    @Before
    public void setUp() throws Exception {
        die1 = new Die();
        die2 = new Die();
        p = new Player(null, 0, null, null);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetFaceValue() throws Exception {
        for (int i = 0; i < 100; i++) {
            p.rollDice(die1, die2);
            assertTrue("Dice value problem",
                    (!(die1.getFaceValue() < 1 || die1.getFaceValue() > 6)) &&
                            (!(die2.getFaceValue() < 1 || die2.getFaceValue() > 6)));
        }
    }

    @Test
    public void testRollDice() throws Exception {
        for(int i=0; i<100; i++) {
            int total = p.rollDice(die1, die2);
            assertTrue("Dice values are false", total == die1.getFaceValue() + die2.getFaceValue());
        }
    }

}