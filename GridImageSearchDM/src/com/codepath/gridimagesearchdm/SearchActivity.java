package com.codepath.gridimagesearchdm;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.net.Uri;
//import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class SearchActivity extends Activity {
	EditText etSearchString;
	GridView gvOutput;
	Button   btnSearch;
	ArrayList<ImageResults> imageResults = new ArrayList<ImageResults>();
	ImageResultArrayAdapter imageAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setupViews();
		imageAdapter = new ImageResultArrayAdapter(this, imageResults);
		gvOutput.setAdapter(imageAdapter);
		gvOutput.setOnItemClickListener( new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int position, long rowId) {
				Intent i = new Intent( getApplicationContext(), ImageDisplayActivity.class);
				ImageResults imageResult = imageResults.get(position);
				i.putExtra("result", imageResult);
				startActivity(i);
			}
		} );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	public void setupViews() {
		// TODO Auto-generated method stub
		etSearchString = (EditText) findViewById(R.id.etSearchString);
		gvOutput       = (GridView) findViewById(R.id.gvOutput);
		btnSearch      = (Button)   findViewById(R.id.btnSearch);
	}

	public String siteFilter = "";
	public String imageSize  = "";
	public String imageColr  = "";
	public String imageType  = "";
	private final int REQUEST_CODE = 20; // nominal call value
	// simple hello from the settings action bar icon
	public void driveIndicate(MenuItem mi) {
		Intent intnt = new Intent(SearchActivity.this, OptionsActivity.class);
		intnt.putExtra("siteFilter", siteFilter);
		intnt.putExtra("imageSize", imageSize);
		intnt.putExtra("imageColr", imageColr);
		intnt.putExtra("imageType", imageType);
		startActivityForResult(intnt, REQUEST_CODE); // brings up the second activity
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  // REQUEST_CODE is defined above
	  if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
	     // Extract name value from result extras
		 siteFilter = data.getExtras().getString("siteFilter");
		 imageSize = data.getExtras().getString("imageSize");
		 imageColr = data.getExtras().getString("imageColr");
		 imageType = data.getExtras().getString("imageType");
	     // Toast the name to display temporarily on screen

	  }
	  Toast.makeText(this, "filter now is " + siteFilter, Toast.LENGTH_SHORT).show();
	} 

	
	public void onImageSearch(View v) {
		String searchTarget = etSearchString.getText().toString();
		Toast.makeText(this, "Searching for " + searchTarget, Toast.LENGTH_LONG).show();
		AsyncHttpClient client = new AsyncHttpClient();
		// "https://ajax.googleapis.com/ajax/services/search/images?"
		client.get( "https://ajax.googleapis.com/ajax/services/search/images?rsz=8&" +
				"start=" + 0 + "&v=1.0&q=" + Uri.encode( searchTarget ),
				new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response ){
				JSONArray imageJsonResults = null;
				try {
					imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
					imageResults.clear();
					imageAdapter.addAll(ImageResults.fromJSONArray(imageJsonResults));
					Log.d("Debug", imageResults.toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		} );
	}

}
