# Retro-conception

**Binome 1 : [BUYAT Dorian]**
**Binome 2 : [BOUQUET Nathan]**

Complétez ce document pour décrire votre projet, les difficultés rencontrées, les design patterns mis en oeuvre, les améliorations possibles, et en quoi la POO vous a été utile.

> **Faites le avec sérieux, ce document ainsi que votre code seront évalués.**
Si vous considérez que votre code est quasi-parfait, je vais chercher les erreurs et en trouver, et cela vous pénalisera.
Si vous êtes critique envers vous-même et que vous expliquez correctement vos difficultés, vous serez "à moitié" pardonné.

Ce n'est pas grave de ne pas avoir été au bout de ce que vous vouliez faire, comportez vous comme un ingénieur qui doit rendre des comptes à son client, et qui doit expliquer pourquoi il n'a pas pu tout faire.
Pour rappel le client n'est pas un developpeur (sinon il ne vous aurait pas payé une fortune pour le faire à sa place), vous devez lui expliquer de manière simple et claire les problèmes rencontrés, en vous justifiant
>Imaginez que vous avez codé Mortal Kombat
Ne dites pas "je n'ai pas eu le temps de tout faire", mais plutôt "j'ai préféré me concentrer sur la création des spectaculaires "Finish Him" des personnages car c'est un élément essentiel du gameplay, cependant la difficulté dynamique en fonction de la maîtrise du joueur n'a pas pu être implémentée à temps, compte tenu que les critères de maîtrises sont difficilement modélisables, toutefois nous pourrions envisager d'implémenter que plus le combat dure longtemps, plus les coups portés sont puissants, ce qui est rapide à implémenter et lors d'une mise à jour, nous pourrions remplacer ce système par quelque chose de plus élaboré"
>


Aussi, en entreprise, vous serez confronté à des programmes très mal codés, et vous allez galérer à les comprendre, vous risquez d'essayer de les corriger et tomber dans les mêmes ecueils que les développeurs précédents.
Pour cela, il est courrant de tenir un jour un Document d'Architecture Technique (DAT) qui explique comment fonctionne le programme, et comment le reprendre ainsi qu'un document de réversibilité qui explique comment reprendre le code de quelqu'un d'autre.
(C'est obligatoire pour les marchés publics de prévoir une réversibilité, c'est à dire que le client peut vous dégager et une autre entreprise doit pouvoir reprendre votre code sans difficulté)
Dans ces documents, il ne s'agit pas de cacher la poussière sous le tapis, il faut être honnête et proposer une vision d'ensemble de votre code, et expliquer pourquoi vous avez fait des choix, et pourquoi vous n'avez pas fait d'autres choix, il est souvent question de compromis (on fait un truc pas ouf pour gagner du temps, mais la qualité du code en pâtit, etc.)
> Vous pouvez dire : "Pour la gestion des collisions, nous utilisons une librairie tierce, toutefois celle-ci ne gère que les collisions entre des rectangles, au fur et à mesure des itérations, des ennemis de grande taille et de forme complexe sont apparus, toutefois, nous avons conservé une hitbox rectangulaire, en résulte que le joueur peut être touché alors que visuellement, il n'est pas en contact avec l'ennemi ; nous avions également envisagé de créer plusieurs hitbox de tailles différentes sur un même ennemi afin de mieux coller à la forme de celui-ci, toutefois, les performances du jeu ont étés trop dégradées"



---
# Partie "Client" (pas trop technique) :

## Objectif du projet

L'objectif de notre projet était de créer un jeu d'échecs en Java, en utilisant une approche orientée objet pour une meilleure structuration et modularité du code.
## Résultat

Nous avons réussi à créer un jeu d'échecs fonctionnel avec une interface graphique. Les joueurs peuvent jouer des parties complètes et les règles du jeu sont respectées.

