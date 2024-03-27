package Models;

public class Roi extends Piece{
    public Roi(Couleur couleur, Case currentCase) {
        super(couleur, currentCase, 0);

        if (couleur == Couleur.BLANC) {
            this.setId(51);
        } else {
            this.setId(52);
        }
    }

    @Override
    public boolean isMouvementValide(Plateau plateau,Case nouvelleCase) {
        return false;
    }
}
