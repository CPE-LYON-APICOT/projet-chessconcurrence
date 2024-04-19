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

        boolean isRoqueLong = estRoqueLong(plateau, nouvelleCase);
        boolean isRoqueCourt = estRoqueCourt(plateau, nouvelleCase);

        if (isRoqueLong) {
            Case roiCase = this.getCurrentCase();
            Case tourCase = plateau.getCase(7, 7);
            tourCase.setPiece(null);
            Case nouvelleTourCase = plateau.getCase(7, 4);
            nouvelleTourCase.setPiece(new Tour(this.getCouleur(), nouvelleTourCase));
            System.out.println("Roque long");
            return (deplacementY >= 1 && deplacementY <= 2 && deplacementX == 0);
        }
        else if (isRoqueCourt) {
            Case tourCase = plateau.getCase(7, 0);
            tourCase.setPiece(null);
            Case nouvelleTourCase = plateau.getCase(7, 2);
            nouvelleTourCase.setPiece(new Tour(this.getCouleur(), nouvelleTourCase));
            System.out.println("Roque court");
            return (deplacementY >= 1 && deplacementY <= 2 && deplacementX == 0);
        }
        else {
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
    }

    private boolean estRoqueLong(Plateau plateau, Case nouvelleCase) {
        // Vérifie si le roi est à sa position initiale

        Case roiCase = plateau.getCase(7, 3);
        if (roiCase.getPiece() != null) {
            return true;

        }

        return false;
    }


    private boolean estRoqueCourt(Plateau plateau, Case nouvelleCase) {
        return false;

    }

}

