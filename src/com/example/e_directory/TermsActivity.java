package com.example.e_directory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class TermsActivity extends Activity{
	
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_terms);

		//WebView mWebView = (WebView)findViewById(R.id.web_view_pdf);
		//new WebView(TnCActivity.this);

		//mWebView.getSettings().setJavaScriptEnabled(true);
		//mWebView.getSettings().setPluginsEnabled(true);
		String linkToPdf = "file:///android_asset/hero_support_terms.pdf";
		File file = new File(linkToPdf);          
		Uri path = Uri.fromFile(file);   

		//mWebView.loadUrl("https://docs.google.com/gview?embedded=true&url="+linkToPdf);
		
		context = this.getApplicationContext();
				
		TextView tv_terms = (TextView)findViewById(R.id.tv_terms);
		//String filename = "file:///android_asset/hero_support_terms.pdf";
		String fileName = "hero_support_terms_doc.txt";
		tv_terms.setText(readTxt(fileName));
				
		
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


