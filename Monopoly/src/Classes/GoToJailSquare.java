package Classes;



public class GoToJailSquare extends Square {

    public GoToJailSquare(String name, int index) {
        super(name, index);
    }

    @Override
    public void action(Player player) {
        System.out.println("Oh no ! Player " + player.getTurn() + " must go to the jail square now !");
        player.getPiece().moveTo(Board.squares[10]);
        player.setSuspended(true);
        System.out.println("Player " + player.getTurn() + " (a.k.a. " + player + ") is on Jail Square right now. ");
        System.out.println("Player " + player.getTurn() + " has " + player.getCash());
    }
}
