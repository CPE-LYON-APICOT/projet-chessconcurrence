
import Models.ObservablePlateau;
import Models.Plateau;
import Models.SingletonPlateau;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Plateau plateau = SingletonPlateau.getInstance();
        ObservablePlateau observablePlateau = new ObservablePlateau(plateau);
        ViewsPlateau viewsPlateau = new ViewsPlateau(observablePlateau);
        viewsPlateau.updateBoard(plateau);
    }
}


/*
import Controllers.ControllerPiece;
import Controllers.ControllerPion;
import Models.*;


        if (isRoque) {
            Case roiFuturePosition = plateau.getCase(nouvelleX, nouvelleY);
            if (roiFuturePosition.getX() == 7 && roiFuturePosition.getY() == 1) { // Roque long
                Case tourCase = plateau.getCase(7, 7);
                tourCase.setPiece(null);
                Case nouvelleTourCase = plateau.getCase(7, 4);
                nouvelleTourCase.setPiece(new Tour(this.getCouleur(), nouvelleTourCase));
                System.out.println("Roque long");
            } else { // Roque court
                Case tourCase = plateau.getCase(7, 0);
                tourCase.setPiece(null);
                Case nouvelleTourCase = plateau.getCase(7, 2);
                nouvelleTourCase.setPiece(new Tour(this.getCouleur(), nouvelleTourCase));
                System.out.println("Roque court");
            }

        } else {

public class Main {
    public static void main(String[] args) {
        // Créer un nouveau plateau
        Plateau plateau = new Plateau();

        // Afficher le plateau avant le déplacement
        System.out.println("Plateau avant le déplacement :");
        plateau.afficherPlateau();
        System.out.println();

        // Récupérer un pion à déplacer (par exemple, un pion blanc)
        Pion pionADeplacer = (Pion) plateau.getCase(1, 0).getPiece();

        // Déplacer le pion vers une nouvelle case (par exemple, avancer d'une case)
        Case nouvelleCase = plateau.getCase(3, 0);

        // Tester le déplacement du pion en utilisant la fonction de votre contrôleur
        boolean deplacementReussi = ControllerPiece.deplacerPiece(plateau, pionADeplacer, nouvelleCase);

        if (deplacementReussi) {
            System.out.println("Déplacement réussi !");
        } else {
            System.out.println("Déplacement échoué.");
        }

        // Afficher le plateau après le déplacement pour vérifier le résultat
        System.out.println("\nPlateau après le déplacement :");
        plateau.afficherPlateau();


        Cavalier cavalierADeplacer = (Cavalier) plateau.getCase(0, 1).getPiece();

        Case nouvelleCaseCavalier = plateau.getCase(2, 2);

        boolean deplacementReussiCavalier = ControllerPiece.deplacerPiece(plateau, cavalierADeplacer, nouvelleCaseCavalier);

        if (deplacementReussiCavalier) {
            System.out.println("Déplacement réussi !");
        } else {
            System.out.println("Déplacement échoué.");
        }
        // Afficher le plateau après le déplacement pour vérifier le résultat
        System.out.println("\nPlateau après le déplacement :");
        plateau.afficherPlateau();
    }
}

*/
