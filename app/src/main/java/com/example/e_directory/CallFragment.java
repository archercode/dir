package com.example.e_directory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by tonnyquintos on 10/3/15.
 */
public class CallFragment extends Fragment implements CallActivity.CurrentLocationListener{


    private ImageButton  btnCall, btnInfo;
    private Boolean flag = false;

    private final String TAG = "Edir";

    private LinearLayout ll_pb, ll_city;
    private TextView tv_city;
    private TextView tv_dept;
    private static final String ARG_SECTION_NUMBER = "section_number";

    int tabNumber;

    String cityName = "Manila";
    CityNumber currCityNumberObj;



    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CallFragment newInstance(int sectionNumber) {
        CallFragment fragment = new CallFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public CallFragment(){
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call_actvity, container, false);

        tabNumber = this.getArguments().getInt(ARG_SECTION_NUMBER);
        if(tabNumber < 3) {
            btnInfo = (ImageButton) view.findViewById(R.id.btn_info);

            tv_dept = (TextView) view.findViewById(R.id.tv_dept);


            switch (tabNumber){
                case 0:
                    tv_dept.setText("Police");
                    break;
                case 1:
                    tv_dept.setText("Hospital");
                    break;
                case 2:
                    tv_dept.setText("Fire");
                    break;
            }


            ll_city = (LinearLayout) view.findViewById(R.id.tv_city_header);
            tv_city = (TextView) view.findViewById(R.id.tv_city);
            tv_city.setText(cityName);
            ll_city.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    showCityList();
                }
            });

            btnInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    showAboutDialog();
                }
            });


            // CALL BUTTON
            btnCall = (ImageButton) view.findViewById(R.id.btn_call);
            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    // CityList
                    // get Text from list List
                    String cityName = tv_city.getText().toString();
                    String numberToCall = null;
                    //currCityNumberObj = CityNumberList.getInstance().getNumbersOfCity(cityName);

                    switch (tabNumber) {
                        case 0: //police
                            numberToCall = currCityNumberObj.getPoliceNum();
                            break;
                        case 1: //hospital

                            String ifNumberNull = SharedPrefManager.getInstance(CallFragment.this.getActivity().getBaseContext()).edittedHospitalNumber(currCityNumberObj.getCity());
                            numberToCall = (ifNumberNull == null) ? currCityNumberObj.getHospNum() : ifNumberNull;

//					numberToCall = arrayAdapter.getHospNum();
                            break;
                        case 2: //fire dept
                            numberToCall = currCityNumberObj.getFireNum();
                            break;
                        case 3:
                        default: //more
                            numberToCall = null;
                            break;

                    }

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + numberToCall));
                    startActivity(intent);
                }
            });

        }

        return view;
    }

    private void showCityList() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                CallFragment.this.getActivity());
        // builderSingle.setIcon(R.drawable.ic_launcher);
        builderSingle.setTitle("Select a city");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                CallFragment.this.getActivity(), android.R.layout.select_dialog_singlechoice);

        arrayAdapter.addAll(CityNumberList.getInstance().getListOfCities());

        builderSingle.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                        dialog.dismiss();

                        Cities currCities = Cities.NONE.getCity(strName.replace("ñ","n"));
                        strName = currCities.getStringLabel();
                        currCityNumberObj = CityNumberList.getInstance().getNumbersOfCity(strName);
                        strName = currCityNumberObj.getCity();

                        tv_city.setText(strName);

                        ((CallActivity)CallFragment.this.getActivity()).setCity(strName, currCityNumberObj);
                    }
                });


        builderSingle.show();
    }

    protected void showAboutDialog() {

        LayoutInflater inflater = CallFragment.this.getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_about, null);
        TextView info = (TextView) view.findViewById(R.id.tv_founders);
        info.setText("Co-founders:" + System.getProperty("line.separator")
                + "Gerard Navarro" + System.getProperty("line.separator")
                + "Ronald Paglinawan" + System.getProperty("line.separator")
                + "Vincent Cheng" + System.getProperty("line.separator")
                + "Jorge Ejercito" + System.getProperty("line.separator")
                + "Rodel Tarroza" + System.getProperty("line.separator")
                + "Ron Villaraza" + System.getProperty("line.separator") + System.getProperty("line.separator")
                + "contact us at" + System.getProperty("line.separator")
                + "info@herosupportph.com" + System.getProperty("line.separator")
                + "Copyright(c) and Trademark(TM) 2015." + System.getProperty("line.separator")
                + "All rights reserved.");


        AlertDialog.Builder builder = new AlertDialog.Builder(CallFragment.this.getActivity());
        builder
                .setCancelable(false)
                .setView(view)
                .setTitle("About E- Directory")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    @Override
    public void onLocationChanged(String strName, CityNumber currCityNumberObj) {
        cityName = strName;
        this.currCityNumberObj = currCityNumberObj;
        refresh(strName, currCityNumberObj);
    }


    public void refresh(String strCity, CityNumber currCityNumberObj){
        cityName = strCity;
        this.currCityNumberObj = currCityNumberObj;

        tv_city.setText(strCity);
        //this.getView().invalidate();
    }
}
