import Controllers.ControllerPiece;
import Models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewsPlateau extends JFrame {
    private JPanel mainPanel;
    private JButton[][] squares;
    private Piece pieceSelectionnee;
    private Plateau plateau;
    private ArrayList<Case> casesPossibles;
    private boolean tourBlanc = true;

    int temp = 0;
    int xtemp = 0;
    int ytemp = 0;

    int xFutur = 0;
    int yFutur = 0;

    private boolean priseEnPassantEffectuee = false;

    public ViewsPlateau(Plateau plateau) {

        this.plateau = plateau;
        this.casesPossibles = new ArrayList<>();



        setTitle("Chess Board");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(8, 8));

        squares = new JButton[8][8];

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col] = new JButton();
                squares[row][col].setBackground((row + col) % 2 == 0 ? Color.GREEN : Color.GRAY);
                mainPanel.add(squares[row][col]);
            }
        }

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                int finalX = x;
                int finalY = y;
                JButton button = squares[x][y];
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int xCurent = 0;
                        Case clickedCase = plateau.getCase(finalX, finalY);

                        System.out.println("feere "+finalX);
                        System.out.println("ferer "+finalY);

                        xFutur = finalX;
                        yFutur = finalY;

                        Piece piece = clickedCase.getPiece();

                        if (piece != null && ((tourBlanc && piece.getCouleur() == Couleur.BLANC) || (!tourBlanc && piece.getCouleur() == Couleur.NOIR))) {
                            // Si la pièce cliquée appartient au joueur actuel et c'est son tour
                            pieceSelectionnee = piece;
                            highlightPossibleMoves(pieceSelectionnee);
                        } else if (pieceSelectionnee != null) {
                            // Si une pièce est déjà sélectionnée et que la case cliquée est vide
                            Case nouvelleCase = plateau.getCase(finalX, finalY);
                            System.out.println("test "+finalX);
                            System.out.println("test "+finalY);
                            if (casesPossibles.contains(nouvelleCase)) {
                                // Déplacer la pièce sélectionnée vers la nouvelle case
                                xCurent = pieceSelectionnee.getCurrentCase().getX();
                                boolean deplacementReussi = ControllerPiece.deplacerPiece(plateau, pieceSelectionnee, nouvelleCase);
                                if (deplacementReussi) {




                                    if (temp == 1) {
                                        System.out.println("Piece : "+finalX);
                                        System.out.println("Piece : "+finalY);
                                        plateau.getCase(finalX -1, finalY).setPiece(null);
                                        Case passantCase = plateau.getCase(xtemp, ytemp);
                                        Piece passantPiece = passantCase.getPiece();
                                        Pion passantPion = (Pion) passantPiece;
                                        passantPion.setDernierDeplacementDouble(false);
                                        ytemp = 0;
                                        xtemp = 0;
                                        temp = 0;

                                        priseEnPassantEffectuee = true;
                                    }
                                    if (pieceSelectionnee instanceof Pion) {
                                        Pion pion = (Pion) pieceSelectionnee;
                                        if ((pion.getCouleur() == Couleur.BLANC && xCurent == 6 && pion.getCurrentCase().getX() == 4) || (pion.getCouleur() == Couleur.NOIR && xCurent == 1 && pion.getCurrentCase().getX() == 3)) {
                                            temp = 1;
                                            ytemp = pion.getCurrentCase().getY();
                                            xtemp = pion.getCurrentCase().getX();
                                            System.out.println("Pion en passant");
                                            pion.setDernierDeplacementDouble(true);
                                        }
                                    }
                                    updateBoard(plateau);
                                    tourBlanc = !tourBlanc; // Changer de tour après un déplacement réussi
                                } else {
                                    JOptionPane.showMessageDialog(null, "Déplacement invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
                                }
                                resetHighlightedCases();
                                pieceSelectionnee = null;
                            }
                        }
                    }
                });
            }
        }

        add(mainPanel);
        setVisible(true);
    }

    // Méthode pour mettre en évidence visuellement les cases possibles pour une pièce sélectionnée
    private void highlightPossibleMoves(Piece piece) {
        // Réinitialiser les cases mises en évidence
        resetHighlightedCases();

        Case ancienneCase = piece.getCurrentCase();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Case nouvelleCase = plateau.getCase(x, y);
                if (piece.isMouvementValide(plateau, nouvelleCase)) {
                    // Mettre en évidence la case possible
                    squares[x][y].setBackground(Color.YELLOW);
                    casesPossibles.add(nouvelleCase);
                }
            }
        }
    }

    // Méthode pour réinitialiser les cases mises en évidence
    private void resetHighlightedCases() {
        for (Case c : casesPossibles) {
            int x = c.getX();
            int y = c.getY();
            squares[x][y].setBackground((x + y) % 2 == 0 ? Color.GREEN : Color.GRAY); // Couleur normale de la case
        }
        casesPossibles.clear(); // Effacer la liste des cases possibles
    }

    // Méthode pour mettre à jour l'affichage du plateau
    // Méthode pour mettre à jour l'affichage du plateau
    public void updateBoard(Plateau plateau) {

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Case currentCase = plateau.getCase(x, y);
                if (plateau.getCase(x, y).getPiece() != null)  {

                        // Sinon, afficher l'icône normalement
                    String couleur = currentCase.getPiece().getCouleur() == Couleur.BLANC ? "Blanc" : "Noir";
                    String typePiece = currentCase.getPiece().getClass().getSimpleName();
                    String imagePath = "Ressources/images/" + typePiece.toLowerCase() + couleur + ".png";
                    ImageIcon icon = new ImageIcon(imagePath);
                    squares[x][y].setIcon(icon);
                    if (priseEnPassantEffectuee && currentCase.getPiece() instanceof Pion && xFutur == x && yFutur == y) {

                        // Si le pion currentCase est blanc alors faire ça squares[x+1][y].setIcon(null);
                        if (currentCase.getPiece().getCouleur() == Couleur.BLANC) {

                            priseEnPassantEffectuee = false;

                        }else {
                            squares[x-1][y].setIcon(null);
                            priseEnPassantEffectuee = false;
                        }

                    }
                } else {
                    // Si la case est vide, supprimer l'
                    squares[x][y].setIcon(null);
                }
            }
        }
        // Réinitialiser la variable priseEnPassantEffectuee après avoir mis à jour l'affichage
    }

}
