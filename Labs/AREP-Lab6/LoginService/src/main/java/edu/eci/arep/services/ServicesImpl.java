package edu.eci.arep.services;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import edu.eci.arep.connection.ConnectionException;
import edu.eci.arep.connection.ConnectionServices;
import edu.eci.arep.connection.ConnectionServicesImpl;
import edu.eci.arep.model.LoginRequest;
import edu.eci.arep.model.Time;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Class That Implements The Login App Services.
 */
public class ServicesImpl implements Services {

    private static final Map<String, String> users = new HashMap<>();
    private static final Gson gson = new Gson();
    private static ConnectionServices services;

    /**
     * Basic Constructor For ServicesImpl.
     *
     * @param trustStorePassword The Password For The TrustStore File.
     * @throws ServiceException When the connection service fails at initializing.
     */
    public ServicesImpl(String trustStorePassword) throws ServiceException {
        users.put("prueba@mail.com", hash("clave"));
        try {
            services = new ConnectionServicesImpl(trustStorePassword);
        } catch (ConnectionException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Login On The App.
     *
     * @param loginRequest The Login Request.
     * @throws ServiceException When The Login Fails.
     */
    @Override
    public void logIn(LoginRequest loginRequest) throws ServiceException {
        String email = loginRequest.getEmail();
        String password = users.get(email);
        if (password == null) {
            throw new ServiceException("There is no user with the email " + email);
        }
        if (!password.equals(hash(loginRequest.getPassword()))) {
            throw new ServiceException("Wrong Credentials");
        }
    }

    /**
     * Encodes A Password With A Hash Function.
     *
     * @param password The Password To Encode.
     * @return The Encrypted Password.
     */
    @Override
    public String hash(String password) {
        return Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
    }

    /**
     * Gets The Time Of The Time Server.
     *
     * @return The Time Of The Time Server.
     * @throws ServiceException When The Time Service Fails.
     */
    @Override
    public Time getTime() throws ServiceException {
        Time time;
        String actualTime;
        try {
            actualTime = services.getTime();
        } catch (ConnectionException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        time = gson.fromJson(actualTime, Time.class);
        return time;
    }
}
