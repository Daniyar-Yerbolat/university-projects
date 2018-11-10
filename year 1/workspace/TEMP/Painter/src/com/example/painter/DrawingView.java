package com.example.painter;

import com.example.painter.R.id;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DrawingView extends View {

	// drawing path
	private Path path;
	// drawing and canvas paint
	private Paint drawPaint, canvasPaint;
	// initial color
	private int paintColor = 0xFF660000;
	// canvas
	private Canvas drawCanvas;
	// canvas bitmap
	private Bitmap canvasBitmap;
	private float brushSize;

	private Button erase = (Button) findViewById(R.id.erasebutton);
	

	public DrawingView(Context context) {
		super(context);
		setupDrawing();
	}

	// setup drawing
	private void setupDrawing() {

		// prepare for drawing and setup paint stroke properties
		brushSize = getResources().getInteger(R.integer.medium_size);
		path = new Path();
		drawPaint = new Paint();
		drawPaint.setColor(paintColor);
		drawPaint.setAntiAlias(true);
		drawPaint.setStrokeWidth(brushSize);
		drawPaint.setStyle(Paint.Style.STROKE);
		drawPaint.setStrokeJoin(Paint.Join.ROUND);
		drawPaint.setStrokeCap(Paint.Cap.ROUND);
		canvasPaint = new Paint(Paint.DITHER_FLAG);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		drawCanvas = new Canvas(canvasBitmap);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
		canvas.drawPath(path, drawPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float touchX = event.getX();
		float touchY = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(touchX, touchY);
			break;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(touchX, touchY);
			break;
		case MotionEvent.ACTION_UP:
			path.lineTo(touchX, touchY);
			drawCanvas.drawPath(path, drawPaint);
			path.reset();
			break;
		default:
			return false;
		}
		invalidate();
		return true;

	}

	public void setColor(String newColor) {
		if (ImageView.Clicked = true)
		ImageView green = (ImageView)findViewById(R.id.greencolor);
		ImageView orange = (ImageView)findViewById(R.id.orangecolor);
		ImageView blue = (ImageView)findViewById(R.id.bluecolor);
		ImageView white = (ImageView)findViewById(R.id.whitecolor);
		ImageView black = (ImageView)findViewById(R.id.blackcolor);
		ImageView green = (ImageView)findViewById(R.id.greencolor);
		ImageView green = (ImageView)findViewById(R.id.greencolor);
		ImageView green = (ImageView)findViewById(R.id.greencolor);
		invalidate();
		paintColor = Color.parseColor(newColor);
		drawPaint.setColor(paintColor);
	}

	public void setBrushSize(float newSize) {
		float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, newSize,
				getResources().getDisplayMetrics());
		brushSize = pixelAmount;
		drawPaint.setStrokeWidth(brushSize);
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
}
