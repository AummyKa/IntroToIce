package com.example.ice_hw;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TimeElapse extends MainActivity{
	TextView MyEvent;
	
	private int year;
	private int month;
	private int day;
	
	//int a = (int) (getDay()-getCurDay());
	
	 
	
	    TextView mTextView;

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	        setContentView(R.layout.time_elapse);
	    	MyEvent = (TextView)findViewById(R.id.MyEvent);
	    	
	    	
	    	SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE); 
			Editor editor = pref.edit();
			String name = pref.getString("myEvent", "");
	    	MyEvent.setText(name);
	    	
	    	String date = pref.getString("Date", "");
	    	
	    	Calendar thatday = dateToCalender(stringToDate(date));
	    	Calendar thisday = Calendar.getInstance(); 
	    	int a = diffCalendar(thisday,thatday);
	          	
	    	//aa-bb-cccc
			/*
			int days = Integer.parseInt(date.substring(0,2));
			int months =Integer.parseInt(date.substring(2,4));
			int years = Integer.parseInt(date.substring(5,4));
			
			*/
	        CountDownTimer mCountDownTimer;
		    long mInitialTime = DateUtils.DAY_IN_MILLIS * (a+1)+ 
		                        DateUtils.HOUR_IN_MILLIS * 0 +
		                        DateUtils.MINUTE_IN_MILLIS * 0 + 
		                        DateUtils.SECOND_IN_MILLIS * 0;
	        
	        
	        
	        
	        //Log.v(myEvent.getText(),"test");
	        
	        Button Back = (Button) findViewById(R.id.Back);
			Back.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					//I_myEvent = myEvent.getText().toString();
					//I_myEvent = "opop";
					//Date_input = Output.getText().toString();
					
					Intent i = new Intent();
					    i.setClass(TimeElapse.this, MainActivity.class);
					    startActivity(i);
				}
			});
	        mTextView = (TextView) findViewById(R.id.Timmer);

	        mCountDownTimer = new CountDownTimer(mInitialTime, 1000) {
	            StringBuilder time = new StringBuilder();
	            @Override
	            public void onFinish() {
	                mTextView.setText(DateUtils.formatElapsedTime(0));
	                //mTextView.setText("Times Up!");
	            }

	            @Override
	            public void onTick(long millisUntilFinished) {
	                time.setLength(0);
	                 // Use days if appropriate
	                if(millisUntilFinished > DateUtils.DAY_IN_MILLIS) {
	                    long count = millisUntilFinished / DateUtils.DAY_IN_MILLIS;
	                    if(count > 1)
	                        time.append(count).append(" days ");
	                    else
	                        time.append(count).append(" day ");

	                    millisUntilFinished %= DateUtils.DAY_IN_MILLIS;
	                }

	                time.append(DateUtils.formatElapsedTime(Math.round(millisUntilFinished / 1000d)));
	                mTextView.setText(time.toString());
	            }
	        }.start();
	    }
	    
	    public static Date stringToDate(String s){
//			String strThatDay = "1985/08/25";
	        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	        Date d = null;
	        try {
	         d = formatter.parse(s);//catch exception
	        } catch (ParseException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	        } 
	        return d; 
		}
	   
	    
	    public static Calendar dateToCalender(Date date){
			Calendar thatDay = Calendar.getInstance();
	        thatDay.setTime(date); //rest is the same...
			return thatDay;
		}
		public static int diffCalendar(Calendar today, Calendar thatday){
			long diff = thatday.getTimeInMillis() - today.getTimeInMillis();
	        long secCount = diff / 1000;
	        double dayCount = secCount/(24*60*60);
	        Log.i("Diff Day ",""+ dayCount);
	        return (int)(Math.ceil(dayCount));
		}
}
