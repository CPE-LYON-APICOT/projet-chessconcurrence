package Models;

public class Cavalier extends Piece{
    public Cavalier(Couleur couleur, Case currentCase) {
        super(couleur, currentCase, 0);

        if (couleur == Couleur.BLANC) {
            this.setId(21);
        } else {
            this.setId(22);
        }
    }

    @Override
    public boolean isMouvementValide(Case nouvelleCase) {
        return false;
    }
}
