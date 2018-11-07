package com.mad.trafficclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_17 extends Fragment {

    private View view;
    private UrlBean urlBean;
    private TextView tv_pm25,tv_temperature,tv_humidity;
    private ScheduledFuture<?> schedule;
    private TextView tv_pm_qd,tv_pm_ts,tv_humidity_qd,tv_humidity_ts;

    public Fragment_17() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_fragment_17, container, false);
        iniv();
        init();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        schedule.cancel(false);
    }

    private void init() {
        schedule=Executors.newScheduledThreadPool(3).scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                getT();
            }
        },0,3, TimeUnit.SECONDS);
    }

    private void getT() {
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        JSONObject object=new JSONObject();
        try {
            object.put("UserName","user");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/action/GetAllSense.do", object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
//                        Log.d(TAG, "onResponse: "+jsonObject.toString());
                        try {
                            int pm=jsonObject.getInt("pm2.5");
                            int humidity=jsonObject.getInt("humidity");
                            tv_humidity.setText("湿    度:"+humidity);
                            tv_pm25.setText("PM2.5:"+pm);
                            tv_temperature.setText("温    度:"+jsonObject.getString("temperature"));

                            tv_pm_qd.setText(pmqd(pm)+"");
                            tv_pm_ts.setText(pmts(pm)+"");

                            tv_humidity_qd.setText(hqd(jsonObject.getInt("LightIntensity"))+"");
                            tv_humidity_ts.setText(hts(jsonObject.getInt("LightIntensity"))+"");

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

    private void iniv() {
        urlBean= Util.loadSetting(getActivity());
        tv_pm25= (TextView) view.findViewById(R.id.tv_pm25);
        tv_temperature= (TextView) view.findViewById(R.id.tv_temperature);
        tv_humidity= (TextView) view.findViewById(R.id.tv_humidity);

        tv_pm_qd= (TextView) view.findViewById(R.id.tv_pm_qd);
        tv_pm_ts= (TextView) view.findViewById(R.id.tv_pm_ts);
        tv_humidity_qd= (TextView) view.findViewById(R.id.tv_humidity_qd);
        tv_humidity_ts= (TextView) view.findViewById(R.id.tv_humidity_ts);

    }

    private String pmqd(int i){
        if (i>=0&&i<100){
            return "良好";
        }else if (i>=100&&i<200){
            return "轻度";
        }else if (i>=200&&i<300){
            return "重度";
        }else{
            return "爆表";
        }
    }
    private String pmts(int i){
        if (i>=0&&i<100){
            return "气象条件会对晨练影响不大";
        }else if (i>=100&&i<200){
            return "受天气影响，较不宜晨练";
        }else if (i>=200&&i<300){
            return "减少外出，出行注意戴口罩";
        }else{
            return "停止一切外出活动";
        }
    }

    private String hqd(int i){
        if (i>=0&&i<1000){
            return "非常弱";
        }else if (i>=1000&&i<2000){
            return "弱";
        }else {
            return "强";
        }
    }
    private String hts(int i){
        if (i>=0&&i<1000){
            return "您无需担心紫外线";
        }else if (i>=1000&&i<2000){
            return "外出适当涂抹低倍数防晒霜";
        }else {
            return "外出需要涂抹中倍数防晒霜";
        }
    }

}
