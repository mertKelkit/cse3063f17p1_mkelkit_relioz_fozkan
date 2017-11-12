package Classes;

public class FreeParkingSquare extends Square {

    public FreeParkingSquare(String name) {
        super(name);
    }

    @Override
    public void action(Player player) {
        System.out.println("Player " + player.getTurn() + " parked his/her piece until the next turn !!!");
        System.out.println("Player " + player.getTurn() + " has " + player.getCash());
    }
}
