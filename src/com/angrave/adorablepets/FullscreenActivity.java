package com.angrave.adorablepets;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class FullscreenActivity extends Activity {
    Button button1;
    int imageIds[] = {R.drawable.offline1,R.drawable.offline2,R.drawable.offline3,R.drawable.offline4,R.drawable.offline5};
    int currentIndex = 0;
    int count = imageIds.length;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);


        Button changeMebtn = (Button) findViewById(R.id.button1);

        changeMebtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				  ImageView image = (ImageView) findViewById(R.id.imageView1);
	              currentIndex++;
	              if (currentIndex == count) {currentIndex = 0;}
				  image.setImageResource(imageIds[currentIndex]);
			}
        });

    }
}