package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * The Main Activity for a small, personal twitter app to capture notes and commentS
 *  <p> it saves the input tweets in json files </p>
 *  @since 1.2.1
 *  @author marcinko and cmput 301 lab TA's
 * @version 1.2
 */

public class LonelyTwitterActivity extends Activity {
	/*COMMENTED OUR BECAUSE HAS NO LOGICAL PURPOSE
	public ArrayList<String> listOfItems;

	private int calculateTweetSize(){
		//
		return -1;
	}

	private String removeStopWords(String text){
		//
		return " ";
	}

	private void startSecondActivity(Intent intent){
		//
	}

	public String someMethod(String s1, String s2, String s3, String s4){
		return " ";
	}

	public  boolean evaluateOtherActivity(Intent intent){
		int count = 0;
		String S = "";
		String expression1 = "", expression2 = "", expression3 = "", expression4 = "";
		Intent intent1 = new Intent();
		startSecondActivity(intent1);
		String S = someMethod(expression1 + expression2 + expression3 + expression4);
		someMethod(expression1 + expression2 + expression3 + expression4);
		try{
			int a = 1;
			int b = 2;
			if(a<2) {
				someMethod("first choice");
			} else{
				someMethod("second choice");
			}
		}
		return true;
	}
*/
	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;

	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	private ArrayAdapter<Tweet> adapter;

	/**
	 * This Method is called when the activity is first created.
	 * Listens for clicks by entered tweet, or for click to clear all tweets
	 * Stays within same activity, no transitions.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
		Button clearButton = (Button) findViewById(R.id.clear);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				Tweet latestTweet = new NormalTweet(text);
				ImportantTweet latestImportantTweet = new ImportantTweet(text);
				// latestTweet.setMessage(latestTweet.getMessage() + "!");
				tweets.add(latestTweet);
				adapter.notifyDataSetChanged();
				saveInFile();
				//saveInFile(text, new Date(System.currentTimeMillis()));
				//finish();

			}
		});
		clearButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				deleteFile(FILENAME);
				tweets.clear();
				adapter.notifyDataSetChanged();
			}
		});
	}

	/**
	 * On the start of the main activity and this method, the past tweets are
	 * loaded from a json file and a new array adapter for the list is made.
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//String[] tweets = loadFromFile();
		loadFromFile();
		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter);
	}

	/**
	 * Method to load past tweets from json file. Opens the file input stream,
	 * and catches file not found exceptions of IO exception.
	 */
	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			Gson gson = new Gson();

			// Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 01-19 2016
			Type listType = new TypeToken<ArrayList<NormalTweet>>() {}.getType();
			tweets = gson.fromJson(in, listType);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			tweets = new ArrayList<Tweet>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

	/**
	 * Method to  save new tweets in file, called by the exiting of the app
	 */
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					0);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
			Gson gson = new Gson();
			gson.toJson(tweets, out);
			out.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
}