package edu.eci.arep.model;

import java.time.LocalDateTime;

/**
 * Represents the Weather Information On The App.
 */
public class Weather {
    private final Object information;
    private LocalDateTime time;

    /**
     * Constructor for Weather.
     *
     * @param information The Weather Information.
     */
    public Weather(Object information) {
        this.information = information;
    }

    /**
     * Sets the timestamp of the information.
     *
     * @param time The LocalDate Value of the timestamp.
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * Returns the timestamp of the information.
     *
     * @return The LocalDate Value of the timestamp.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Returns the information of the weather.
     *
     * @return The information of the weather.
     */
    public Object getInformation() {
        return information;
    }
}
