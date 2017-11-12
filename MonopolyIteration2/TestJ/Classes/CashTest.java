package Classes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CashTest {
    Cash c;

    @Before
    public void setUp() throws Exception {
        c = new Cash(200);
    }

    @Test
    public void getAmount() throws Exception {
        assertEquals(200, c.getAmount());
    }

    @Test
    public void addCash() throws Exception {
        c.addCash(10l);
        assertEquals(210, c.getAmount());
    }

    @Test
    public void dropCash() throws Exception {
        c.dropCash(10l);
        assertEquals(190, c.getAmount());
    }

    @Test
    public void dropCash1() throws Exception {
        c.dropCash(10);
        assertEquals(180, c.getAmount());
    }

}