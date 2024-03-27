package Interfaces;

import Models.Case;
import Models.Plateau;

public interface Mouvement  {
    boolean isMouvementValide(Plateau plateau, Case nouvelleCase);

}
