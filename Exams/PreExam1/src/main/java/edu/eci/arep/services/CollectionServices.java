package edu.eci.arep.services;

import edu.eci.arep.model.Result;

/**
 * Interface of Services Required for the App in the collection.
 * Author: Daniel Felipe Walteros Trujillo
 */
public interface CollectionServices {
    /**
     * Calculate the average of a result object.
     *
     * @param result The object with tha set of values
     */
    void calculateAverage(Result result);

    /**
     * Calculate the sum of a result object.
     *
     * @param result The object with tha set of values
     */
    void calculateSum(Result result);

    /**
     * Orders the values of a result object.
     *
     * @param result The object with tha set of values
     */
    void sort(Result result);
}
