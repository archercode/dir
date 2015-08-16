package com.example.db;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	   public static final String DATABASE_NAME = "Hotlines.db";
	   public static final String CONTACTS_TABLE_NAME = "hotlines";
	   public static final String CONTACTS_COLUMN_ID = "id";
	   public static final String CONTACTS_COLUMN_CITY = "city";
	   public static final String CONTACTS_COLUMN_POLICE = "police";
	   public static final String CONTACTS_COLUMN_HOSPITAL = "hospital";
	   public static final String CONTACTS_COLUMN_FIRE = "fire";
	   private HashMap hp;

	   public DBHelper(Context context)
	   {
	      super(context, DATABASE_NAME , null, 1);
	   }

	   @Override
	   public void onCreate(SQLiteDatabase db) {
	      // TODO Auto-generated method stub
	      db.execSQL(
	      "create table hotlines " +
	      "(id integer primary key, city text,police text,hospital text, fire text)"
	      );
	   }

	   @Override
	   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	      // TODO Auto-generated method stub
	      db.execSQL("DROP TABLE IF EXISTS contacts");
	      onCreate(db);
	   }

	   public boolean changeHospital  (String hospital)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentValues = new ContentValues();
	      contentValues.put("hospital", hospital);
	      db.insert("hotlines", null, contentValues);
	      return true;
	   }
	   
	   public Cursor getData(int id){
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
	      return res;
	   }
	   
	   public int numberOfRows(){
	      SQLiteDatabase db = this.getReadableDatabase();
	      int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
	      return numRows;
	   }
	   
	   public boolean updateHospital (Integer id, String hospital)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentValues = new ContentValues();
	      contentValues.put("hospital", hospital);
	      db.update("hotlines", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
	      return true;
	   }

	   public Integer deleteContact (Integer id)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      return db.delete("contacts", 
	      "id = ? ", 
	      new String[] { Integer.toString(id) });
	   }
	   
	   public ArrayList<String> getAllCotacts()
	   {
	      ArrayList<String> array_list = new ArrayList<String>();
	      
	      //hp = new HashMap();
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from hotlines", null );
	      res.moveToFirst();
	      
	      while(res.isAfterLast() == false){
	         array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_CITY)));
	         res.moveToNext();
	      }
	   return array_list;
	   }
	}
