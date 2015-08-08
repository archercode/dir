package com.example.e_directory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class CityListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_citylist);


		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				CityListActivity.this,
				android.R.layout.simple_list_item_1);

		arrayAdapter.addAll(CityNumberList.getInstance().getListOfCities());

		ListView lv = (ListView)findViewById(R.id.lv_cities);
		lv.setAdapter(arrayAdapter);
		
		ImageButton btnBack = (ImageButton) findViewById(R.id.btn_back);
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
