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
