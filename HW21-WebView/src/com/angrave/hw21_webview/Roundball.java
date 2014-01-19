package com.angrave.hw21_webview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class Roundball extends Activity {
	WebView web;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_three);
		web = (WebView) findViewById(R.id.webView1);
		//web.getSettings().setBuiltInZoomControls(true);
		web.getSettings().setJavaScriptEnabled(true);
		web.getSettings().setDomStorageEnabled(true);
		web.loadUrl("file:///android_asset/roundball/roundball.html");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.three, menu);
		return true;
	}

}
