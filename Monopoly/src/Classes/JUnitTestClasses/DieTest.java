package Classes.JUnitTestClasses;

import Classes.Die;

import static org.junit.Assert.*;


public class DieTest extends Die {
    Die die;
    @org.junit.Before
    public void setUp() throws Exception {
        die = new Die();
    }

    @org.junit.Test
    public void testGetFaceValue() {
        for (int i = 0; i < 100; i++) {
            die.rollDie();
            assertTrue("Dice value problem", !(die.getFaceValue() < 1 || die.getFaceValue() > 6));
        }
    }
}