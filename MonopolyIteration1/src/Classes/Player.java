package Classes;


public class Player {

    private String name;
    private int turn;
    private Piece piece;

    public Player(String name, int turn, Piece piece) {
        this.name = name;
        this.turn = turn;
        this.piece = piece;
    }

    //encapsulation for some attributes
    public Piece getPiece() {
        return piece;
    }

    public String getName() {
        return name;
    }

    public int getTurn() {
        return turn;
    }

    protected void setTurn(int turn) {
        this.turn = turn;
    }
}
