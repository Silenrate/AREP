package edu.eci.arep;

import com.google.gson.Gson;
import edu.eci.arep.model.Result;
import edu.eci.arep.services.CollectionServices;
import edu.eci.arep.services.CollectionServicesImpl;
import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.List;

import static spark.Spark.*;

/**
 * Spark Web App
 * Author: Daniel Felipe Walteros Trujillo
 */
public class App {

    public static final String HTML_BASIC_START = "<!DOCTYPE html><html><body>";
    public static final String HTML_BASIC_END = "</body></html>";
    private static final Gson gson = new Gson();
    private static final CollectionServices services = new CollectionServicesImpl();

    /**
     * Main function of the Spark App.
     *
     * @param args List of special arguments, do not have functionality.
     */
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/static");
        get("/", App::inputDataPage);
        get("/result", App::resultPage);
    }

    /**
     * Return the HTML of the Input Data Page.
     *
     * @param req The request made for that page.
     * @param res The response of that page.
     * @return A String that represents the HTML of the Input Data Page.
     */
    private static String inputDataPage(Request req, Response res) {
        res.redirect("/index.html");
        res.status(200);
        return null;
    }

    /**
     * Return the HTML of the Result Page.
     *
     * @param req The request made for that page.
     * @param res The response of that page.
     * @return A String that represents the HTML of the Result Page.
     */
    private static String resultPage(Request req, Response res) {
        res.type("application/json");
        List<String> data = Arrays.asList(req.queryParams("values").split(","));
        Result result = getResult(data);
        return gson.toJson(result);
    }

    /**
     * Get the Sum and The Average Of A Set Of Numbers.
     *
     * @param data A string that represents the set of numbers, splitting the numbers with a comma.
     * @return A class with the ordered set, the sum and the average.
     */
    private static Result getResult(List<String> data) {
        Result result = new Result();
        result.setValuesFromString(data);
        services.sort(result);
        services.calculateAverage(result);
        services.calculateSum(result);
        return result;
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
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}
