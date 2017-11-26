package Classes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;



public class GoSquareTest {


    @Test
    public void action() throws Exception {

        Player p = new Player("Rümeysa", 1,null, new Cash(200));
        assertEquals(p.getTurn(), 1);
        assertEquals(p.getCash(),200); // it could be 200$

    }

}
