package edu.eci.arep.model;

/**
 * Time Class.
 */
public class Time {

    String actualTime;

    /**
     * Basic Constructor For Time Class.
     *
     * @param actualTime A string value that represents the actual time.
     */
    public Time(String actualTime) {
        this.actualTime = actualTime;
    }

    /**
     * Returns The Actual Time.
     *
     * @return The Actual Time
     */
    public String getActualTime() {
        return actualTime;
    }
}
