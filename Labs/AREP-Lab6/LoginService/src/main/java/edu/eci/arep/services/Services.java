package edu.eci.arep.services;

import edu.eci.arep.model.LoginRequest;
import edu.eci.arep.model.Time;

/**
 * Interface With The Login App Services.
 */
public interface Services {

    /**
     * Login On The App.
     *
     * @param loginRequest The Login Request.
     * @throws ServiceException When The Login Fails.
     */
    void logIn(LoginRequest loginRequest) throws ServiceException;

    /**
     * Encodes A Password With A Hash Function.
     *
     * @param password The Password To Encode.
     * @return The Encrypted Password.
     */
    String hash(String password);

    /**
     * Gets The Time Of The Time Server.
     *
     * @return The Time Of The Time Server.
     * @throws ServiceException When The Time Service Fails.
     */
    Time getTime() throws ServiceException;
}
