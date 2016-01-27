package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by marcinko on 1/26/16.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 {
    public TweetListTest(){
        super(LonelyTwitterActivity.class);
    }

    public void testAddTweet(){
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("Test Tweet");

        tweets.add(tweet);

        assertTrue(tweets.hasTweet(tweet));
        try{
            tweets.add(tweet);
        }
        catch (IllegalArgumentException e){
        }
        fail(); // if it makes it past the catch, passing the illegalArgumentException, fails

    }

    public void testHasTweet() {
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("Hello");

        assertFalse(tweets.hasTweet(tweet)); //if already has tweet auto fail

        tweets.add(tweet);
        tweets.hasTweet(tweet);

        assertTrue(tweets.hasTweet(tweet));

    }

    public void testRemoveTweet(){
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("Test Tweet");

        tweets.add(tweet);
        tweets.delete(tweet);

        assertFalse(tweets.hasTweet(tweet));
    }

    public void testGetTweet(){
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("Test Tweet");

        tweets.add(tweet);
        Tweet returnedTweet = tweets.getTweet(0);

        assertEquals(returnedTweet.getMessage(),tweet.getMessage());
        assertEquals(returnedTweet.getDate(),tweet.getDate());
    }

    public void testGetTweets(){
        TweetList tweets = new TweetList();
        List<Tweet> testList = tweets.getTweets();
        assertTrue(testList.isEmpty());
        Tweet tweet = new NormalTweet(new Date(280),"Older Tweet");
        Tweet tweet2 = new NormalTweet(new Date(880), "Newer Tweet");
        tweets.add(tweet);
        tweets.add(tweet2);

        testList = tweets.getTweets();
        Date old = testList.get(0).getDate();
        Date newer = testList.get(1).getDate();

        assertTrue(old.before(newer));
    }

    public void testGetCount(){
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("hey");
        Tweet tweet2 = new NormalTweet("hi");
        Tweet tweet3 = new NormalTweet("hello lo");
        Tweet tweet4 = new NormalTweet("whats up");
        assertEquals(tweets.getCount(),0);
        tweets.add(tweet);
        assertEquals(tweets.getCount(), 1);
        tweets.add(tweet2);
        assertEquals(tweets.getCount(), 2);
        tweets.add(tweet3);
        assertEquals(tweets.getCount(), 3);
        tweets.add(tweet4);
        assertEquals(tweets.getCount(),4);
    }


}
