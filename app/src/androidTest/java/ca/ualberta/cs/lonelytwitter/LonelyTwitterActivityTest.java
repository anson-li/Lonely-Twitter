package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void testEditTweet() {

        final String tweetText;
        final EditText bodyText;
        final Button saveButton;
        final ListView oldTweetsList;

        LonelyTwitterActivity activity = (LonelyTwitterActivity)getActivity();
        activity.getTweets().clear();


        tweetText = "Hello!";
        bodyText = activity.getBodyText();

        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText(tweetText);
            }
        });
        getInstrumentation().waitForIdleSync();
        saveButton = activity.getSaveButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        oldTweetsList = activity.getOldTweetsList();


        Tweet newestTweet = (Tweet)oldTweetsList.getItemAtPosition(0);
        assertEquals(tweetText, newestTweet.getText());

        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync();

        // test that the tweet editor starts up with the correct tweet

        // test that we can edit a tweet

        // test that we can push an edited tweet

        // test that the modified tweet was saved

        // test that we can push a save button for the edited tweet

        assertTrue(activity.getTweets().get(0).getText().equals(tweetText));
    }
}