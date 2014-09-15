package com.example.ice_hw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {

	private static final int PROGRESS = 0x1;
	
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		
		final int welcomeScreenDisplay = 2000;
		/** create a thread to show splash up to splash time */
		new Handler().postDelayed(new Runnable() {
	        public void run() {

	            Intent intent = new Intent();
	            intent.setClass(SplashScreen.this, MainActivity.class);

	            SplashScreen.this.startActivity(intent);
	            SplashScreen.this.finish();

	            // transition from splash to main menu
	            overridePendingTransition(R.anim.activityfadein,
	                    R.anim.splashfadeout);

	        }
	    }, welcomeScreenDisplay);
	}



}
