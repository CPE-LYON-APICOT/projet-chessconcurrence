import Controllers.ControllerPiece;
import Models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ViewsPlateau extends JFrame implements Observer {
    private JPanel mainPanel;
    private JLabel tourLabel;
    private JLabel chronoBlancLabel;
    private JLabel chronoNoirLabel;
    private JButton[][] squares;
    private Piece pieceSelectionnee;
    private ObservablePlateau observablePlateau;
    private ArrayList<Case> casesPossibles;
    private Timer timer;

    public ViewsPlateau(ObservablePlateau observablePlateau) {
        this.observablePlateau = observablePlateau;
        this.observablePlateau.addObserver(this);
        this.casesPossibles = new ArrayList<>();

        setTitle("Chess Board");
        setSize(950, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8));

        squares = new JButton[8][8];

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col] = new JButton();
                squares[row][col].setBackground((row + col) % 2 == 0 ? Color.GREEN : Color.GRAY);
                boardPanel.add(squares[row][col]);
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
                        if (observablePlateau.isEchecEtMat()) {
                            return; // Ne rien faire si le jeu est terminé
                        }

                        Case clickedCase = observablePlateau.getPlateau().getCase(finalX, finalY);
                        Piece piece = clickedCase.getPiece();

                        if (piece != null && ((observablePlateau.isTourBlanc() && piece.getCouleur() == Couleur.BLANC) || (!observablePlateau.isTourBlanc() && piece.getCouleur() == Couleur.NOIR))) {
                            pieceSelectionnee = piece;
                            highlightPossibleMoves(pieceSelectionnee);
                        } else if (pieceSelectionnee != null) {
                            Case nouvelleCase = observablePlateau.getPlateau().getCase(finalX, finalY);
                            if (casesPossibles.contains(nouvelleCase)) {
                                observablePlateau.deplacerPiece(pieceSelectionnee, nouvelleCase);
                                resetHighlightedCases();
                                pieceSelectionnee = null;

                                // Arrêter le chrono actif et démarrer celui de l'autre joueur
                                if (observablePlateau.isTourBlanc()) {

                                    observablePlateau.getChronometreNoir().stop();
                                    observablePlateau.getChronometreBlanc().start();
                                    chronoNoirLabel.setForeground(Color.BLACK);
                                    chronoBlancLabel.setForeground(Color.RED);

                                } else {

                                    observablePlateau.getChronometreBlanc().stop();
                                    observablePlateau.getChronometreNoir().start();
                                    chronoBlancLabel.setForeground(Color.BLACK);
                                    chronoNoirLabel.setForeground(Color.RED);
                                }
                            }
                        }
                    }
                });
            }
        }

        tourLabel = new JLabel("Tour actuel : Blanc", SwingConstants.CENTER);
        tourLabel.setFont(new Font("Serif", Font.BOLD, 24));
        chronoBlancLabel = new JLabel("Temps Blanc: 10:00", SwingConstants.CENTER);
        chronoNoirLabel = new JLabel("Temps Noir: 10:00", SwingConstants.CENTER);
        chronoBlancLabel.setFont(new Font("Serif", Font.BOLD, 18));
        chronoNoirLabel.setFont(new Font("Serif", Font.BOLD, 18));

        mainPanel.add(tourLabel, BorderLayout.NORTH);
        mainPanel.add(chronoBlancLabel, BorderLayout.WEST);
        mainPanel.add(chronoNoirLabel, BorderLayout.EAST);
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateChronoLabels();
            }
        });
        timer.start();

        // Démarrer les chronomètres au début de la partie
        chronoBlancLabel.setForeground(Color.RED); // Chrono blanc actif au début
        chronoNoirLabel.setForeground(Color.BLACK);
        observablePlateau.getChronometreBlanc().start();
        observablePlateau.getChronometreNoir().stop();
    }

    // Méthode pour mettre à jour les labels des chronomètres
    private void updateChronoLabels() {
        long tempsBlancRestant = (600000 - observablePlateau.getTempsEcouleBlanc()) / 1000;
        long tempsNoirRestant = (600000 - observablePlateau.getTempsEcouleNoir()) / 1000;

        long minutesBlanc = tempsBlancRestant / 60;
        long secondesBlanc = tempsBlancRestant % 60;
        long minutesNoir = tempsNoirRestant / 60;
        long secondesNoir = tempsNoirRestant % 60;

        chronoBlancLabel.setText(String.format("Temps Blanc: %02d:%02d", minutesBlanc, secondesBlanc));
        chronoNoirLabel.setText(String.format("Temps Noir: %02d:%02d", minutesNoir, secondesNoir));

        // Vérifier si le temps est écoulé pour un joueur
        if (tempsBlancRestant <= 0) {
            // Temps écoulé pour les blancs
            JOptionPane.showMessageDialog(this, "Temps écoulé pour les Blancs !", "Temps écoulé", JOptionPane.WARNING_MESSAGE);
            observablePlateau.setEchecEtMat(true);
        } else if (tempsNoirRestant <= 0) {
            // Temps écoulé pour les noirs
            JOptionPane.showMessageDialog(this, "Temps écoulé pour les Noirs !", "Temps écoulé", JOptionPane.WARNING_MESSAGE);
            observablePlateau.setEchecEtMat(true);
        }
    }

    // Méthode pour mettre en évidence visuellement les cases possibles pour une pièce sélectionnée
    private void highlightPossibleMoves(Piece piece) {
        resetHighlightedCases();
        Plateau plateau = observablePlateau.getPlateau();

        Case ancienneCase = piece.getCurrentCase();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Case nouvelleCase = plateau.getCase(x, y);
                if (piece.isMouvementValide(plateau, nouvelleCase)) {
                    // Simuler le déplacement
                    Piece pieceOriginale = nouvelleCase.getPiece();
                    Case caseActuelle = piece.getCurrentCase();
                    caseActuelle.setPiece(null);
                    nouvelleCase.setPiece(piece);
                    piece.setCurrentCase(nouvelleCase);

                    boolean enEchec = plateau.estEnEchec(piece.getCouleur());

                    nouvelleCase.setPiece(pieceOriginale);
                    caseActuelle.setPiece(piece);
                    piece.setCurrentCase(caseActuelle);

                    if (!enEchec) {
                        squares[x][y].setBackground(Color.YELLOW);
                        casesPossibles.add(nouvelleCase);
                    }
                }
            }
        }
    }

    // Méthode pour réinitialiser les cases mises en évidence
    private void resetHighlightedCases() {
        for (Case c : casesPossibles) {
            int x = c.getX();
            int y = c.getY();
            squares[x][y].setBackground((x + y) % 2 == 0 ? Color.GREEN : Color.GRAY);
        }
        casesPossibles.clear();
    }

    // Méthode pour mettre à jour l'affichage du plateau
    @Override
    public void update(Observable o, Object arg) {
        Plateau plateau = observablePlateau.getPlateau();
        updateBoard(plateau);
        String tourActuel = observablePlateau.isTourBlanc() ? "Blanc" : "Noir";
        tourLabel.setText("Tour actuel : " + tourActuel);

        // Vérifier si un joueur est en échec
        Couleur couleurAdversaire = observablePlateau.isTourBlanc() ? Couleur.NOIR : Couleur.BLANC;
        if (observablePlateau.isEchec()) {
            JOptionPane.showMessageDialog(this, "Le joueur " + tourActuel + " est en échec !", "Échec", JOptionPane.WARNING_MESSAGE);
        }

        // Vérifier si un joueur est en échec et mat
        if (observablePlateau.isEchecEtMat()) {
            String gagnant = observablePlateau.isTourBlanc() ? "Noir" : "Blanc";
            JOptionPane.showMessageDialog(this, "Échec et mat ! Le joueur " + gagnant + " gagne.", "Échec et Mat", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    // Méthode pour mettre à jour l'affichage du plateau
    public void updateBoard(Plateau plateau) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Case currentCase = plateau.getCase(x, y);
                if (currentCase.getPiece() != null) {
                    String couleur = currentCase.getPiece().getCouleur() == Couleur.BLANC ? "Blanc" : "Noir";
                    String typePiece = currentCase.getPiece().getClass().getSimpleName();
                    String imagePath = "Ressources/images/" + typePiece.toLowerCase() + couleur + ".png";
                    ImageIcon icon = new ImageIcon(imagePath);
                    squares[x][y].setIcon(icon);
                } else {
                    squares[x][y].setIcon(null);
                }
            }
        }
    }

    public static void main(String[] args) {
        Plateau plateau = new Plateau();
        ObservablePlateau observablePlateau = new ObservablePlateau(plateau);
        new ViewsPlateau(observablePlateau);
    }
}


