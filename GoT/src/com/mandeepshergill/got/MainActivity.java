package com.mandeepshergill.got;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import com.mandeepshergill.courseraAngrave.R;

public class MainActivity extends Activity {
	MediaPlayer gameOfThrones;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gameOfThrones = MediaPlayer.create(this, R.raw.got);
//		gameOfThrones.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		Log.e("Create","onCreate!");
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void openFB(View v) {
		String url = "http://www.westeros.org";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
	
	public void openBC(View v) {
		String url = "http://www.hbo.com";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);		
	}
	
	public void onResume() {
		Log.e("resume","onResume");
		gameOfThrones.start();
		super.onResume();
	}
	public void onPause() {
		Log.e("pause","onPause!");
		gameOfThrones.pause();
		//gameOfThrones.release();
		super.onPause();
	}

}
