package edu.eci.arep.connection;

/**
 * Interface of the Connection To Time API Methods.
 */
public interface ConnectionServices {

    /**
     * Gets The Time Of The Time Server.
     *
     * @return The Time Of The Time Server
     * @throws ConnectionException When The Connection To Time API Fails.
     */
    String getTime() throws ConnectionException;
}
