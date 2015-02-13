/**
 * 
 */
package com.androidexample.calllog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CallDetailServer extends Activity {
	 
   ///public static int search = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
     
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asyncronoustask_android_example);  
         
        final Button GetServerData = (Button) findViewById(R.id.GetServerData);
        TextView heading = (TextView) findViewById(R.id.heading);
        try{
		    Bundle b = getIntent().getExtras();
		    VariableValues.Search   = b.getInt("mPosition",1);
		    
	      } 
		catch(Exception e)
		 {
		   Log.i("Jobs","Get Values : "+e.toString()); 
	     } 
        if(VariableValues.Search==1)
		  heading.setText("Today Data...");
        else if(VariableValues.Search==2)
        	heading.setText("One Week Data...");
        else if(VariableValues.Search==3)
        	heading.setText("One Month Data...");
        
        // Server Request URL
        String serverURL = "http://droidindia.com/calllog.php";
        
        //String serverURL = "http://droidindia.com/calllogserver.php";
         
        // Create Object and call AsyncTask execute Method
        new LongOperation().execute(serverURL);
        
        GetServerData.setOnClickListener(new OnClickListener() {
            
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				//Log.i("test", "----"+mPosition);
		    	 Intent i=new Intent(getBaseContext(),CallList.class);
		         i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		         startActivity(i);
			}
        });    
         
    }
     
     
    // Class with extends AsyncTask class
    private class LongOperation  extends AsyncTask<String, Void, Void> {
         
        private final HttpClient Client = new DefaultHttpClient();
        private String Content;
        private String Error = null;
        private ProgressDialog Dialog = new ProgressDialog(CallDetailServer.this);
        String data =""; 
        TextView uiUpdate = (TextView) findViewById(R.id.output);
        int sizeData = 0;  
        
        
        
        protected void onPreExecute() {
            // NOTE: You can call UI Element here.
             
            //UI Element
           // uiUpdate.setText("Output : ");
            Dialog.setMessage("Please wait..");
            Dialog.show();
            
            
            try {
            	
            	if(VariableValues.IMEI == null){
            		
            		TelephonyManager tmgr = (TelephonyManager) 
					getSystemService(Context.TELEPHONY_SERVICE);
			
			//if(VariableValues.IMEI.equals(""))
				  VariableValues.IMEI = tmgr.getDeviceId();
            	}
            	
	            final List<UserData> sched = DBAdapter.getAllUserData();
	            //Log.i("size=", "size-----"+sched.size());
	            sizeData = sched.size();
	            data +="&" + URLEncoder.encode("data", "UTF-8") + "=";
	            data += URLEncoder.encode(""+sizeData, "UTF-8")+"|"+VariableValues.IMEI+"|";
	            data += URLEncoder.encode(""+VariableValues.Search, "UTF-8");
	            
	            if(sizeData>0){
	            	
	            	
				   for (UserData Schedule : sched) {
					    data += "|";
					   
						data +=   URLEncoder.encode(Schedule._imei, "UTF-8")+"^";
						
						data += URLEncoder.encode(Schedule._name, "UTF-8")+"^";
						
						data += URLEncoder.encode(Schedule._phone, "UTF-8")+"^";
						
						data += URLEncoder.encode(Schedule._date, "UTF-8")+"^";
						
						data += URLEncoder.encode(Schedule._type, "UTF-8")+"^";
						
						data += URLEncoder.encode(Schedule._duration, "UTF-8");
						
						
						//Log.i("tag", data);
					
				  }
	            }
	            	
            } catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
            
        }
 
        // Call after onPreExecute method
        protected Void doInBackground(String... urls) {
        	
        	 // String text = "";
              BufferedReader reader=null;
   
	             // Send data 
	            try
	            { 
	              
	                // Defined URL  where to send data
	                URL url = new URL(urls[0]);
	                 
	             // Send POST data request
	   
	              URLConnection conn = url.openConnection(); 
	              conn.setDoOutput(true); 
	              OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream()); 
	              wr.write( data ); 
	              wr.flush(); 
	          
	              // Get the server response 
	               
	            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            
	            // Read Server Response
	            while((line = reader.readLine()) != null)
	                {
	                       // Append server response in string
	                       sb.append(line + "\n");
	                }
	                
	                
	             Content = sb.toString();
	            }
	            catch(Exception ex)
	            {
	            	Error = ex.getMessage();
	            }
	            finally
	            {
	                try
	                {
	     
	                    reader.close();
	                }
	   
	                catch(Exception ex) {}
	            }
        	
            
            return null;
        }
         
        protected void onPostExecute(Void unused) {
            // NOTE: You can call UI Element here.
             
            // Close progress dialog
            Dialog.dismiss();
             
            if (Error != null) {
                 
                uiUpdate.setText("Output : "+Error);
                 
            } else {
                 
            	String OutputData = "";
                JSONObject jsonResponse;
                      
                try {
                      
                     /****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
                     jsonResponse = new JSONObject(Content);
                      
                     /***** Returns the value mapped by name if it exists and is a JSONArray. ***/
                     /*******  Returns null otherwise.  *******/
                     JSONArray jsonMainNode = jsonResponse.optJSONArray("Android");
                      
                     /*********** Process each JSON Node ************/
  
                     int lengthJsonArr = jsonMainNode.length();  
  
                     for(int i=0; i < lengthJsonArr; i++) 
                     {
                         /****** Get Object for each JSON node.***********/
                         JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                          
                         /******* Fetch node values **********/
                         String name       = jsonChildNode.optString("name").toString();
                         String number     = jsonChildNode.optString("number").toString();
                         String date_added = jsonChildNode.optString("date_added").toString();
                         String type       = jsonChildNode.optString("type").toString();
                         String duration   = jsonChildNode.optString("duration").toString();
                          
                        if(number.equals("000000")){
                        	OutputData += " Calls Not Found. " 
                            +"\n\n--------------------------------------------------\n";
                        }
                        else{
                        	String typeValue = "Incomming";
                        	if(type.equals("2"))
                        		typeValue = "Outgoing";
                        	
                         OutputData += " Name 		    : "+ name +" \n "
                                     + "Number 		: "+ number +" \n "
                                     + "Time 				: "+ date_added +" \n " 
                                     + "Type 				: "+ typeValue +" \n "
                                     + "Duration 	    : "+ duration +" \n "
                                     +"--------------------------------------------------\n";
                        }
                         //Log.i("JSON parse", song_name);
                    }
                      
                     /************ Show Output on screen/activity **********/
  
                     uiUpdate.setText( OutputData );
                      
                 } catch (JSONException e) {
          
                     e.printStackTrace();
                 }
  
            	
            	
                //uiUpdate.setText("Output : "+Content);
                DBAdapter.deleteAllUserData();
                 
             }
        }
         
    }
}
