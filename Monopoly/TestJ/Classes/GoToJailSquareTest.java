package Classes;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;

import static Classes.Board.getSquaresFromCsvFile;
import static org.junit.Assert.*;



public class GoToJailSquareTest {
    Player p;


    @Test
    public void action() throws Exception {
        p = new Player("Rümeysa", 1,null, new Cash(200));
        System.out.println("Oh no ! Player " + p.getTurn() + " must go to the jail square now !");
        assertEquals(p.getTurn(), 1);


    }

}
