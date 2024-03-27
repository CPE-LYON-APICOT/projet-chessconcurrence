package Models;

public class Fou extends Piece{
    public Fou(Couleur couleur, Case currentCase) {
        super(couleur, currentCase, 0);

        if (couleur == Couleur.BLANC) {
            this.setId(31);
        } else {
            this.setId(32);
        }
    }

    @Override
    public boolean isMouvementValide(Plateau plateau,Case nouvelleCase) {
        return false;
    }
}
