package com.androidexample.calllog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;


public class IncomingCall extends BroadcastReceiver {
	Context pcontext;
	String result = "";
	final String servicefunction = "EmailCALL";
	static String incomingNumber = "";
	// InputHandeler inputhan = new InputHandeler(servicefunction);
	static final String ACTION = "android.intent.action.PHONE_STATE";

	static boolean flag =false;
	static long start_time,end_time;
	
	public void onReceive(Context context, Intent intent) {
		DBAdapter.init(context);
		pcontext = context;
		
		final Controller aController = (Controller) context.getApplicationContext();

	try {
		
		
				TelephonyManager tmgr = (TelephonyManager) context
						.getSystemService(Context.TELEPHONY_SERVICE);
				
				
				MyPhoneStateListener PhoneListener = new MyPhoneStateListener(aController);

				tmgr.listen(PhoneListener, PhoneStateListener.LISTEN_CALL_STATE);

		} catch (Exception e) {
			Log.e("Phone Receive Error", " " + e);
		}

	}

	private class MyPhoneStateListener extends PhoneStateListener {
		Controller aController = null;
		MyPhoneStateListener(Controller aController)
		{
			this.aController = aController;
			
		}
		
		public void onCallStateChanged(int state, String incomingNumber) {
			 //Log.d("MyPhoneListener",state+"   incoming no:"+incomingNumber);
            
			if (state == 1) {

				
				aController.setPhoneNumber(incomingNumber);
				aController.settype("1");
				

			}
		}
	}
}