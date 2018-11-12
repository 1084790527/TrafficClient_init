package com.mad.trafficclient.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mad.trafficclient.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_23 extends Fragment implements View.OnClickListener {

    private View view;
    private Button btn_inquire;

    public Fragment_23() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_fragment_23, container, false);
        iniv();
        init();
        return view;
    }

    private void init() {

    }

    private void iniv() {
        btn_inquire= (Button) view.findViewById(R.id.btn_inquire);
        btn_inquire.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_inquire:

                break;
        }
    }
}
