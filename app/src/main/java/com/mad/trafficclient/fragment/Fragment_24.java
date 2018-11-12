package com.mad.trafficclient.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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

import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_24 extends Fragment implements View.OnClickListener {

    private String TAG="AAA";
    private View view;
    private TextView l_1,l_2,l_3,l_4,l_5,l_6,l_7,l_8,tv_date,tv_week,tv_wd,tv_sd,tv_pm;
    private List<TextView> tvList;
    private UrlBean urlBean;
    private ScheduledFuture<?> scheduledFuture;
    private ImageView iv_refresh,iv_jj1,iv_jj2;
    private boolean jj;
    private Handler handler;

    public Fragment_24() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_24, container, false);
        iniv();
        init();
        return view;
    }

    private void init() {
        scheduledFuture=Executors.newScheduledThreadPool(3).scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                setl();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (jj){
                            iv_jj1.setImageResource(R.drawable.jiaojing1_1);
                            iv_jj2.setImageResource(R.drawable.jiaojing2_1);
                            jj=false;
                        }else {
                            iv_jj1.setImageResource(R.drawable.jiaojing1_2);
                            iv_jj2.setImageResource(R.drawable.jiaojing2_2);
                            jj=true;
                        }
                    }
                });

            }
        },0,3, TimeUnit.SECONDS);

        setWSD();
        SimpleDateFormat format=new SimpleDateFormat("yy-MM-dd");
        Date date=new Date(System.currentTimeMillis());
        tv_date.setText(format.format(date)+"");
        SimpleDateFormat format1=new SimpleDateFormat("E");
        String week=format1.format(date);
        tv_week.setText("星期"+week.substring(1)+"");
    }

    private void setWSD() {
        JSONObject object=new JSONObject();
        try {
            object.put("UserName","user");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue queue=Volley.newRequestQueue(getActivity());
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/action/GetAllSense.do", object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
//                Log.d(TAG, "onResponse: "+jsonObject.toString());
                try {
                    tv_wd.setText("温度："+jsonObject.getString("temperature")+"℃");
                    tv_sd.setText("相对湿度："+jsonObject.getString("humidity")+"%");
                    tv_pm.setText("PM2.5："+jsonObject.getString("pm2.5")+"μg/m3");
                } catch (Exception e) {
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
        scheduledFuture.cancel(false);
    }

    private void setl() {
        for (int i=0;i<tvList.size();i++){
            int x=i+1;
            JSONObject object=new JSONObject();
            try {
                object.put("RoadId",x);
                object.put("UserName","user");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestQueue queue= Volley.newRequestQueue(getActivity());
            final int finalI = i;
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/action/GetRoadStatus.do", object,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
//                            Log.d(TAG, "onResponse: "+jsonObject.toString());
                            try {
                                tvList.get(finalI).setBackgroundColor(Color.parseColor(ys(jsonObject.getInt("Status"))));
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

    private void iniv() {
        l_1= (TextView) view.findViewById(R.id.l_1);
        l_2= (TextView) view.findViewById(R.id.l_2);
        l_3= (TextView) view.findViewById(R.id.l_3);
        l_4= (TextView) view.findViewById(R.id.l_4);
        l_5= (TextView) view.findViewById(R.id.l_5);
        l_6= (TextView) view.findViewById(R.id.l_6);
        l_7= (TextView) view.findViewById(R.id.l_7);
        l_8= (TextView) view.findViewById(R.id.l_8);
        tvList=new ArrayList<>();
        tvList.add(l_1);
        tvList.add(l_2);
        tvList.add(l_3);
        tvList.add(l_4);
        tvList.add(l_5);
        tvList.add(l_6);
        tvList.add(l_7);
        tvList.add(l_8);
        urlBean= Util.loadSetting(getActivity());

        tv_date= (TextView) view.findViewById(R.id.tv_date);
        tv_week= (TextView) view.findViewById(R.id.tv_week);
        tv_wd= (TextView) view.findViewById(R.id.tv_wd);
        tv_sd= (TextView) view.findViewById(R.id.tv_sd);
        tv_pm= (TextView) view.findViewById(R.id.tv_pm);
        iv_refresh= (ImageView) view.findViewById(R.id.iv_refresh);
        iv_jj1= (ImageView) view.findViewById(R.id.iv_jj1);
        iv_jj2= (ImageView) view.findViewById(R.id.iv_jj2);

        jj=true;
        handler=new Handler(Looper.getMainLooper());

        iv_refresh.setOnClickListener(this);

    }

    private String ys(int y){
        switch (y){
            case 1:
                return "#6ab82e";
            case 2:
                return "#ece93a";
            case 3:
                return "#f49b25";
            case 4:
                return "#e33532";
            case 5:
                return "#b01e23";
            default:
                return "#ffffff";
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_refresh:
                setWSD();
                break;
        }
    }
}
