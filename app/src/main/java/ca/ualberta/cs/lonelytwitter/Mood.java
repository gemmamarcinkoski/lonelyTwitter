package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by marcinko on 1/12/16.
 * Small abstract class to edit time stamped dates to hold mood type.
 */
public abstract class Mood {
    private Date date;

    public Mood(Date date) {
        this.date = date;
    }

    public Mood() {
        this.date = new Date();
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public abstract String moodModifier(String message);


}
