package edu.eci.arep;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Client {
    public static final String APP_URI = "https://areppractica1.herokuapp.com/";

    public static void main(String[] args) throws Exception {
        String stringValues = "4,2.0,50,1.3";
        stringValues = stringValues.replace(",", "%2C");
        URL url = new URL(APP_URI + "/result?values=" + stringValues);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response);
        }
    }
}
