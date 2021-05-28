package edu.eci.arep;

import edu.eci.arep.services.CalculateService;
import edu.eci.arep.services.CalculateServiceImpl;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


/**
 * Unit test for simple App.
 */
public class AppTest {

    private final CalculateService service = new CalculateServiceImpl();

    @Test
    public void shouldNotFindTheFile() {
        String[] args = {"src/main/resources/unknown.txt"};
        try {
            App.main(args);
            fail("Should fail giving the path of a non existent file");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("(The system cannot find the file specified)"));
        }
    }

    @Test
    public void shouldNotCalculateWithAnEmptyFile() {
        String[] args = {"src/main/resources/emptyFile"};
        try {
            App.main(args);
            fail("Should fail giving the path of an empty file");
        } catch (IOException e) {
            fail("Should not fail giving the path of a non existent file");
        } catch (ArithmeticException ex) {
            assertEquals("Cannot get the mean of an empty collection", ex.getMessage());
        }
    }

    @Test
    public void shouldCalculateTheMeanWithTheFirstAmountOfData() throws IOException {
        File file = new File("src/main/resources/prueba1.txt");
        service.readFile(file);
        double mean = service.calculateMean();
        assertEquals(550.6, mean, 0.0);
    }

    @Test
    public void shouldCalculateTheStandardDeviationWithTheFirstAmountOfData() throws IOException {
        File file = new File("src/main/resources/prueba1.txt");
        service.readFile(file);
        double standardDeviation = service.calculateStandardDeviation();
        assertEquals(572.03, standardDeviation, 0.01);
    }

    @Test
    public void shouldCalculateTheMeanWithTheSecondAmountOfData() throws IOException {
        File file = new File("src/main/resources/prueba2.txt");
        service.readFile(file);
        double mean = service.calculateMean();
        assertEquals(60.32, mean, 0.001);
    }

    @Test
    public void shouldCalculateTheStandardDeviationWithTheSecondAmountOfData() throws IOException {
        File file = new File("src/main/resources/prueba2.txt");
        service.readFile(file);
        double standardDeviation = service.calculateStandardDeviation();
        assertEquals(62.26, standardDeviation, 0.01);
    }

    @Test
    public void shouldCalculateTheMeanWithTheThirdAmountOfData() throws IOException {
        File file = new File("src/main/resources/prueba3.txt");
        service.readFile(file);
        double mean = service.calculateMean();
        assertEquals(638.9, mean, 0.0);
    }

    @Test
    public void shouldCalculateTheStandardDeviationWithTheThirdAmountOfData() throws IOException {
        File file = new File("src/main/resources/prueba3.txt");
        service.readFile(file);
        double standardDeviation = service.calculateStandardDeviation();
        assertEquals(625.63, standardDeviation, 0.01);
    }

    @Test
    public void shouldCalculateTheMeanWithTheFourthAmountOfData() throws IOException {
        File file = new File("src/main/resources/prueba4.txt");
        service.readFile(file);
        double mean = service.calculateMean();
        assertEquals(321.14, mean, 0.001);
    }

    @Test
    public void shouldCalculateTheStandardDeviationWithTheFourthAmountOfData() throws IOException {
        File file = new File("src/main/resources/prueba4.txt");
        service.readFile(file);
        double standardDeviation = service.calculateStandardDeviation();
        assertEquals(497.4, standardDeviation, 0.01);
    }

}
