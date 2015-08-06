package com.example.e_directory;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class CallActivity extends FragmentActivity{
	

	private LocationManager locationMangaer = null;
	private LocationListener locationListener = null;
	
	private ImageButton btnRefresh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call);
		
		
		btnRefresh = (ImageButton)findViewById(R.id.btn_refresh);
		btnRefresh.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
			}
		});
		
	}

}
