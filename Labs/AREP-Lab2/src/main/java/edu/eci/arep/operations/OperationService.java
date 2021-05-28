package edu.eci.arep.operations;

import edu.eci.arep.mycollection.MyLinkedList;

/**
 * Interface that represents any new operation on My Linked List.
 */
public interface OperationService {
    /**
     * Calculate The Result Of A Operation On My Linked List.
     *
     * @param linkedList The Linked List whit the amount of data.
     * @return The Result Of A Operation On My Linked List.
     */
    double calculateResult(MyLinkedList<Double> linkedList);
}
