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
    public boolean isMouvementValide(Plateau plateau, Case nouvelleCase) {
        Case ancienneCase = this.getCurrentCase();

        // Obtenir les coordonnées de la nouvelle case et de l'ancienne case
        int ancienneX = ancienneCase.getX();
        int ancienneY = ancienneCase.getY();
        int nouvelleX = nouvelleCase.getX();
        int nouvelleY = nouvelleCase.getY();

        // Calculer le déplacement de la tour
        int deplacementX = Math.abs(nouvelleX - ancienneX);
        int deplacementY = Math.abs(nouvelleY - ancienneY);

        // Vérifier si la case de destination est sur la même ligne ou colonne que la position actuelle de la tour
        if ((deplacementX == 0 && deplacementY != 0) || (deplacementX != 0 && deplacementY == 0)) {

            // Vérifier si la tour ne saute pas des cases
            if (deplacementX == 0) {
                int minY = Math.min(ancienneY, nouvelleY);
                int maxY = Math.max(ancienneY, nouvelleY);
                for (int y = minY + 1; y < maxY; y++) {
                    Case caseIntermediaire = plateau.getCase(ancienneX, y);
                    if (caseIntermediaire.getPiece() != null) {
                        return false; // La tour saute des cases
                    }
                }
            } else {
                int minX = Math.min(ancienneX, nouvelleX);
                int maxX = Math.max(ancienneX, nouvelleX);
                for (int x = minX + 1; x < maxX; x++) {
                    Case caseIntermediaire = plateau.getCase(x, ancienneY);
                    if (caseIntermediaire.getPiece() != null) {
                        return false; // La tour saute des cases
                    }
                }
            }

            // Vérifier si la case de destination est vide ou occupée par une pièce adverse
            if (nouvelleCase.getPiece() == null || nouvelleCase.getPiece().getCouleur() != this.getCouleur()) {
                return true; // Déplacement valide
            }
        }

        return false; // Autres mouvements invalides
    }
}

