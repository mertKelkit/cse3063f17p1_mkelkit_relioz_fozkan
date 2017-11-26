package Classes;


public class LotSquare extends PurchasableSquare {

    private final int NO_DIE = -1;

    public LotSquare(String name, int index) {
        super(name, index);
    }

    @Override
    public void action(Player player) {
        if(this.isHasOwner()) {
            if(this.getOwner().equals(player))
                return;
            System.out.println(this + " owned by " + this.getOwner() + ". " + player + " has to pay " + this.getRent(player, NO_DIE) + "$.");
            if(player.getCash().getAmount() - this.getRent(player, 0) <= 0) {
                player.setBankrupt(true);
                System.out.println("Player " + player.getTurn() + " is bankrupt !");
                System.out.println("Player " + player.getTurn() + " is removing from the game.");
                return;
            }
            else {
                player.getCash().dropCash((long)this.getRent(player, NO_DIE));
                this.getOwner().getCash().addCash((long)this.getRent(player, NO_DIE));
                System.out.println("Player " + player.getTurn() + " has paid " + this.getRent(player, NO_DIE) + "$ to Player " + this.getOwner().getTurn() + ".");
                System.out.println("Player " + player.getTurn() + " has " + player.getCash().getAmount() + "$.");
                return;
            }
        }
        else
            player.purchaseSquare(MonopolyGame.getDie1(), this);
    }

    @Override
    public int getRent(Player player, int dieValue) {
        return this.getRent();
    }

}
