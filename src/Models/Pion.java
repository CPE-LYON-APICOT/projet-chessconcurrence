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

        // Obtenir les coordonnées de la nouvelle case et de l'ancienne case
        int ancienneX = ancienneCase.getX();
        int ancienneY = ancienneCase.getY();
        int nouvelleX = nouvelleCase.getX();
        int nouvelleY = nouvelleCase.getY();

        // Déterminer le déplacement du pion
        int deplacementX = Math.abs(nouvelleX - ancienneX);
        int deplacementY = Math.abs(nouvelleY - ancienneY);

        // Vérifier la direction du déplacement en fonction de la couleur du pion
        int direction;
        if (this.getCouleur() == Couleur.BLANC) {
            direction = -1; // Les pions blancs avancent vers le haut de l'échiquier
        } else {
            direction = 1; // Les pions noirs avancent vers le bas de l'échiquier
        }

        // Le déplacement est valide si :
        // - Le pion avance d'une case vers l'avant dans la bonne direction
        if (nouvelleX - ancienneX == direction && deplacementY == 0 && nouvelleCase.getPiece() == null) {
            return true;
        }

        // Le déplacement en diagonale est valide uniquement s'il y a une pièce adverse sur la case cible
        if (deplacementX == 1 && deplacementY == 1) {
            // Vérifier si la case cible est occupée par une pièce adverse
            if (nouvelleCase.getPiece() != null && nouvelleCase.getPiece().getCouleur() != this.getCouleur()) {
                return true;
            }
        }

        // Condition spéciale pour le premier déplacement
        if ((ancienneX == 1 && this.getCouleur() == Couleur.NOIR || ancienneX == 6 && this.getCouleur() == Couleur.BLANC) && (nouvelleX - ancienneX == 2 * direction) && deplacementY == 0 && nouvelleCase.getPiece() == null) {
            // Vérifier si une case est occupée juste devant si le pion avance de deux cases
            Case caseDevant = plateau.getCase(ancienneX + direction, ancienneY);
            if (caseDevant.getPiece() != null) {
                return false; // Case occupée juste devant, déplacement invalide
            }
            return true;
        }

        return false;
    }

    public Case getCurrentCase() {
        return this.getCase();
    }

    public void setCurrentCase(Case nouvelleCase) {
        this.setCase(nouvelleCase);
    }
}