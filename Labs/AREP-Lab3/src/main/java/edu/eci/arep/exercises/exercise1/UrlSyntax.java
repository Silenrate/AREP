package edu.eci.arep.exercises.exercise1;

import java.net.URL;

/**
 * Exercise 1 Class
 * Author: Daniel Felipe Walteros Trujillo
 * AREP-1
 */
public class UrlSyntax {
    /**
     * Prints the basic properties of this url: "http://www.google.com.co:1234/index.html" with the value "galleta", the valuer "daniel" and the ref "recetas".
     *
     * @param args List of the arguments needed for run the program.
     * @throws Exception When the url reading fails.
     */
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://www.google.com.co:1234/index.html?value=galleta&valuer=daniel#recetas");
        System.out.println("Protocol: " + url.getProtocol());
        System.out.println("Authority: " + url.getAuthority());
        System.out.println("Host: " + url.getHost());
        System.out.println("Port: " + url.getPort());
        System.out.println("Path: " + url.getPath());
        System.out.println("Query: " + url.getQuery());
        System.out.println("File: " + url.getFile());
        System.out.println("Ref: " + url.getRef());
    }
}
