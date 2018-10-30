package com.mad.trafficclient.fragment;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_10 extends Fragment implements View.OnClickListener {

    private static final String TAG = "AAA";
    private View view;
    private TextView tv_title_1,tv_title_2,tv_time_1_1,tv_distance_1_1,tv_time_1_2,tv_distance_1_2,tv_time_2_1,tv_distance_2_1,tv_time_2_2,tv_distance_2_2;
    private LinearLayout ll_1_1,ll_1_2,ll_2_1,ll_2_2;
    private ScheduledFuture schedule;
    private Button btn_details;
    private UrlBean urlBean;

    public Fragment_10() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_layout10, container, false);

        iniv();

        init();

        return view;
    }

    private void init() {
        tv_title_1.setOnClickListener(this);
        tv_title_2.setOnClickListener(this);
        btn_details.setOnClickListener(this);

        schedule=Executors.newScheduledThreadPool(2).scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                setData(1);
                setData(2);
            }
        },0,3, TimeUnit.SECONDS);
    }

    private void setData(final int id) {
        JSONObject object=new JSONObject();
        try {
            object.put("BusStationId",1);
            object.put("UserName","sdsds");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://"+urlBean.getUrl()+":"+urlBean.getPort()+"/transportservice/action/GetBusStationInfo.do", object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray=jsonObject.getJSONArray("ROWS_DETAIL");
                    if (id==1){
                        int i=jsonArray.getJSONObject(0).getInt("Distance");
                        int time=i/1000;
                        tv_time_1_1.setText(time+"分钟到达");
                        tv_distance_1_1.setText("距离站台"+i+"米");
                        i=jsonArray.getJSONObject(1).getInt("Distance");
                        time=i/1000;
                        tv_time_1_2.setText(time+"分钟到达");
                        tv_distance_1_2.setText("距离站台"+i+"米");
                    }else {
                        int i=jsonArray.getJSONObject(0).getInt("Distance");
                        int time=i/1000;
                        tv_time_2_1.setText(time+"分钟到达");
                        tv_distance_2_1.setText("距离站台"+i+"米");
                        i=jsonArray.getJSONObject(1).getInt("Distance");
                        time=i/1000;
                        tv_time_2_2.setText(time+"分钟到达");
                        tv_distance_2_2.setText("距离站台"+i+"米");
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

    private void iniv() {
        urlBean= Util.loadSetting(getActivity());
        btn_details= (Button) view.findViewById(R.id.btn_details);

        tv_title_1= (TextView) view.findViewById(R.id.tv_title_1);
        ll_1_1= (LinearLayout) view.findViewById(R.id.ll_1_1);
        ll_1_2= (LinearLayout) view.findViewById(R.id.ll_1_2);
        tv_title_2= (TextView) view.findViewById(R.id.tv_title_2);
        ll_2_1= (LinearLayout) view.findViewById(R.id.ll_2_1);
        ll_2_2= (LinearLayout) view.findViewById(R.id.ll_2_2);

        tv_time_1_1= (TextView) view.findViewById(R.id.tv_time_1_1);
        tv_distance_1_1= (TextView) view.findViewById(R.id.tv_distance_1_1);
        tv_time_1_2= (TextView) view.findViewById(R.id.tv_time_1_2);
        tv_distance_1_2= (TextView) view.findViewById(R.id.tv_distance_1_2);

        tv_time_2_1= (TextView) view.findViewById(R.id.tv_time_2_1);
        tv_distance_2_1= (TextView) view.findViewById(R.id.tv_distance_2_1);
        tv_time_2_2= (TextView) view.findViewById(R.id.tv_time_2_2);
        tv_distance_2_2= (TextView) view.findViewById(R.id.tv_distance_2_2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_title_1:
                if ("▼    中医院站".equals(tv_title_1.getText().toString())){
                    tv_title_1.setText("▶    中医院站");
                    ll_1_1.setVisibility(View.GONE);
                    ll_1_2.setVisibility(View.GONE);
                }else {
                    tv_title_1.setText("▼    中医院站");
                    ll_1_1.setVisibility(View.VISIBLE);
                    ll_1_2.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_title_2:
                if ("▼    联想大厦站".equals(tv_title_2.getText().toString())){
                    tv_title_2.setText("▶    联想大厦站");
                    ll_2_1.setVisibility(View.GONE);
                    ll_2_2.setVisibility(View.GONE);
                }else {
                    tv_title_2.setText("▼    联想大厦站");
                    ll_2_1.setVisibility(View.VISIBLE);
                    ll_2_2.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_details:
//                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
//                View v;
//                builder.setView(v).create();
//
//                AlertDialog dialog=builder.show();
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        schedule.cancel(false);
    }
}
