package com.example.cockalaram;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Create an offset from the current time in which the alarm will go off.
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 5);

		//Create a new PendingIntent and add it to the AlarmManager
		Intent intent = new Intent(this, AlarmReceiverActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this,
				12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager am = 
				(AlarmManager)getSystemService(Activity.ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
				pendingIntent);
	}
}

