package Classes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class FreeParkingSquareTest {
    Player p;
    @Test
    public void action() throws Exception {

        Player p = new Player("Rümeysa", 1,null,null);
       assertEquals(p.getTurn(), 1);

}

}
