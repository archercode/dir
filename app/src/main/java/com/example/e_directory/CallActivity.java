package com.example.e_directory;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CallActivity extends FragmentActivity {

	private LocationManager locationMangaer = null;
	private LocationListener locationListener = null;
	private ImageButton btnRefresh, btnSettings;
	private Button btnPolice,
			btnHospital, btnFire, btnMore;
	private Boolean flag = false;

	private final String TAG = "Edir";

	private LinearLayout ll_pb, llMain, llSpecial;

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

	CityNumber currCityNumberObj;


    ArrayList<CallFragment> listeners;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call);


        listeners = new ArrayList<CallFragment>();

		btnPolice = (Button) findViewById(R.id.btn_police);
		btnHospital = (Button) findViewById(R.id.btn_hospital);
		btnFire = (Button) findViewById(R.id.btn_fire);
        btnMore = (Button) findViewById(R.id.btn_more);


        ll_pb = (LinearLayout) findViewById(R.id.layloadingH);
        llMain = (LinearLayout) findViewById(R.id.linearLayout);
        llSpecial = (LinearLayout) findViewById(R.id.header_special);
        locationMangaer = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        lookForCity();
        btnRefresh = (ImageButton) findViewById(R.id.btn_refresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                lookForCity();
            }
        });

        btnSettings = (ImageButton) findViewById(R.id.btn_settings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(CallActivity.this,
                        CityListActivity.class);
                startActivity(intent);
            }
        });


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        btnHospital.setSelected(false);
                        btnPolice.setSelected(true);
                        btnFire.setSelected(false);
                        btnMore.setSelected(false);

                        llMain.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        btnHospital.setSelected(true);
                        btnPolice.setSelected(false);
                        btnFire.setSelected(false);
                        btnMore.setSelected(false);

                        llMain.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        btnHospital.setSelected(false);
                        btnPolice.setSelected(false);
                        btnFire.setSelected(true);
                        btnMore.setSelected(false);

                        llMain.setVisibility(View.VISIBLE);
                        break;
                    case 3:

                        btnHospital.setSelected(false);
                        btnPolice.setSelected(false);
                        btnFire.setSelected(false);
                        btnMore.setSelected(true);

                        llMain.setVisibility(View.GONE);
                        break;


                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        mViewPager.setCurrentItem(0);
        btnHospital.setSelected(false);
        btnPolice.setSelected(true);
        btnFire.setSelected(false);
        btnMore.setSelected(false);
        mViewPager.setOffscreenPageLimit(4);


        // POLICE TAB




        btnPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                mViewPager.setCurrentItem(0);


            }
        });

        // HOSPITAL TAB

        btnHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                mViewPager.setCurrentItem(1);
            }
        });

        // FIRE TAB

        btnFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                mViewPager.setCurrentItem(2);
            }
        });
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //llSpecial.setVisibility(View.GONE);
                mViewPager.setCurrentItem(3);

            }
        });

    }

	public void lookForCity() {
		// TONNY
		
		// if (displayNetworkStatus()) {
		if (isNetworkAvailable() && displayNetworkStatus()) {

			Log.v(TAG, "onClick Network");
			ll_pb.setVisibility(View.VISIBLE);
			
			// dont attempt if not connected to a network
			
			locationListener = new MyLocationListener();
			
			locationMangaer.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER,
					// LocationManager.GPS_PROVIDER,
					5000, 10, locationListener);

		} 
		//edited by: TONNY
		/*else if (displayGpsStatus()) {
			

			Log.v(TAG, "onClick");
			ll_pb.setVisibility(View.VISIBLE);
			locationListener = new MyLocationListener();
			locationMangaer.requestLocationUpdates(
			// LocationManager.NETWORK_PROVIDER,
					LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
		} */
		else {
			
			/*float apiVer = getAPIVerison();
			
			if(apiVer >= 4.0f)
				alertbox("E-Directory", "Your network is off. Please turn on the Location services.", "Turn Location On", "Cancel");
			else	*/
			
			
			alertbox("E-Directory", "Your network is off. Please turn on the Location and Network services.", "OK", "Cancel");
			
		}
	}
	
	public static float getAPIVerison() {
	    float f=1f;
	    try {
	        StringBuilder strBuild = new StringBuilder();
	        strBuild.append(android.os.Build.VERSION.RELEASE.substring(0, 2));
	        f= Float.valueOf(strBuild.toString());
	    } catch (NumberFormatException e) {
	        Log.e("myApp", "error retriving api version" + e.getMessage());
	    }

	    return f;
	}

	/*----Method to Check GPS is enable or disable ----- */
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	
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

	private Boolean displayNetworkStatus() {
		ContentResolver contentResolver = getBaseContext().getContentResolver();
		boolean networkStatus = Settings.Secure.isLocationProviderEnabled(
                contentResolver, LocationManager.NETWORK_PROVIDER);
		if (networkStatus) {
			return true;

		} else {
			
			
//			showSettingsAlert("Access Fine Location Android");
			
			return false;
		}
	}

	/*----------Method to create an AlertBox ------------- */
	protected void showNoCityAlert() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Could not locate your city. Please check your network connection.")
				.setCancelable(false)
				.setTitle("E- Directory")
				.setPositiveButton("Retry",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								lookForCity();
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
	protected void alertbox(String title, String mymessage, String okButton, String cancelButton) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(mymessage)
				.setCancelable(false)
				.setTitle(title)
				.setPositiveButton(okButton,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// finish the current activity
								// AlertBoxAdvance.this.finish();

								WifiManager wifiManager = (WifiManager)getBaseContext().getSystemService(Context.WIFI_SERVICE);
								wifiManager.setWifiEnabled(true);
								
								Intent myIntent = new Intent(
										Settings.ACTION_LOCATION_SOURCE_SETTINGS);
								startActivity(myIntent);
								dialog.cancel();
								
							}
						})
				.setNegativeButton(cancelButton,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // cancel the dialog box
                                dialog.cancel();
                            }
                        });
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	public void showSettingsAlert(String provider) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				CallActivity.this);

		alertDialog.setTitle(provider + " SETTINGS");

		alertDialog
				.setMessage(provider + " is not enabled! Want to go to settings menu?");

		alertDialog.setPositiveButton("Settings",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(
								Settings.ACTION_LOCATION_SOURCE_SETTINGS);
						CallActivity.this.startActivity(intent);
					}
				});

		alertDialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

		alertDialog.show();
	}



	
	public static JSONObject getLocationInfo(Location loc) {
		StringBuilder stringBuilder = new StringBuilder();
		try {

			// address = address.replaceAll(" ","%20");

			HttpPost httppost = new HttpPost(
					"http://maps.google.com/maps/api/geocode/json?latlng="
							+ loc.getLatitude() + "," + loc.getLongitude()
							+ "&components=locality&sensor=false");
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

			double lat = //14.4321175f;
					//14.481467f; 
					locs[0].getLatitude();
			double lon = 
					//120.9913723f;
					//121.008370f; 
					locs[0].getLongitude();
			
			longitude = String.valueOf(lon); 
			latitude = String.valueOf(lat);
			

			Geocoder gcd = new Geocoder(CallActivity.this.getApplicationContext(), Locale.getDefault());
			List<Address> addresses;
			try {
				addresses = gcd.getFromLocation(lat, lon, 1);

				if (addresses.size() > 0) {

					Cities currCity = Cities.MANILA;
					
					if(addresses.get(0).getSubAdminArea() != null && currCity.getCity(addresses.get(0).getSubAdminArea()) != Cities.NONE ){
						cityName = addresses.get(0).getSubAdminArea();
						//currCity = currCity.getCity(addresses.get(0).getSubAdminArea());
						Log.d("butch","subadminarea");
					}else if (addresses.get(0).getSubLocality() != null && currCity.getCity(addresses.get(0).getSubLocality()) != Cities.NONE ){
						//currCity = currCity.getCity( addresses.get(0).getSubLocality());
						cityName = addresses.get(0).getSubLocality();
						Log.d("butch","sublocality");
					}else if (addresses.get(0).getAdminArea() != null && currCity.getCity(addresses.get(0).getAdminArea()) != Cities.NONE ){
						//currCity = currCity.getCity(addresses.get(0).getAdminArea());
						cityName = addresses.get(0).getAdminArea();
						Log.d("butch","adminare");
					}else{
						//currCity = currCity.getCity(addresses.get(0).getLocality());
						cityName = addresses.get(0).getLocality();
						Log.d("butch","locality");
					}
					
				
				    Cities currCities = Cities.NONE.getCity(cityName.replace("ñ","n"));
					cityName = currCities.getStringLabel();
					currCityNumberObj = CityNumberList.getInstance().getNumbersOfCity(cityName);
					cityName = currCityNumberObj.getCity();

					
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			/*JSONObject jsonRes = getLocationInfo(locs[0]);

			try {
				boolean found = false;
				JSONArray addresses = ((JSONObject) jsonRes.getJSONArray(
						"results").get(0)).getJSONArray("address_components");
				for (int i = 0; i < addresses.length(); i++) {
					found = false;
					JSONObject obj = (JSONObject) addresses.get(i);
					JSONArray components = obj.getJSONArray("types");

					for (int j = 0; j < components.length() && !found; j++) {
						if (components.get(j).toString().equals("locality")) {
							found = true;
						}
					}

					if (found) { 
						cityName = obj.getString("long_name");
						if(!cityName.equals("Manila"))
							cityName += " City";
					}

				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
*/
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			
			if(cityName.trim().isEmpty()){
				showNoCityAlert();
			}/*else {
				
				tv_city.setText(cityName);

			}			*/


            for(CallFragment listener: listeners){
                listener.onLocationChanged(cityName, currCityNumberObj);
            }

			ll_pb.setVisibility(View.GONE);
			locationMangaer.removeUpdates(locationListener);
			
			super.onPostExecute(result);
		}

	}



	/*----------Listener class to get coordinates ------------- */
	private class MyLocationListener implements LocationListener {
		@Override
		public void onLocationChanged(Location loc) {

			String longitude = "Longitude: " + loc.getLongitude();
			Log.v(TAG, longitude);
			String latitude = "Latitude: " + loc.getLatitude();
			Log.v(TAG, latitude);

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
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//lookForCity();
	}


	public interface CurrentLocationListener{

		public void onLocationChanged(String cityName, CityNumber currCityNumber);
	}

    public void setCity(String cityName, CityNumber currCityNumber){

        for(CallFragment listener: listeners){
            listener.refresh(cityName, currCityNumber);
        }

    }

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            if(position < 3) {
                Fragment frag = CallFragment.newInstance(position);
                listeners.add((CallFragment) frag);
                return frag;
            }else{
                return new MoreFragment();
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
                case 3:
                    return "More";
            }
            return null;
        }
    }


}
