package edu.eci.arep;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Basic Spark Web App For Use With Docker.
 */
public class App {
    /**
     * Main Function Of Spark Web App.
     *
     * @param args Extra arguments required for run the App (do not have functionality yet).
     */
    public static void main(String... args) {
        port(getPort());
        get("hello", (req, res) -> "Hello Docker!");
    }

    /**
     * Get the Port Of The Web Application.
     *
     * @return The value of the port configured in the system environment, returns 4567 by default.
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
