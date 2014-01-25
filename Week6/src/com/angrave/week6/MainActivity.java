package com.angrave.week6;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText mName;
	private EditText mPhone;
	private EditText mEmail;
	private EditText mComments;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mName = (EditText) findViewById(R.id.name);
		mPhone = (EditText) findViewById(R.id.phone);
		mEmail = (EditText) findViewById(R.id.email);
		mComments = (EditText) findViewById(R.id.comments);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void launch(View button) {
		String comments = mComments.getText().toString();
		String email = mEmail.getText().toString();
		String phone = mPhone.getText().toString();
		String name = mName.getText().toString();
		int position = 0;

		try {
			position = email.indexOf("@");
			if (position == -1) {
				Toast.makeText(this.getApplicationContext(), "Invalid email!", Toast.LENGTH_LONG).show();
				mEmail.requestFocus();
				return;
			}
		}
		catch (Exception e) {
			Log.d("exception", e.toString());
		}
		
		String username = email.substring(0, position);
		String thanks = "Thank you " + username + "!";
		
		Toast.makeText(this.getApplicationContext(), thanks, Toast.LENGTH_LONG).show();
		
		Animation anim = AnimationUtils.makeOutAnimation(this, true);
		button.startAnimation(anim);
		button.setVisibility(View.INVISIBLE);
		Toast.makeText(this.getApplicationContext(), R.string.app_name, Toast.LENGTH_LONG).show();
	}

}
