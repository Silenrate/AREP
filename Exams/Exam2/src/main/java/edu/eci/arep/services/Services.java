package edu.eci.arep.services;

import edu.eci.arep.model.AppResponse;

/**
 * Interface with the Calculator Methods for the App.
 */
public interface Services {

    /**
     * Calculates The Square Root from a Number.
     *
     * @param number The Number To Calculate.
     * @return The AppResponse Object With The Square Root from the Number.
     */
    AppResponse getSquareRoot(Double number);

    /**
     * Calculates The Cosine from a Number.
     *
     * @param number The Number To Calculate.
     * @return The AppResponse Object With The Cosine from the Number.
     */
    AppResponse getCosine(Double number);
}
