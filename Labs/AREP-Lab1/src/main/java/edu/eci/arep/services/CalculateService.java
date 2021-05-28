package edu.eci.arep.services;

import java.io.File;
import java.io.IOException;

/**
 * Interface that represents the services on the App.
 */
public interface CalculateService {
    /**
     * Read the set of numbers of a file and stores it in a Linked List.
     *
     * @param file File that have the set of numbers.
     * @throws IOException When a error occurs happen reading the file.
     */
    void readFile(File file) throws IOException;

    /**
     * Calculate the Standard Deviation on a previous full Linked List.
     *
     * @return The Standard Deviation of the Linked List.
     */
    double calculateStandardDeviation();

    /**
     * Calculate the Mean on a previous full Linked List.
     *
     * @return The Mean of the Linked List.
     */
    double calculateMean();
}
