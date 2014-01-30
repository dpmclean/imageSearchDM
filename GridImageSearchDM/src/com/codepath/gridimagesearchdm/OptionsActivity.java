package com.codepath.gridimagesearchdm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class OptionsActivity extends Activity {

	String siteFilter;
	EditText etActive;
	Spinner spnrSize;
	Spinner spnrColr;
	Spinner spnrType;
	public String imageSize  = "";
	public String imageColr  = "";
	public String imageType  = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		siteFilter = getIntent().getStringExtra("siteFilter");
		setContentView(R.layout.activity_options);
		Toast.makeText(this, "what bar? ..." + siteFilter, Toast.LENGTH_LONG).show();
		etActive = (EditText) findViewById(R.id.etSiteMap);
		etActive.setText(siteFilter);
		etActive.setSelection(siteFilter.length());
		
		spnrSize = (Spinner) findViewById(R.id.spnSize);
		imageSize = getIntent().getStringExtra("imageSize");
		setSpinnerToValue(spnrSize, imageSize);
		
		spnrColr = (Spinner) findViewById(R.id.spnColr);
		imageColr = getIntent().getStringExtra("imageColr");
		setSpinnerToValue(spnrColr, imageColr);
		
		spnrType = (Spinner) findViewById(R.id.spnType);
		imageType = getIntent().getStringExtra("imageType");
		setSpinnerToValue(spnrType, imageType);
		
	}

	public void setSpinnerToValue(Spinner spinner, String value) {
	    int index = 0;
	    SpinnerAdapter adapter = spinner.getAdapter();
	    for (int i = 0; i < adapter.getCount(); i++) {
	        if (adapter.getItem(i).equals(value)) {
	            index = i;
	        }
	    }
	    spinner.setSelection(index);
	}

	
	private final int REQUEST_CODE = 20; // nominal return value
	public void onValueLock(View view) {
		Intent i = new Intent(OptionsActivity.this, SearchActivity.class);

		EditText etName = (EditText) findViewById(R.id.etSiteMap);
		i.putExtra( "siteFilter", etName.getText().toString() ); // pass arbitrary data to launched activity
		imageSize = spnrSize.getSelectedItem().toString();
		imageColr = spnrColr.getSelectedItem().toString();
		imageType = spnrType.getSelectedItem().toString();
		i.putExtra( "imageSize", imageSize );
		i.putExtra( "imageColr", imageColr );
		i.putExtra( "imageType", imageType );
		setResult(RESULT_OK, i); // set result code and bundle data for response
		finish(); // closes the activity, pass data to parent
	}
	
	//public void onSubmit(View v) {
		//EditText etName = (EditText) findViewById(R.id.etSiteMap);
		// Prepare data intent 
		//Intent data = new Intent();
		// Pass relevant data back as a result
		//data.putExtra("siteFilter", etName.getText().toString());
		// Activity finished ok, return the data
		//setResult(RESULT_OK, data); // set result code and bundle data for response
		//finish(); // closes the activity, pass data to parent
	//}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options, menu);
		return true;
	}

}
