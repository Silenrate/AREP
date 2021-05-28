package edu.eci.arep.client;

import com.google.gson.Gson;
import edu.eci.arep.model.Message;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

/**
 * Class that Implements the Client Service Method.
 */
public class ClientServicesImpl implements ClientServices {

    private static final String API_URI = "http://192.168.1.20";
    private final String[] ports = {":35001", ":35002", ":35003"};
    private int serverNumber = 0;
    private static final Gson gson = new Gson();

    /**
     * Adds A New Message Into The Log Service App And Returns The Last Ten Messages.
     *
     * @param message The New Message Info.
     * @throws IOException When The Connection With The Log Service App Fails.
     */
    @Override
    public void addNewMessage(Message message) throws IOException {
        String baseUrl = API_URI + ports[serverNumber] + "/addMessage";
        CloseableHttpClient httpClient = HttpClients.custom().build();
        HttpPost httpPost = new HttpPost(baseUrl);
        String messageObject = gson.toJson(message);
        StringEntity entity = new StringEntity(messageObject);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        httpClient.execute(httpPost);
        System.out.println("POST Request to: " + baseUrl + " made at: " + LocalDateTime.now());
        roundRobin();
    }

    /**
     * Returns the last messages on the App.
     *
     * @return A Json Object with the last ten messages on the App.
     * @throws IOException When The App Connection Fails.
     */
    @Override
    public Object getLastMessages() throws IOException {
        String baseUrl = API_URI + ports[serverNumber] + "/messages";
        CloseableHttpClient httpClient = HttpClients.custom().build();
        HttpGet httpGet = new HttpGet(baseUrl);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println("GET Request to: " + baseUrl + " made at: " + LocalDateTime.now());
        roundRobin();
        return result;
    }

    /**
     * Updates the number for routing making Round Robin.
     */
    private void roundRobin() {
        serverNumber = (serverNumber + 1) % ports.length;
    }
}
