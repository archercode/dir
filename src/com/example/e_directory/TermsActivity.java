package com.example.e_directory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TermsActivity extends Activity{
	
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_terms_info);
		
		
		final boolean isFirstLoad = SharedPrefManager.getInstance(getApplicationContext()).isFirstLoad();
		
		if(!isFirstLoad){
			Intent intent = new Intent(TermsActivity.this,CallActivity.class);
			startActivity(intent);
			finish();
		}
		
 
		String linkToPdf = "file:///android_asset/hero_support_terms.pdf";
		File file = new File(linkToPdf);          
		Uri path = Uri.fromFile(file);    
		
		context = this.getApplicationContext();
				
		TextView tv_terms = (TextView)findViewById(R.id.tv_terms); 
		String fileName = "hero_support_terms_doc.txt";
		tv_terms.setText(readTxt(fileName));
		
		
		Button btn_agree = (Button) findViewById(R.id.btn_agree);
		btn_agree.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(TermsActivity.this,UserInfoActivity.class);
				intent.putExtra("fromTerms", isFirstLoad);
				if(isFirstLoad)
					SharedPrefManager.getInstance(getApplicationContext()).setFirstLoad(false);
				startActivity(intent);
				finish();
			}
		});
		
		Button btn_disagree = (Button) findViewById(R.id.btn_disagree);
		btn_disagree.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(TermsActivity.this);
				// Add the buttons
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				               // User clicked OK button 
				        	   dialog.cancel();
				           }
				       }); 
				builder.setTitle("User has declined the Terms and Conditions");
				builder.setMessage("Please hit the Home button to exit the app.");
				

				// Create the AlertDialog
				AlertDialog dialog = builder.create();
				dialog.show();
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


