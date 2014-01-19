package com.angrave.hw21_webview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class Nasa extends Activity {
	WebView web;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);
		web = (WebView) findViewById(R.id.webView1);
		//web.getSettings().setBuiltInZoomControls(true);
		//web.getSettings().setJavaScriptEnabled(true);
		web.loadUrl("file:///android_asset/uofi-at-nasa.html");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.two, menu);
		return true;
	}

}
