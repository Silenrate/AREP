package edu.eci.arep.persistence;

import edu.eci.arep.model.Message;

import java.util.List;

/**
 * Interface of the Persistence Methods For The App DB.
 */
public interface PersistenceServices {
    /**
     * Adds a new message to the DB.
     *
     * @param message The New Message Information.
     */
    void addMessage(Message message);

    /**
     * Returns the last ten messages from the DB.
     *
     * @return A List that have the last ten messages from the DB.
     */
    List<Message> getLastMessages();
}
