package com.mad.trafficclient.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_16 extends Fragment {

    private UrlBean urlBean;
    private TextView tv_pm25,tv_temperature,tv_humidity,tv_l_1,tv_l_2,tv_l_3,tv_t_1,tv_t_2,tv_t_3;
    private View view;
    private Handler handler;
    private String TAG="AAA";
    private List<TextView> lList;
    private List<TextView> tList;
    private ScheduledFuture<?> schedule;

    public Fragment_16() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_fragment_16, container, false);
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
                getL();
            }
        },0,3, TimeUnit.SECONDS);

    }

    private void getL() {
        for (int i=0;i<3;i++){
            RequestQueue queue=Volley.newRequestQueue(getActivity());
            JSONObject object=new JSONObject();
            try {
                int x=i+1;
                object.put("RoadId",x);
                object.put("UserName","user");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            final int finalI = i;
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/action/GetRoadStatus.do", object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
//                    Log.d(TAG, "onResponse: "+jsonObject.toString());
                    int x=finalI+1;
                    try {
                        lList.get(finalI).setText(x+"号道路："+l(jsonObject.getInt("Status")));
                        tList.get(finalI).setBackgroundColor(Color.parseColor(t(jsonObject.getInt("Status"))));
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
                            tv_humidity.setText("湿    度:"+jsonObject.getString("humidity"));
                            tv_pm25.setText("PM2.5:"+jsonObject.getString("pm2.5"));
                            tv_temperature.setText("温    度:"+jsonObject.getString("temperature"));
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
        handler=new Handler(Looper.getMainLooper());
        urlBean=Util.loadSetting(getActivity());
        tv_pm25= (TextView) view.findViewById(R.id.tv_pm25);
        tv_temperature= (TextView) view.findViewById(R.id.tv_temperature);
        tv_humidity= (TextView) view.findViewById(R.id.tv_humidity);

        tv_l_1= (TextView) view.findViewById(R.id.tv_l_1);
        tv_l_2= (TextView) view.findViewById(R.id.tv_l_2);
        tv_l_3= (TextView) view.findViewById(R.id.tv_l_3);
        lList=new ArrayList<>();
        lList.add(tv_l_1);
        lList.add(tv_l_2);
        lList.add(tv_l_3);

        tv_t_1= (TextView) view.findViewById(R.id.tv_t_1);
        tv_t_2= (TextView) view.findViewById(R.id.tv_t_2);
        tv_t_3= (TextView) view.findViewById(R.id.tv_t_3);
        tList=new ArrayList<>();
        tList.add(tv_t_1);
        tList.add(tv_t_2);
        tList.add(tv_t_3);
    }

    private String l(int y){
        switch (y){
            case 1:
                return "通畅";
            case 2:
                return "较通畅";
            case 3:
                return "拥挤";
            case 4:
                return "堵塞";
            case 5:
                return "爆表";
        }
        return null;
    }
    private String t(int y){
        switch (y){
            case 1:
                return "#0ebd12";
            case 2:
                return "#98ed1f";
            case 3:
                return "#ffff01";
            case 4:
                return "#ff0103";
            case 5:
                return "#4c060e";
        }
        return null;
    }

}
