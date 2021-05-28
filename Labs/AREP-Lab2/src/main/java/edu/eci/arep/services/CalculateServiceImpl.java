package edu.eci.arep.services;

import edu.eci.arep.mycollection.MyLinkedList;
import edu.eci.arep.operations.MeanOperationService;
import edu.eci.arep.operations.OperationService;
import edu.eci.arep.operations.StandardDeviationOperationService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Class that implements the services on the App.
 */
public class CalculateServiceImpl implements CalculateService {

    private MyLinkedList<Double> myLinkedList;
    private OperationService operationService;

    /**
     * Read the set of numbers to calculate later.
     *
     * @param numbers The linked list that represents the set of numbers.
     */
    @Override
    public void readNumbers(List<String> numbers) {
        myLinkedList = new MyLinkedList<>();
        int amountOfNumbers = numbers.size();
        for (int i = 0; i < amountOfNumbers; i++) {
            myLinkedList.add(Double.parseDouble(numbers.get(i)));
        }
    }

    /**
     * Calculate the Standard Deviation on a previous full Linked List.
     *
     * @return The Standard Deviation of the Linked List.
     */
    @Override
    public double calculateStandardDeviation() {
        operationService = new StandardDeviationOperationService();
        return operationService.calculateResult(myLinkedList);
    }

    /**
     * Calculate the Mean on a previous full Linked List.
     *
     * @return The Mean of the Linked List.
     */
    @Override
    public double calculateMean() {
        operationService = new MeanOperationService();
        return operationService.calculateResult(myLinkedList);
    }
}
