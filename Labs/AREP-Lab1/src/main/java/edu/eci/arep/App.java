package edu.eci.arep;

import edu.eci.arep.services.CalculateService;
import edu.eci.arep.services.CalculateServiceImpl;

import java.io.File;
import java.io.IOException;

/**
 * Author: Daniel Felipe Walteros Trujillo
 * AREP-1
 */
public class App {
    /**
     * Main Function For The App
     *
     * @param args List of the arguments needed for run the program, the only argument is the pathFile where the set of number is.
     * @throws IOException When the pathFile does not exist.
     */
    public static void main(String[] args) throws IOException {
        String pathFile = args[0];
        File file = new File(pathFile);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                throw new UnsupportedOperationException("El directorio asignado esta vacío");
            }
            for (File f : files) {
                if (f.isFile()) {
                    calculate(f);
                }
            }
        } else {
            calculate(file);
        }
    }

    /**
     * Function that prints on the terminal the mean and standard deviation of a number set in a file.
     *
     * @param file The file that have the number set.
     * @throws IOException When a error occurs happen reading the file.
     */
    private static void calculate(File file) throws IOException {
        CalculateService service = new CalculateServiceImpl();
        service.readFile(file);
        double mean = service.calculateMean();
        double standardDeviation = service.calculateStandardDeviation();
        System.out.printf("%n Results of the number set of %s %n", file);
        System.out.printf("The mean is: %.2f %n", mean);
        System.out.printf("The Standard Deviation is: %.2f %n", standardDeviation);
    }
}
