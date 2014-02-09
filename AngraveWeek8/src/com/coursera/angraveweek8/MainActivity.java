package com.coursera.angraveweek8;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private Bitmap mBitmap;
	private Bitmap mPenguin;
	private Paint paint;
	private float x;
	private float y;
	private float vx=1;
	private float vy=1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mPenguin = BitmapFactory.decodeResource(getResources(), R.drawable.penguin);
		mBitmap = Bitmap.createBitmap(4,4,Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(mBitmap);
		paint = new Paint();
		c.drawColor(0xFF808080);
		paint.setColor(0xFF0000FF);
		paint.setStrokeWidth(0);
		//paint.setAntiAlias(true);
		//c.drawLine(0, 0, 3, 3, paint);
		paint.setStyle(Style.STROKE);
		c.drawRect(1,1,3,3, paint);
		
//		ImageView image = new ImageView(this);
//		image.setImageBitmap(b);
		
		View v = new View(this) {

			@Override
			protected void onDraw(Canvas canvas) {
				canvas.drawColor(0xFFFF9900);
				float scaleX = this.getWidth() / ((float)mBitmap.getWidth());
				float scaleY = this.getHeight() / ((float)mBitmap.getWidth());
				canvas.save();
				canvas.scale(scaleX, scaleY);
				canvas.drawBitmap(mBitmap,0,0,null);
				//canvas.scale(1/scaleX, 1/scaleY);
				canvas.restore();
				float angle = SystemClock.uptimeMillis()/10.0f;
				canvas.translate(x, y);
				canvas.rotate(angle, 200, 200);
				paint.setColor(0xffffffff);
				paint.setStyle(Style.FILL_AND_STROKE);
				canvas.drawCircle(mPenguin.getWidth(), mPenguin.getHeight(), mPenguin.getHeight()/2, paint);
				//canvas.scale(100,100);
				
				canvas.drawBitmap(mPenguin, x, y, null);
				//canvas.scale(10,10);
				if (y +2*mPenguin.getHeight() > this.getHeight()) {
					vy = -0.8f * vy;
				} else {
					vy = vy+1;
				}
				//x = x+vx;
			//	y = y+vy;
				//vy = vy+1;
				postInvalidateDelayed(10);
			}
		};
		
		
		
		OnTouchListener onTouch = new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					x = event.getX();
					y = event.getY();
					vy = 0;
					vx = 0;
				}
				return true;
			}
		};
		v.setOnTouchListener(onTouch);
		setContentView(v);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
