package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity {

	ArrayList<Tweet> tweets; // model
	ArrayAdapter<Tweet> adapter; // view
 	private static final String FILENAME = "file.sav"; // model
	private EditText bodyText; // view
	private ListView oldTweetsList; // view


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) { // view

		super.onCreate(savedInstanceState);  // view
		setContentView(R.layout.main); // view

		bodyText = (EditText) findViewById(R.id.body); // view
		Button saveButton = (Button) findViewById(R.id.save); // view
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList); // view

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) { // controller
				setResult(RESULT_OK); // controller
				String text = bodyText.getText().toString(); // view
				tweets.add(new NormalTweet(text)); // model
				saveInFile(); // model
				bodyText.setText(""); // controller
				adapter.notifyDataSetChanged(); // view
			}
		});

		Button clearButton = (Button) findViewById(R.id.clear); // view
		clearButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) { //controller
				//bodyText = (EditText) findViewById(R.id.body);
				bodyText.setText(""); //controller
			}
		});

		Button viewButton = (Button) findViewById(R.id.view); //view
		viewButton.setOnClickListener(new android.view.View.OnClickListener() { //controller
			public void onClick(android.view.View v) { // controller
				Intent myIntent = new Intent(v.getContext(), ViewActivity.class); //controller
				startActivity(myIntent); //controller
			}
		});

	}

	@Override
	protected void onStart() { //view
		// TODO Auto-generated method stub
		super.onStart(); //view
		loadFromFile(); //model
		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweets); // controller
		oldTweetsList.setAdapter(adapter); // controller
	}

	private void loadFromFile() { // model
		try {

			// code taken from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html

			FileInputStream fis = openFileInput(FILENAME); //model
			BufferedReader in = new BufferedReader(new InputStreamReader(fis)); //model
			Gson gson = new Gson(); // model
			Type listType = new TypeToken<ArrayList<NormalTweet>>() {}.getType(); //model
			tweets = gson.fromJson(in, listType); //model

		} catch (FileNotFoundException e) { //model
			// TODO Auto-generated catch block
			tweets = new ArrayList<Tweet>(); //model
		} catch (IOException e) { //model
			// TODO Auto-generated catch block
			throw new RuntimeException(); //model
		}
	}
	
	private void saveInFile() { //model
		try {
			FileOutputStream fos = openFileOutput(FILENAME, 0); //model
			OutputStreamWriter writer = new OutputStreamWriter(fos); //model
			Gson gson = new Gson(); //model
			gson.toJson(tweets, writer); //model
			writer.flush(); //model
			fos.close(); //model
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //model
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //model
		}
	}

	public void myNotify(MyObservable observable) { //controller
		adapter.notify(); //controller
	}
}