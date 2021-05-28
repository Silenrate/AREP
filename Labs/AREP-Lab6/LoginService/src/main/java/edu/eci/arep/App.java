package edu.eci.arep;

import com.google.gson.Gson;
import edu.eci.arep.model.LoginRequest;
import edu.eci.arep.services.ServiceException;
import edu.eci.arep.services.Services;
import edu.eci.arep.services.ServicesImpl;
import spark.Request;
import spark.Response;
import spark.staticfiles.StaticFilesConfiguration;

import java.util.logging.Level;
import java.util.logging.Logger;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.secure;

/**
 * Login Service Spark Web App.
 */
public class App {

    private static final Gson gson = new Gson();

    private static Services services;

    private static final String SESSION_LOGGED_STATE = "IsLoggedIn";

    /**
     * Main Function Of Spark Web App.
     *
     * @param args Arguments required for run the App, the keystore and truststore passwords.
     */
    public static void main(String... args) {
        if (args.length != 2) {
            System.out.println("To execute this App you require two arguments, the keystore and truststore passwords");
            System.exit(0);
        }
        try {
            services = new ServicesImpl(args[1]);
        } catch (ServiceException e) {
            Logger.getLogger(ServicesImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        secure("keystore/loginservice.p12", args[0], null, null);
        port(getPort());
        before("/protected/*", App::validateSecureRoute);
        StaticFilesConfiguration staticHandler = new StaticFilesConfiguration();
        staticHandler.configure("/static");
        before((request, response) ->
                staticHandler.consume(request.raw(), response.raw()));
        get("/", App::redirectToIndexPage);
        get("/time", App::getTime);
        post("/login", "application/json", App::loginHandler);
    }

    /**
     * Returns The Time On The Server.
     *
     * @param request  The HTTP Request.
     * @param response The HTTP Response.
     * @return The Time On The Time App Server.
     */
    private static Object getTime(Request request, Response response) {
        response.status(200);
        Object result;
        try {
            result = gson.toJson(services.getTime());
        } catch (ServiceException e) {
            System.out.println("GET Failed Because " + e.getMessage());
            e.printStackTrace();
            result = e.getMessage();
        }
        return result;
    }

    /**
     * Validates If The Access To The Secure Path Is Authenticated.
     *
     * @param request  The HTTP Request.
     * @param response The HTTP Response.
     */
    private static void validateSecureRoute(Request request, Response response) {
        request.session(true);
        if (request.session().isNew()) {
            request.session().attribute(SESSION_LOGGED_STATE, false);
        }
        boolean isLoggedIn = request.session().attribute(SESSION_LOGGED_STATE);
        if (!isLoggedIn) {
            response.redirect("/index.html");
            System.out.println("Unsuccessful attempt to connect to Protected Route");
        } else {
            System.out.println("Successful access to Protected Route");
        }
    }

    /**
     * Handles The Login Event On The App.
     *
     * @param request  The HTTP Request.
     * @param response The HTTP Response.
     * @return A Message With The Final State Of The Login Process.
     */
    private static Object loginHandler(Request request, Response response) {
        request.session(true);
        LoginRequest loginRequest = gson.fromJson(request.body(), LoginRequest.class);
        String result;
        try {
            services.logIn(loginRequest);
            request.session().attribute("User", loginRequest.getEmail());
            request.session().attribute(SESSION_LOGGED_STATE, true);
            response.status(201);
            result = "Log In Successful";
            System.out.println(result + " of " + loginRequest.getEmail());
        } catch (ServiceException e) {
            response.status(409);
            result = e.getMessage();
            System.out.println("Failed Authentication Attempt to user " + loginRequest.getEmail());
        }
        return result;
    }

    /**
     * Redirects to the Index Page.
     *
     * @param request  The HTTP Request.
     * @param response The HTTP Response.
     * @return Null
     */
    private static Object redirectToIndexPage(Request request, Response response) {
        response.redirect("/index.html");
        return null;
    }

    /**
     * Get the Port Of The Web Application.
     *
     * @return The value of the port configured in the system environment, returns 7000 by default.
     */
    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 7000;
    }


}
