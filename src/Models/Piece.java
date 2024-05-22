package Models;

import Interfaces.Mouvement;

import javax.swing.*;

public abstract class Piece implements Mouvement {
    private Case currentCase;

    private Couleur couleur;
    private int id;

    public boolean moved;

    @Override
    public boolean isMouvementValide(Plateau plateau, Case nouvelleCase) {
        Case ancienneCase = this.getCurrentCase();

        // Vérifier si la case de destination est vide
        if (nouvelleCase.getPiece() != null) {
            return false; // Case occupée, déplacement invalide
        }

        // Obtenir les coordonnées de la nouvelle case et de l'ancienne case
        int ancienneX = ancienneCase.getX();
        int ancienneY = ancienneCase.getY();
        int nouvelleX = nouvelleCase.getX();
        int nouvelleY = nouvelleCase.getY();

        // Déterminer le déplacement du pion
        int deplacementX = Math.abs(nouvelleX - ancienneX);
        int deplacementY = Math.abs(nouvelleY - ancienneY);

        return false;
    }

    public Piece(Couleur couleur, Case currentCase, int id) {
        this.couleur = couleur;
        this.currentCase = currentCase;
        this.id = id;
        this.moved = false;
    }
    public Case getCase() {
        return currentCase;
    }

    public void setCase(Case currentCase) {
        this.currentCase = currentCase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public Case getCurrentCase() {
        return currentCase;
    }

    public void setCurrentCase(Case nouvelleCase) {
        this.currentCase = nouvelleCase;
    }

    public Boolean getType() {
        return this instanceof Pion;
    }

    // Méthode pour vérifier si la pièce a bougé
    protected boolean hasMoved() {
        return moved;
    }

    protected boolean aBouge() {
        return false;
    }

    protected boolean isHasMoved() {
        return false;
    }

    public void deplacer(Plateau plateau, Case nouvelleCase) {
        Case ancienneCase = this.getCurrentCase();
        ancienneCase.setPiece(null);
        nouvelleCase.setPiece(this);
        this.setCurrentCase(nouvelleCase);
        this.moved = true;
    }
}