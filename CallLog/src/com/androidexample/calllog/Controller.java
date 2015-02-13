package com.androidexample.calllog;


import android.app.Application;

public class Controller extends Application {

	
	private final String TAG = "PGC"; // PhoneGuardController
	
	public  String IMEI = "";
	public  String type = "";
	public  String PhoneNumber = "";
	public long start_time=0;
	
	public String getIMEI() {
		return IMEI;
	}
	
	public void setIMEI(String IMEI) {
		 this.IMEI = IMEI;
	}
	
	public String gettype() {
		return type;
	}
	
	public void settype(String type) {
		 this.type = type;
	}
	
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	
	public void setPhoneNumber(String PhoneNumber) {
		 this.PhoneNumber = PhoneNumber;
	}
	
	public long getstart_time() {
		return start_time;
	}
	
	public void setstart_time(long start_time) {
		 this.start_time = start_time;
	}
	
}
