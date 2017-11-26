package Classes;

import org.junit.Test;

import static org.junit.Assert.*;

public class LotSquareTest {
    @Test
    public void action() throws Exception {
        Player p = new Player ("Mert",1,new Piece(),new Cash(10000));
        LotSquare sq = new LotSquare("Square3", 2);
        sq.setPrice(100);
        for(int i=0; i<100; i++) {
            p.purchaseSquare(new Die(), sq);
        }
        assertEquals(true, p.getOwnedSquares().get(0).equals(sq));
    }

    @Test
    public void getRent() throws Exception {
        PurchasableSquare sq = new LotSquare("Square1", 10);
        sq.setRent(3);
        assertEquals(3, sq.getRent());
    }

}