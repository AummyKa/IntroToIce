package com.example.ice_hw;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	 
	public EditText myEvent;
	
	private TextView Output;
	private Button changeDate;
	 
	private int year;
	private int month;
	private int day;
	private Intent intent;
	static String I_myEvent;
	static String Date_input;
	CharSequence edit_text_value;
	
		
	private int That_day;

	static final int DATE_PICKER_ID = 1111; 
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
			myEvent = (EditText) findViewById(R.id.myEvent);
				
			
			//Log.v("hi",edit_text_value.toString());
			
		 	Output = (TextView) findViewById(R.id.output);
		 	//Date_input = Output.getText().toString();
	        changeDate = (Button) findViewById(R.id.ChangeDate);
	 
	        // Get current date by calender
	         
	        final Calendar c = Calendar.getInstance();
	        year  = c.get(Calendar.YEAR);
	        month = c.get(Calendar.MONTH);
	        day   = c.get(Calendar.DAY_OF_MONTH);
	        
	        String a = Integer.toString(day);
	        Log.v(a,"check");
	 
	        // Show current date
	         
	        Output.setText(new StringBuilder()
	                // Month is 0 based, just add 1
	                .append(month + 1).append("-").append(day).append("-")
	                .append(year).append(" "));
	  
	        // Button listener to show date picker dialog
	         
	        changeDate.setOnClickListener(new OnClickListener() {
	 
	            @Override
	            public void onClick(View v) {
	                // On button click show datepicker dialog 
	                showDialog(DATE_PICKER_ID);
	 
	            }
	 
	        });
	        
	        //Select a specific button to bundle it with the action you want
			Button yes = (Button) findViewById(R.id.yes);
			yes.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					I_myEvent = myEvent.getText().toString();
					SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE); 
					Editor editor = pref.edit();
					editor.putString("myEvent",I_myEvent);
					
					Date_input = Output.getText().toString();
					editor.putString("Date",Date_input);
					Log.i("test",Date_input);
					
					
					editor.commit();
					//I_myEvent = "opop";
					
				
									
					Intent i = new Intent();
					    i.setClass(MainActivity.this, TimeElapse.class);
					    startActivity(i);
				}

			});
	 }
	 
	
	 
	
	 
	 public String getEvent(){
		 return I_myEvent;
	 }
	 
	 public String getDateInput(){
		 return Date_input;
	 }
	
	 public int getMonth(){
		int That_month = month+1;
		return That_month;
		 
	 }
	 
	 public int getYear(){
		 int That_year = year;
		 return That_year;
	 }
	 
	 public int getDay(){
		 return day;
		 
	 }
	 
	
	 
	 public int getCurYear(){
		 Calendar a = Calendar.getInstance();
		 int This_year = a.YEAR;
		 return This_year;
	 }
	 
	 public long getCurDay(){
		 long a = Calendar.getInstance().getTimeInMillis();			
		 return a;
	 }
	 
	 
	 /*public void addKeyListener() {

			// get edittext component
			
			
			// add a keylistener to monitor the keaybord avitvity...
			myEvent.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {

				// if the users pressed a button and that button was "0"
				if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_0)) {

					// display the input text....
					Toast.makeText(MainActivity.this,myEvent.getText(), Toast.LENGTH_LONG).show();
					return true;

			    // if the users pressed a button and that button was "9"
				} else if ((event.getAction() == KeyEvent.ACTION_DOWN) 	&& (keyCode == KeyEvent.KEYCODE_9)) {

					// display message
					Toast.makeText(MainActivity.this, "Number 9 is pressed!", Toast.LENGTH_LONG).show();
					return true;
				}

				return false;
			}
		 });
			
			
		
		}*/
	 
	 @Override
	    protected Dialog onCreateDialog(int id) {
	        switch (id) {
	        case DATE_PICKER_ID:
	             
	            // open datepicker dialog. 
	            // set date picker for current date 
	            // add pickerListener listner to date picker
	            return new DatePickerDialog(this, pickerListener, year, month,day);
	        }
	        return null;
	    }
	 
	    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {
	 
	        // when dialog box is closed, below method will be called.
	        @Override
	        public void onDateSet(DatePicker view, int selectedYear,
	                int selectedMonth, int selectedDay) {
	             
	            year  = selectedYear;
	            month = selectedMonth;
	            day   = selectedDay;
	 
	            // Show selected date 
	            Output.setText(new StringBuilder().append(day)
	                    .append("-").append(month+1).append("-").append(year)
	                    .append(" "));
	     
	           }
	        };
}