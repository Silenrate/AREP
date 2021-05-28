package edu.eci.arep.model;

/**
 * Temperature Class For The Model.
 */
public class Temperature {

    private final double value;

    /**
     * Constructor For Temperature.
     *
     * @param value The Temperature Value.
     */
    public Temperature(double value) {
        this.value = value;
    }

    /**
     * Returns The Temperature Value.
     *
     * @return The Double Temperature Value.
     */
    public double getValue() {
        return value;
    }
}
