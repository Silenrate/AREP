package edu.eci.arep.services;

/**
 * App Personalized Exception.
 */
public class ServiceException extends Exception {

    /**
     * Basic Constructor For ServiceException.
     *
     * @param message The Error Message.
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Basic Constructor For ServiceException.
     *
     * @param message The Error Message.
     * @param cause   The Cause Of The Exception.
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
