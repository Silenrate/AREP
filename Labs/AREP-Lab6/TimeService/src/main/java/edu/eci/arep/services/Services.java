package edu.eci.arep.services;

import edu.eci.arep.model.Time;

/**
 * Interface with the Time Service App methods.
 */
public interface Services {

    /**
     * Returns the time on the App.
     *
     * @return A String value of the time on the App.
     */
    Time getTime();
}
