package Classes;


public class Piece {

    private PieceShape shape;
    //every piece has a square where it stays on
    private Square square;
    private int currentIndex;

    public Piece(PieceShape shape, Square square) {
        this.shape = shape;
        this.square = square;
        currentIndex = 0;
    }

    public PieceShape getShape() {
        return shape;
    }

    protected void moveTo(int diceValue) {
        currentIndex += diceValue;
        this.square = Board.getSquare(currentIndex);
    }

    public Square getSquare() {
        return square;
    }
}
