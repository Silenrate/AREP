package edu.eci.arep.exercises.exercise2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Exercise 2 Class
 * Author: Daniel Felipe Walteros Trujillo
 * AREP-1
 */
public class URLReader {
    /**
     * Reads a url and writes a file with the html source code of the page with that url.
     *
     * @param args List of the arguments needed for run the program, the first one is the url needed.
     * @throws Exception When the url argument is missed or the reading and writing of the html fails.
     */
    public static void main(String[] args) throws Exception {
        if (args.length <= 0) {
            throw new UnsupportedOperationException("Se debe especificar una URl, ejemplo: http://www.google.com/");
        }
        String urlArgument = args[0];
        String htmlPageRoute = "src/main/resources/";
        URL url = new URL(urlArgument);
        String filename = url.getHost();
        filename = filename.replace(".", "");
        try {
            URLConnection urlConnection = url.openConnection();
            Map<String, List<String>> headers = urlConnection.getHeaderFields();
            Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();
            System.out.println("-------headers------");
            for (Map.Entry<String, List<String>> entry : entrySet) {
                String headerName = entry.getKey();
                if (headerName != null) {
                    System.out.print(headerName + ":");
                }
                List<String> headerValues = entry.getValue();
                for (String value : headerValues) {
                    System.out.print(value);
                }
                System.out.println("");
            }
            System.out.println("-------message-body------");
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            String fullFileName = htmlPageRoute + filename + ".html";
            PrintWriter writer = new PrintWriter(fullFileName, "UTF-8");
            while ((inputLine = reader.readLine()) != null) {
                writer.println(inputLine);
            }
            writer.close();
            System.out.println("El html fue creado en la ruta " + fullFileName);
        } catch (IOException x) {
            System.err.println(x);
        }
    }
}
