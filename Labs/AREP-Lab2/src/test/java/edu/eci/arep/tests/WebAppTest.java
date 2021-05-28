package edu.eci.arep.tests;

import edu.eci.arep.App;
import edu.eci.arep.BasicAppTest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Unit test class for Spark Web Mean And Standard Deviation App.
 */
public class WebAppTest implements BasicAppTest {

    @Before
    public void setup() {
        App.main(null);
    }

    @Test
    public void shouldCalculateTheMeanAndTheStandardDeviationFromWebWithTheTwoAmountsOfData() throws IOException {
        String result = getResult(firstAmountOfData);
        assertTrue(result.contains("The Mean is " + meanOfFirstAmountOfData + " and the Standard Deviation is " + standardDeviationOfFirstAmountOfData));
        String result2 = getResult(secondAmountOfData);
        assertTrue(result2.contains("The Mean is " + meanOfSecondAmountOfData + " and the Standard Deviation is " + standardDeviationOfSecondAmountOfData));
    }

    private String getResult(List<String> data) throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom().build();
        int port = App.getPort();
        String values = getValuesInUrlFormat(data.toString());
        HttpGet httpGet = new HttpGet("http://localhost:" + port + "/result?values=" + values);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    private String getValuesInUrlFormat(String data) {
        String values = data.replace("[", "");
        values = values.replace("]", "");
        values = values.replace(", ", "%2C");
        return values;
    }
}
