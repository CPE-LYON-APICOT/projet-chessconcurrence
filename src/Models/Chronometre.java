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
