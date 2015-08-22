
package com.example.e_directory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;

public class UserInfoActivity extends Activity implements OnFocusChangeListener, OnClickListener{
	
	private String firstName, lastName, email, mobile, bday, gender, city;
	
	
	private void showAlert(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Add the buttons
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               // User clicked OK button 
		        	   dialog.cancel();
		           }
		       });
		builder.setNegativeButton("Skip", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               // User cancelled the dialog
		        	   skip();
		           }
		       });
		builder.setTitle(R.string.user_alert_title);
		builder.setMessage(this.getResources().getString(R.string.user_note) 
				+ " " + this.getResources().getString(R.string.user_alert_body));
		

		// Create the AlertDialog
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	public void skip(){
		Intent intent = new Intent(UserInfoActivity.this, CallActivity.class);
		startActivity(intent);
	}
	
	public void save(){
		
		//TODO: save data
		skip();
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		boolean fromTerms = getIntent().getBooleanExtra("fromTerms", false);
		if(fromTerms)
			showAlert();
		
		
		setContentView(R.layout.activity_userinfo);
		EditText etNameFirst = (EditText) findViewById(R.id.et_name_first);
		etNameFirst.setOnFocusChangeListener(this);
		etNameFirst.setHintTextColor(getResources().getColor(R.color.text_white));
		etNameFirst = (EditText) findViewById(R.id.et_name_last);
		etNameFirst.setOnFocusChangeListener(this);
		etNameFirst.setHintTextColor(getResources().getColor(R.color.text_white));
		etNameFirst = (EditText) findViewById(R.id.et_phone);
		etNameFirst.setOnFocusChangeListener(this);
		etNameFirst.setHintTextColor(getResources().getColor(R.color.text_white));
		etNameFirst = (EditText) findViewById(R.id.et_email);
		etNameFirst.setOnFocusChangeListener(this);
		etNameFirst.setHintTextColor(getResources().getColor(R.color.text_white));
		etNameFirst = (EditText) findViewById(R.id.et_bday);
		etNameFirst.setOnFocusChangeListener(this);
		etNameFirst.setHintTextColor(getResources().getColor(R.color.text_white));
		etNameFirst = (EditText) findViewById(R.id.et_gender);
		etNameFirst.setOnFocusChangeListener(this);
		etNameFirst.setHintTextColor(getResources().getColor(R.color.text_white));
		etNameFirst = (EditText) findViewById(R.id.et_city);
		etNameFirst.setOnFocusChangeListener(this);
		etNameFirst.setHintTextColor(getResources().getColor(R.color.text_white));
		
		
		Button btnSubmit = (Button) findViewById(R.id.btn_save);
		btnSubmit.setOnClickListener(this);
		
		Button btnSkip = (Button) findViewById(R.id.btn_skip);
		btnSkip.setOnClickListener(this);
	}

	@Override
	public void onFocusChange(View view, boolean isFocused) {
		// TODO Auto-generated method stub
		if(!isFocused){
			switch(view.getId()){
			case R.id.et_name_last:
				SharedPrefManager.getInstance(this.getApplicationContext())
				.saveUserInfo(UserInfo.NAME_LAST,((EditText)view).getText().toString());
				break;
			case R.id.et_name_first:
				SharedPrefManager.getInstance(this.getApplicationContext())
				.saveUserInfo(UserInfo.NAME_FIRST,((EditText)view).getText().toString());
				break;
			case R.id.et_bday:
				SharedPrefManager.getInstance(this.getApplicationContext())
				.saveUserInfo(UserInfo.BDAY,((EditText)view).getText().toString());
				break;
			case R.id.et_city:
				SharedPrefManager.getInstance(this.getApplicationContext())
				.saveUserInfo(UserInfo.CITY,((EditText)view).getText().toString());
				break;
			case R.id.et_email:
				SharedPrefManager.getInstance(this.getApplicationContext())
				.saveUserInfo(UserInfo.EMAIL,((EditText)view).getText().toString());
				break;
			case R.id.et_gender:
				SharedPrefManager.getInstance(this.getApplicationContext())
				.saveUserInfo(UserInfo.GENDER,((EditText)view).getText().toString());
				break;
			case R.id.et_phone:
				SharedPrefManager.getInstance(this.getApplicationContext())
				.saveUserInfo(UserInfo.MOBILE,((EditText)view).getText().toString());
				break;
			}
		}
	}


	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()){
		case R.id.btn_save:
			//value checker
			save();
			
			break;
		case R.id.btn_skip:
			//value checker
			skip();
			
			break;
		}
	}

}
