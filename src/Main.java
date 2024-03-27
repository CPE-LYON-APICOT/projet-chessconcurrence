import Controllers.ControllerPion;
import Models.*;

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
        Case nouvelleCase = plateau.getCase(4, 0);

        // Tester le déplacement du pion en utilisant la fonction de votre contrôleur
        boolean deplacementReussi = ControllerPion.deplacerPion(plateau, pionADeplacer, nouvelleCase);

        if (deplacementReussi) {
            System.out.println("Déplacement réussi !");
        } else {
            System.out.println("Déplacement échoué.");
        }

        // Afficher le plateau après le déplacement pour vérifier le résultat
        System.out.println("\nPlateau après le déplacement :");
        plateau.afficherPlateau();
    }
}
