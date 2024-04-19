package Controllers;
import Models.*;

public class ControllerPiece {
    public static boolean deplacerPiece(Plateau plateau, Piece piece, Case nouvelleCase) {
        if (piece instanceof Roi && ((Roi) piece).isPetitRoqueValide(plateau, nouvelleCase)) {
            // Effectuer le petit roque
            ((Roi) piece).petitRoque(plateau, nouvelleCase);
            return true;
        } else {
            // Vérifier si le mouvement est valide pour une pièce normale
            if (piece.isMouvementValide(plateau, nouvelleCase)) {
                // Vérifier si la case de destination est vide ou contient une pièce adverse
                if (nouvelleCase.getPiece() == null || nouvelleCase.getPiece().getCouleur() != piece.getCouleur()) {
                    // Déplacer la pièce vers la nouvelle case
                    Case ancienneCase = piece.getCurrentCase();
                    ancienneCase.setPiece(null);
                    piece.setCurrentCase(nouvelleCase);
                    nouvelleCase.setPiece(piece);


                    return true; // Déplacement réussi
                } else {
                    // La case de destination est occupée par une pièce de la même couleur ou contient un roi adverse, le déplacement n'est pas valide
                    System.out.println("Déplacement invalide : case occupée par une pièce de la même couleur ou roi adverse");
                    return false;
                }
            } else {
                // Mouvement non valide pour la pièce
                System.out.println("Déplacement invalide : mouvement non autorisé pour la pièce");
                return false;
            }
        }
    }

}
