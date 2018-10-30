package com.mad.trafficclient.fragment;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.UrlBean;
import com.mad.trafficclient.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_PRIVATE;


public class Fragment_5 extends Fragment implements View.OnClickListener {

    String TAG="AAA";
    private View view;
    private RelativeLayout rl_temperature,rl_humidity,rl_illumination,rl_CO2,rl_PM25,rl_roadStatus;
    private TextView tv_temperature,tv_humidity,tv_illumination,tv_CO2,tv_PM25,tv_roadStatus;
    private ScheduledFuture tData;
    private SharedPreferences loadSettingLoad;
    private int temperature,humidity,light,co2,pm25,roadStatus;
    private UrlBean urlBean;
    public Fragment_5() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_layout05, container, false);

        init();

        setDate();

        return view;
    }

    private void setDate() {
        tData=Executors.newScheduledThreadPool(3).scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                setRoadStatus();
                setAllSense();
            }
        },0,3, TimeUnit.SECONDS);
    }

    private void setAllSense() {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("UserName","sdsd");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://"+urlBean.getUrl()+":"+urlBean.getPort()+"/transportservice/action/GetAllSense.do", jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
//                        Log.d("AAA", "setAllSense: "+jsonObject.toString());
                        try {
                            tv_temperature.setText(jsonObject.getString("temperature"));
                            if (Integer.parseInt(jsonObject.getString("temperature"))>temperature){
                                rl_temperature.setBackgroundColor(Color.parseColor("#FFFF0000"));
                            }else {
                                rl_temperature.setBackgroundColor(Color.parseColor("#2dce01"));
                            }
                            tv_humidity.setText(jsonObject.getString("humidity"));
                            if (Integer.parseInt(jsonObject.getString("humidity"))>humidity){
                                rl_humidity.setBackgroundColor(Color.parseColor("#FFFF0000"));
                            }else {
                                rl_humidity.setBackgroundColor(Color.parseColor("#2dce01"));
                            }
                            tv_illumination.setText(jsonObject.getString("LightIntensity"));
                            if (Integer.parseInt(jsonObject.getString("LightIntensity"))>light){
                                rl_illumination.setBackgroundColor(Color.parseColor("#FFFF0000"));
                            }else {
                                rl_illumination.setBackgroundColor(Color.parseColor("#2dce01"));
                            }
                            tv_CO2.setText(jsonObject.getString("co2"));
                            if (Integer.parseInt(jsonObject.getString("co2"))>co2){
                                rl_CO2.setBackgroundColor(Color.parseColor("#FFFF0000"));
                            }else {
                                rl_CO2.setBackgroundColor(Color.parseColor("#2dce01"));
                            }
                            tv_PM25.setText(jsonObject.getString("pm2.5"));
                            if (Integer.parseInt(jsonObject.getString("pm25"))>pm25){
                                rl_PM25.setBackgroundColor(Color.parseColor("#FFFF0000"));
                            }else {
                                rl_PM25.setBackgroundColor(Color.parseColor("#2dce01"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(request);
    }

    private void setRoadStatus() {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("RoadId",1);
            jsonObject.put("UserName","sdsd");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://"+urlBean.getUrl()+":"+urlBean.getPort()+"/transportservice/action/GetRoadStatus.do", jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
//                        Log.d("AAA", "setRoadStatus: "+jsonObject.toString());
                        try {
                            tv_roadStatus.setText(jsonObject.getString("Status"));
                            if (Integer.parseInt(jsonObject.getString("Status"))>roadStatus){
                                rl_roadStatus.setBackgroundColor(Color.parseColor("#FFFF0000"));
                            }else {
                                rl_roadStatus.setBackgroundColor(Color.parseColor("#2dce01"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(request);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tData.cancel(false);
    }

    private void init() {
        urlBean= Util.loadSetting(getActivity());
        rl_temperature= (RelativeLayout) view.findViewById(R.id.rl_temperature);
        rl_temperature.setOnClickListener(this);
        rl_humidity= (RelativeLayout) view.findViewById(R.id.rl_humidity);
        rl_humidity.setOnClickListener(this);
        rl_illumination= (RelativeLayout) view.findViewById(R.id.rl_illumination);
        rl_illumination.setOnClickListener(this);
        rl_CO2= (RelativeLayout) view.findViewById(R.id.rl_CO2);
        rl_CO2.setOnClickListener(this);
        rl_PM25= (RelativeLayout) view.findViewById(R.id.rl_PM25);
        rl_PM25.setOnClickListener(this);
        rl_roadStatus= (RelativeLayout) view.findViewById(R.id.rl_roadStatus);
        rl_roadStatus.setOnClickListener(this);

        tv_temperature= (TextView) view.findViewById(R.id.tv_temperature);
        tv_humidity= (TextView) view.findViewById(R.id.tv_humidity);
        tv_illumination= (TextView) view.findViewById(R.id.tv_illumination);
        tv_CO2= (TextView) view.findViewById(R.id.tv_CO2);
        tv_PM25= (TextView) view.findViewById(R.id.tv_PM25);
        tv_roadStatus= (TextView) view.findViewById(R.id.tv_roadStatus);

        try {
            loadSettingLoad = getActivity().getSharedPreferences("thresholdSetting", MODE_PRIVATE);
            temperature= Integer.parseInt(loadSettingLoad.getString("temperature",""));
            humidity= Integer.parseInt(loadSettingLoad.getString("humidity",""));
            light= Integer.parseInt(loadSettingLoad.getString("light",""));
            co2= Integer.parseInt(loadSettingLoad.getString("co2",""));
            pm25= Integer.parseInt(loadSettingLoad.getString("pm25",""));
            roadStatus= Integer.parseInt(loadSettingLoad.getString("roadStatus",""));
        } catch (NumberFormatException e) {
            temperature=0;
            humidity=0;
            light=0;
            co2=0;
            pm25=0;
            roadStatus=0;
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_temperature:
                Log.d(TAG, "onClick: 1");
                break;
            case R.id.rl_humidity:
                Log.d(TAG, "onClick: 2");
                break;
            case R.id.rl_illumination:
                Log.d(TAG, "onClick: 3");
                break;
            case R.id.rl_CO2:
                Log.d(TAG, "onClick: 4");
                break;
            case R.id.rl_PM25:
                Log.d(TAG, "onClick: 5");
                break;
            case R.id.rl_roadStatus:
                Log.d(TAG, "onClick: 6");
                break;
        }
    }
}
