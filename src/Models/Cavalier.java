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
    public boolean isMouvementValide(Plateau plateau, Case nouvelleCase) {
        Case ancienneCase = this.getCurrentCase();

        // Obtenir les coordonnées de la nouvelle case et de l'ancienne case
        int ancienneX = ancienneCase.getX();
        int ancienneY = ancienneCase.getY();
        int nouvelleX = nouvelleCase.getX();
        int nouvelleY = nouvelleCase.getY();

        // Calculer le déplacement du cavalier
        int deplacementX = Math.abs(nouvelleX - ancienneX);
        int deplacementY = Math.abs(nouvelleY - ancienneY);

        // Le déplacement est valide si le cavalier se déplace en L
        if ((deplacementX == 2 && deplacementY == 1) || (deplacementX == 1 && deplacementY == 2)) {

            // Vérifier si la case de destination est vide ou occupée par une pièce adverse
            if (nouvelleCase.getPiece() == null || nouvelleCase.getPiece().getCouleur() != this.getCouleur()) {
                return true; // Déplacement valide
            } else if (nouvelleCase.getPiece() instanceof Roi && nouvelleCase.getPiece().getCouleur() != this.getCouleur()) {
                // Si la pièce sur la case de destination est un roi adverse, le déplacement est invalide
                return false;
            } else {
                // Si la case contient une pièce adverse autre qu'un roi, le déplacement est valide
                return true;
            }
        }

        return false; // Autres mouvements invalides
    }
}
