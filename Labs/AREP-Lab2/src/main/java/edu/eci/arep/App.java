package edu.eci.arep;

import edu.eci.arep.services.CalculateService;
import edu.eci.arep.services.CalculateServiceImpl;
import spark.Request;
import spark.Response;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Spark Mean And Standard Web App
 */
public class App {

    private static final CalculateService service = new CalculateServiceImpl();
    public static final String HTML_BASIC_START = "<!DOCTYPE html><html><body>";
    public static final String HTML_BASIC_END = "</body></html>";

    /**
     * Main function of the Spark App.
     *
     * @param args List of special arguments, do not have functionality.
     */
    public static void main(String[] args) {
        port(getPort());
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
        return HTML_BASIC_START
                + "<h2>Mean And Standard Deviation Calculator</h2>"
                + "<form action=\"/result\">"
                + "Please put the set of numbers separated by a comma.<br>"
                + "<br><br>"
                + "<input type=\"text\" name=\"values\" value=\"\">"
                + "<br><br>"
                + "<input type=\"submit\" value=\"Calculate\">"
                + "</form>"
                + HTML_BASIC_END;
    }

    /**
     * Return the HTML of the Result Page.
     *
     * @param req The request made for that page.
     * @param res The response of that page.
     * @return A String that represents the HTML of the Result Page.
     */
    private static String resultPage(Request req, Response res) {
        String message = getResult(req.queryParams("values"));
        return HTML_BASIC_START
                + "<p>Data entered: [" + req.queryParams("values") + "]</p>"
                + "<p>" + message + "</p>"
                + HTML_BASIC_END;
    }

    /**
     * Get the Mean And Standard Deviation Of A Set Of Numbers.
     *
     * @param values A string that represents the set of numbers, splitting the numbers with a comma.
     * @return A message with the mean And standard deviation of the set of numbers or a error message in case of error.
     */
    private static String getResult(String values) {
        String message;
        DecimalFormat df = new DecimalFormat("###.##");
        List<String> data = Arrays.asList(values.split(","));
        try {
            service.readNumbers(data);
            String mean = df.format(service.calculateMean());
            String standardDeviation = df.format(service.calculateStandardDeviation());
            message = String.format("The Mean is %s and the Standard Deviation is %s", mean, standardDeviation);
        } catch (Exception e) {
            message = "Please verify the data submitted, e.g. (1,2.0,3.5)";
        }
        return message;
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
