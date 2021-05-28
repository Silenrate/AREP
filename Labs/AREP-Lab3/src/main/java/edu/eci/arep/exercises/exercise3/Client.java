package edu.eci.arep.exercises.exercise3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Client Basic Socket Class
 * Author: Daniel Felipe Walteros Trujillo
 * AREP-1
 */
public class Client {
    /**
     * Runs A Socket For The Client.
     *
     * @param args List of special arguments, do not have functionality yet.
     * @throws IOException When the connection to the server fails.
     */
    public static void main(String[] args) throws IOException {
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            echoSocket = new Socket("127.0.0.1", 35000);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host Desconocido");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("No se puedo acceder a las herramientas I/O de la conexión.");
            System.exit(1);
        }
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        String response;
        while (!((response = in.readLine()).isEmpty())) {
            System.out.println(response);
        }
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            response = in.readLine();
            System.out.println(response);
            if (response.equals("salir")) break;
        }
        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }

}
