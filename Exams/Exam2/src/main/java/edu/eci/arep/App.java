package edu.eci.arep;

import com.google.gson.Gson;
import edu.eci.arep.model.AppResponse;
import edu.eci.arep.services.Services;
import edu.eci.arep.services.ServicesImpl;
import spark.Request;
import spark.Response;

import static spark.Spark.get;
import static spark.Spark.port;


/**
 * Calculator Spark Web App.
 */
public class App {

    private static final Services services = new ServicesImpl();
    private static final Gson gson = new Gson();

    /**
     * Main Function Of Calculator Spark Web App.
     *
     * @param args Arguments required for run the App, do not have functionality yet.
     */
    public static void main(String[] args) {
        port(getPort());
        get("/", (req, res) -> "For use the calculator API go to the route /cos?value=0 o /sqrt?value=100");
        get("/cos", App::cosHandler);
        get("/sqrt", App::sqrtHandler);
    }

    /**
     * Calculates The Square Root from a Number.
     *
     * @param request  The HTTP Request.
     * @param response The HTTP Response.
     * @return The Square Root from the Number.
     */
    private static Object sqrtHandler(Request request, Response response) {
        Double value = Double.parseDouble(request.queryParams("value"));
        AppResponse appResponse = services.getSquareRoot(value);
        System.out.println("Square Root Request For " + request.ip());
        response.header("Content-Type", "application/json");
        return gson.toJson(appResponse);
    }

    /**
     * Calculates The Cosine from a Number In Radians.
     *
     * @param request  The HTTP Request.
     * @param response The HTTP Response.
     * @return The Cosine from a Number In Radians.
     */
    private static Object cosHandler(Request request, Response response) {
        Double value = Double.parseDouble(request.queryParams("value"));
        AppResponse appResponse = services.getCosine(value);
        System.out.println("Cosine Request For " + request.ip());
        response.header("Content-Type", "application/json");
        return gson.toJson(appResponse);
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
