package Classes;

public class Piece {

    //every piece has a shape, a square and an index which defines the location on the board
    private PieceShape shape;
    //every piece has a square where it stays on
    private Square square;
    private int currentIndex;

    public Piece() {}

    public Piece(PieceShape shape, Square square) {
        this.shape = shape;
        this.square = square;
        currentIndex = 0;
    }

    public PieceShape getShape() {
        return shape;
    }

    //the method which moves piece with given dice value
    protected void moveTo(int diceValue) {
        currentIndex += diceValue;
        this.square = Board.getSquare(currentIndex);
    }

    public Square getSquare() {
        return square;
    }
}
