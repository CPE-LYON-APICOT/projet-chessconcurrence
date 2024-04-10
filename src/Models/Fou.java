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
    public boolean isMouvementValide(Plateau plateau, Case nouvelleCase) {
        Case ancienneCase = this.getCurrentCase();

        // Obtenir les coordonnées de la nouvelle case et de l'ancienne case
        int ancienneX = ancienneCase.getX();
        int ancienneY = ancienneCase.getY();
        int nouvelleX = nouvelleCase.getX();
        int nouvelleY = nouvelleCase.getY();

        // Calculer le déplacement du fou
        int deplacementX = Math.abs(nouvelleX - ancienneX);
        int deplacementY = Math.abs(nouvelleY - ancienneY);

        // Le déplacement est valide si le fou se déplace en diagonale
        if (deplacementX == deplacementY) {
            // Vérifier si la case de destination est vide ou occupée par une pièce adverse
            if (nouvelleCase.getPiece() == null || nouvelleCase.getPiece().getCouleur() != this.getCouleur()) {
                // Vérifier si le chemin est libre
                int directionX = (nouvelleX - ancienneX) > 0 ? 1 : -1;
                int directionY = (nouvelleY - ancienneY) > 0 ? 1 : -1;
                int x = ancienneX + directionX;
                int y = ancienneY + directionY;

                while (x != nouvelleX && y != nouvelleY) {
                    if (plateau.getCase(x, y).getPiece() != null) {
                        // Il y a une pièce sur le chemin, le mouvement est invalide
                        return false;
                    }
                    x += directionX;
                    y += directionY;
                }
                return true; // Déplacement valide
            }
        }

        return false; // Autres mouvements invalides
    }
}
