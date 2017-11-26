package Classes;


import java.io.PrintWriter;

public class JailSquare extends Square {

    public JailSquare(String name, int index) {
        super(name, index);
    }
    @Override
    public void action(Player player) {
        System.out.println("Player " + player.getTurn() + " is just a visitor in here. " + player + " will start moving next turn.");
        System.out.println("Player " + player.getTurn() + " has " + player.getCash());
    }
}
