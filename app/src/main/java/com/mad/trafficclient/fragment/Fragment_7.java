package com.mad.trafficclient.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.mad.trafficclient.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_7 extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private static final String TAG = "AAA";
    private View view;
    private Switch switch_thresholdSetting;
    private EditText et_temperature,et_humidity,et_light,et_co2,et_pm25,et_roadStatus;
    private Button btn_save;
    private boolean isSwitch;

    public Fragment_7() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_layout07, container, false);

        iniv();
        init();
        ini();

        return view;
    }

    private void ini() {
        try {
            SharedPreferences loadSettingLoad = getActivity().getSharedPreferences("thresholdSetting", MODE_PRIVATE);
            boolean setting= Boolean.parseBoolean(loadSettingLoad.getString("setting",""));
//            Log.d(TAG, "ini: "+setting);
            isWitch(setting);
            switch_thresholdSetting.setChecked(setting);
            et_temperature.setText(loadSettingLoad.getString("temperature",""));
            et_humidity.setText(loadSettingLoad.getString("humidity",""));
            et_light.setText(loadSettingLoad.getString("light",""));
            et_co2.setText(loadSettingLoad.getString("co2",""));
            et_pm25.setText(loadSettingLoad.getString("pm25",""));
            et_roadStatus.setText(loadSettingLoad.getString("roadStatus",""));
        }catch (Exception e){
            isSwitch=false;
            switch_thresholdSetting.setChecked(false);
        }


    }

    private void init() {
        switch_thresholdSetting.setOnCheckedChangeListener(this);
        btn_save.setOnClickListener(this);
    }

    private void iniv() {
        switch_thresholdSetting= (Switch) view.findViewById(R.id.switch_thresholdSetting);
//        switch_thresholdSetting.setChecked(true);
        et_temperature= (EditText) view.findViewById(R.id.et_temperature);
        et_humidity= (EditText) view.findViewById(R.id.et_humidity);
        et_light= (EditText) view.findViewById(R.id.et_light);
        et_co2= (EditText) view.findViewById(R.id.et_co2);
        et_pm25= (EditText) view.findViewById(R.id.et_pm25);
        et_roadStatus= (EditText) view.findViewById(R.id.et_roadStatus);
        btn_save= (Button) view.findViewById(R.id.btn_save);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        isWitch(b);
        isSwitch=b;
//        SharedPreferences loadSettingLoad = getActivity().getSharedPreferences("thresholdSetting", MODE_PRIVATE);
//        Log.d(TAG, "onCheckedChanged: "+loadSettingLoad.getString("setting",""));
    }

    private void isWitch(boolean b) {
        if (b){
//            Log.d(TAG, "onCheckedChanged: "+b);
            switch_thresholdSetting.setText("开");
            OnOffET(false);
        }else {
//            Log.d(TAG, "onCheckedChanged: "+b);
            switch_thresholdSetting.setText("关");
            OnOffET(true);
        }
    }

    @Override
    public void onClick(View view) {
        SharedPreferences spSettingSave = getActivity().getSharedPreferences("thresholdSetting", MODE_PRIVATE);
        SharedPreferences.Editor editor = spSettingSave.edit();
        editor.putString("setting", isSwitch+"");
        editor.putString("temperature",et_temperature.getText().toString());
        editor.putString("humidity",et_humidity.getText().toString());
        editor.putString("light",et_light.getText().toString());
        editor.putString("co2",et_co2.getText().toString());
        editor.putString("pm25",et_pm25.getText().toString());
        editor.putString("roadStatus",et_roadStatus.getText().toString());
        editor.commit();
    }


    private void OnOffET(boolean b){
        et_temperature.setEnabled(b);
        et_co2.setEnabled(b);
        et_humidity.setEnabled(b);
        et_light.setEnabled(b);
        et_pm25.setEnabled(b);
        et_roadStatus.setEnabled(b);
    }
}