- deplacement des pieces différents selon la pièce selectionnée
- gestion des mangeages de pièces
- - affichage d'un message de fin de partie en cas d'échec et mat
- echec / echec et mat
- bloquer les deplacements qui mettent en echec son propre roi
- affichage des coups possibles pour une piece selectionnée 
- mise en place d'un chronomètre pour chaque joueur
- affichage des changements de couleur pour indiquer quel joueur doit jouer

### Améliorations possibles

- Implémenter une fonctionnalité d'IA pour permettre aux joueurs de jouer contre l'ordinateur.
- Ajouter des fonctionnalités supplémentaires telles que l'enregistrement des parties, des statistiques de jeu, ou même des options de personnalisation de l'interface.
- Améliorer l'interface utilisateur pour la rendre plus conviviale et esthétique.
- Faire un projet Client / Serveur pour permettre à des joueurs distants de jouer ensemble.

---
# Partie "Développeur" (plus technique) :


### Implémentations remarquables

Nous sommes particulièrement fiers de la manière dont nous avons géré la logique du jeu d'échecs tout en respectant la logique de la POO. En utilisant des classes pour représenter chaque pièce et le plateau de jeu, nous avons pu mettre en place les règles du jeu de manière claire et extensible.

La mise en place de la règle des échechs et des échecs et mat a été un défi intéressant, mais nous avons réussi à l'implémenter de manière élégante en utilisant des exceptions pour signaler les erreurs dans une fenêtre.

La mise en place de l'interface graphique a été un autre point fort de notre projet. Nous avons utilisé la bibliothèque Swing pour créer une interface utilisateur intuitive et agréable pour les joueurs. Les pièces d'échecs étaient représentées par des images et les joueurs pouvaient interagir avec le plateau en cliquant sur les cases pour déplacer les pièces.

Et pour finir l'utilisation des design patterns pour structurer notre code et faciliter la maintenance et l'évolutivité de l'application sont des parties dont nous sommes fiers car ça nous a permisde mettre en place des solutions compliquées de manière simple et efficace comme l'affichage des changements de la couleur qui doit jouer et un chrnomètre fonctionnel.
### Faiblesses du code

