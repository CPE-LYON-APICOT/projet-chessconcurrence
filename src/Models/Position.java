package Models;

public class Position {
    private int ligne;
    private int colonne;

    public Position(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    // Ajoutez des méthodes getter et setter au besoin

    @Override
    public String toString() {
        return "(" + ligne + "," + colonne + ")";
    }
}