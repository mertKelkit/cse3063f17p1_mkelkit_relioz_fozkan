package Classes;


public class LuxuryTaxSquare extends Square {

    public LuxuryTaxSquare(String name) {
        super(name);
    }

    @Override
    public void action(Player player) {
        System.out.println("Player " + player.getTurn() + " must pay 75$ to the bank because of luxury tax !!");
        player.getCash().dropCash(75l);
        if(!player.checkMoney()) {
            System.out.println("Player " + player.getTurn() + " goes bankrupt !");
            System.out.println("Player " + player.getTurn() + " removing from the game...");
            player.setBankrupt(true);
            return;
        }
        System.out.println("Player " + player.getTurn() + " (a.k.a. " + player + ") paid the luxury tax.");
        System.out.println("Now Player " + player.getTurn() + " has " + player.getCash());
    }
}
