package com.imrokraft.arrayadapterexample;


import java.util.ArrayList;
import java.util.Date;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class UserAdapter extends ArrayAdapter<UserModel>{

	ArrayList<UserModel> usersList;
	Context myContext;


	public UserAdapter(Context context, ArrayList<UserModel> usersList) {
		super(context, R.layout.list_item, usersList);
		this.usersList=usersList;
		this.myContext=context;
	}

	// It gets a View that displays in the drop down popup the data at the specified position
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	// It gets a View that displays the data at the specified position
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);

	}


	private View getCustomView(final int position, View currentRow, ViewGroup parent) {
		if(currentRow == null){
			LayoutInflater mLayoutInflater = LayoutInflater.from(myContext);
			currentRow = mLayoutInflater.inflate(R.layout.list_item, parent, false);
		}

		TextView nameTextView = (TextView) currentRow.findViewById(R.id.txtview_name);
		TextView emailTextView = (TextView) currentRow.findViewById(R.id.txtview_email);
		TextView timeTextView = ((TextView) currentRow.findViewById(R.id.textView1));
		//		ImageView iconImageView = (ImageView) currentRow.findViewById(R.id.imageView_photo);

		nameTextView.setText(usersList.get(position).getName());
		emailTextView.setText(usersList.get(position).getEmail());
		Date dt=new Date();

		int h=24-(dt.getHours());		
		int m=dt.getMinutes();

		timeTextView.setText(h+":"+m+" PM");


		currentRow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {				

				Toast.makeText(getContext(), "clicked   "+position, Toast.LENGTH_SHORT).show();
				MainActivity.oldUserToRemove=usersList.get(position);
				MainActivity.positionToRemove=position;

				Intent myIntent =new Intent(((MainActivity)myContext), EditActivity.class);
				//myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				((MainActivity)myContext).startActivityForResult(myIntent,2);
				return;

			}
		});

		currentRow.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {

				Toast.makeText((MainActivity)myContext, "Long-click", Toast.LENGTH_LONG).show();	

				AlertDialog.Builder builder = new AlertDialog.Builder((MainActivity)myContext);
				builder.setTitle("Title Message");
				builder.setIcon(R.drawable.icon); // lame icon
				builder.setMessage("Do you want Data ! Delete or Not");
				builder.setCancelable(false)	
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {

						usersList.remove(position);
						((MainActivity)myContext).myUserAdapter=new UserAdapter((MainActivity)myContext, usersList );
						((ListView) ((MainActivity)myContext).findViewById(R.id.my_main_listview)).setAdapter(((MainActivity)myContext).myUserAdapter);
						((MainActivity)myContext).myUserAdapter.notifyDataSetChanged();	


					}
				})
				.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {			
						dialog.cancel();
					}
				});

				AlertDialog alertDialog = builder.create();
				builder.show();
				return false;
			}

		});



		return currentRow;
	}


}
