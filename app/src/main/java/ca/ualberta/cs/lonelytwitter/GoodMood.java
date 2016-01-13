package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by marcinko on 1/12/16.
 */
public class GoodMood extends Mood {
    protected Date date;

    @Override
    public String moodModifier(String message) {
        return message + "C:";
    }
}
