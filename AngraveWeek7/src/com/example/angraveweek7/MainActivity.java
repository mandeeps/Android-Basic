package com.example.angraveweek7;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();
	private static final String KEY_COUNT = "count";
	private SharedPreferences mPrefs;
	private TextView mTextView;

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
		setContentView(mTextView);
		mTextView.setOnClickListener(this);
		//setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		int clickCount = 20 + mPrefs.getInt("clicked", 0);
		// TODO Auto-generated method stub
		mPrefs.edit().putInt("clicked", clickCount).putBoolean("user", true).commit();
		mTextView.setTextColor(0xff00ff00);
		mTextView.setText("Click! " + clickCount);
	}

}
