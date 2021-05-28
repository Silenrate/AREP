package edu.eci.arep.model;

import java.util.Date;

/**
 * Class that represents a Message On The App.
 */
public class Message {

    private final String data;
    private Date date;

    /**
     * Constructor For Message Class.
     *
     * @param message Message Information.
     * @param date    Date Of The Message Creation.
     */
    public Message(String message, Date date) {
        this.data = message;
        this.date = date;
    }

    /**
     * Constructor For Message Class.
     *
     * @param data Message Information.
     */
    public Message(String data) {
        this.data = data;
    }

    /**
     * Returns the information of the Message.
     *
     * @return A string with the information of the Message.
     */
    public String getData() {
        return data;
    }

    /**
     * Returns the date creation of the Message.
     *
     * @return A string with the date creation of the Message.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the message to the actual date.
     */
    public void setActualDate() {
        this.date = new Date();
    }
}
