package com.vipin.demoservice;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
	@Override
	public void onCreate() {
		System.out.println("Service is Created");
		Toast.makeText(getApplicationContext(), "Service is create", Toast.LENGTH_SHORT).show();
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("Service Bounded");
		Toast.makeText(getApplicationContext(), "Service is onBind", Toast.LENGTH_SHORT).show();
		return null;
	}
	@Override
	public void unbindService(ServiceConnection conn) {
		System.out.println("Service unBounded");
		Toast.makeText(getApplicationContext(), "Service is unbindService", Toast.LENGTH_SHORT).show();
		super.unbindService(conn);
	}

	@Override
	public void onDestroy() {
		System.out.println("Service is destrory");
		Toast.makeText(getApplicationContext(), "Service is Destroyed", Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}

}
