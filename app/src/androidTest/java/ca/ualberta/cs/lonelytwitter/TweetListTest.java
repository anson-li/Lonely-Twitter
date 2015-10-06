package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by ansonli on 2015-09-28.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 implements MyObserver{



    public TweetListTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivityTest.class);
    }

    private Boolean wasNotified = Boolean.FALSE;

    public void testAddObserver() {
        TweetList list = new TweetList();
        list.addObserver(this);
        wasNotified = Boolean.FALSE;
        list.add(new NormalTweet("test"));
        assertTrue(wasNotified);
    }



    public void testAddTweet() {
        TweetList list = new TweetList();
        list.add(new NormalTweet("test"));
    }

    public void testDeleteTweet() {
        TweetList list = new TweetList();
        NormalTweet test = new NormalTweet("test");
        list.add(test);
        list.delete(test);
        assertFalse(list.hastweet(test));
    }

    public void testCountTweets() {
        TweetList list = new TweetList();
        list.add(new NormalTweet("test"));
        list.add(new NormalTweet("rest"));
        list.add(new NormalTweet("mest"));
        int test = list.getcount();
        assertEquals(3, test);
    }

    public void testDuplicate() {
        try {
            TweetList list = new TweetList();
            list.add(new NormalTweet("test"));
            list.add(new NormalTweet("test"));
            fail( "Missing exception" );
        } catch( IllegalArgumentException e ) {
            assertEquals( "You tried adding a duplicate tweet...", e.getMessage() ); // Optionally make sure you get the correct message, too
        }

    }

    public void testgetTweets() {
        TweetList list = new TweetList();
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        list.add(new NormalTweet("mest"));
        list.add(new NormalTweet("lest"));
        tweets = list.gettweets();
        assertNotNull(tweets);
    }

    public void myNotify(Object observable) {
        wasNotified = Boolean.TRUE;
    }

    public void testTweetObserver() {
        TweetList list = new TweetList();
        list.addObserver(this);
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        wasNotified = Boolean.FALSE;
        tweet.setText("different");
        assertTrue(wasNotified);
    }
}