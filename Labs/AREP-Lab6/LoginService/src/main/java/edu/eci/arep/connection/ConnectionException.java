package edu.eci.arep.connection;

/**
 * Personalized Exception For Connection With The Time Service API.
 */
public class ConnectionException extends Exception {

    /**
     * Basic Constructor For ConnectionException.
     *
     * @param message The Error Message.
     */
    public ConnectionException(String message) {
        super(message);
    }

    /**
     * Basic Constructor For ConnectionException.
     *
     * @param message The Error Message.
     * @param cause   The Cause Of The Exception.
     */
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
