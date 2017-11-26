package Classes;


public class UtilitySquare extends PurchasableSquare {

    public UtilitySquare(String name, int index) {
        super(name, index);
    }

    @Override
    public void action(Player player) {
        //if this square has an owner
        if(this.isHasOwner()) {
            //if owner is itself, finish the method
            if(this.getOwner().equals(player))
                return;
            //rolls die for paying rent
            int dieValue = player.rollDie(MonopolyGame.getDie1());
            //player rolls a die for paying rent
            System.out.println(this + " owned by " + this.getOwner() + ". " + player + " has to pay " + this.getRent(player, dieValue) + "$.");
            //if doesn't have enough cash, player gets out from the game
            if(player.getCash().getAmount() - this.getRent(player, dieValue) <= 0) {
                player.setBankrupt(true);
                System.out.println("Player " + player.getTurn() + " is bankrupt !");
                System.out.println("Player " + player.getTurn() + " is removing from the game.");
                return;
            }
            //if has money, drop player's cash and add cash to the owner
            else {
                player.getCash().dropCash((long)this.getRent(player, dieValue));
                this.getOwner().getCash().addCash((long)this.getRent(player, dieValue));
                System.out.println("Player " + player.getTurn() + " has paid " + this.getRent(player, dieValue) + "$ to Player " + this.getOwner().getTurn() + ".");
                System.out.println("Player " + player.getTurn() + " has " + player.getCash().getAmount() + "$.");
                return;
            }
        }
        //attemp to buy
        else
            player.purchaseSquare(MonopolyGame.getDie1(), this);
    }

    @Override
    public int getRent(Player player, int dieValue) {
        this.setRent(10 * dieValue);
        return 10*dieValue;
    }
}
