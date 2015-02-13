package com.androidexample.customdialog;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


public class CustomDialog extends Activity {

	private Button buttonClick;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_dialog_main);

		buttonClick = (Button) findViewById(R.id.buttonClick);

		// add listener to button 
		buttonClick.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// custom dialog
				final Dialog dialog = new Dialog(CustomDialog.this);
				dialog.setContentView(R.layout.dialog);
				dialog.setTitle("Custom Dialog");

				// set the custom dialog components - text, image and button
				TextView text = (TextView) dialog.findViewById(R.id.textDialog);
				text.setText("Custom dialog Android example.");
				ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
				image.setImageResource(R.drawable.image0);

				dialog.show();
				
				Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
				// if button is clicked, close the custom dialog
				declineButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});

				
			}

		});

	}

}