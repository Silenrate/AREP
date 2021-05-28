package edu.eci.arep;

import edu.eci.arep.apiconnection.ConnectionService;
import edu.eci.arep.apiconnection.ConnectionServiceImpl;
import edu.eci.arep.cache.CacheService;
import edu.eci.arep.cache.CacheServiceImpl;
import edu.eci.arep.model.Weather;
import spark.Request;
import spark.Response;

import java.time.LocalDateTime;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Weather Spark Basic Web App
 */
public class App {

    public static final ConnectionService connectionService = new ConnectionServiceImpl();
    public static final CacheService cacheService = new CacheServiceImpl();

    /**
     * Main Function Of Spark Web App.
     *
     * @param args Extra Arguments For The App, do not do anything.
     */
    public static void main(String[] args) {
        port(getPort());
        get("/clima", App::weather);
    }

    /**
     * Gets the JSON Object that represents the weather on a certain place.
     *
     * @param request  The HTTP Request For The App.
     * @param response The HTTP Response For The App.
     * @return An object describing the weather on a certain place or the error message.
     */
    private static Object weather(Request request, Response response) {
        String place = request.queryParams("lugar");
        if (place == null || place.equals("")) {
            return "No place provided ex. /clima?lugar=London";
        }
        Object result;
        try {
            if (cacheService.isWeatherInCache(place)) {
                result = cacheService.getWeather(place).getInformation();
            } else {
                result = connectionService.getWeatherOfPlace(place);
                Weather weather = new Weather(result);
                weather.setTime(LocalDateTime.now());
                cacheService.putWeatherInCache(place, weather);
            }
            response.header("Content-Type", "application/json");
        } catch (Exception e) {
            response.status(500);
            result = "API Error: " + e.getMessage();
        }
        return result;
    }

    /**
     * Get the Port Of The Web Application.
     *
     * @return The value of the port configured in the system environment, returns 35000 by default.
     */
    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}
