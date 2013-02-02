package com.example.kidsteaser;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;

public class MainActivity extends Activity {
	
	ImageAdapter imageAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		GridView grid = (GridView) findViewById(R.id.gridview);
		
		imageAdapter = new ImageAdapter(this);
		grid.setAdapter(imageAdapter);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void onNextClick(View view) {
		
		imageAdapter.newQuiz();
		
	}

}
