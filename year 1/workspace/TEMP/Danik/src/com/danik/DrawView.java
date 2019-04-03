package com.danik;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ImageFormat;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;;

public class DrawView extends View {
	// drawing path
	private Path drawPath;
	// drawing and canvas paint
	private Paint drawPaint, canvasPaint;
	// initial color
	private int[] paintColor = { 0xFF660000, 0xff000000, 0xff0000ff, 0xff0000ff, 0xff444444 };
	// canvas
	private Canvas drawCanvas;
	// canvas bitmap
	private Bitmap canvasBitmap;

	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setupDrawing();
		ImageButton button1 = new ImageButton(context);
		button1 = (ImageButton) findViewById(R.id.color1);
	}

	private void setupDrawing() {
		drawPath = new Path();
		drawPaint = new Paint();
		

		drawPaint.setColor(paintColor[1]);
	}

}
