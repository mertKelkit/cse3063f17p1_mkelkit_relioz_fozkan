package Classes;

/**
 * Created by mertkelkit on 08/11/2017.
 */
public class Cash {

    private long amount;

    public Cash(long amount) {
        this.amount = amount;
    }

    public long getAmount() { return amount; }

    public void increaseAmount(long amount) {
        this.amount += amount;
    }

    public void decreaseAmount(long amount) {
        this.amount -= amount;
    }

    public String toString() {
        return String.valueOf(amount) + "$";
    }
}
