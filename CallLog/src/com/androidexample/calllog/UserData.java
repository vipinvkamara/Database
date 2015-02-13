package com.androidexample.calllog;

import java.util.ArrayList;
import java.util.Date;

public  class UserData {
	 
    //private variables
    int _id;
    String _name;
    String _phone;
    String _imei;
    String _date;
    String _type;
    String _duration;
 
      // Empty constructor
      public UserData(){
 
       }


   // constructor
   public UserData(int id, String name, String phone,String imei,String type,String duration){
       this._id = id;
       this._name = name;
       this._phone = phone;
       this._imei = imei;
       
       this._type = type;
       this._duration = duration;
       
   }
 
   


   // getting ID
   public int getID(){
       return this._id;
   }
 
   // setting id
   public void setID(int id){
       this._id = id;
   }
 
   // getting name
   public String getName(){
       return this._name;
   }
 
   // setting name
   public void setName(String name){
       this._name = name;
   }
 
   // getting email
   public String getPhone(){
       return this._phone;
   }
 
   // setting email
   public void setPhone(String phone){
       this._phone = phone;
   }
   
// getting imei
   public String getimei(){
       return this._imei;
   }
 
   // setting imei
   public void setimei(String imei){
       this._imei = imei;
   }
   
// getting imei
   public String getDate(){
       return this._date;
   }
 
   // setting imei
   public void setDate(String date){
       this._date = date;
   }
   
   
   public String getType(){
       return this._type;
   }
 
   // setting imei
   public void setType(String type){
       this._type = type;
   }
   
   
   public String getDuration(){
       return this._duration;
   }
 
   // setting imei
   public void setDuration(String duration){
       this._duration = duration;
   }
   
   
   

   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
       return "UserInfo [name=" + _name + ", email=" + _phone + "]";
   }

}