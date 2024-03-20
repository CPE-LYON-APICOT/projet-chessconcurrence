package Models;

import Interfaces.Mouvement;
public abstract class Piece implements Mouvement {
    private Case currentCase;

    private Couleur couleur;
    private int id;

    public Piece(Couleur couleur, Case currentCase, int id) {
        this.couleur = couleur;
        this.currentCase = currentCase;
        this.id = id;
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
}