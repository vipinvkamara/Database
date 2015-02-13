package com.androidexample.calllog;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

///import org.ksoap2.serialization.SoapObject;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CallList extends Activity {
    
    ListView list;
    CallListData adapter;
    AtomicBoolean isRunning = new AtomicBoolean(false);
    public static String urlData = "";
    
    public static ArrayList<CallListValues> SheduleValuesArr = new ArrayList<CallListValues>();
    public static CallList SheduleActivity = null;
    public static CallList SheduleInstance = null;
    Resources res=null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	DBAdapter.init(this);
        super.onCreate(savedInstanceState);
        SheduleActivity = this;
        SheduleInstance=this;
        setContentView(R.layout.shedules);
        refreshJobs();
      /*******************WEEKLY DATA**********************/
     // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        //DBAdapter.addUserData(new UserData("Ravi", "9100000000"));
        
        				
    }
    
    public void onStopThread() {
		isRunning.set(false);
		Log.i("Activation", "stop");
	}
    
    @Override
    public void onDestroy()
    {
        //adapter.imageLoader.stopThread();
    	try{
    	if(list!=null)
         list.setAdapter(null);
    	}catch(Exception e){}	
        super.onDestroy();
    }
    /*
    public OnClickListener listener=new OnClickListener(){
        @Override
        public void onClick(View arg0) {
        	Intent i=new Intent(getBaseContext(),Outbox.class);
			startActivity(i);
	        finish();
        }
    };*/
    
    
    public void onItemClick(int mPosition,int type)
    {
    	 CallListValues tempValues=null;
    	 tempValues = (CallListValues) SheduleValuesArr.get(mPosition);
    	 
    	 mPosition =  mPosition + 1;
    	 
    	 //Log.i("test", "----"+mPosition);
    	 Intent i=new Intent(getBaseContext(),CallDetailServer.class);
         i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         Bundle b = new Bundle();
         b.putInt("mPosition", mPosition);
         
 		i.putExtras(b);
    	startActivity(i);
    }
    
    
    public void refreshJobs()
    {
    	
    	try{  	

			    SheduleValuesArr.clear();
			    CallListValues values = new CallListValues();
				values.setPid(1);
				values.setPhoneText("Today Call Log");
				
				SheduleValuesArr.add(values);
				
				CallListValues values1 = new CallListValues();
				values1.setPid(2);
				values1.setPhoneText("Last 7 Days Call Log");
				
				SheduleValuesArr.add(values1);
				
				CallListValues values2 = new CallListValues();
				values2.setPid(3);
				values2.setPhoneText("One Month Call Log");
				
				SheduleValuesArr.add(values2);
			
		    
		    Log.i("Jobs","=== :"+SheduleValuesArr.size());
		   //if(InboxValuesArr.size()>0)
		   {
		    res =getResources();
	        list=(ListView)findViewById(R.id.list);
	        adapter=new CallListData(SheduleActivity, SheduleValuesArr,res);
	        list.setAdapter(adapter);
	        
	       
	       }
		}catch(Exception e){
			Log.e("Animation", "Exception : "+e);
		}


    }
    
    
	 public void parseServerJobs(final String response,String UserNameValue,String PasswordValue) throws ParserConfigurationException, SAXException, IOException
		{
		 

		}

	 public final boolean isInternetOn() {
			ConnectivityManager connec =  (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
			// ARE WE CONNECTED TO THE NET
			if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
			     connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
			// MESSAGE TO SCREEN FOR TESTING (IF REQ)
			//Toast.makeText(this, " connected ", Toast.LENGTH_SHORT).show();
				//connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
				//connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
			return true;
			} else if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||  connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
			  //System.out.println(“Not Connected”);
				//Toast.makeText(this, " Not connected ", Toast.LENGTH_SHORT).show();
			  return false;
			}
			return false;
			}
 
	 
	 public void GetText(String urlValue,String UserNameValue,String PasswordValue) throws UnsupportedEncodingException
	    {
	    }
	 
	 
	
	 
}


