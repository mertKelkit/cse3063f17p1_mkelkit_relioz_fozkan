package Classes;


public abstract class PurchasableSquare extends Square {

    private int price;
    private int rent;
    private boolean hasOwner;
    private Player owner;
    private int index;

    public PurchasableSquare(String name, int index) {
        super(name, index);
        this.hasOwner = false;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isHasOwner() {
        return hasOwner;
    }

    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }

    public int getRent() { return rent; }

    public abstract int getRent(Player player, int dieValue);

}
