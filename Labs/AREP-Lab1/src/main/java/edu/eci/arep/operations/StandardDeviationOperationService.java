package edu.eci.arep.operations;

import edu.eci.arep.mycollection.MyLinkedList;

/**
 * Class that calculates the Standard Deviation Of A Set Of Numbers On My Linked List.
 */
public class StandardDeviationOperationService implements OperationService {

    private final MeanOperationService meanOperationService = new MeanOperationService();

    /**
     * Calculate The Standard Deviation On My Linked List.
     *
     * @param linkedList The Linked List whit the amount of data.
     * @return The Standard Deviation On My Linked List.
     */
    @Override
    public double calculateResult(MyLinkedList<Double> linkedList) {
        if (linkedList.isEmpty()) {
            throw new ArithmeticException("Cannot get the mean of an empty collection");
        }
        double mean = meanOperationService.calculateResult(linkedList);
        double counter = 0;
        int length = linkedList.size();
        for (int i = 0; i < length; i++) {
            counter += Math.pow(linkedList.get(i) - mean, 2);
        }
        double variance = counter / (length - 1);
        return Math.sqrt(variance);
    }
}
