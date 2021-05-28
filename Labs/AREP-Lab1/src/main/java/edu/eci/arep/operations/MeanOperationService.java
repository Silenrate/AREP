package edu.eci.arep.operations;

import edu.eci.arep.mycollection.MyLinkedList;

/**
 * Class that calculates the Mean Of A Set Of Numbers On My Linked List.
 */
public class MeanOperationService implements OperationService {

    /**
     * Calculate The Mean On My Linked List.
     *
     * @param linkedList The Linked List whit the amount of data.
     * @return The Mean On My Linked List.
     */
    @Override
    public double calculateResult(MyLinkedList<Double> linkedList) {
        if (linkedList.isEmpty()) {
            throw new ArithmeticException("Cannot get the mean of an empty collection");
        }
        double counter = 0;
        int length = linkedList.size();
        for (int i = 0; i < length; i++) {
            counter += linkedList.get(i);
        }
        return counter / length;
    }
}
