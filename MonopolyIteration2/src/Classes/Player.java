package Classes;

public class Player {

    private String name;
    private int turn;
    private Piece piece;
    private Cash cash;
    private boolean isSuspended;

    public Player(String name, int turn, Piece piece, Cash cash) {
        this.name = name;
        this.turn = turn;
        this.piece = piece;
        this.cash = cash;
        this.isSuspended = false;
    }

    public int rollDice(Die die1, Die die2) {
        die1.setFaceValue();
        die2.setFaceValue();
        return die1.getFaceValue() + die2.getFaceValue();
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
}
