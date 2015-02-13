package com.androidexample.calllog;

import java.util.ArrayList;
import java.util.Date;

public class CallListValues {
	private  int Pid=0;
	private  String PhoneText="";
	
	
	public void setPid(int Pid)
	{
		this.Pid = Pid;
	}
	public void setPhoneText(String PhoneText)
	{
		this.PhoneText = PhoneText;
	}
	
	public int getPid()
	{
		return this.Pid;
	}
	
	public String getPhoneText()
	{
		return this.PhoneText;
	}
	
}
