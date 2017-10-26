package Classes;


public class Player {

    private String name;
    private int order;
    private Piece piece;

    public Player(String name, int order, Piece piece) {
        this.name = name;
        this.order = order;
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }
}
