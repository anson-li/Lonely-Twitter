package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Observer;

/**
 * Created by ansonli on 2015-09-28.
 */
public class TweetList implements MyObservable, MyObserver {
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    private volatile ArrayList<MyObserver> observers = new ArrayList<MyObserver>();

    public void add(Tweet tweet) {
        if (!tweets.contains(tweet)) {
            tweets.add(tweet);
            tweet.addObserver(this);
            notifyAllObservers();
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

    public void addObserver(MyObserver observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (MyObserver observer : observers) {
            observer.myNotify(this);
        }
    }

    public void myNotify(MyObservable observable) {
        notifyAllObservers();
    }

}
