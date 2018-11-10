package com.example.testing_paint;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener {
	private DrawingView drawView;
	private ImageButton currPaint, drawBtn;
	private float smallBrush, mediumBrush, largeBrush;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		drawView = (DrawingView) findViewById(R.id.drawing);
		LinearLayout paintLayout = (LinearLayout) findViewById(R.id.paint_colors);
		currPaint = (ImageButton) paintLayout.getChildAt(0);
		currPaint.setImageDrawable(getDrawable(R.drawable.paint_pressed));
		smallBrush = getResources().getInteger(R.integer.small_size);
		mediumBrush = getResources().getInteger(R.integer.medium_size);
		largeBrush = getResources().getInteger(R.integer.large_size);
		drawBtn = (ImageButton) findViewById(R.id.draw_btn);
		drawView.setBrushSize(mediumBrush);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void paintClicked(View view) {
		if (view != currPaint) {
			ImageButton imgView = (ImageButton) view;
			String color = view.getTag().toString();
			drawView.setColor(color);
			imgView.setImageDrawable(getDrawable(R.drawable.paint_pressed));
			currPaint.setImageDrawable(getDrawable(R.drawable.paint));
			currPaint = (ImageButton) view;
		}
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.draw_btn) {
			final Dialog brushDialog = new Dialog(this);
			brushDialog.setTitle("Brush size:");
			ImageButton smallBtn = (ImageButton) brushDialog.findViewById(R.id.small_brush);
			smallBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					drawView.setBrushSize(smallBrush);
					drawView.setLastBrushSize(smallBrush);
					brushDialog.dismiss();
				}
			});
			ImageButton mediumBtn = (ImageButton) brushDialog.findViewById(R.id.medium_brush);
			mediumBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					drawView.setBrushSize(mediumBrush);
					drawView.setLastBrushSize(mediumBrush);
					brushDialog.dismiss();
				}
			});

			ImageButton largeBtn = (ImageButton) brushDialog.findViewById(R.id.large_brush);
			largeBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					drawView.setBrushSize(largeBrush);
					drawView.setLastBrushSize(largeBrush);
					brushDialog.dismiss();
				}
			});
			brushDialog.show();
		}

	}
}
