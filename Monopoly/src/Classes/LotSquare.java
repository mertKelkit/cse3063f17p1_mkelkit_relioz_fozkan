package Classes;


import java.io.PrintWriter;

public class LotSquare extends PurchasableSquare {

    public LotSquare(String name, int index) {
        super(name, index);
    }

    @Override
    public void action(Player player) {
        if(this.isHasOwner()) {
            if(this.getOwner().equals(player))
                return;
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