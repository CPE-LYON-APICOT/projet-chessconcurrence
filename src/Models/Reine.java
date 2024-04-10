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
    public boolean isMouvementValide(Plateau plateau, Case nouvelleCase) {
        Case ancienneCase = this.getCurrentCase();

        // Obtenir les coordonnées de la nouvelle case et de l'ancienne case
        int ancienneX = ancienneCase.getX();
        int ancienneY = ancienneCase.getY();
        int nouvelleX = nouvelleCase.getX();
        int nouvelleY = nouvelleCase.getY();

        // Calculer le déplacement de la reine
        int deplacementX = Math.abs(nouvelleX - ancienneX);
        int deplacementY = Math.abs(nouvelleY - ancienneY);

        // Le déplacement est valide si la reine se déplace en ligne droite ou en diagonale
        if ((deplacementX == 0 && deplacementY != 0) || (deplacementX != 0 && deplacementY == 0) ||
                (deplacementX == deplacementY)) {

            // Vérifier si la case de destination est vide ou occupée par une pièce adverse
            if (nouvelleCase.getPiece() == null || nouvelleCase.getPiece().getCouleur() != this.getCouleur()) {
                // Vérifier si le chemin est libre
                int pasX = nouvelleX > ancienneX ? 1 : nouvelleX < ancienneX ? -1 : 0;
                int pasY = nouvelleY > ancienneY ? 1 : nouvelleY < ancienneY ? -1 : 0;

                int x = ancienneX + pasX;
                int y = ancienneY + pasY;

                while (x != nouvelleX || y != nouvelleY) {
                    if (plateau.getCase(x, y).getPiece() != null) {
                        return false; // Il y a une pièce sur le chemin, le mouvement est invalide
                    }
                    x += pasX;
                    y += pasY;
                }
                return true; // Déplacement valide
            }
        }

        return false; // Autres mouvements invalides
    }
}
