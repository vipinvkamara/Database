package com.imrokraft.arrayadapterexample;

import java.util.ArrayList;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	public static UserModel newUserToAdd;
	public static UserModel oldUserToRemove;
	public static int positionToRemove;
	private static final int NOTIFICATION_ID=1337;
	private static final int NOTIFICATION_DEL_ID=133;

	UserAdapter myUserAdapter;
	ArrayList<UserModel> usersList;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myUserAdapter=new UserAdapter(MainActivity.this, createDummyUserModels());
		((ListView) findViewById(R.id.my_main_listview)).setAdapter(myUserAdapter);
		//		((ListView) findViewById(R.id.my_main_listview)).setOnItemLongClickListener(new OnItemLongClickListener() {
		//
		//			@Override
		//			public boolean onItemLongClick(AdapterView<?> parent, View view,
		//					final int position, long id) {
		//				Toast.makeText(MainActivity.this, "Long-click", Toast.LENGTH_LONG).show();	
		//
		//				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		//				builder.setTitle("Title Message");
		//				builder.setIcon(android.R.drawable.ic_dialog_info); // lame icon
		//				builder.setMessage("Do you want Data ! Delete or Not");
		//				builder.setCancelable(false)	
		//				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
		//					public void onClick(DialogInterface dialog,int id) {
		//						usersList.remove(position);
		//						myUserAdapter=new UserAdapter(MainActivity.this, usersList );
		//						((ListView) findViewById(R.id.my_main_listview)).setAdapter(myUserAdapter);
		//						myUserAdapter.notifyDataSetChanged();	
		//					}
		//				})
		//				.setNegativeButton("No",new DialogInterface.OnClickListener() {
		//					public void onClick(DialogInterface dialog,int id) {			
		//						dialog.cancel();
		//					}
		//				});
		//
		//				AlertDialog alertDialog = builder.create();
		//				builder.show();
		//				return false;
		//			}
		//			
		//		});
	}

	public ArrayList<UserModel> createDummyUserModels(){

		String[] names={"anand","senthil","vipin","praveen","kiran","anoop","vipin","praveen","kiran","anoop"};
		String[] emails={"anandab@gmail.com","senthiljs@gmail.com","vipinkamara@gmail.com","praveen@gmail.com","kiran@gmail.com", "anoop@gmail.com","vipinkamara@gmail.com","praveen@gmail.com","kiran@gmail.com", "anoop@gmail.com"};
		usersList=new ArrayList<UserModel>();
		for (int i = 0; i < emails.length; i++) {
			usersList.add(new UserModel(names[i],emails[i]));
		}
		return usersList;
	}
	public void onClickAdd(View v) {
		startActivityForResult(new Intent(this,AddActivity.class), 1);

	}
	public void OnClear(View v) {
		usersList.clear();	
		myUserAdapter=new UserAdapter(MainActivity.this, usersList );
		((ListView) findViewById(R.id.my_main_listview)).setAdapter(myUserAdapter);
		myUserAdapter.notifyDataSetChanged();	
		Toast.makeText(getApplicationContext(), "User Data is Clear", Toast.LENGTH_SHORT).show();		
	}

	@Override
	protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
		super.onActivityResult(requestCode, responseCode, intent);
		if(responseCode==RESULT_OK){
			if((requestCode==1) && (newUserToAdd!=null)){
				usersList.add(newUserToAdd);
				myUserAdapter=new UserAdapter(MainActivity.this, usersList );
				((ListView) findViewById(R.id.my_main_listview)).setAdapter(myUserAdapter);
				myUserAdapter.notifyDataSetChanged();	
				Toast.makeText(getApplicationContext(), "New User added", Toast.LENGTH_SHORT).show();
				sendAddNotification();
			}else if((requestCode==2) ){

				//usersList.remove(oldUserToRemove);
				usersList.remove(positionToRemove);
				usersList.add(positionToRemove, newUserToAdd);
				myUserAdapter=new UserAdapter(MainActivity.this, usersList );
				((ListView) findViewById(R.id.my_main_listview)).setAdapter(myUserAdapter);
				myUserAdapter.notifyDataSetChanged();	
				Toast.makeText(getApplicationContext(), "User edited", Toast.LENGTH_SHORT).show();
				dataDeleteNotification();

			}
		}
	}
	@SuppressWarnings("deprecation")
	protected void sendAddNotification() {
		//Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
		Intent intent = new Intent(this, AddActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);		


		Toast.makeText(getApplicationContext(), "data is display", Toast.LENGTH_SHORT).show();
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		builder.setSmallIcon(R.drawable.ic_launcher);


		// Set the intent that will fire when the user taps the notification.
		builder.setContentIntent(pendingIntent);

		// Set the notification to auto-cancel. This means that the notification will disappear
		// after the user taps it, rather than remaining until it's explicitly dismissed.
		builder.setAutoCancel(true);
		builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
		builder.setContentTitle("Add New Data");
		builder.setContentText("Time to See  notifications!");
		
		builder.setNumber(NOTIFICATION_ID);
		// END_INCLUDE (build_notification)
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(NOTIFICATION_ID, builder.build());

	}


	@SuppressWarnings("deprecation")
	protected void dataDeleteNotification(){

		//int NOTIFICATIONS_ID=13;
		Intent intent = new Intent();
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);		


		Toast.makeText(getApplicationContext(), "data is display", Toast.LENGTH_SHORT).show();
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		builder.setSmallIcon(R.drawable.ic_launcher);


		// Set the intent that will fire when the user taps the notification.
		builder.setContentIntent(pendingIntent);

		// Set the notification to auto-cancel. This means that the notification will disappear
		// after the user taps it, rather than remaining until it's explicitly dismissed.
		builder.setAutoCancel(true);
		builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
		builder.setContentTitle("User Edit & Add Data");
		builder.setContentText("Time to See  notifications!");
		
		builder.setNumber(NOTIFICATION_DEL_ID);
		// END_INCLUDE (build_notification)
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(NOTIFICATION_DEL_ID, builder.build());
		
	}

}
