package com.example.smsapp;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Send extends ActionBarActivity {
	EditText phno;
	EditText message;
	Button send;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String sel=MainActivity.name;
		Toast.makeText(getApplicationContext(), ""+sel,Toast.LENGTH_LONG).show();
		setContentView(R.layout.send_activity);
		phno=(EditText)findViewById(R.id.phoneNumber);
		message=(EditText)findViewById(R.id.Message);
		send=(Button)findViewById(R.id.sent);

		//String phoneNumber=phno.getText().toString();
		//String messagecondent=message.getText().toString();
		//		send.setOnClickListener(new View.OnClickListener() {
		//
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				sendSmsMessage();
		//			}
		//		});
		//
		//
		//	}
		//
		//	protected void sendSmsMessage(){
		//		String phoneNumber=phno.getText().toString();
		//		String messagecondent=message.getText().toString();
		//		
		//		try {
		//SmsManager smsManager=SmsManager.getDefault();
				//smsManager.sendTextMessage(phoneNumber, null, messagecondent, null,null);
		//			//smsManager.sendTextMessage(destinationAddress, scAddress, text, sentIntent, deliveryIntent);
		
		//			Toast.makeText(this, "sms is send", Toast.LENGTH_SHORT).show();
		//		} catch (Exception e) {
		//			// TODO Auto-generated catch block
		//			Toast.makeText(this, "Sending faild", Toast.LENGTH_SHORT).show();
		//
		//			e.printStackTrace();
		//		}

		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				String strphone = phno.getText().toString();
				String strSMS = message.getText().toString();

				if (strphone.length() > 0 && strSMS.length() > 0) {
					sendSMS(strphone, strSMS);
				} else {
					Toast.makeText(getBaseContext(),
							"plz enter the phone or the sms",
							Toast.LENGTH_SHORT).show();
				}

			}

			private void sendSMS(String phoneNo, String SMS) {
				// TODO Auto-generated method stub

				String SENT = "SMS_SENT";
				String DELIVERED = "SMS_DELIVERED";

				PendingIntent pi = PendingIntent.getBroadcast(getBaseContext(),0, new Intent(SENT), 0);

				PendingIntent piDelevered = PendingIntent.getBroadcast(getBaseContext(), 0, new Intent(DELIVERED), 0);

				registerReceiver(new BroadcastReceiver() {
					@Override
					public void onReceive(Context arg0, Intent arg1) {
						switch (getResultCode()) {
						case Activity.RESULT_OK:
							Toast.makeText(getBaseContext(), "SMS sent",Toast.LENGTH_SHORT).show();
							break;
						case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
							Toast.makeText(getBaseContext(), "Generic failure",Toast.LENGTH_SHORT).show();
							break;
						case SmsManager.RESULT_ERROR_NO_SERVICE:
							Toast.makeText(getBaseContext(), "No service",Toast.LENGTH_SHORT).show();
							break;
						case SmsManager.RESULT_ERROR_NULL_PDU:
							Toast.makeText(getBaseContext(), "Null PDU",Toast.LENGTH_SHORT).show();
							break;
						case SmsManager.RESULT_ERROR_RADIO_OFF:
							Toast.makeText(getBaseContext(), "Radio off",Toast.LENGTH_SHORT).show();
							break;
						}
					}
				}, new IntentFilter(SENT));
				registerReceiver(new BroadcastReceiver() {
					@Override
					public void onReceive(Context arg0, Intent arg1) {
						switch (getResultCode()) {
						case Activity.RESULT_OK:
							Toast.makeText(getBaseContext(),"SMS delivered",Toast.LENGTH_SHORT).show();
							break;
						case Activity.RESULT_CANCELED:
							Toast.makeText(getBaseContext(),"SMS not delivered", Toast.LENGTH_SHORT)
							.show();
							break;
						}
					}
				}, new IntentFilter(DELIVERED));
				SmsManager sms = SmsManager.getDefault();
				sms.sendTextMessage(phoneNo, null, SMS, pi, piDelevered);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}

