package Controllers;

import Models.Case;
import Models.Piece;
import Models.Plateau;
import Models.Couleur;

public class ControllerPiece {
    public static int deplacerPiece(Plateau plateau, Piece piece, Case nouvelleCase) {
        System.out.println("Tentative de déplacement de " + piece.getClass().getSimpleName() + " de (" + piece.getCurrentCase().getX() + "," + piece.getCurrentCase().getY() + ") à (" + nouvelleCase.getX() + "," + nouvelleCase.getY() + ")");

        // Vérifier si le mouvement est valide
        if (piece.isMouvementValide(plateau, nouvelleCase)) {
            // Vérifier si la case de destination est vide ou contient une pièce adverse
            if (nouvelleCase.getPiece() == null || nouvelleCase.getPiece().getCouleur() != piece.getCouleur()) {
                // Simuler le déplacement
                Case ancienneCase = piece.getCurrentCase();
                Piece pieceOriginale = nouvelleCase.getPiece();
                ancienneCase.setPiece(null);
                nouvelleCase.setPiece(piece);
                piece.setCurrentCase(nouvelleCase);

                // Vérifier si le déplacement met le roi du joueur en échec
                boolean enEchec = plateau.estEnEchec(piece.getCouleur());

                // Restaurer l'état original du plateau
                nouvelleCase.setPiece(pieceOriginale);
                ancienneCase.setPiece(piece);
                piece.setCurrentCase(ancienneCase);

                if (enEchec) {
                    System.out.println("Déplacement invalide : met le roi en échec");
                    return -1; // Mouvement invalide
                }

                // Déplacer la pièce pour de vrai
                ancienneCase.setPiece(null);
                nouvelleCase.setPiece(piece);
                piece.setCurrentCase(nouvelleCase);

                // Vérifier la fin de partie
                Couleur adversaire = piece.getCouleur() == Couleur.BLANC ? Couleur.NOIR : Couleur.BLANC;
                if (plateau.estEchecEtMat(adversaire)) {
                    System.out.println("Échec et mat ! Le joueur " + piece.getCouleur() + " gagne.");
                    return 2; // Échec et mat
                } else if (plateau.estEnEchec(adversaire)) {
                    System.out.println("Échec au roi " + adversaire);
                    return 1; // Échec
                }

                return 0; // Déplacement réussi
            } else {
                System.out.println("Déplacement invalide : case occupée par une pièce de la même couleur ou roi adverse");
                return -1; // Mouvement invalide
            }
        } else {
            System.out.println("Déplacement invalide : mouvement non autorisé pour la pièce");
            return -1; // Mouvement invalide
        }
    }
}
