package Models;

import Controllers.ControllerPiece;

import java.util.Observable;

public class ObservablePlateau extends Observable {
    private Plateau plateau;
    private boolean tourBlanc;

    private boolean echec;
    private boolean echecEtMat;

    private Chronometre chronometreBlanc;
    private Chronometre chronometreNoir;

    public ObservablePlateau(Plateau plateau) {
        this.plateau = plateau;
        this.tourBlanc = true; // Les blancs commencent
        this.echec = false;
        this.echecEtMat = false;
        this.chronometreBlanc = new Chronometre();
        this.chronometreNoir = new Chronometre();
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

    public boolean isEchec() {
        return echec;
    }
    public void deplacerPiece(Piece piece, Case nouvelleCase) {
        echec = false;
        if (ControllerPiece.deplacerPiece(plateau, piece, nouvelleCase) >= 0) {
            // Démarrer ou arrêter le chrono en fonction du tour
            if (tourBlanc) {
                // Vérifier si le chronomètre des blancs n'a pas encore démarré
                if (chronometreBlanc.getTimeElapsed() == 0) {
                    chronometreBlanc.start();
                }
                // Arrêter le chrono des blancs
                chronometreBlanc.stop();
                // Démarrer le chrono des noirs uniquement si ce n'est pas le premier coup
                if (chronometreNoir.getTimeElapsed() > 0) {
                    chronometreNoir.start();
                }
            } else {
                // Vérifier si le chronomètre des noirs n'a pas encore démarré
                if (chronometreNoir.getTimeElapsed() == 0) {
                    chronometreNoir.start();
                }
                // Arrêter le chrono des noirs
                chronometreNoir.stop();
                // Démarrer le chrono des blancs
                chronometreBlanc.start();
            }

            tourBlanc = !tourBlanc;

            // Vérifier l'état du jeu pour l'échec et mat
            Couleur couleurAdversaire = tourBlanc ? Couleur.BLANC : Couleur.NOIR;
            if (plateau.estEnEchec(couleurAdversaire) && !plateau.estEchecEtMat(couleurAdversaire)) {
                echec = true;
            }
            if (plateau.estEchecEtMat(couleurAdversaire)) {
                echecEtMat = true;
                chronometreNoir.stop();
                chronometreBlanc.stop();
            }

            setChanged();
            notifyObservers();
        }
    }



    public long getTempsEcouleBlanc() {
        return chronometreBlanc.getTimeElapsed();
    }

    public long getTempsEcouleNoir() {
        return chronometreNoir.getTimeElapsed();
    }

    // Méthode pour récupérer le chronomètre des blancs
    public Chronometre getChronometreBlanc() {
        return chronometreBlanc;
    }

    // Méthode pour récupérer le chronomètre des noirs
    public Chronometre getChronometreNoir() {
        return chronometreNoir;
    }

    // Méthode pour définir l'état de l'échec et mat
    public void setEchecEtMat(boolean echecEtMat) {
        this.echecEtMat = echecEtMat;
    }

    public  void setEchec(boolean echec){
        this.echec = echec;
    }
}
