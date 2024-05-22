package Models;

public class Case {
    private int x, y;
    private Piece piece;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
}