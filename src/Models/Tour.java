package Models;

public class Tour extends Piece {
    public Tour(Couleur couleur, Case currentCase) {
        super(couleur, currentCase, 0);

        if (couleur == Couleur.BLANC) {
            this.setId(11);
        } else {
            this.setId(12);
        }
    }

    @Override
    public boolean isMouvementValide(Plateau plateau,Case nouvelleCase) {
        return false;
    }
}

