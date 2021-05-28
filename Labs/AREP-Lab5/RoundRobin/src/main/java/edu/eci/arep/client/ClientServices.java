package edu.eci.arep.client;

import edu.eci.arep.model.Message;

import java.io.IOException;

/**
 * Interface with the client method for the Web App.
 */
public interface ClientServices {
    /**
     * Adds A New Message Into The Log Service App And Returns The Last Ten Messages.
     *
     * @param message The New Message Info.
     * @throws IOException When The Connection With The Log Service App Fails.
     */
    void addNewMessage(Message message) throws IOException;

    /**
     * Returns the last messages on the App.
     *
     * @return A Json Object with the last ten messages on the App.
     * @throws IOException When The App Connection Fails.
     */
    Object getLastMessages() throws IOException;
}
