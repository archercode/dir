package com.example.e_directory;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
	
	private static SharedPrefManager instance;
	private static SharedPreferences sharedpreferences;
	private final static String MyPREFERENCES = "edire"; 
	
	private SharedPrefManager() {
	}
	
	public static SharedPrefManager getInstance(Context ctx){
		if(instance == null){
			instance = new SharedPrefManager();
			sharedpreferences = ctx.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		}
		
		
		return instance;
	}
	

	public void saveString(String key, String value){
		SharedPreferences.Editor editor = sharedpreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	public void saveBoolean(String key, boolean value){
		SharedPreferences.Editor editor = sharedpreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	public void saveUserInfo(UserInfo kind, String value){
		saveString(kind.getKey(), value); 
	}
	
	public boolean isFirstLoad(){
		return sharedpreferences.getBoolean("firstLoad", true);
	}
	
	public void setFirstLoad(boolean value){
		this.saveBoolean("firstLoad",value);
	}
	
	
	

}