[C'est ici que vous me dites ce que vous savez que vous avez mal fait, expliquez pourquoi vous avez fait ce choix (manque de temps, manque de compétence, trop pénible à faire, etc.)]

### Difficultés rencontrées

#### 1. Gestion des mouvements et des règles du jeu

La gestion des mouvements des pièces et des règles du jeu a été complexe à implémenter de manière efficace. Nous avons dû nous assurer que chaque pièce se déplace correctement selon les règles spécifiques à son type, tout en prenant en compte les interactions avec les autres pièces sur le plateau. Pour surmonter cette difficulté, nous avons divisé la logique du jeu en plusieurs classes pour chaque type de pièce et pour le plateau, en utilisant des méthodes bien définies pour vérifier la validité des déplacements.

#### 2. Gestion des états et des transitions du jeu

La gestion des états et des transitions dans le jeu, en particulier pour détecter les échecs et les échecs et mat, a été une tâche complexe. Nous avons dû mettre en place un système robuste pour détecter quand un joueur était en situation d'échec ou d'échec et mat, ce qui impliquait de vérifier la validité des mouvements à chaque tour. Cela nécessitait une coordination minutieuse entre les différentes classes du jeu, notamment les pièces, le plateau et les règles du jeu. En outre, nous devions garantir que les transitions entre les états du jeu étaient gérées de manière précise pour maintenir l'intégrité de la partie et assurer une expérience de jeu fluide pour les utilisateurs.

### *Design Patterns* mis en oeuvre

#### 1. Stratégie
Nous avons utiliser un design patern strategie pour créer différentes conditions pour valider un coup en fonction de la pièce qui le réalise.

##### 1.1. Mouvement
Nous avons créé une interface Mouvement pour définir la méthode isMouvementValide(), qui est implémentée par chaque type de pièce (pion, tour, cavalier, etc.). Cette approche nous permet de définir des règles de déplacement spécifiques pour chaque pièce, tout en les rendant interchangeables et extensibles.

##### 1.2. Exemple de classe Cavalier
La classe Cavalier implémente l'interface Mouvement pour définir les règles de déplacement du cavalier. Elle vérifie si le déplacement est en forme de L (2 cases horizontalement et 1 verticalement, ou 1 case horizontalement et 2 verticalement) et si la case de destination est vide ou occupée par une pièce adverse. Cette approche nous permet de définir des règles de déplacement spécifiques pour chaque pièce, tout en les rendant interchangeables et extensibles.
<pre>
```
package Interfaces;

import Models.Case;
import Models.Plateau;

public interface Mouvement  {
    boolean isMouvementValide(Plateau plateau, Case nouvelleCase);
}
```
</pre>

<pre>
```
package Models;

import java.util.ArrayList;
import java.util.List;

public class Cavalier extends Piece{
    public Cavalier(Couleur couleur, Case currentCase) {
        super(couleur, currentCase, 0);

        if (couleur == Couleur.BLANC) {
            this.setId(21);
        } else {
            this.setId(22);
        }
    }

    @Override
    public boolean isMouvementValide(Plateau plateau, Case nouvelleCase) {
        Case ancienneCase = this.getCurrentCase();

        // Obtenir les coordonnées de la nouvelle case et de l'ancienne case
        int ancienneX = ancienneCase.getX();
        int ancienneY = ancienneCase.getY();
        int nouvelleX = nouvelleCase.getX();
        int nouvelleY = nouvelleCase.getY();

        // Calculer le déplacement du cavalier
        int deplacementX = Math.abs(nouvelleX - ancienneX);
        int deplacementY = Math.abs(nouvelleY - ancienneY);

        // Le déplacement est valide si le cavalier se déplace en L
        if ((deplacementX == 2 && deplacementY == 1) || (deplacementX == 1 && deplacementY == 2)) {

            // Vérifier si la case de destination est vide ou occupée par une pièce adverse
            if (nouvelleCase.getPiece() == null || nouvelleCase.getPiece().getCouleur() != this.getCouleur()) {
                return true; // Déplacement valide
            } else if (nouvelleCase.getPiece() instanceof Roi && nouvelleCase.getPiece().getCouleur() != this.getCouleur()) {
                // Si la pièce sur la case de destination est un roi adverse, le déplacement est invalide
                return false;
            } else {
                // Si la case contient une pièce adverse autre qu'un roi, le déplacement est valide
                return true;
            }
        }

        return false; // Autres mouvements invalides
    }
}

```
</pre>

#### 2. Singleton

Notre utilisation du Singleton garantit qu'une seule instance du plateau est utilisée dans toute l'application, ce qui peut être crucial pour maintenir la cohérence des données dans le contexte d'un jeu d'échecs où une seule instance de plateau est nécessaire pour représenter l'état actuel du jeu.

##### 2.1. SingletonPlateau :

Il représente le Singleton, garantissant qu'une seule instance de l'objet Plateau est créée.
La méthode getInstance() est utilisée pour obtenir cette instance unique du Plateau. Si aucune instance n'existe, elle en crée une nouvelle.
La méthode getInstance() est synchronisée pour éviter les problèmes de concurrence lors de l'accès à l'instance unique dans un environnement multithreadé.

##### 2.2. Main :

Dans la méthode main, elle obtient l'instance unique du plateau à l'aide de SingletonPlateau.getInstance().
Ensuite, elle crée une instance de ObservablePlateau, qui encapsule le plateau et permet d'observer les changements d'état.
Elle crée également une instance de ViewsPlateau, qui est la vue de l'interface utilisateur pour afficher le plateau de jeu.
Enfin, elle appelle viewsPlateau.updateBoard(plateau) pour mettre à jour l'affichage initial du plateau dans l'interface utilisateur.

<pre>
```
package Models;

public class SingletonPlateau {
    private static Plateau instance;

    private SingletonPlateau() {
    }

    public static synchronized Plateau getInstance() {
        if (instance == null) {
            instance = new Plateau();
        }
        return instance;
    }
}
```
</pre>
<pre>
```
import Models.ObservablePlateau;
import Models.Plateau;
import Models.SingletonPlateau;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Plateau plateau = SingletonPlateau.getInstance();
        ObservablePlateau observablePlateau = new ObservablePlateau(plateau);
        ViewsPlateau viewsPlateau = new ViewsPlateau(observablePlateau);
        viewsPlateau.updateBoard(plateau);
    }
}
```
</pre>

#### 3. Obervateur

Le patron de conception Observer/Observable permet à un objet (Observable) de notifier automatiquement un ensemble d'objets (Observers) lorsqu'il y a un changement d'état. Cela est utile pour maintenir la cohérence entre les objets qui doivent rester synchronisés sans qu'ils aient besoin de connaître les détails de chacun.

##### 3.1. ObservablePlateau : 

représente l'objet observé. Il encapsule l'état du plateau et gère les changements d'état (comme le déplacement des pièces). Lorsqu'un changement d'état se produit, il notifie les observateurs en signifiant que c'est à un joueur de jouer.

##### 3.2. ViewsPlateau : 

représente l'observateur. Il s'inscrit auprès de l'observable (ObservablePlateau) pour être notifié des changements d'état et mettre à jour l'interface utilisateur en conséquence (comme le déplacement des pièces ou le changement de tour).

<pre>
```
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
```
</pre>

<pre>
```
import Models.ObservablePlateau;
import Models.Plateau;
import Models.SingletonPlateau;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Plateau plateau = SingletonPlateau.getInstance();
        ObservablePlateau observablePlateau = new ObservablePlateau(plateau);
        ViewsPlateau viewsPlateau = new ViewsPlateau(observablePlateau);
        viewsPlateau.updateBoard(plateau);
    }
}
```
</pre>

<pre>
``` dans notre ViewsPlateau nous l'avons utilisé comme cela:
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
                            }
                        }
                    }
                });
            }
        }

et comme cela pour l'update:
    @Override
    public void update(Observable o, Object arg) {
        Plateau plateau = observablePlateau.getPlateau();
        updateBoard(plateau);
        String tourActuel = observablePlateau.isTourBlanc() ? "Blanc" : "Noir";
        tourLabel.setText("Tour actuel : " + tourActuel);

        if (observablePlateau.isEchecEtMat()) {
            String gagnant = observablePlateau.isTourBlanc() ? "Noir" : "Blanc";
            JOptionPane.showMessageDialog(this, "Échec et mat ! Le joueur " + gagnant + " gagne.", "Échec et Mat", JOptionPane.INFORMATION_MESSAGE);
        }
    }
```
</pre>

#### 4. Observer

Le patron de conception Observer/Observable permet à un objet (Observable) de notifier automatiquement un ensemble d'objets (Observers) lorsqu'il y a un changement d'état. Cela est utile pour maintenir la cohérence entre les objets qui doivent rester synchronisés sans qu'ils aient besoin de connaître les détails de chacun.

##### 4.1. Chronometre :

représente l'objet Chronomètre.

##### 4.2. ObservablePlateau :

représente l'objet observé. Il gère les changements d'état (comme le déplacement des pièces). Lorsqu'un changement d'état se produit, il arrete/demarre les chronometre.

##### 4.3. ViewsPlateau :

Dans la vue, l'observateur réagie aux notifications de l'ObservablePlateau en mettant à jour l'interface utilisateur pour refléter les changements d'état du jeu en mettant à jour les chronomètres des joueurs.


<pre>
```
package Models;

public class Chronometre {
    private long startTime;
    private long elapsedTime;
    private boolean running;

    // Constante pour la durée initiale du chronomètre (10 minutes en millisecondes)
    private static final long DUREE_INITIALE = 600000;

    public void start() {
        if (!running) {
            startTime = System.currentTimeMillis();
            running = true;
        }
    }

    public void stop() {
        if (running) {
            elapsedTime += System.currentTimeMillis() - startTime;
            running = false;
        }
    }

    public long getTimeElapsed() {
        if (running) {
            return elapsedTime + (System.currentTimeMillis() - startTime);
        } else {
            return elapsedTime;
        }
    }

    // Méthode pour réinitialiser le chronomètre à sa durée initiale
    public void reset() {
        elapsedTime = 0;
    }

    // Méthode pour obtenir la durée initiale du chronomètre
    public static long getDureeInitiale() {
        return DUREE_INITIALE;
    }

    // Méthode pour définir le temps de départ du chronomètre
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
```
</pre>

<pre>
```
package Models;

import Controllers.ControllerPiece;

import java.util.Observable;

public class ObservablePlateau extends Observable {
    private Plateau plateau;
    private boolean tourBlanc;
    private boolean echecEtMat;

    private Chronometre chronometreBlanc;
    private Chronometre chronometreNoir;

    public ObservablePlateau(Plateau plateau) {
        this.plateau = plateau;
        this.tourBlanc = true; // Les blancs commencent
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
    public void deplacerPiece(Piece piece, Case nouvelleCase) {
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
            Couleur couleurAdversaire = tourBlanc ? Couleur.NOIR : Couleur.BLANC;
            if (plateau.estEchecEtMat(couleurAdversaire)) {
                echecEtMat = true;
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
}

```
</pre>

<pre>
```
else if (pieceSelectionnee != null) {
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

```
</pre>


---
# Partie pédagogique


### En quoi la POO vous a été utile

La programmation orientée objet (POO) a été essentielle dans notre projet pour plusieurs raisons :

- Modularité: En divisant notre code en classes et en objets, nous avons pu organiser notre projet de manière modulaire. Chaque pièce d'échecs, chaque mouvement et chaque aspect du plateau de jeu étaient encapsulés dans des objets distincts, ce qui rendait le code plus facile à comprendre et à maintenir.

- Réutilisabilité: Grâce à l'héritage et au polymorphisme, nous avons pu réutiliser du code commun et éviter la redondance. Par exemple, toutes les pièces d'échecs partageaient des caractéristiques communes et implémentaient des comportements spécifiques à leur type.

- Extensibilité: La POO nous a permis d'ajouter de nouvelles fonctionnalités et de nouvelles pièces au jeu sans perturber le code existant. En ajoutant de nouvelles classes pour de nouveaux types de pièces ou de nouveaux mouvements, nous pouvions étendre le jeu facilement.

- Encapsulation: L'encapsulation nous a permis de cacher les détails d'implémentation des différentes parties du jeu. Par exemple, la logique de déplacement des pièces était encapsulée dans des méthodes spécifiques à chaque pièce, ce qui facilitait la gestion des règles du jeu.

- Maintenabilité: En organisant notre code en classes et en objets, nous avons amélioré la maintenabilité du projet. Les modifications ou les corrections de bugs pouvaient être apportées plus facilement en ciblant des parties spécifiques du code.

En résumé, la POO a été un pilier fondamental de notre projet, nous permettant d'apprendre à développer une application bien structurée, flexible et facile à maintenir.

### Conclusion

Ce projet nous a permis de mettre en pratique nos connaissances en programmation orientée objet tout en nous confrontant à des défis concrets de développement logiciel. Nous avons appris l'importance de la conception et de la structuration du code pour faciliter la maintenance et l'évolutivité d'une application. Malgré quelques difficultés rencontrées, nous sommes satisfaits du résultat obtenu et conscients des axes d'amélioration pour de futurs projets.