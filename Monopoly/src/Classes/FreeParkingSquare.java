package Classes;

import java.io.PrintWriter;

public class FreeParkingSquare extends Square {

    public FreeParkingSquare(String name, int index) {
        super(name, index);
    }

    @Override
    public void action(Player player) {
        System.out.println("Player " + player.getTurn() + " parked his/her piece until the next turn !!!");
        System.out.println("Player " + player.getTurn() + " has " + player.getCash());
    }
}
