package edu.eci.arep.apiconnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Class That Implements the service that connects with Open Weather API.
 */
public class ConnectionServiceImpl implements ConnectionService {

    public static final String API_URI = "http://api.openweathermap.org/data/2.5/weather?q=";
    public static final String API_TOKEN = "0ebb279a5949cccfa1ffcd7c2f326dd1";

    /**
     * Returns the weather on a specific place.
     *
     * @param place The name of the place.
     * @return A object that represents the weather on a specific place.
     * @throws Exception If the connection with the OpenWeather API fails.
     */
    @Override
    public Object getWeatherOfPlace(String place) throws Exception {
        URL url = new URL(API_URI + place + "&appid=" + API_TOKEN);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        StringBuilder response;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        return response;
    }
}
