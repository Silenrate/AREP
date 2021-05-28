package edu.eci.arep.cache;

/**
 * Personalized Exception For The App.
 */
public class CacheException extends Exception {
    /**
     * Constructor For CacheException
     *
     * @param msg Error Message
     */
    public CacheException(String msg) {
        super(msg);
    }
}
