package com.example.smsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;


public class IncomingSms extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {


		try {
			final Bundle bundle = intent.getExtras();

			if (bundle != null) {

				final Object[] pdusObj = (Object[]) bundle.get("pdus");

				for (int i = 0; i < pdusObj.length; i++) {

					SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
					String phoneNumber = currentMessage.getDisplayOriginatingAddress();

					String senderNum = phoneNumber+"";
					String message = currentMessage.getDisplayMessageBody()+"";

					Toast.makeText(context, "senderNum: "+ senderNum + ", message: " + message, Toast.LENGTH_LONG).show();
				//	MainActivity.name=message ;

				} // end for loop
			} // bundle is null

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}