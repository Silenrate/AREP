package edu.eci.arep.services;

import edu.eci.arep.model.AppResponse;

/**
 * Class That Implements the Calculator Methods for the App.
 */
public class ServicesImpl implements Services {

    /**
     * Calculates The Square Root from a Number.
     *
     * @param number The Number To Calculate.
     * @return The AppResponse Object With The Square Root from the Number.
     */
    @Override
    public AppResponse getSquareRoot(Double number) {
        Double sqrt = Math.sqrt(number);
        return new AppResponse("sqrt", number, sqrt);
    }

    /**
     * Calculates The Cosine from a Number.
     *
     * @param number The Number To Calculate.
     * @return The AppResponse Object With The Cosine from the Number.
     */
    @Override
    public AppResponse getCosine(Double number) {
        Double cos = Math.cos(number);
        return new AppResponse("cos", number, cos);
    }
}
