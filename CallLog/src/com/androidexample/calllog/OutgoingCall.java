package com.androidexample.calllog;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class OutgoingCall extends BroadcastReceiver {
	static boolean flag =false;
	static long start_time,end_time;
        @Override
        public void onReceive(Context context, Intent intent) {
        	
        	final Controller aController = (Controller) context.getApplicationContext();
                Bundle bundle = intent.getExtras();
                //Log.i("outgoing", "---OutgoingCall reciever start--");
                if(null == bundle)
                        return;
                String phonenumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
                aController.setPhoneNumber(phonenumber);
                aController.settype("2");
        }
}