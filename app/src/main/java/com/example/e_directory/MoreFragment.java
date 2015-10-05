package com.example.e_directory;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by francescabarcelona on 10/5/15.
 */
public class MoreFragment  extends Fragment implements View.OnClickListener{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more,  null);

        //return super.onCreateView(inflater, container, savedInstanceState);

        Button btn = (Button) view.findViewById(R.id.btn_skyway);
        btn.setOnClickListener(this);
        btn = (Button) view.findViewById(R.id.btn_nlex);
        btn.setOnClickListener(this);
        btn = (Button) view.findViewById(R.id.btn_slex);
        btn.setOnClickListener(this);
        btn = (Button) view.findViewById(R.id.btn_sctex);
        btn.setOnClickListener(this);
        btn = (Button) view.findViewById(R.id.btn_tplex);
        btn.setOnClickListener(this);
        btn = (Button) view.findViewById(R.id.btn_startoll);
        btn.setOnClickListener(this);
        btn = (Button) view.findViewById(R.id.btn_peatc);
        btn.setOnClickListener(this);
        btn = (Button) view.findViewById(R.id.btn_mates);
        btn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        String numberToCall = "";

        switch(view.getId()){
            case R.id.btn_dotc:
                break;

        }


        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + numberToCall));
        startActivity(intent);
    }



}
