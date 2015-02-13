package com.vipin.demoservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void startService(View v) {

		startService(new Intent(getBaseContext(), MyService.class));
	}
	public void stopService(View v) {
		stopService(new Intent(getBaseContext(), MyService.class));
	}

}
