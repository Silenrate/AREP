package edu.eci.arep;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.secure;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        secure("keyscerts/ecikeystore.p12","123456",null,null);
        port(getPort());
        get("/hello", (req, res) -> "Hello From HTTPS");
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
        return 5000;
    }
}
