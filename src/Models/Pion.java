package Models;
public class Pion extends Piece {
    public Pion(Couleur couleur, Case currentCase) {
        super(couleur, currentCase, 0);

        if (couleur == Couleur.BLANC) {
            this.setId(61);
        } else {
            this.setId(62);
        }
    }

    @Override
    public boolean isMouvementValide() {
        return false;
    }
}