package com.coursera.angraveweek8;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private Bitmap mBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mBitmap = Bitmap.createBitmap(4,4,Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(mBitmap);
		Paint paint = new Paint();
		c.drawColor(0xFF808080);
		paint.setColor(0xFF0000FF);
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
				canvas.scale(scaleX, scaleY);
				canvas.drawBitmap(mBitmap,0,0,null);
			}
		};
		
		setContentView(v);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
