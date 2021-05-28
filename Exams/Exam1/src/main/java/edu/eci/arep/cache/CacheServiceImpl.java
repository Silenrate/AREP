package edu.eci.arep.cache;

import edu.eci.arep.model.Weather;

import java.time.LocalDateTime;
import java.util.HashMap;

public class CacheServiceImpl implements CacheService {

    private static final long MINUTES_IN_CACHE = 10;

    private final HashMap<String, Weather> values = new HashMap<>();

    /**
     * Gets the weather on the cache for a specific place.
     *
     * @param place The name of the place.
     * @return The weather on the cache for a specific place.
     * @throws CacheException If there is no weather info about that place on cache.
     */
    @Override
    public Weather getWeather(String place) throws CacheException {
        if (!values.containsKey(place)) {
            throw new CacheException("There is no " + place + " in cache");
        }
        return values.get(place);
    }

    /**
     * Puts a new Weather Info On The Cache.
     *
     * @param place   The place of the weather info.
     * @param weather The weather info.
     * @throws CacheException If there is already weather info of that place in cache.
     */
    @Override
    public void putWeatherInCache(String place, Weather weather) throws CacheException {
        if (isWeatherInCache(place)) {
            throw new CacheException("The place" + place + " is already in cache");
        }
        values.put(place, weather);
    }

    /**
     * Removes weather info of a place from the cache.
     *
     * @param place The place of the weather info that it is gonna be removed form cache.
     */
    @Override
    public void removeWeatherOfCache(String place) {
        values.remove(place);
    }

    /**
     * Determines if the weather info of a place is on the cache.
     *
     * @param place The place of the weather info.
     * @return A boolean value that determines if the weather info of a place is on the cache.
     */
    @Override
    public boolean isWeatherInCache(String place) {
        Weather weather = values.get(place);
        boolean isInCache = true;
        if (weather == null) {
            isInCache = false;
        } else if (LocalDateTime.now().isAfter(weather.getTime().plusMinutes(MINUTES_IN_CACHE))) {
            removeWeatherOfCache(place);
            isInCache = false;
        }
        return isInCache;
    }
}
