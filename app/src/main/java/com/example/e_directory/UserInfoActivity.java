package com.example.e_directory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseObject;

public class UserInfoActivity extends Activity implements
		OnFocusChangeListener, OnClickListener {

	private String firstName, lastName, email, mobile, bday, gender, city;

	private EditText et_first, et_last, et_email, et_phone, et_gender, et_city, et_bday;

	private void showAlert() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Add the buttons
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// User clicked OK button
				dialog.cancel();
			}
		});
		builder.setNegativeButton("Skip",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// User cancelled the dialog
						skip();
					}
				});
		builder.setTitle(R.string.user_alert_title);
		builder.setMessage(this.getResources().getString(R.string.user_note)
				+ System.getProperty("line.separator")
				+ System.getProperty("line.separator")
				+ this.getResources().getString(R.string.user_alert_body));

		// Create the AlertDialog
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public void skip() {
		Intent intent = new Intent(UserInfoActivity.this, CallActivity.class);
		startActivity(intent);
	}

	public void save() {

		// TODO: save data
		skip();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		boolean fromTerms = getIntent().getBooleanExtra("fromTerms", false);
		if (fromTerms)
			showAlert();

		setContentView(R.layout.activity_userinfo);

		// required
		et_first = (EditText) findViewById(R.id.et_name_first);
		et_first.setOnFocusChangeListener(this);
		et_first.setHintTextColor(getResources().getColor(R.color.text_white));
		et_last = (EditText) findViewById(R.id.et_name_last);
		et_last.setOnFocusChangeListener(this);
		et_last.setHintTextColor(getResources().getColor(R.color.text_white));
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_phone.setOnFocusChangeListener(this);
		et_phone.setHintTextColor(getResources().getColor(R.color.text_white));
		et_email = (EditText) findViewById(R.id.et_email);
		et_email.setOnFocusChangeListener(this);
		et_email.setHintTextColor(getResources().getColor(R.color.text_white));

		et_bday = (EditText) findViewById(R.id.et_bday);
		et_bday.setOnFocusChangeListener(this);
		et_bday.setHintTextColor(getResources().getColor(R.color.text_white));
		et_gender = (EditText) findViewById(R.id.et_gender);
		et_gender.setOnFocusChangeListener(this);
		et_gender.setHintTextColor(getResources().getColor(R.color.text_white));
		et_city = (EditText) findViewById(R.id.et_city);
		et_city.setOnFocusChangeListener(this);
		et_city.setHintTextColor(getResources().getColor(R.color.text_white));

		Button btnSubmit = (Button) findViewById(R.id.btn_save);
		btnSubmit.setOnClickListener(this);

		Button btnSkip = (Button) findViewById(R.id.btn_skip);
		btnSkip.setOnClickListener(this);
	}

	@Override
	public void onFocusChange(View view, boolean isFocused) {
		// TODO Auto-generated method stub
		if (!isFocused) {
			switch (view.getId()) {
			case R.id.et_name_last:
				SharedPrefManager.getInstance(this.getApplicationContext())
						.saveUserInfo(UserInfo.NAME_LAST,
								((EditText) view).getText().toString());
				break;
			case R.id.et_name_first:
				SharedPrefManager.getInstance(this.getApplicationContext())
						.saveUserInfo(UserInfo.NAME_FIRST,
								((EditText) view).getText().toString());
				break;
			case R.id.et_bday:
				SharedPrefManager.getInstance(this.getApplicationContext())
						.saveUserInfo(UserInfo.BDAY,
								((EditText) view).getText().toString());
				break;
			case R.id.et_city:
				SharedPrefManager.getInstance(this.getApplicationContext())
						.saveUserInfo(UserInfo.CITY,
								((EditText) view).getText().toString());
				break;
			case R.id.et_email:
				SharedPrefManager.getInstance(this.getApplicationContext())
						.saveUserInfo(UserInfo.EMAIL,
								((EditText) view).getText().toString());
				break;
			case R.id.et_gender:
				SharedPrefManager.getInstance(this.getApplicationContext())
						.saveUserInfo(UserInfo.GENDER,
								((EditText) view).getText().toString());
				break;
			case R.id.et_phone:
				SharedPrefManager.getInstance(this.getApplicationContext())
						.saveUserInfo(UserInfo.MOBILE,
								((EditText) view).getText().toString());
				break;
			}
		}
	}

	public void showError(String title, String msg) {
		LayoutInflater inflater = this.getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.dialog_about, null);
		TextView info = (TextView) dialogView.findViewById(R.id.tv_founders);
		info.setText(msg);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(false).setView(dialogView).setTitle(title)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.btn_save:
			// value checker

			// check

			if (et_first.getText().toString().equals("")
					|| et_last.getText().toString().equals("")
					|| et_email.getText().toString().equals("")
					|| et_phone.getText().toString().equals("")) {

				showError("Missing Information",
						"Please fill out all the required fields before submitting.");

				
			} else {

				final String email = et_email.getText().toString().trim();
				final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

				if (email.matches(emailPattern) && email.length() > 0) {

					ParseObject user = new ParseObject("UserInformation");
					user.put("firstName", et_first.getText().toString());
					user.put("lastName", et_last.getText().toString());
					user.put("email", et_email.getText().toString());
					user.put("mobileNumber", et_phone.getText().toString());
					user.put("birthday", et_bday.getText().toString());
					user.put("gender", et_gender.getText().toString());
					user.put("hometown", et_city.getText().toString());
					user.saveInBackground();
					save();
				} else {

					showError("Invalid email", "Please input correct format.");
				}

			}

			break;
		case R.id.btn_skip:
			// value checker
			skip();

			break;
		}
	}

}
