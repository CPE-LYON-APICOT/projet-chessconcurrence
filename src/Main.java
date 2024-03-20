import Models.Plateau;
import Models.Position;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Exemple de création de ViewsPlateau et utilisation de la méthode updateBoard
        ViewsPlateau viewsPlateau = new ViewsPlateau();
        Plateau plateau = new Plateau();
        // Assurez-vous d'avoir un plateau initialisé avec des pièces pour le tester
        viewsPlateau.updateBoard(plateau);
    }
}