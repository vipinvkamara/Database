package com.imrokraft.arrayadapterexample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;


public class EditActivity extends ActionBarActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_user);

		((EditText)findViewById(R.id.add_new_user_name)).setText(MainActivity.oldUserToRemove.getName());
		((EditText)findViewById(R.id.add_new_user_email)).setText(MainActivity.oldUserToRemove.getEmail());

	}

	public void createNewUser(View v) {
		String name="", email="";
		name=((EditText)findViewById(R.id.add_new_user_name)).getText().toString();
		email=((EditText)findViewById(R.id.add_new_user_email)).getText().toString();

		MainActivity.newUserToAdd= new UserModel(name, email);
		setResult(RESULT_OK);
		finish();
	}
}
