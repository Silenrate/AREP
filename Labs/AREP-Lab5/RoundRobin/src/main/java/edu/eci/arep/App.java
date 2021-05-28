package edu.eci.arep;

import com.google.gson.Gson;
import edu.eci.arep.client.ClientServices;
import edu.eci.arep.client.ClientServicesImpl;
import edu.eci.arep.model.Message;
import spark.Request;
import spark.Response;

import java.io.IOException;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

/**
 * Round Robin Spark Web App.
 */
public class App {

    private static final ClientServices services = new ClientServicesImpl();
    private static final Gson gson = new Gson();

    /**
     * Main Function Of Spark Web App.
     *
     * @param args Extra arguments required for run the App (do not have functionality yet).
     */
    public static void main(String... args) {
        port(getPort());
        staticFiles.location("/static");
        get("/", App::redirectToIndexPage);
        get("/lastMessages", App::lastMessagesHandler);
        post("/addNewMessage", "application/json", App::addMessageHandler);
    }

    /**
     * Get the Port Of The Web Application.
     *
     * @return The value of the port configured in the system environment, returns 4568 by default.
     */
    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568;
    }

    /**
     * Adds the new Message On The App.
     *
     * @param request  The HTTP Request that have in his body a JSON with the new message data.
     * @param response The HTTP Response.
     * @return A Json Object with the successful or the error message.
     */
    private static Object addMessageHandler(Request request, Response response) {
        Message message = gson.fromJson(request.body(), Message.class);
        Object result;
        try {
            response.status(201);
            services.addNewMessage(message);
            result = "New Message Successfully Created";
        } catch (IOException e) {
            response.status(409);
            result = e.getMessage();
        }
        return result;
    }

    /**
     * Returns the last ten messages On The App.
     *
     * @param request  The HTTP Request.
     * @param response The HTTP Response.
     * @return A Json Object with the last ten messages or with the error message.
     */
    private static Object lastMessagesHandler(Request request, Response response) {
        Object result;
        try {
            response.header("Content-Type", "application/json");
            result = services.getLastMessages();
        } catch (IOException e) {
            response.status(500);
            result = e.getMessage();
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
}
