package com.example.e_directory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class TermsInfoActivity extends Activity{
	
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_terms);
		
		
 
		String linkToPdf = "file:///android_asset/hero_support_terms.pdf";
		File file = new File(linkToPdf);          
		Uri path = Uri.fromFile(file);    
		
		context = this.getApplicationContext();
				
		TextView tv_terms = (TextView)findViewById(R.id.tv_terms); 
		String fileName = "hero_support_terms_doc.txt";
		tv_terms.setText(readTxt(fileName));
		
		
		Button btn_agree = (Button) findViewById(R.id.btn_save);
		btn_agree.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(TermsInfoActivity.this,UserInfoActivity.class); 
				startActivity(intent);
			}
		});
		
		ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}



	public String readTxt(String fileName){

		try {
			InputStream is;
			is = context.getAssets().open(fileName);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

			int i;
			i = is.read();
			while (i != -1)
			{
				byteArrayOutputStream.write(i);
				i = is.read();
			}

			is.close();

			return byteArrayOutputStream.toString();

		} 

		catch (IOException e) 
		{
			e.printStackTrace();
		}

		return fileName;
	}
}


