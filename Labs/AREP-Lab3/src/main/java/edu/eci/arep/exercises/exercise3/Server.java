package edu.eci.arep.exercises.exercise3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server Socket Class That Returns The Square Of The Input.
 * Author: Daniel Felipe Walteros Trujillo
 * AREP-1
 */
public class Server {
    /**
     * Runs A Socket For The Server.
     *
     * @param args List of special arguments, do not have functionality yet.
     * @throws IOException When the initializing of the server fails.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        int port = 35000;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Servidor Encendido");
        } catch (IOException e) {
            System.err.println("El puerto " + port + "está ocupado.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Conexión rechazada");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        String outputLine;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.equals("salir")) break;
            System.out.println("Mensaje:" + inputLine);
            double inputValue = Double.parseDouble(inputLine);
            outputLine = "Respuesta: " + (inputValue * inputValue);
            out.println(outputLine);
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }

}
