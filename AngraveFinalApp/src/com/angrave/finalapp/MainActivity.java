package com.angrave.finalapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextPaint;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private Bitmap bmp;
	private Canvas c;
	private Bitmap grumpy;
	private TextPaint tp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		tp = new TextPaint();		
		tp.setTextSize(46);
		tp.setShadowLayer(30, 0, 0, Color.WHITE);
		tp.setColor(Color.BLACK);
		tp.setTextAlign(Align.CENTER);
		grumpy = BitmapFactory.decodeResource(getResources(), R.raw.grumpy);
		bmp = Bitmap.createBitmap(grumpy.getWidth(), grumpy.getHeight(), Bitmap.Config.ARGB_8888);
		
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void writeText(View view) {
		c = new Canvas(bmp);
		c.drawBitmap(grumpy, 0, 0, null);
		
		String text1 = ((EditText) findViewById(R.id.editText1)).getText().toString();
		String text2 = ((EditText) findViewById(R.id.editText2)).getText().toString();
		
		// Draw text several times because I don't know how
		// to increase opacity of text shadowing to improve legibility
		c.drawText(text1, c.getWidth()/2, 50, tp);
		c.drawText(text2, c.getWidth()/2, bmp.getHeight() -20, tp);
		c.drawText(text1, c.getWidth()/2, 50, tp);
		c.drawText(text2, c.getWidth()/2, bmp.getHeight() -20, tp);
		c.drawText(text1, c.getWidth()/2, 50, tp);
		c.drawText(text2, c.getWidth()/2, bmp.getHeight() -20, tp);
		
		((ImageView) findViewById(R.id.imageView1)).setImageBitmap(bmp);
	}
	
	public void shareMeme(View view) throws IOException {
		File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		path.mkdirs();
		String filename = "GrumpyCat_" + System.currentTimeMillis() + ".jpg";
		File file = new File(path, filename);
		FileOutputStream stream = null;
		
		try {
			stream = new FileOutputStream(file);
			bmp.compress(CompressFormat.JPEG, 85, stream);
			stream.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		Uri uri = Uri.fromFile(file);
		Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		intent.setData(uri);
		sendBroadcast(intent);

		Intent share = new Intent(Intent.ACTION_SEND);
		share.setType("image/jpeg");
		share.putExtra(Intent.EXTRA_STREAM, uri);
		startActivity(Intent.createChooser(share, "Share With..."));
	}

}
