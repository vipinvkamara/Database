package com.example.texttospeach;

import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {


	TextToSpeech speech;
	private EditText write;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		write = (EditText)findViewById(R.id.editText1);		
		Intent intent = getIntent();
		String message = intent.getStringExtra("msg");
		write.setText(message);

		speech=new TextToSpeech(getApplicationContext(),new TextToSpeech.OnInitListener() {
			@Override
			public void onInit(int status) {
				if(status != TextToSpeech.ERROR){
					speech.setLanguage(Locale.UK);
				}				
			}
		});
		speech.speak(message, TextToSpeech.QUEUE_FLUSH, null);

	}	

	@Override
	public void onPause(){
		if(speech !=null){
			speech.stop();
			speech.shutdown();
		}
		super.onPause();
	}

	@SuppressWarnings("deprecation")
	public void speakText(View view){
		while(true){

			String toSpeak = write.getText().toString();
			Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
			speech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

		}
	}

}



