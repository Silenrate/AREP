package edu.eci.arep.services;

import edu.eci.arep.model.Temperature;

/**
 * Class That Implements the conversion method for the App.
 */
public class ServicesImpl implements Services {

    /**
     * Converts From Fahrenheit Temperature To Celsius.
     *
     * @param fahrenheitTemperature The Fahrenheit Temperature.
     * @return The Celsius Temperature.
     */
    @Override
    public Temperature convertFromFahrenheitToCelsius(double fahrenheitTemperature) {
        double newValue = (fahrenheitTemperature - 32.0) * (5.0 / 9.0);
        return new Temperature(newValue);
    }
}
