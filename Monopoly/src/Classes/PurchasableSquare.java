package Classes;


public abstract class PurchasableSquare extends Square {

    private int cost;
    private int rent;
    private boolean hasOwner;
    private Player owner;

    public PurchasableSquare(String name) {
        super(name);
        this.hasOwner = false;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getRent() {
        return rent;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isHasOwner() {
        return hasOwner;
    }

    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }
}
