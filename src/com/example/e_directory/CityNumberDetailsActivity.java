package com.example.e_directory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class CityNumberDetailsActivity extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_numberdetails);
		
		int cityNum = this.getIntent().getIntExtra("city", -1);
		CityNumber cityNumber = CityNumberList.getInstance().getNumbersOfCity(cityNum);
		
		TextView tvPolice, tvFire, tvCity;
		EditText editHosp;
		
		tvCity = (TextView)findViewById(R.id.tv_cityName);
		tvCity.setText(cityNumber.getCity());
		
		tvPolice = (TextView)findViewById(R.id.tv_p_num);
		tvFire = (TextView)findViewById(R.id.tv_f_num);
		editHosp = (EditText)findViewById(R.id.edit_hosp_number);
		
		tvPolice.setText(cityNumber.getPoliceNum());
		tvFire.setText(cityNumber.getFireNum());
		
		String hospNum = (cityNumber.getCustomHosp() == null) ? cityNumber.getHospNum(): cityNumber.getCustomHosp();
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
				
			}
		});
		
	}
}
