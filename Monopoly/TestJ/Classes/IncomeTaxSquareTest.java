package Classes;

import org.junit.Test;

import static org.junit.Assert.*;

public class IncomeTaxSquareTest {
    @Test
    public void action() throws Exception {
        Player player = new Player("Rümeysa", 2,null,new Cash(200));

        player.getCash().dropCash(10);
        long expected = player.getCash().getAmount();
        long actual =  180;
        assertEquals(expected,actual);

    }

}