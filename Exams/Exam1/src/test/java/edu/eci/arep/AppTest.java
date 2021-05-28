package edu.eci.arep;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Unit test for Spark Web App.
 */
public class AppTest {

    @BeforeClass
    public static void setup() {
        App.main(null);
    }

    @Test
    public void shouldReceiveWeatherInfo() throws IOException {
        String result = getResult("http://localhost:" + App.getPort() + "/clima?lugar=London");
        assertNotNull(result);
    }

    @Test
    public void shouldNotReceiveWeatherInfo() throws IOException {
        String result = getResult("http://localhost:" + App.getPort() + "/clima?lugar=");
        assertEquals("No place provided ex. /clima?lugar=London", result);
    }

    private String getResult(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom().build();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
}
