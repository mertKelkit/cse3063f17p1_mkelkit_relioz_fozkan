package Classes;


public class RegularSquare extends Square {

    public RegularSquare(String name) {
        super(name);
    }

    @Override
    public void action(Player player) {
        System.out.println("Player " + player.getTurn() + " has " + player.getCash());
    }


}
