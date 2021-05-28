package edu.eci.arep.apiconnection;

/**
 * Interface that represents the service that connects with Open Weather API.
 */
public interface ConnectionService {
    /**
     * Returns the weather on a specific place.
     *
     * @param place The name of the place.
     * @return A object that represents the weather on a specific place.
     * @throws Exception If the connection with the OpenWeather API fails.
     */
    Object getWeatherOfPlace(String place) throws Exception;
}
