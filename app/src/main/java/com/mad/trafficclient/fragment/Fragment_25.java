package com.mad.trafficclient.fragment;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.fragment.fragment.Fragment_25_1;
import com.mad.trafficclient.fragment.fragment.Fragment_25_2;
import com.mad.trafficclient.fragment.fragment.Fragment_25_3;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_25 extends Fragment implements View.OnClickListener {

    private View view;
    private TextView tv_grzx,tv_czjl,tv_fzsz;

    public Fragment_25() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_fragment_25, container, false);
        iniv();
        init();
        return view;
    }

    private void init() {
    }


    private void iniv() {
        tv_grzx= (TextView) view.findViewById(R.id.tv_grzx);
        tv_grzx.setOnClickListener(this);
        tv_czjl= (TextView) view.findViewById(R.id.tv_czjl);
        tv_czjl.setOnClickListener(this);
        tv_fzsz= (TextView) view.findViewById(R.id.tv_fzsz);
        tv_fzsz.setOnClickListener(this);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.rl_f,new Fragment_25_1()).commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_grzx:
                tv_grzx.setText(R.string.grzx_1);
                tv_czjl.setText(R.string.czjl_2);
                tv_fzsz.setText(R.string.fzsz_2);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.rl_f,new Fragment_25_1()).commit();
                break;
            case R.id.tv_czjl:
                tv_grzx.setText(R.string.grzx_2);
                tv_czjl.setText(R.string.czjl_1);
                tv_fzsz.setText(R.string.fzsz_2);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.rl_f,new Fragment_25_2()).commit();
                break;
            case R.id.tv_fzsz:
                tv_grzx.setText(R.string.grzx_2);
                tv_czjl.setText(R.string.czjl_2);
                tv_fzsz.setText(R.string.fzsz_1);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.rl_f,new Fragment_25_3()).commit();
                break;
        }
    }
}
