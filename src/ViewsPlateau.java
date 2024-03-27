import Models.Plateau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewsPlateau extends JFrame {
    private JPanel mainPanel;
    private JButton[][] squares;
    private Plateau plateau; // Ajout de l'attribut plateau

    public ViewsPlateau(Plateau plateau) {
        this.plateau = plateau; // Initialisation du plateau
        setTitle("Chess Board");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(8, 8));

        squares = new JButton[8][8];

        // Ajout des écouteurs d'événements aux boutons
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col] = new JButton();
                squares[row][col].setBackground((row + col) % 2 == 0 ? Color.GREEN : Color.GRAY);
                squares[row][col].addActionListener(new SquareButtonListener(row, col)); // Ajout de l'écouteur d'événements
                mainPanel.add(squares[row][col]);
            }
        }

        add(mainPanel);
        setVisible(true);
    }

    public void updateBoard(Plateau plateau) {
        this.plateau = plateau; // Mise à jour du plateau
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

    // Écouteur d'événements pour les boutons représentant les cases
    private class SquareButtonListener implements ActionListener {
        private int x, y;

        public SquareButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Ici vous pouvez invoquer la logique pour déplacer la pièce à partir des coordonnées (x, y)
            // Utilisez le plateau pour effectuer les opérations nécessaires
        }
    }
}