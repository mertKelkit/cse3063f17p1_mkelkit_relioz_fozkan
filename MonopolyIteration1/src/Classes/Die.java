package Classes;

import java.util.Random;


public class Die {

    //every die has face value and random generator
    private int faceValue;
    private Random rand;

    public Die() {
        rand = new Random();
        faceValue = rand.nextInt(6) + 1;
    }

    //generate a random number between 1 and 6
    public void rollDie() {
        faceValue = rand.nextInt(6) + 1;
    }

    //returns face value of the die
    public int getFaceValue() {
        return faceValue;
    }
}
