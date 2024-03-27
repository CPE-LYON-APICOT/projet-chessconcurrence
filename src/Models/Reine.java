package Models;

public class Reine extends Piece {
    public Reine(Couleur couleur, Case currentCase) {
        super(couleur, currentCase, 0);

        if (couleur == Couleur.BLANC) {
            this.setId(41);
        } else {
            this.setId(42);
        }
    }

    @Override
    public boolean isMouvementValide(Plateau plateau,Case nouvelleCase) {
        return false;
    }
}
