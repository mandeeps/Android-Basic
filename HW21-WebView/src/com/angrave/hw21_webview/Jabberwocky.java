package com.angrave.hw21_webview;

import com.angrave.hw21_webview.R;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;

public class Jabberwocky extends Activity {
	WebView web;
	MediaPlayer music;
	Boolean img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_four);
		web = (WebView) findViewById(R.id.webView1);
		//web.getSettings().setBuiltInZoomControls(true);
		//web.getSettings().setJavaScriptEnabled(true);
		music = MediaPlayer.create(this, R.raw.music);
		web.loadUrl("file:///android_asset/jabberwocky.html");
		img = false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.four, menu);
		return true;
	}
	
	public void openWiki(View v) {
		String url = "https://en.wikipedia.org/wiki/Jaberwocky";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
	
	public void openImg(View v) {
		if (!img) {
			web.loadUrl("file:///android_asset/jabberwocky.jpg");
			img = true;
		}
		else {
			web.loadUrl("file:///android_asset/jabberwocky.html");
			img = false;
		}
	}
	
	public void onResume() {
		Log.e("resume","onResume");
		music.start();
		super.onResume();
	}
	public void onPause() {
		Log.e("pause","onPause!");
		music.pause();
		super.onPause();
	}

}
