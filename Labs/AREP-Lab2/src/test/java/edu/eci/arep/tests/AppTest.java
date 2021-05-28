package edu.eci.arep.tests;

import edu.eci.arep.BasicAppTest;
import edu.eci.arep.services.CalculateService;
import edu.eci.arep.services.CalculateServiceImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test class for Mean And Standard Deviation App.
 */
public class AppTest implements BasicAppTest {

    private final CalculateService service = new CalculateServiceImpl();

    @Test
    public void shouldCalculateTheMeanWithTheFirstAmountOfData() {
        service.readNumbers(firstAmountOfData);
        double mean = service.calculateMean();
        assertEquals(meanOfFirstAmountOfData, mean, 0.001);
    }

    @Test
    public void shouldCalculateTheStandardDeviationWithTheFirstAmountOfData() {
        service.readNumbers(firstAmountOfData);
        double standardDeviation = service.calculateStandardDeviation();
        assertEquals(standardDeviationOfFirstAmountOfData, standardDeviation, 0.01);
    }

    @Test
    public void shouldCalculateTheMeanWithTheSecondAmountOfData() {
        service.readNumbers(secondAmountOfData);
        double mean = service.calculateMean();
        assertEquals(meanOfSecondAmountOfData, mean, 0.001);
    }

    @Test
    public void shouldCalculateTheStandardDeviationWithTheSecondAmountOfData() {
        service.readNumbers(secondAmountOfData);
        double standardDeviation = service.calculateStandardDeviation();
        assertEquals(standardDeviationOfSecondAmountOfData, standardDeviation, 0.01);
    }

}
