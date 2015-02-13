package com.androidexample.calllog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;


public class CallDurationReceiver extends BroadcastReceiver {
	static boolean flag =false;
	static long start_time,end_time;    
	@Override
	    public void onReceive(Context arg0, Intent intent) {
		
		final Controller aController = (Controller) arg0.getApplicationContext();
		
	        String action = intent.getAction();
	        if(action.equalsIgnoreCase("android.intent.action.PHONE_STATE")){
	        	
	        	//Log.i("CallDuration", "call satate : "+intent.getStringExtra(TelephonyManager.EXTRA_STATE));
	        	
	            if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
	                                TelephonyManager.EXTRA_STATE_OFFHOOK)) {

	            	aController.setstart_time(System.currentTimeMillis());
	                Log.i("CallDuration", "---CallDuration start--"+aController.getstart_time());
	            }         
	            if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
	                    TelephonyManager.EXTRA_STATE_IDLE)) {
	                end_time=System.currentTimeMillis();
	 //Total time talked =
	                long total_time = end_time-aController.getstart_time();
	                //Store total_time somewhere or pass it to an Activity using intent
	                
	                Log.i("CallDuration", "---CallDuration end--"+end_time+"--Total--"+total_time);

	                int seconds = (int) (total_time / 1000) % 60 ;
	                int minutes = (int) ((total_time / (1000*60)) % 60);
	                int hours   = (int) ((total_time / (1000*60*60)) % 24);
	                
	                String duration = "";
	                if(hours>0)
	                	duration += hours+":"; 
	                //duration += minutes+":";
	                if(minutes < 10)
	                	duration += "0"+minutes+":";
	                else
	                	duration += ""+minutes+":";
	                
	                if(seconds < 10)
	                	duration += "0"+seconds;
	                else
	                	duration += ""+seconds;
	                
	                Log.i("CallDuration", hours+":"+minutes+":"+seconds+" PhoneNumber:"+aController.getPhoneNumber()+" type:"+aController.gettype());
	                TelephonyManager tmgr = (TelephonyManager) arg0
					.getSystemService(Context.TELEPHONY_SERVICE);
	                
	                String IMEI = tmgr.getDeviceId();
	                	
	                UserData uData = new UserData(1,"New",""+aController.getPhoneNumber(),IMEI,aController.gettype(),duration);
					DBAdapter.addUserData(uData); 
	}    

 }   
}
}