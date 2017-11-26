package Classes;


import java.util.ArrayList;

public class Player {

    private String name;
    private int turn;
    private Piece piece;
    private Cash cash;
    private boolean isSuspended;
    private int suspensionCounter;
    private int doubleCounter;
    private boolean isBankrupt;
    private ArrayList<PurchasableSquare> ownedSquares;

    public Player(String name, int turn, Piece piece, Cash cash) {
        this.name = name;
        this.turn = turn;
        this.piece = piece;
        this.cash = cash;
        this.isSuspended = false;
        this.suspensionCounter = 0;
        this.doubleCounter = 0;
        this.isBankrupt = false;
        this.ownedSquares = new ArrayList<>();
    }

    public int rollDice(Die die1, Die die2) {
        die1.setFaceValue();
        die2.setFaceValue();
        return die1.getFaceValue() + die2.getFaceValue();
    }

    public int rollDie(Die die) {
        die.setFaceValue();
        return die.getFaceValue();
    }
    //encapsulation for some attributes
    public Piece getPiece() {
        return piece;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Cash getCash() {
        return cash;
    }

    public String toString() {
        return name;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void incrementSuspensionCounter() {
        suspensionCounter++;
    }

    public int getSuspensionCounter() {
        return suspensionCounter;
    }

    public void resetSuspensionCounter() {
        suspensionCounter = 0;
    }

    public boolean checkMoney() {
        if(this.getCash().getAmount() <= 0)
            return false;
        return true;
    }

    public void incrementDoubleCounter() {
        doubleCounter++;
    }

    public int getDoubleCounter() {
        return doubleCounter;
    }

    public void resetDoubleCounter() {
        doubleCounter = 0;
    }

    public void setBankrupt(boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void purchaseSquare(Die die, PurchasableSquare square) {
        this.rollDie(die);
        if(die.getFaceValue() > 4 && this.getCash().getAmount() - square.getPrice() > 0) {
            ownedSquares.add(square);
            square.setHasOwner(true);
            square.setOwner(this);
            this.getCash().dropCash((long)square.getPrice());
            System.out.println("Player " + this.getTurn() + " purchased " + square + ".");
        }
        else {
            System.out.println("Player " + this.getTurn() + " couldn't roll a die larger than 4 or has not enough money to buy " + square + "!");
        }
        System.out.println("Player " + this.getTurn() + " (a.k.a. " + this + ") has " + this.getCash().getAmount() + "$ right now.");
    }
}
