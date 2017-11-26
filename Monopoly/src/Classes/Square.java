package Classes;

import java.io.PrintWriter;

public abstract class Square {

    //name of the square
    private String name;
    private int index;

    public Square(String name, int index) {
        this.name = name;
        this.index = index;
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
