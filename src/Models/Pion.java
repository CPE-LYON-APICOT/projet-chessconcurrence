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
    public boolean isMouvementValide(Plateau plateau, Case nouvelleCase) {
        Case ancienneCase = this.getCurrentCase();

        // Vérifier si la case de destination est vide
        if (nouvelleCase.getPiece() != null) {
            return false; // Case occupée, déplacement invalide
        }

        // Obtenir les coordonnées de la nouvelle case et de l'ancienne case
        int ancienneX = ancienneCase.getX();
        int ancienneY = ancienneCase.getY();
        int nouvelleX = nouvelleCase.getX();
        int nouvelleY = nouvelleCase.getY();

        // Déterminer le déplacement du pion
        int deplacementX = Math.abs(nouvelleX - ancienneX);
        int deplacementY = Math.abs(nouvelleY - ancienneY);

        // Le déplacement est valide si :
        // - Le pion avance d'une case vers l'avant
        // - Ou si c'est le premier coup, le pion peut avancer de deux cases vers l'avant
        if ((deplacementX == 1 && deplacementY == 0) ||
                ((ancienneX == 1 && this.getCouleur() == Couleur.BLANC || ancienneX == 6 && this.getCouleur() == Couleur.NOIR) && deplacementX == 2 && deplacementY == 0)) {

            // Vérifier si une case est occupée juste devant si le pion avance de deux cases
            if (deplacementX == 2) {
                int pasX = ancienneX < nouvelleX ? 1 : -1;
                Case caseDevant = plateau.getCase(ancienneX + pasX, ancienneY);
                if (caseDevant.getPiece() != null) {
                    return false; // Case occupée juste devant, déplacement invalide
                }
            }

            return true; // Déplacement valide
        }

        return false; // Autres mouvements invalides
    }
    public Case getCurrentCase() {
        return this.getCase();
    }

    public void setCurrentCase(Case nouvelleCase) {
        this.setCase(nouvelleCase);
    }
}