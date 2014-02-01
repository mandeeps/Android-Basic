package com.example.angraveweek7;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private static final int REQUEST_CODE = 1;
	private static final String TAG = MainActivity.class.getSimpleName();
	private static final String KEY_COUNT = "count";
	private SharedPreferences mPrefs;
	private TextView mTextView;
	private Bitmap mBitmap;
	private Canvas mCanvas;
	private ImageView mImageView;
	private Paint mPaint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mPrefs = getPreferences(MODE_PRIVATE);
		int count = mPrefs.getInt(KEY_COUNT, 0);
		Log.d(TAG, "Count is " + count);
		count = count + 1;
		Editor editor = mPrefs.edit();
		editor.putInt(KEY_COUNT, count);
		editor.commit();
		mTextView = new TextView(this);
		mTextView.setTextSize(80);
		mTextView.setText("Count is " + count);
		//setContentView(mTextView);
		mTextView.setOnClickListener(this);
		
		mBitmap = Bitmap.createBitmap(480, 600, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		mCanvas.drawColor(0xffff6600);
		mPaint = new Paint();
		mPaint.setColor(0xff000099);
		mPaint.setStrokeWidth(10);
		mCanvas.drawLine(0, 0, 480, 600, mPaint);
		mImageView = new ImageView(this);
		mImageView.setImageBitmap(mBitmap);
		//setContentView(mImageView);
		
		
		setContentView(R.layout.activity_main);
		OnClickListener listener = new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType("image/*");
				startActivityForResult(Intent.createChooser(intent, "Select..."), REQUEST_CODE);
				//startActivity(intent);
				
			}
		};
		findViewById(R.id.button1).setOnClickListener(listener );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
			Uri uri = data.getData();
			Toast.makeText(getApplicationContext(), uri.toString(), Toast.LENGTH_LONG).show();
			
			try {
				InputStream stream = getContentResolver().openInputStream(uri);
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inJustDecodeBounds = true;
				mBitmap = BitmapFactory.decodeStream(stream,null,options);
				int w = options.outWidth;
				int h = options.outHeight;
				Log.d(TAG, "Decoding bitmap" + w + "x" + h);
				stream.close();
				
				int displayW = getResources().getDisplayMetrics().widthPixels;
				int displayH = getResources().getDisplayMetrics().heightPixels;
				
				int sample = 1;
				while (w / sample > displayW * sample || h / sample > displayH * sample) {
					sample = sample * 2;
				}
				options.inJustDecodeBounds = false;
				options.inSampleSize = sample;
				stream = getContentResolver().openInputStream(uri);
				mBitmap = BitmapFactory.decodeStream(stream, null, options);
				stream.close();
				
				ImageView v = (ImageView) findViewById(R.id.imageView1);
				v.setImageBitmap(mBitmap);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				Log.e("TAG", "exception in try/catch");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View arg0) {
		//SystemClock.sleep(2000);
		Runnable add20 = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int clickCount = 20 + mPrefs.getInt("clicked", 0);
				// TODO Auto-generated method stub
				mPrefs.edit().putInt("clicked", clickCount).putBoolean("user", true).commit();
				mTextView.setTextColor(0xff00ff00);
				mTextView.setText("Click! " + clickCount);		
			}
		};
		
		mTextView.postDelayed(add20, 1000);
		

	}


}
