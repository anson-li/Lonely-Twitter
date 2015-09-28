package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * Created by ansonli on 2015-09-28.
 */
public class TweetList {
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public void add(Tweet tweet) {
        if (!tweets.contains(tweet)) {
            tweets.add(tweet);
        } else {
            throw new IllegalArgumentException("You tried adding a duplicate tweet...");
        }

    }

    public void delete(Tweet tweet) {
        if (tweets.contains(tweet)) {
            tweets.remove(tweet);
        }
    }

    public boolean hastweet(Tweet tweet) {
        if (tweets.contains(tweet)) {
            return true;
        } else {
            return false;
        }
    }

    public int getcount() {
        return tweets.size();
    }

    public ArrayList<Tweet> gettweets() {
        return tweets;
    }
}
