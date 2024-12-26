package ma.zyn.app.config.util;

public class UniqueID {
    private static long current = System.currentTimeMillis();

    private UniqueID() {
    }

    static public synchronized long get() {
        return current++;
    }
}