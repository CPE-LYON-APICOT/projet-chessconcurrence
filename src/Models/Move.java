package Models;

public class Move {
    private Piece piece;
    private Case startCase;
    private Case endCase;
    private Piece capturedPiece;

    // Constructeur pour initialiser un mouvement
    public Move(Piece piece, Case startCase, Case endCase, Piece capturedPiece) {
        this.piece = piece;
        this.startCase = startCase;
        this.endCase = endCase;
        this.capturedPiece = capturedPiece;
    }

    // Méthodes d'accès pour récupérer les informations du mouvement
    public Piece getPiece() {
        return piece;
    }

    public Case getStartCase() {
        return startCase;
    }

    public Case getEndCase() {
        return endCase;
    }

    public Piece getCapturedPiece() {
        return capturedPiece;
    }

    // Méthodes pour récupérer les coordonnées de départ et d'arrivée
    public int getStartX() {
        return startCase.getX();
    }

    public int getStartY() {
        return startCase.getY();
    }

    public int getEndX() {
        return endCase.getX();
    }

    public int getEndY() {
        return endCase.getY();
    }

}
