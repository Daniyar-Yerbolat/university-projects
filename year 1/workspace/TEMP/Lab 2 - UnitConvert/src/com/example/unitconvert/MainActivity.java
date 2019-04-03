package com.example.unitconvert;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text = (EditText) findViewById(R.id.editText1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void Calculate(View view) {

		RadioButton mileButton = (RadioButton) findViewById(R.id.radio0);
		RadioButton kmhButton = (RadioButton) findViewById(R.id.radio1);
		if (text.getText().length() == 0) {
			Toast.makeText(this, "enter a valid number", Toast.LENGTH_LONG).show();
		} else {
			double inputValue = Double.parseDouble(text.getText().toString());
			if (mileButton.isChecked()) {
				text.setText(String.valueOf(convertToMiles(inputValue)));
				mileButton.setChecked(false);
				kmhButton.setChecked(true);
			} else {
				text.setText(String.valueOf(convertToKmh(inputValue)));
				kmhButton.setChecked(false);
				mileButton.setChecked(true);
			}
		}
	}

	private double convertToMiles(double inputValue) {
		return (inputValue * 1.609344);
	}

	private double convertToKmh(double inputValue) {
		return (inputValue * 0.621372);
	}
}
