package Models;

import java.util.HashMap;
import java.util.Map;
public class Plateau {
    private Case[][] cases;

    public Plateau() {
        this.cases = new Case[8][8];
        initialiserPlateau();
    }

    private void initialiserPlateau() {
        // Initialisation des cases vides
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cases[i][j] = new Case(i,j);
            }
        }

        // Initialisation des pièces pour les joueurs blancs
        cases[0][0].setPiece(new Tour(Couleur.BLANC, cases[0][0]));
        cases[0][1].setPiece(new Cavalier(Couleur.BLANC, cases[0][1]));
        cases[0][2].setPiece(new Fou(Couleur.BLANC, cases[0][2]));
        cases[0][3].setPiece(new Reine(Couleur.BLANC, cases[0][3]));
        cases[0][4].setPiece(new Roi(Couleur.BLANC, cases[0][4]));
        cases[0][5].setPiece(new Fou(Couleur.BLANC, cases[0][5]));
        cases[0][6].setPiece(new Cavalier(Couleur.BLANC, cases[0][6]));
        cases[0][7].setPiece(new Tour(Couleur.BLANC, cases[0][7]));
        //cases[2][0].setPiece(new Tour(Couleur.BLANC, cases[2][0]));
        // Initialisation des pions pour les joueurs blancs
        for (int i = 0; i < 8; i++) {
            cases[1][i].setPiece(new Pion(Couleur.BLANC, cases[1][i]));
        }

        // Initialisation des pièces pour les joueurs noirs
        cases[7][0].setPiece(new Tour(Couleur.NOIR, cases[7][0]));
        cases[7][1].setPiece(new Cavalier(Couleur.NOIR, cases[7][1]));
        cases[7][2].setPiece(new Fou(Couleur.NOIR, cases[7][2]));
        cases[7][3].setPiece(new Reine(Couleur.NOIR, cases[7][3]));
        cases[7][4].setPiece(new Roi(Couleur.NOIR, cases[7][4]));
        cases[7][5].setPiece(new Fou(Couleur.NOIR, cases[7][5]));
        cases[7][6].setPiece(new Cavalier(Couleur.NOIR, cases[7][6]));
        cases[7][7].setPiece(new Tour(Couleur.NOIR, cases[7][7]));
        // Initialisation des pions pour les joueurs noirs
        for (int i = 0; i < 8; i++) {
            cases[6][i].setPiece(new Pion(Couleur.NOIR, cases[6][i]));
        }
    }

    public void afficherPlateau() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (cases[i][j].getPiece() != null) {
                    System.out.print(cases[i][j].getPiece().getId() + " ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

    public Case getCase(int x, int y) {
        return cases[x][y];
    }

    public void setCase(int x, int y, Case c) {
        cases[x][y] = c;
    }
}