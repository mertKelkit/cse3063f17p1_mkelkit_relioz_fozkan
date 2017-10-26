package Classes;

import java.util.Random;


public class Die {

    private int faceValue;
    private Random rand;

    public Die() {
        rand = new Random();
        faceValue = rand.nextInt(6) + 1;
    }

    public void rollDie() {
        faceValue = rand.nextInt(6) + 1;
    }

    public int getFaceValue() {
        return faceValue;
    }
}
