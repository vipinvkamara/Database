package com.example.smsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {	
	//public static String name;
	public static String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		name="Select";
		Toast.makeText(getApplicationContext(),""+name,Toast.LENGTH_LONG).show();

	}
	public  void OnSend(View v) {
		startActivity(new Intent(this,Send.class));

	}
	public  void onRecive(View v) {
		startActivity(new Intent(this,Recive.class));

	}
}
