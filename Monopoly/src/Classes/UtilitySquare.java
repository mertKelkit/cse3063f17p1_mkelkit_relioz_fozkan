package Classes;


import java.io.FileWriter;
import java.io.PrintWriter;

public class UtilitySquare extends PurchasableSquare {

    public UtilitySquare(String name, int index) {
        super(name, index);
    }

    @Override
    public void action(Player player) {
        if(this.isHasOwner()) {
            if(this.getOwner().equals(player))
                return;
            //player rolls a die for paying rent
            int dieValue = player.rollDie(MonopolyGame.getDie1());
            this.setRent(10 * dieValue);
            System.out.println(this + " owned by " + this.getOwner() + ". " + player + " has to pay " + this.getRent() + "$.");
            if(player.getCash().getAmount() - this.getRent() <= 0) {
                System.out.println("Player " + player.getTurn() + " is bankrupt !");
                return;
            }
            else {
                player.getCash().dropCash((long)this.getRent());
                this.getOwner().getCash().addCash((long)this.getRent());
                System.out.println("Player " + player.getTurn() + " has paid " + this.getRent() + "$ to Player " + this.getOwner().getTurn() + ".");
                System.out.println("Player " + player.getTurn() + " has " + player.getCash().getAmount() + "$.");
                return;
            }
        }
        else
            player.purchaseSquare(MonopolyGame.getDie1(), this);
    }
}
