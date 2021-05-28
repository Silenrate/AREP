package edu.eci.arep.cache;

import edu.eci.arep.model.Weather;

/**
 * Interface that represents the service cache for the App.
 */
public interface CacheService {
    /**
     * Gets the weather on the cache for a specific place.
     *
     * @param place The name of the place.
     * @return The weather on the cache for a specific place.
     * @throws CacheException If there is no weather info about that place on cache.
     */
    Weather getWeather(String place) throws CacheException;

    /**
     * Puts a new Weather Info On The Cache.
     *
     * @param place   The place of the weather info.
     * @param weather The weather info.
     * @throws CacheException If there is already weather info of that place in cache.
     */
    void putWeatherInCache(String place, Weather weather) throws CacheException;

    /**
     * Removes weather info of a place from the cache.
     *
     * @param place The place of the weather info that it is gonna be removed form cache.
     */
    void removeWeatherOfCache(String place);

    /**
     * Determines if the weather info of a place is on the cache.
     *
     * @param place The place of the weather info.
     * @return A boolean value that determines if the weather info of a place is on the cache.
     */
    boolean isWeatherInCache(String place);
}
