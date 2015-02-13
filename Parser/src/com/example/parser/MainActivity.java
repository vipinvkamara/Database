package com.example.parser;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

	private String url1 = "http://api.openweathermap.org/data/2.5/weather?q=";
	private String url2 = "&mode=xml";
	private EditText location,country,temperature,humidity,pressure;
	private HandleXML obj;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		location = (EditText)findViewById(R.id.editText1);
		country = (EditText)findViewById(R.id.editText2);
		temperature = (EditText)findViewById(R.id.editText3);
		humidity = (EditText)findViewById(R.id.editText4);
		pressure = (EditText)findViewById(R.id.editText5);
	}



	public void OpenWether(View v){
		String url = location.getText().toString();
		String finalUrl = url1 + url + url2;
		country.setText(finalUrl);
		obj = new HandleXML(finalUrl);
		obj.fetchXML();
		while(obj.parsingComplete);
		country.setText(obj.getCountry());
		temperature.setText(obj.getTemperature());
		humidity.setText(obj.getHumidity());
		pressure.setText(obj.getPressure());

	}

}