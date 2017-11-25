package Classes;

public abstract class Square {

    //name of the square
    private String name;
    private int index;

    public Square(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public abstract void action(Player player);

}
