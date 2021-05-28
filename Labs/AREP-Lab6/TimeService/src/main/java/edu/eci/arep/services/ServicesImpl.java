package edu.eci.arep.services;

import edu.eci.arep.model.Time;

import java.util.Date;

/**
 * Class that implements the Time Service App methods.
 */
public class ServicesImpl implements Services {

    /**
     * Returns the time on the App.
     *
     * @return A String value of the time on the App.
     */
    @Override
    public Time getTime() {
        Date actualTime = new Date();
        return new Time(actualTime.toString());
    }
}
