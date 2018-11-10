package com.example.paintingapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button buttonErase;
	private DrawView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new DrawView(this);
		view = (DrawView) findViewById(R.id.view1);
		setContentView(R.layout.activity_main);
		buttonErase = (Button) findViewById(R.id.button1);
		
		
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
