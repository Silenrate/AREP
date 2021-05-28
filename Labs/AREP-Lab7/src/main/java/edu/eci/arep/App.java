package edu.eci.arep;

import com.google.gson.Gson;
import edu.eci.arep.model.Temperature;
import edu.eci.arep.services.Services;
import edu.eci.arep.services.ServicesImpl;
import spark.Request;
import spark.Response;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Temperature Spark Web App.
 */
public class App {

    private static final Services services = new ServicesImpl();
    private static final Gson gson = new Gson();

    /**
     * Main Function Of Temperature Spark Web App.
     *
     * @param args Arguments required for run the App, do not have functionality yet.
     */
    public static void main(String[] args) {
        port(getPort());
        get("/", (req, res) -> "For use the temperature API go to tue route /temperature?value=100");
        get("/temperature", App::temperatureHandler);
    }

    /**
     * Convert the temperature from fahrenheit to celsius.
     *
     * @param request  The HTTP Request.
     * @param response The HTTP Response.
     * @return The Temperature In Celsius.
     */
    private static Object temperatureHandler(Request request, Response response) {
        double value = Double.parseDouble(request.queryParams("value"));
        Temperature temperature = services.convertFromFahrenheitToCelsius(value);
        System.out.println("GET Request received from" + request.ip());
        response.header("Content-Type", "application/json");
        return gson.toJson(temperature);
    }

    /**
     * Get the Port Of The Web Application.
     *
     * @return The value of the port configured in the system environment, returns 4567 by default.
     */
    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
