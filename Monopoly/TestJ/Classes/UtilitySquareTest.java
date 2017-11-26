package Classes;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilitySquareTest {
    @Test
    public void action() throws Exception {
        Player p = new Player ("Ferhat",3,new Piece(),new Cash(400));
        LotSquare sq = new LotSquare("Square5", 5);
        sq.setPrice(20);
        for(int i=0; i<5; i++) {
            p.purchaseSquare(new Die(), sq);
        }
        assertEquals(true, p.getOwnedSquares().get(0).equals(sq));
    }

    @Test
    public void getRent() throws Exception {
        Die d = new Die();
        PurchasableSquare sq = new LotSquare("Square5", 5);
        sq.setRent(10*5);
        assertEquals(50, sq.getRent());
    }

}