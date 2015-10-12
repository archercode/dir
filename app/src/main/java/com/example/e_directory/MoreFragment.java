package com.example.e_directory;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.HashMap;

/**
 * Created by francescabarcelona on 10/5/15.
 */
public class MoreFragment  extends Fragment implements View.OnClickListener{


    HashMap<Integer, String> numbers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        numbers = new HashMap<Integer, String>();
        numbers.put(R.id.btn_skyway,"+639175398762");
        numbers.put(R.id.btn_nlex,"+639189061401");
        numbers.put(R.id.btn_slex,"+639176877539");
        //numbers.put(R.id.btn_cavitex,"+639189340713");
        numbers.put(R.id.btn_tplex,"+639178880715");
        numbers.put(R.id.btn_sctex,"+639209672839");
        numbers.put(R.id.btn_startoll,"+639175304538");
        numbers.put(R.id.btn_peatc,"+639189340713");
        numbers.put(R.id.btn_mates,"+63495087539");
        numbers.put(R.id.btn_ndrmmc,"+6329115061");
        numbers.put(R.id.btn_dswd,"+6329318101");
        numbers.put(R.id.btn_phcoastguard,"+6325278481");
        numbers.put(R.id.btn_pagasa,"+632434-2696");
        numbers.put(R.id.btn_dotc,"+6327890");
        numbers.put(R.id.btn_mmda,"+632136");
        numbers.put(R.id.btn_nbi,"+6325238231");

    }

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

        String numberToCall = numbers.get(view.getId());

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + numberToCall));
        startActivity(intent);
    }



}
