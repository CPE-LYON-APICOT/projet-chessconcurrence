package Models;

import Controllers.ControllerPiece;

public class Roi extends Piece{
    public Roi(Couleur couleur, Case currentCase) {
        super(couleur, currentCase, 0);

        if (couleur == Couleur.BLANC) {
            this.setId(51);
        } else {
            this.setId(52);
        }
    }

    public Case getCurrentCase() {
        return this.getCase();
    }

    public void setCurrentCase(Case nouvelleCase) {
        this.setCase(nouvelleCase);
    }
    @Override
    public boolean isMouvementValide(Plateau plateau, Case nouvelleCase) {
        Case ancienneCase = this.getCurrentCase();

        // Obtenir les coordonnées de la nouvelle case et de l'ancienne case
        int ancienneX = ancienneCase.getX();
        int ancienneY = ancienneCase.getY();
        int nouvelleX = nouvelleCase.getX();
        int nouvelleY = nouvelleCase.getY();

        // Calculer le déplacement du roi
        int deplacementX = Math.abs(nouvelleX - ancienneX);
        int deplacementY = Math.abs(nouvelleY - ancienneY);

        // Le déplacement est valide si le roi se déplace d'une seule case dans n'importe quelle direction
        if ((deplacementX == 1 && deplacementY == 0) || (deplacementX == 0 && deplacementY == 1) ||
                (deplacementX == 1 && deplacementY == 1)) {

            // Vérifier si la case de destination est vide ou occupée par une pièce adverse
            if (nouvelleCase.getPiece() == null || nouvelleCase.getPiece().getCouleur() != this.getCouleur()) {
                return true;
            }
        }

        return false;
    }

    public boolean isPetitRoqueValide(Plateau plateau, Case tourCase) {
        // Vérifier si le roi n'a pas encore bougé
        if (!this.hasMoved() && !plateau.isChecked(this.getCouleur())) {
            // Vérifier si la tour est à droite du roi et n'a pas bougé
            if (tourCase.getX() > this.getCurrentCase().getX() && !tourCase.getPiece().hasMoved()) {
                // Vérifier si les cases entre le roi et la tour sont vides
                for (int x = this.getCurrentCase().getX() + 1; x < tourCase.getX(); x++) {
                    if (plateau.getCase(x, this.getCurrentCase().getY()).getPiece() != null) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private int getMoveCount() {
        return 0;
    }

    public void petitRoque(Plateau plateau, Case tourCase) {
        // Déplacer le roi vers la tour
        Case destinationRoi = plateau.getCase(this.getCurrentCase().getX() + 2, this.getCurrentCase().getY());
        ControllerPiece.deplacerPiece(plateau, this, destinationRoi);

        // Déplacer la tour à côté du roi
        Case destinationTour = plateau.getCase(this.getCurrentCase().getX() + 1, this.getCurrentCase().getY());
        ControllerPiece.deplacerPiece(plateau, tourCase.getPiece(), destinationTour);
    }
}
