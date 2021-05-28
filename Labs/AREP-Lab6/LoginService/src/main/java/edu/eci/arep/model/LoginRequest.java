package edu.eci.arep.model;

/**
 * Login Request Class.
 */
public class LoginRequest {

    private final String email;
    private final String password;

    /**
     * Basic Constructor For The Login Request.
     *
     * @param email    The Email Of The Login Request.
     * @param password The Password Of The Login Request.
     */
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Gets The Email Of The Login Request.
     *
     * @return The Email Of The Login Request.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets The Password Of The Login Request.
     *
     * @return The Password Of The Login Request.
     */
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "{email= \"" + email + "\"" +
                ", password=\"" + password + "\"}";
    }
}
