package com.mad.trafficclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mad.trafficclient.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_15 extends Fragment {
    private View view;
    private TextView tv_recharge;
    private EditText et_money;
    private Button btn_setting;

    public Fragment_15() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_fragment_15, container, false);
        iniv();
        init();
        return view;
    }
    private void init() {

    }

    private void iniv() {
        tv_recharge= (TextView) view.findViewById(R.id.tv_recharge);
        et_money= (EditText) view.findViewById(R.id.et_money);
        btn_setting= (Button) view.findViewById(R.id.btn_setting);
    }


}
