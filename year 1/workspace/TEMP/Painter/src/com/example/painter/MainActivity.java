package com.example.painter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener {

	private DrawingView drawView;
	private Button erase;
	private ImageButton currPaint, colorgreen, colororange, colorpurple, colorblack, colorwhite, colorblue, colorred,
			coloryellow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		drawView = (DrawingView) findViewById(R.id.drawing);
		LinearLayout paintLayout = (LinearLayout) findViewById(R.id.layoutbottom);
		currPaint = (ImageButton) paintLayout.getChildAt(0);
		currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
	}

	public void paintClicked(View view) {
		if (view != currPaint) {
			ImageButton imgView = (ImageButton) view;
			String color = view.getTag().toString();
			drawView.setColor(color);
			imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
			currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
			currPaint = (ImageButton) view;
		}
	}

	@Override
	public void onClick(View view) {
	}
}
