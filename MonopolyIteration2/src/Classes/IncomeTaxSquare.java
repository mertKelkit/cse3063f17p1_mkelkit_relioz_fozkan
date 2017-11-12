package Classes;


public class IncomeTaxSquare extends Square {

    public IncomeTaxSquare(String name) {
        super(name);
    }

    @Override
    public void action(Player player) {
        System.out.println("Player " + player.getTurn() + " has to pay %10 of his/her money to the bank.");
        player.getCash().dropCash(10);
        System.out.println("Player " + player.getTurn() + " (a.k.a. " + player + ") paid the income tax. ");
        System.out.println("Now Player " + player.getTurn() + " has " + player.getCash());
    }
}
