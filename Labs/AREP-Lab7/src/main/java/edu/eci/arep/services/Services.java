package edu.eci.arep.services;

import edu.eci.arep.model.Temperature;

/**
 * Interface with the conversion method for the App.
 */
public interface Services {

    /**
     * Converts From Fahrenheit Temperature To Celsius.
     *
     * @param fahrenheitTemperature The Fahrenheit Temperature.
     * @return The Celsius Temperature.
     */
    Temperature convertFromFahrenheitToCelsius(double fahrenheitTemperature);
}
