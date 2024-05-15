package Models;

import Controllers.ControllerPiece;

import java.util.Observable;

public class ObservablePlateau extends Observable {
    private Plateau plateau;
    private boolean tourBlanc;
    private boolean echecEtMat;

    public ObservablePlateau(Plateau plateau) {
        this.plateau = plateau;
        this.tourBlanc = true; // Les blancs commencent
        this.echecEtMat = false;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public boolean isTourBlanc() {
        return tourBlanc;
    }

    public boolean isEchecEtMat() {
        return echecEtMat;
    }

    public void deplacerPiece(Piece piece, Case nouvelleCase) {
        if (ControllerPiece.deplacerPiece(plateau, piece, nouvelleCase) >= 0) {
            // Changer de tour
            tourBlanc = !tourBlanc;

            // Vérifier l'état du jeu pour l'échec et mat
            Couleur couleurAdversaire = tourBlanc ? Couleur.NOIR : Couleur.BLANC;
            if (plateau.estEchecEtMat(couleurAdversaire)) {
                echecEtMat = true;
            }

            setChanged();
            notifyObservers();
        }
    }
}
