package Models;

import java.util.HashMap;
import java.util.Map;
public class Plateau {
    private Case[][] cases;
    private Case lastDoubleStepStart; // Case de départ du dernier pion ayant avancé de deux cases
    private Case lastDoubleStepEnd; // Case d'arrivée du dernier pion ayant avancé de deux cases

    // Méthode pour enregistrer le dernier double pas
    public void setLastDoubleStep(Case start, Case end) {
        this.lastDoubleStepStart = start;
        this.lastDoubleStepEnd = end;
    }

    public Case getLastDoubleStepStart() {
        return lastDoubleStepStart;
    }

    public Case getLastDoubleStepEnd() {
        return lastDoubleStepEnd;
    }

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
        cases[0][0].setPiece(new Tour(Couleur.NOIR, cases[0][0]));
        cases[0][1].setPiece(new Cavalier(Couleur.NOIR, cases[0][1]));
        cases[0][2].setPiece(new Fou(Couleur.NOIR, cases[0][2]));
        cases[0][3].setPiece(new Reine(Couleur.NOIR, cases[0][3]));
        cases[0][4].setPiece(new Roi(Couleur.NOIR, cases[0][4]));
        cases[0][5].setPiece(new Fou(Couleur.NOIR, cases[0][5]));
        cases[0][6].setPiece(new Cavalier(Couleur.NOIR, cases[0][6]));
        cases[0][7].setPiece(new Tour(Couleur.NOIR, cases[0][7]));
        //cases[4][3].setPiece(new Roi(Couleur.BLANC, cases[4][3]));
        //cases[2][2].setPiece(new Tour(Couleur.NOIR, cases[2][2]));
        // Initialisation des pions pour les joueurs blancs
        for (int i = 0; i < 8; i++) {
            cases[1][i].setPiece(new Pion(Couleur.NOIR, cases[1][i]));
        }

        // Initialisation des pièces pour les joueurs noirs
        cases[7][0].setPiece(new Tour(Couleur.BLANC, cases[7][0]));
        cases[7][1].setPiece(new Cavalier(Couleur.BLANC, cases[7][1]));
        cases[7][2].setPiece(new Fou(Couleur.BLANC, cases[7][2]));
        cases[7][3].setPiece(new Reine(Couleur.BLANC, cases[7][3]));
        cases[7][4].setPiece(new Roi(Couleur.BLANC, cases[7][4]));
        cases[7][5].setPiece(new Fou(Couleur.BLANC, cases[7][5]));
        cases[7][6].setPiece(new Cavalier(Couleur.BLANC, cases[7][6]));
        cases[7][7].setPiece(new Tour(Couleur.BLANC, cases[7][7]));
        // Initialisation des pions pour les joueurs noirs
        for (int i = 0; i < 8; i++) {
            cases[6][i].setPiece(new Pion(Couleur.BLANC, cases[6][i]));
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

    private Case findKing(Couleur couleurRoi) {
        for (Case[] row : cases) {
            for (Case c : row) {
                Piece piece = c.getPiece();
                if (piece instanceof Roi && piece.getCouleur() == couleurRoi) {
                    return c;
                }
            }
        }
        return null;
    }



    private Piece[] getPiecesActives() {
        Piece[] pieces = new Piece[32];
        int index = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (cases[i][j].getPiece() != null) {
                    pieces[index] = cases[i][j].getPiece();
                    index++;
                }
            }
        }
        return pieces;
    }

    public boolean estEnEchec(Couleur couleur) {
        Case roiCase = null;

        // Trouver la position du roi
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Case c = cases[i][j];
                if (c.getPiece() != null && c.getPiece() instanceof Roi && c.getPiece().getCouleur() == couleur) {
                    roiCase = c;
                    break;
                }
            }
        }

        if (roiCase == null) {
            System.out.println("Erreur : Roi non trouvé sur le plateau");
            return false;
        }

        // Vérifier si une pièce adverse peut capturer le roi
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Case c = cases[i][j];
                if (c.getPiece() != null && c.getPiece().getCouleur() != couleur) {
                    if (c.getPiece().isMouvementValide(this, roiCase)) {
                        System.out.println("Le roi " + couleur + " est en échec par la pièce " + c.getPiece().getClass().getSimpleName() + " en (" + c.getX() + "," + c.getY() + ")");
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean estEchecEtMat(Couleur couleur) {
        if (!estEnEchec(couleur)) {
            return false;
        }

        // Vérifier si le joueur peut sortir de l'échec
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Case c = cases[i][j];
                if (c.getPiece() != null && c.getPiece().getCouleur() == couleur) {
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            Case destination = cases[x][y];
                            Piece pieceOriginale = destination.getPiece();
                            if (c.getPiece().isMouvementValide(this, destination)) {
                                // Simuler le déplacement
                                Case ancienneCase = c;
                                destination.setPiece(c.getPiece());
                                ancienneCase.setPiece(null);
                                if (!estEnEchec(couleur)) {
                                    // Annuler le déplacement
                                    ancienneCase.setPiece(destination.getPiece());
                                    destination.setPiece(pieceOriginale);
                                    return false;
                                }
                                // Annuler le déplacement
                                ancienneCase.setPiece(destination.getPiece());
                                destination.setPiece(pieceOriginale);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    public boolean caseEstSousAttaque(Couleur couleur, Case caseACheck) {
        System.out.println("Vérification si la case (" + caseACheck.getX() + "," + caseACheck.getY() + ") est sous attaque");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Case c = cases[i][j];
                if (c.getPiece() != null && c.getPiece().getCouleur() != couleur) {
                    if (c.getPiece().isMouvementValide(this, caseACheck)) {
                        System.out.println("Case (" + caseACheck.getX() + "," + caseACheck.getY() + ") est attaquée par " + c.getPiece().getClass().getSimpleName() + " en (" + c.getX() + "," + c.getY() + ")");
                        return true;
                    }
                }
            }
        }
        return false;
    }



}