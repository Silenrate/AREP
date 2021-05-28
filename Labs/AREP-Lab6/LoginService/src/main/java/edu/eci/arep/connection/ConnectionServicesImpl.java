package edu.eci.arep.connection;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * Class that implements the Connection To Time API Methods.
 */
public class ConnectionServicesImpl implements ConnectionServices {

    private static final String TIME_API_URL = "https://ec2-52-87-219-41.compute-1.amazonaws.com:8000";

    /**
     * Basic Constructor For ConnectionServicesImpl
     *
     * @throws ConnectionException When The Truststore initialization fails.
     * @param password The Truststore Password.
     */
    public ConnectionServicesImpl(String password) throws ConnectionException {
        // Create a file and a password representation
        File trustStoreFile = new File("keystore/myTrustStore");
        char[] trustStorePassword = password.toCharArray();
        // Load the trust store, the default type is "pkcs12", the alternative is "jks"
        KeyStore trustStore;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        } catch (KeyStoreException e) {
            throw new ConnectionException("Error al leer el keystore", e);
        }
        TrustManagerFactory tmf;
        try {
            trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);
            // Get the singleton instance of the TrustManagerFactory
            tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            // Init the TrustManagerFactory using the truststore object
            tmf.init(trustStore);
        } catch (IOException | NoSuchAlgorithmException | CertificateException | KeyStoreException e) {
            throw new ConnectionException("Error al cargar el Truststore", e);
        }
        //Set the default global SSLContext so all the connections will use it
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new ConnectionException("Error al configurar SSL Context", e);
        }
        SSLContext.setDefault(sslContext);
    }

    /**
     * Gets The Time Of The Time Server.
     *
     * @return The Time Of The Time Server
     * @throws ConnectionException When The Connection To Time API Fails.
     */
    @Override
    public String getTime() throws ConnectionException {
        // Crea el objeto que representa una URL
        URL siteURL;
        try {
            siteURL = new URL(TIME_API_URL + "/time");
        } catch (MalformedURLException ex) {
            throw new ConnectionException(ex.getMessage(), ex);
        }
        // Crea el objeto que URLConnection
        URLConnection urlConnection;
        try {
            urlConnection = siteURL.openConnection();
        } catch (IOException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
        System.out.println("Making GET Request To " + TIME_API_URL);
        String inputLine;
        try {
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            inputLine = reader.readLine();
        } catch (IOException x) {
            throw new ConnectionException(x.getMessage(), x);
        }
        if (inputLine == null) {
            throw new ConnectionException("Connection Error");
        }
        System.out.println("GET Successful");
        return inputLine;
    }
}
