package edu.eci.arep;

import com.google.gson.Gson;
import edu.eci.arep.services.Services;
import edu.eci.arep.services.ServicesImpl;
import spark.Request;
import spark.Response;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.secure;

/**
 * Log Service Spark Web App.
 */
public class App {

    private static final Gson gson = new Gson();
    private static final Services services = new ServicesImpl();

    /**
     * Main Function Of Spark Web App.
     *
     * @param args Arguments required for run the App, the keystore password.
     */
    public static void main(String... args) {
        if (args.length != 1) {
            System.out.println("To execute this App you require one argument, the keystore password");
            System.exit(0);
        }
        secure("keystore/timeservice.p12", args[0], null, null);
        port(getPort());
        get("/time", App::getTimeHandler);
    }

    /**
     * Get the Port Of The Web Application.
     *
     * @return The value of the port configured in the system environment, returns 8000 by default.
     */
    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8000;
    }

    /**
     * Returns The Time On The Server.
     *
     * @param request  The HTTP Request.
     * @param response The HTTP Response.
     * @return A Json Object With The Time On The Server Or With The Error Message.
     */
    private static Object getTimeHandler(Request request, Response response) {
        Object result;
        try {
            result = gson.toJson(services.getTime());
            System.out.println("Time Requested By: " + request.ip());
        } catch (Exception e) {
            response.status(500);
            result = e.getMessage();
            System.out.println("Time Requested By: " + request.ip() + "Failed Because:");
            e.printStackTrace();
        }
        return result;
    }
}
