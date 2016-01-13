package ca.ualberta.cs.lonelytwitter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by marcinko on 1/12/16.
 */
public class tweet {
    private Date date;
    private String message;
    private Boolean isImportant;
    private ArrayList<Mood> moodList;

    public tweet(Date date, String message) {
        this.date = date;
        this.message = message;
    }

    public tweet(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) throws tweetTooLongException {
        if (message.length() > 140) {
            throw new tweetTooLongException();
        }
        this.message = message;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
