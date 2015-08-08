package com.example.e_directory;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CallActivity extends FragmentActivity{
	

	private LocationManager locationMangaer = null;
	private LocationListener locationListener = null;
	
	private ImageButton btnRefresh;
	private Boolean flag = false;
	
	private final String TAG = "Edir";
	
	private LinearLayout ll_pb;
	private TextView tv_city;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call);
		
		ll_pb = (LinearLayout)findViewById(R.id.layloadingH);
		tv_city = (TextView)findViewById(R.id.tv_city);

		locationMangaer = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		btnRefresh = (ImageButton)findViewById(R.id.btn_refresh);
		btnRefresh.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				flag = displayGpsStatus();
				if (flag) {

					Log.v(TAG, "onClick");

					/*editLocation.setText("Please!! move your device to"
							+ " see the changes in coordinates." + "\nWait..");
*/
					ll_pb.setVisibility(View.VISIBLE);
					locationListener = new MyLocationListener();

					locationMangaer.requestLocationUpdates(
							LocationManager.NETWORK_PROVIDER,
							//LocationManager.GPS_PROVIDER,
							5000, 10, locationListener);
					

				} else {
					alertbox("Gps Status!!", "Your GPS is: OFF");
				}
				
			}
		});
		
	}
	
	/*----Method to Check GPS is enable or disable ----- */
	private Boolean displayGpsStatus() {
		ContentResolver contentResolver = getBaseContext().getContentResolver();
		boolean gpsStatus = Settings.Secure.isLocationProviderEnabled(
				contentResolver, LocationManager.GPS_PROVIDER);
		if (gpsStatus) {
			return true;

		} else {
			return false;
		}
	}

	/*----------Method to create an AlertBox ------------- */
	protected void alertbox(String title, String mymessage) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Your Device's GPS is Disable")
				.setCancelable(false)
				.setTitle("** Gps Status **")
				.setPositiveButton("Gps On",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// finish the current activity
								// AlertBoxAdvance.this.finish();
								Intent myIntent = new Intent(
										Settings.ACTION_SECURITY_SETTINGS);
								startActivity(myIntent);
								dialog.cancel();
							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// cancel the dialog box
								dialog.cancel();
							}
						});
		AlertDialog alert = builder.create();
		alert.show();
	}

	public static JSONObject getLocationInfo(Location loc) {
	    StringBuilder stringBuilder = new StringBuilder();
	    try {

	    //address = address.replaceAll(" ","%20");    

	    HttpPost httppost = new HttpPost("http://maps.google.com/maps/api/geocode/json?latlng=" + loc.getLatitude() 
	    		+ "," + loc.getLongitude()+ "&components=locality&sensor=false");
	    HttpClient client = new DefaultHttpClient();
	    HttpResponse response;
	    stringBuilder = new StringBuilder();


	        response = client.execute(httppost);
	        HttpEntity entity = response.getEntity();
	        InputStream stream = entity.getContent();
	        int b;
	        while ((b = stream.read()) != -1) {
	            stringBuilder.append((char) b);
	        }
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	    JSONObject jsonObject = new JSONObject();
	    try {
	        jsonObject = new JSONObject(stringBuilder.toString());
	    } catch (JSONException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	    return jsonObject;
	}
	
	private class LocationTask extends AsyncTask<Location, Void, Void> {
		String cityName = "";
		String longitude, latitude;
		@Override
		protected Void doInBackground(Location... locs) {
			// TODO Auto-generated method stub
			
			longitude = String.valueOf(locs[0].getLongitude());
			latitude = String.valueOf(locs[0].getLatitude());
			
			
			//cityName = latitude;
			//cityName = String.valueOf(getLocationInfo(locs[0]));
			
			//TODO: parse JSON to show 
			
			JSONObject jsonRes = getLocationInfo(locs[0]);
			
			try {
				boolean found = false;
				JSONArray addresses = ((JSONObject)jsonRes.getJSONArray("results").get(0)).getJSONArray("address_components");
				for(int i = 0; i < addresses.length(); i++){
					found = false;
					JSONObject obj = (JSONObject) addresses.get(i);//new JSONObject(addresses.get(i));
					
					JSONArray components = obj.getJSONArray("types");
					
					
					for(int j = 0; j < components.length() || !found; j++) {
						if(components.get(j).toString().equals("locality")){
							found = true;
						}
					}
					
					if(found){
						cityName = obj.getString("long_name")+" City";//"found!";
					}
					
					
					
				}
				
				
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			String s = longitude + "\n" + latitude + "\n\nMy Currrent City is: " + cityName; 
			tv_city.setText(cityName);
			
			super.onPostExecute(result);
		}
		
		
	}
	
	/*----------Listener class to get coordinates ------------- */
	private class MyLocationListener implements LocationListener {
		@Override
		public void onLocationChanged(Location loc) {

			if(!tv_city.getText().equals(""))
				ll_pb.setVisibility(View.INVISIBLE); 
			
			Toast.makeText(
					getBaseContext(),
					"Location changed : Lat: " + loc.getLatitude() + " Lng: "
							+ loc.getLongitude(), Toast.LENGTH_SHORT).show();
			String longitude = "Longitude: " + loc.getLongitude();
			Log.v(TAG, longitude);
			String latitude = "Latitude: " + loc.getLatitude();
			Log.v(TAG, latitude);
			/*----------to get City-Name from coordinates ------------- */
			/*String cityName = null;
			Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
			List<Address> addresses;
			try {
				addresses = gcd.getFromLocation(loc.getLatitude(),
						loc.getLongitude(), 1);
				if (addresses.size() > 0)
					System.out.println(addresses.get(0).getLocality());
				cityName = addresses.get(0).getLocality();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
			cityName = String.valueOf(getLocationInfo(loc));
			
			
			
			
			String s = longitude + "\n" + latitude
					+ "\n\nMy Currrent City is: " + cityName;
			editLocation.setText(s);*/
			
			new LocationTask().execute(loc);
		}

		
		
		
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
		}
	}

}
