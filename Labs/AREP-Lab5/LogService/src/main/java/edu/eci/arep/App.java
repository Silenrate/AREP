package edu.eci.arep;

import com.google.gson.Gson;
import edu.eci.arep.model.Message;
import edu.eci.arep.services.LogServices;
import edu.eci.arep.services.LogServicesImpl;
import spark.Request;
import spark.Response;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

/**
 * Log Service Spark Web App.
 */
public class App {

    private static final Gson gson = new Gson();
    private static final LogServices services = new LogServicesImpl();

    /**
     * Main Function Of Spark Web App.
     *
     * @param args Extra arguments required for run the App (do not have functionality yet).
     */
    public static void main(String... args) {
        port(getPort());
        get("/messages", App::getMessagesHandler);
        post("/addMessage", "application/json", App::addMessageHandler);
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

    /**
     * Adds the new Message and returns tha last ten messages.
     *
     * @param request  The HTTP Request that have in his body a JSON with the new message data.
     * @param response The HTTP Response.
     * @return A Json Object with a message describing the process state.
     */
    private static Object addMessageHandler(Request request, Response response) {
        Message message = gson.fromJson(request.body(), Message.class);
        Object result;
        try {
            services.addMessage(message);
            response.status(201);
            result = "Message Successfully Created";
        } catch (Exception e) {
            System.out.println("Creation Failed because: " + e.getMessage());
            response.status(409);
            result = e.getMessage();
        }
        return result;
    }

    /**
     * Returns tha last ten messages of the App.
     *
     * @param request  The HTTP Request.
     * @param response The HTTP Response.
     * @return A Json Object with the last ten messages or with the error message.
     */
    private static Object getMessagesHandler(Request request, Response response) {
        Object result;
        try {
            result = gson.toJson(services.getLastMessages());
        } catch (Exception e) {
            response.status(500);
            result = e.getMessage();
        }
        return result;
    }
}
