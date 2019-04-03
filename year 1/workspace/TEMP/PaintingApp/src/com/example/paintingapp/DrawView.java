package com.example.paintingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

public class DrawView extends View {
	private Paint brush = new Paint();
	private Path path = new Path();
	private Button erase;
	private int brushColor = Color.BLUE;
	private float brushSize;
	private Bitmap canvasBitmap;
	private Canvas drawCanvas;

	public DrawView(Context context) {
		super(context);
		brush.setStrokeWidth(brushSize);
		brush.setAntiAlias(true);
		brush.setStrokeJoin(Paint.Join.ROUND);
		brush.setStyle(Paint.Style.STROKE);
		brush.setColor(brushColor);
		eraseAll();

	}

	public void eraseAll() {
		erase.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// reset the path
				path.reset();
				// invalidate the view
				postInvalidate();

			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float pointX = event.getX();
		float pointY = event.getY();

		// Checks for the event that occurs
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(pointX, pointY);

			return true;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(pointX, pointY);
			break;
		case MotionEvent.ACTION_UP:
			path.reset();
			break;
		default:
			return false;
		}
		invalidate();
		return true;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		drawCanvas = new Canvas(canvasBitmap);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawPath(path, brush);
	}

}
