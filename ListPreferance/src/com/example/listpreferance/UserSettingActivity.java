package com.example.listpreferance;


import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.appcompat.R;

public class UserSettingActivity extends PreferenceActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.layout.setting);
	}
}
