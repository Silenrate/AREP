package edu.eci.arep.services;

import edu.eci.arep.mycollection.MyLinkedList;

import java.util.List;

/**
 * Interface that represents the services on the App.
 */
public interface CalculateService {
    /**
     * Read the set of numbers to calculate later.
     *
     * @param numbers A list that represents the set of numbers in string value.
     */
    void readNumbers(List<String> numbers);

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
