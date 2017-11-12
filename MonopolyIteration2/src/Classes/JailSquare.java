package Classes;


public class JailSquare extends Square {

    public JailSquare(String name) {
        super(name);
    }
    @Override
    public void action(Player player) {
        System.out.println("Player " + player.getTurn() + " is just a visitor in here. " + player + " will start moving next turn.");
    }
}
