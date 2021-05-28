package edu.eci.arep.services;

import edu.eci.arep.mycollection.MyLinkedList;
import edu.eci.arep.operations.MeanOperationService;
import edu.eci.arep.operations.OperationService;
import edu.eci.arep.operations.StandardDeviationOperationService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class that implements the services on the App.
 */
public class CalculateServiceImpl implements CalculateService {

    private MyLinkedList<Double> myLinkedList;
    private OperationService operationService;

    /**
     * Read the set of numbers of a file and stores it in a Linked List.
     *
     * @param file File that have the set of numbers.
     * @throws IOException When a error occurs happen reading the file.
     */
    @Override
    public void readFile(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        MyLinkedList<Double> list = new MyLinkedList<>();
        while ((line = bufferedReader.readLine()) != null) {
            list.add(Double.parseDouble(line));
        }
        fileReader.close();
        this.myLinkedList = list;
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
