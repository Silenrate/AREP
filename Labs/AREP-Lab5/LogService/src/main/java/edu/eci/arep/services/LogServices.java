package edu.eci.arep.services;

import edu.eci.arep.model.Message;

import java.util.List;

/**
 * Interface with the Log Service methods.
 */
public interface LogServices {

    /**
     * Adds a new message on the App.
     *
     * @param message The New Message Information.
     * @throws LogServicesException When The DB Connection Fails or The New Message is Empty.
     */
    void addMessage(Message message) throws LogServicesException;

    /**
     * Returns the last ten messages on the App.
     *
     * @return A List that have the last ten messages on the App.
     */
    List<Message> getLastMessages();
}
