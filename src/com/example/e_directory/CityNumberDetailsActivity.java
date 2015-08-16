package com.example.e_directory;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class CityNumberDetailsActivity extends Activity{
	
	int cityNum;
	CityNumber cityNumber;
	TextView tvPolice, tvFire, tvCity;
	EditText editHosp;
	String newHospitalNumber;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_numberdetails);
		
		cityNum = this.getIntent().getIntExtra("city", -1);
		cityNumber = CityNumberList.getInstance().getNumbersOfCity(cityNum);
		
		
		tvCity = (TextView)findViewById(R.id.tv_cityName);
		tvCity.setText(cityNumber.getCity());
		
		tvPolice = (TextView)findViewById(R.id.tv_p_num);
		tvFire = (TextView)findViewById(R.id.tv_f_num);
		editHosp = (EditText)findViewById(R.id.edit_hosp_number);
		
		tvPolice.setText(cityNumber.getPoliceNum());
		tvFire.setText(cityNumber.getFireNum());
		
		String ifNumberNull = SharedPrefManager.getInstance(getBaseContext()).edittedHospitalNumber(cityNumber.getCity());
		
		String hospNum = (ifNumberNull == null) ? cityNumber.getHospNum(): ifNumberNull;
		editHosp.setText(hospNum);

		ImageButton btnBack = (ImageButton) findViewById(R.id.btn_back);
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		Button btnSave = (Button)findViewById(R.id.btn_save);
		btnSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				newHospitalNumber = editHosp.getText().toString();
				cityNumber.setCustomHosp(newHospitalNumber);
				SharedPrefManager.getInstance(getBaseContext()).saveString(cityNumber.getCity(),newHospitalNumber);
			}
		});
		
		Button btnRest = (Button)findViewById(R.id.btn_reset);
		btnRest.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				
				// TODO Auto-generated method stub
				cityNumber.setCustomHosp(null);
				cityNumber.setHospNum(cityNumber.getHospNum());
				editHosp.setText(cityNumber.getHospNum());
			}
		});
		
	}
}
