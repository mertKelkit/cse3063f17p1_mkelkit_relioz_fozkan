package Classes;

public class GoSquare extends Square {

    public GoSquare(String name) {
        super(name);
    }

    @Override
    public void action(Player player) {
        System.out.println("Player " + player.getTurn() + " (a.k.a. " + player + ") is on Go Square right now. 200$ paid to the player !");
        player.getCash().addCash(200l);
    }
}
