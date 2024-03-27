import Models.Plateau;

import javax.swing.*;
import java.awt.*;

public class ViewsPlateau extends JFrame {
    private JPanel mainPanel;
    private JButton[][] squares;

    public ViewsPlateau() {
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

        add(mainPanel);
        setVisible(true);
    }

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
