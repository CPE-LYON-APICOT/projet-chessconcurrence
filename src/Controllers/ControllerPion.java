package Controllers;

import Models.Case;
import Models.Pion;
import Models.Plateau;

public class ControllerPion {
    public static boolean deplacerPion(Plateau plateau, Pion pion, Case nouvelleCase) {
        // Vérifier si le mouvement est valide
        if (pion.isMouvementValide(plateau, nouvelleCase)) {
            // Vérifier si la case de destination est vide
            if (nouvelleCase.getPiece() == null) {
                // Déplacer le pion vers la nouvelle case
                Case ancienneCase = pion.getCurrentCase();
                ancienneCase.setPiece(null);
                pion.setCurrentCase(nouvelleCase);
                nouvelleCase.setPiece(pion);
                return true; // Déplacement réussi
            } else {
                // La case de destination est occupée, le déplacement n'est pas valide
                System.out.println("Déplacement invalide : case occupée");
                return false;
            }
        } else {
            // Mouvement non valide pour le pion
            System.out.println("Déplacement invalide : mouvement non autorisé pour le pion");
            return false;
        }
    }
}
