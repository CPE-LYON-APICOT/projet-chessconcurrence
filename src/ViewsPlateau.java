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
    private boolean tourBlanc = true; // True si c'est le tour des blancs, false sinon

    public ViewsPlateau(Plateau plateau) {
        this.plateau = plateau;
        this.casesPossibles = new ArrayList<>();

        setTitle("Chess Board");
        setSize(400, 400);
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
                        Case clickedCase = plateau.getCase(finalX, finalY);
                        Piece piece = clickedCase.getPiece();

                        if (piece != null && ((tourBlanc && piece.getCouleur() == Couleur.BLANC) || (!tourBlanc && piece.getCouleur() == Couleur.NOIR))) {
                            // Si la pièce cliquée appartient au joueur actuel et c'est son tour
                            pieceSelectionnee = piece;
                            highlightPossibleMoves(pieceSelectionnee);
                        } else if (pieceSelectionnee != null) {
                            // Si une pièce est déjà sélectionnée et que la case cliquée est vide
                            Case nouvelleCase = plateau.getCase(finalX, finalY);
                            if (casesPossibles.contains(nouvelleCase)) {
                                // Déplacer la pièce sélectionnée vers la nouvelle case
                                boolean deplacementReussi = ControllerPiece.deplacerPiece(plateau, pieceSelectionnee, nouvelleCase);
                                if (deplacementReussi) {
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
    public void updateBoard(Plateau plateau) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (plateau.getCase(x, y).getPiece() != null) {
                    squares[x][y].setText(Integer.toString(plateau.getCase(x, y).getPiece().getId()));
                } else {
                    squares[x][y].setText(""); // Vide le texte si aucune pièce dans la case
                }
            }
        }
    }
}
