package Classes;

import org.junit.Test;

import static org.junit.Assert.*;

public class RailRoadSquareTest {
    @Test
    public void action() throws Exception {
        Player p = new Player ("Rümeysa",2,new Piece(),new Cash(100));
        LotSquare sq = new LotSquare("Square4", 1);
        sq.setPrice(10);
        for(int i=0; i<3; i++) {
            p.purchaseSquare(new Die(), sq);
        }
        assertEquals(true, p.getOwnedSquares().get(0).equals(sq));
    }

    @Test
    public void getRent() throws Exception {
        PurchasableSquare sq = new LotSquare("Square3", 30);
        sq.setRent(5);
        assertEquals(5, sq.getRent());
    }

}