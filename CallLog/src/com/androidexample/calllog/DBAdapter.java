package com.androidexample.calllog;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    
    /******* if debug is set true then it will show all Logcat message ***/
    public static final boolean DEBUG = true;
     
    /********** Logcat TAG ************/
    public static final String LOG_TAG = "DBAdapter";
     
    /************ Table Fields ************/
    public static final String KEY_ID = "_id";
 
    public static final String KEY_IMEI = "imei"; 
    public static final String KEY_NAME = "name";
 
    public static final String KEY_PHONE = "pnumber"; 
    public static final String KEY_DATE = "date_added";
    
    public static final String KEY_TYPE = "type";   
    public static final String KEY_DURATION  = "duration"; 
     
    /************* Database Name ************/
    public static final String DATABASE_NAME = "DB_sqllite";
     
    /**** Database Version (Increase one if want to also upgrade your database) ****/
    public static final int DATABASE_VERSION = 1;// started at 1
 
    /** Table names */
    public static final String USER_TABLE = "tbl_user";
     
    /**** Set all table with comma seperated like USER_TABLE,ABC_TABLE ******/
    private static final String[ ] ALL_TABLES = { USER_TABLE };
     
    /** Create table syntax */
    private static final String USER_CREATE = "create table tbl_user( _id integer primary key autoincrement,imei text not null,name text not null,pnumber   text not null,date_added text not null,type text not null,duration text not null);";  
     
    /********* Used to open database in syncronized way *********/
    private static DataBaseHelper DBHelper = null;
  
    protected DBAdapter() {
     
    }
     
    /********** Initialize database *********/
    public static void init(Context context) {
        if (DBHelper == null) {
            if (DEBUG)
                Log.i("DBAdapter", context.toString());
            DBHelper = new DataBaseHelper(context);
        }
    }
     
  /********** Main Database creation INNER class ********/
    private static class DataBaseHelper extends SQLiteOpenHelper {
        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
 
        @Override
        public void onCreate(SQLiteDatabase db) {
            if (DEBUG)
                Log.i(LOG_TAG, "new create");
            try {
                db.execSQL(USER_CREATE);
                
            } catch (Exception exception) {
                if (DEBUG)
                    Log.i(LOG_TAG, "Exception onCreate() exception");
            }
        }
 
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (DEBUG)
                Log.w(LOG_TAG, "Upgrading database from version" + oldVersion
                        + "to" + newVersion + "...");
 
            for (String table : ALL_TABLES) {
                db.execSQL("DROP TABLE IF EXISTS " + table);
            }
            onCreate(db);
        }
 
    } // Inner class closed
     
     
    /***** Open database for insert,update,delete in syncronized manner ****/
    private static synchronized SQLiteDatabase open() throws SQLException {
        return DBHelper.getWritableDatabase();
    }
 
 
    /************* General functions*************/
     
     
    /*********** Escape string for single quotes (Insert,Update) ********/
    private static String sqlEscapeString(String aString) {
        String aReturn = "";
         
        if (null != aString) {
            //aReturn = aString.replace(", );
            aReturn = DatabaseUtils.sqlEscapeString(aString);
            // Remove the enclosing single quotes ...
            aReturn = aReturn.substring(1, aReturn.length() - 1);
        }
         
        return aReturn;
    }
 
    /********** UnEscape string for single quotes (show data) ************/
    private static String sqlUnEscapeString(String aString) {
         
        String aReturn = "";
         
        if (null != aString) {
            aReturn = aString.replace("\'", "");
        }
         
        return aReturn;
    }
     
   /********* User data functons *********/
 
  public static void addUserData(UserData uData) {
 
        // Open database for Read / Write       
 
         
	  Calendar currentDate = Calendar.getInstance();
	  SimpleDateFormat formatter= 
	  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	  String dateNow = formatter.format(currentDate.getTime());
      	
 		final SQLiteDatabase db = open();
        String name = sqlEscapeString(uData.getName());
        String email = sqlEscapeString(uData.getPhone());
        String imei = sqlEscapeString(uData.getimei());
        
        String type = sqlEscapeString(uData.getType());
        String duration = sqlEscapeString(uData.getDuration());
        
        ContentValues cVal = new ContentValues();  
        cVal.put(KEY_IMEI, imei);
        cVal.put(KEY_NAME, name);
        cVal.put(KEY_PHONE, email);
        cVal.put(KEY_DATE, " "+dateNow);
        cVal.put(KEY_TYPE, type);
        cVal.put(KEY_DURATION, duration);
        
        
        // Insert user values in database
        db.insert(USER_TABLE, null, cVal);     
        db.close(); // Closing database connection
    }
 
 
   // Updating single data
   public static int updateUserData(int id,String duration) {
 
        final SQLiteDatabase db = open();  
  
        ContentValues values = new ContentValues();
         values.put(KEY_DURATION, duration);
        
         
        // updating row
        return db.update(USER_TABLE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }
    
    // Getting single contact
   public static UserData getUserData(int id) {
 
       // Open database for Read / Write
        final SQLiteDatabase db = open();
  
        Cursor cursor = db.query(USER_TABLE, new String[] { KEY_ID,
        		KEY_NAME, KEY_PHONE,KEY_TYPE,KEY_DURATION }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
 
 
        if (cursor != null)
            cursor.moveToFirst();
  
        UserData data = new UserData(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
 
        // return user data
        return data;
    }
  
      // Getting All User data
    public static List<UserData> getAllUserData() {
 
        
 
        // Open database for Read / Write
        final SQLiteDatabase db = open();
        
        List<UserData> contactList = new ArrayList<UserData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + USER_TABLE; 
  
        Cursor cursor = db.rawQuery ( selectQuery, null );
  
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserData data = new UserData();
                data.setID(Integer.parseInt(cursor.getString(0)));
                data.setimei(cursor.getString(1));
                data.setName(cursor.getString(2));
                data.setPhone(cursor.getString(3));
                data.setDate(cursor.getString(4));
                
                data.setType(cursor.getString(5));
                data.setDuration(cursor.getString(6));
    //Log.i("===", "=="+cursor.getString(3));
                // Adding contact to list
                contactList.add(data);
            } while (cursor.moveToNext());
        }
        db.close(); // Closing database connection
        // return user list
        return contactList;
    }
 
 
  
    // Deleting single contact
    public static void deleteUserData(UserData data) {
        final SQLiteDatabase db = open();
        db.delete(USER_TABLE, KEY_ID + " = ?",
                new String[] { String.valueOf(data.getID()) });
        db.close();
    }
  
    // Deleting single contact
    public static void deleteAllUserData() {
        final SQLiteDatabase db = open();
        db.delete(USER_TABLE, null,
                null);
        db.close();
    }
    // Getting dataCount
 
    public static int getUserDataCount() {
 
        final SQLiteDatabase db = open();
 
        String countQuery = "SELECT  * FROM " + USER_TABLE;
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
  
        // return count
        return cursor.getCount();
    }
 
}  // CLASS CLOSED