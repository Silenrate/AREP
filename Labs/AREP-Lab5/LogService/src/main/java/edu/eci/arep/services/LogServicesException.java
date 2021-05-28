package edu.eci.arep.services;

/**
 * Personalized Exception For Log Service Web App.
 */
public class LogServicesException extends Exception {

    /**
     * Constructor For LogServicesException
     *
     * @param msg The Error Message
     */
    public LogServicesException(String msg) {
        super(msg);
    }
}
