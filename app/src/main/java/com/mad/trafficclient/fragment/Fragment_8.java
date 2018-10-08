package com.mad.trafficclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mad.trafficclient.R;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_8 extends Fragment {

    private View view;
    private TextView tv_date;

    public Fragment_8() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_layout08, container, false);

        iniv();

        init();

        return view;
    }

    private void init() {

    }

    private void iniv() {
        tv_date= (TextView) view.findViewById(R.id.tv_date);
    }

}
