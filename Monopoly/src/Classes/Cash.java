package Classes;

public class Cash {

    private long amount;

    public Cash(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    public void addCash(long amount) {
        this.amount += amount;
    }

    public void dropCash(long amount) {
        this.amount -= amount;
    }

    public void dropCash(int percentage) {
        this.amount -= amount*percentage/100;
    }

    public String toString() {
        return amount + "$";
    }
}
