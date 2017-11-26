package Classes;

import org.junit.Test;

import static org.junit.Assert.*;

public class JailSquareTest {
    @Test
    public void action() throws Exception {
        Player p = new Player("Ferhat", 10,new Piece(), new Cash(200));

       assertEquals(p.getTurn(),10);
       assertEquals(p.getCash().getAmount(),200);



    }

}