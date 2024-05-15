package Models;

public class Roi extends Piece {
    public Roi(Couleur couleur, Case currentCase) {
        super(couleur, currentCase, 0);

        if (couleur == Couleur.BLANC) {
            this.setId(51);
        } else {
            this.setId(52);
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

        // Calculer le déplacement du roi
        int deplacementX = Math.abs(nouvelleX - ancienneX);
        int deplacementY = Math.abs(nouvelleY - ancienneY);

        // Vérifier si le mouvement est un roque
        if (deplacementY == 2 && deplacementX == 0) {
            if (estRoqueLong(plateau, nouvelleCase)) {
                System.out.println("Roque long possible");
                return true;
            }
            if (estRoqueCourt(plateau, nouvelleCase)) {
                System.out.println("Roque court possible");
                return true;
            }
        }

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

    private boolean estRoqueLong(Plateau plateau, Case nouvelleCase) {
        System.out.println("Vérification du roque long");
        if (!this.hasMoved() && nouvelleCase.getX() == this.getCurrentCase().getX() && nouvelleCase.getY() == this.getCurrentCase().getY() - 2) {
            Case caseTour = plateau.getCase(this.getCurrentCase().getX(), 0);
            Piece tour = caseTour.getPiece();
            if (tour instanceof Tour && !tour.hasMoved() &&
                    plateau.getCase(this.getCurrentCase().getX(), 1).getPiece() == null &&
                    plateau.getCase(this.getCurrentCase().getX(), 2).getPiece() == null &&
                    plateau.getCase(this.getCurrentCase().getX(), 3).getPiece() == null &&
                    !plateau.estEnEchec(this.getCouleur()) &&
                    !plateau.caseEstSousAttaque(this.getCouleur(), plateau.getCase(this.getCurrentCase().getX(), 2)) &&
                    !plateau.caseEstSousAttaque(this.getCouleur(), plateau.getCase(this.getCurrentCase().getX(), 3))) {
                return true;
            }
        }
        return false;
    }

    private boolean estRoqueCourt(Plateau plateau, Case nouvelleCase) {
        System.out.println("Vérification du roque court");
        if (!this.hasMoved() && nouvelleCase.getX() == this.getCurrentCase().getX() && nouvelleCase.getY() == this.getCurrentCase().getY() + 2) {
            Case caseTour = plateau.getCase(this.getCurrentCase().getX(), 7);
            Piece tour = caseTour.getPiece();
            if (tour instanceof Tour && !tour.hasMoved() &&
                    plateau.getCase(this.getCurrentCase().getX(), 5).getPiece() == null &&
                    plateau.getCase(this.getCurrentCase().getX(), 6).getPiece() == null &&
                    !plateau.estEnEchec(this.getCouleur()) &&
                    !plateau.caseEstSousAttaque(this.getCouleur(), plateau.getCase(this.getCurrentCase().getX(), 5)) &&
                    !plateau.caseEstSousAttaque(this.getCouleur(), plateau.getCase(this.getCurrentCase().getX(), 6))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deplacer(Plateau plateau, Case nouvelleCase) {
        Case ancienneCase = this.getCurrentCase();

        if (estRoqueLong(plateau, nouvelleCase)) {
            System.out.println("Effectuer le roque long");
            // Effectuer le roque long
            Piece tour = plateau.getCase(ancienneCase.getX(), 0).getPiece();
            tour.deplacer(plateau, plateau.getCase(ancienneCase.getX(), 3));
            System.out.println("Tour déplacée de (" + ancienneCase.getX() + ", 0) à (" + ancienneCase.getX() + ", 3)");
            super.deplacer(plateau, nouvelleCase);
            this.moved = true;
            System.out.println("Roque long effectué");
        } else if (estRoqueCourt(plateau, nouvelleCase)) {
            System.out.println("Effectuer le roque court");
            // Effectuer le roque court
            Piece tour = plateau.getCase(ancienneCase.getX(), 7).getPiece();
            tour.deplacer(plateau, plateau.getCase(ancienneCase.getX(), 5));
            System.out.println("Tour déplacée de (" + ancienneCase.getX() + ", 7) à (" + ancienneCase.getX() + ", 5)");
            super.deplacer(plateau, nouvelleCase);
            this.moved = true;
            System.out.println("Roque court effectué");
        } else {
            // Déplacement normal
            super.deplacer(plateau, nouvelleCase);
            this.moved = true;
        }
    }
}
