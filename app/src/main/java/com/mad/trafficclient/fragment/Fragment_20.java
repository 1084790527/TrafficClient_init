package com.mad.trafficclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_20 extends Fragment implements View.OnClickListener {

    private static final String TAG = "AAA";
    private View view;
    private LinearLayout ll_forecast;
    private ImageView iv_refresh,iv_icon;
    private TextView tv_date,tv_city,tv_temperature;
    private UrlBean urlBean;

    public Fragment_20() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_fragment_20, container, false);
        iniv();
        init();
        return view;
    }

    private void init() {
//        View layout=LayoutInflater.from(getActivity()).inflate(R.layout.f_20_ll,null);
//        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT);
//        params.weight=1;
//        ll_forecast.addView(layout,params);
//
//        View layout1=LayoutInflater.from(getActivity()).inflate(R.layout.f_20_ll,null);
//        LinearLayout.LayoutParams params1=new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT);
//        params1.weight=1;
//        ll_forecast.addView(layout1,params1);
//
//        View layout2=LayoutInflater.from(getActivity()).inflate(R.layout.f_20_ll,null);
//        LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT);
//        params2.weight=1;
//        ll_forecast.addView(layout2,params2);
//
//        View layout3=LayoutInflater.from(getActivity()).inflate(R.layout.f_20_ll,null);
//        LinearLayout.LayoutParams params3=new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT);
//        params3.weight=1;
//        ll_forecast.addView(layout3,params3);
//
//        View layout4=LayoutInflater.from(getActivity()).inflate(R.layout.f_20_ll,null);
//        LinearLayout.LayoutParams params4=new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT);
//        params4.weight=1;
//        ll_forecast.addView(layout4,params4);

        getNetData();
    }

    private void getNetData() {
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        JSONObject object=new JSONObject();
        try {
            object.put("UserName","user");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/action/GetWeather.do", object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                ll_forecast.removeAllViews();
//                Log.d(TAG, "onResponse: " + jsonObject.toString());
                try {
                    JSONArray jsonArray=jsonObject.getJSONArray("ROWS_DETAIL");
                    tv_date.setText(jsonArray.getJSONObject(0).getString("WData")+"  周四");
                    tv_temperature.setText(jsonObject.getString("WCurrent")+"");
                    for (int i=0;i<5;i++){
                        JSONObject ob=jsonArray.getJSONObject(i);
                        View view=LayoutInflater.from(getActivity()).inflate(R.layout.f_20_ll,null);
                        ((TextView)view.findViewById(R.id.tv_date)).setText(ob.getString("WData")+""+zou(i));
                        ((TextView)view.findViewById(R.id.tv_tmperature)).setText(ob.getString("temperature")+"");
                        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(0, RadioGroup.LayoutParams.MATCH_PARENT,1);
                        ll_forecast.addView(view,params);
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

    private String zou(int i){
        switch (i){
            case 0:
                return "今天";
            case 1:
                return "明天";
            case 2:
                return "后天";
            case 3:
                return "大后天";
            case 4:
                return "大后天";
            case 5:
                return "后天";
            case 6:
                return "后天";
            default:
                return "XXX";
        }
    }

    private void iniv() {
        urlBean= Util.loadSetting(getActivity());
        ll_forecast= (LinearLayout) view.findViewById(R.id.ll_forecast);
        iv_refresh= (ImageView) view.findViewById(R.id.iv_refresh);
        iv_refresh.setOnClickListener(this);
        iv_icon= (ImageView) view.findViewById(R.id.iv_icon);
        tv_date= (TextView) view.findViewById(R.id.tv_date);
        tv_city= (TextView) view.findViewById(R.id.tv_city);
        tv_temperature= (TextView) view.findViewById(R.id.tv_temperature);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_refresh:
                getNetData();
                break;
        }
    }
}
