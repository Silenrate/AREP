package edu.eci.arep.services;

import edu.eci.arep.model.Message;
import edu.eci.arep.persistence.PersistenceServices;
import edu.eci.arep.persistence.PersistenceServicesImpl;

import java.util.List;

/**
 * Class that implements the Log Services Methods.
 */
public class LogServicesImpl implements LogServices {

    private final PersistenceServices services = new PersistenceServicesImpl();

    /**
     * Adds a new message on the App.
     *
     * @param message The New Message Information.
     * @throws LogServicesException When The DB Connection Fails or The New Message is Empty.
     */
    @Override
    public void addMessage(Message message) throws LogServicesException {
        if (message.getData() == null) {
            throw new LogServicesException("The request do not have a data attribute with the new message");
        }
        message.setActualDate();
        services.addMessage(message);
    }

    /**
     * Returns the last ten messages on the App.
     *
     * @return A List that have the last ten messages on the App.
     */
    @Override
    public List<Message> getLastMessages() {
        return services.getLastMessages();
    }
}
