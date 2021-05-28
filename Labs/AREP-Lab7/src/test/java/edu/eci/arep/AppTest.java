package edu.eci.arep;

import com.google.gson.Gson;
import edu.eci.arep.model.Temperature;
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

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static final Gson gson = new Gson();

    @BeforeClass
    public static void setup() {
        App.main(null);
    }

    @Test
    public void shouldGetTheCelsiusTemperature() throws IOException {
        double value = 32.0;
        CloseableHttpClient httpClient;
        httpClient = HttpClients.custom().build();
        HttpGet httpGet = new HttpGet("http://localhost:" + App.getPort() + "/temperature?value=" + value);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = rd.readLine();
        Temperature temperature = gson.fromJson(line, Temperature.class);
        assertEquals(0.0, temperature.getValue(), 0.01);
    }
}
