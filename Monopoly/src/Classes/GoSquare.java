package Classes;

import java.io.PrintWriter;

public class GoSquare extends Square {

    public GoSquare(String name, int index) {
        super(name, index);
    }

    @Override
    public void action(Player player) {
        System.out.println("Player " + player.getTurn() + " (a.k.a. " + player + ") is on Go Square right now. 200$ paid to the player !");
        player.getCash().addCash(200l);
        System.out.println("Now Player " + player.getTurn() + " has " + player.getCash());
    }
}
