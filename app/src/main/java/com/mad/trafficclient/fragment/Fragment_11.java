package com.mad.trafficclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.R;
import com.mad.trafficclient.adapter.F_11_list_Adapter;
import com.mad.trafficclient.bean.F_11_Bean;
import com.mad.trafficclient.util.UrlBean;
import com.mad.trafficclient.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_11 extends Fragment {

    private View view;
    private GridView gv_list;
    private F_11_list_Adapter adapter;
    private List<F_11_Bean> list;
    private UrlBean urlBean;
    private String TAG="AAA";
    private ScheduledFuture s;
    private boolean t;
    public Fragment_11() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_layout11, container, false);

        iniv();

        init();

        return view;
    }

    private void init() {
//        setData();
        getData();
        s=Executors.newScheduledThreadPool(3).scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                t=false;
                getData();
            }
        },3,3, TimeUnit.SECONDS);
    }

    private void getData() {
        JSONObject object=new JSONObject();
        try {
            object.put("UserName","user");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/action/GetAllSense.do", object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
//                Log.d(TAG, "onResponse: "+jsonObject.toString());
                try {
                    setLightIntensity(jsonObject.getInt("LightIntensity"));
                    setTemperature(jsonObject.getInt("temperature"));
                    setCo2(jsonObject.getInt("co2"));
                    setPm25(jsonObject.getInt("pm2.5"));
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

    private void setPm25(int anInt) {
        F_11_Bean bean;
        if (t){
            bean=new F_11_Bean();
            bean.setIcon(R.drawable.icon105);
            bean.setTitle("空气污染扩散指数");
        }else {
            bean=list.get(4);
        }

        String qd="";
        String xx="";
        if (anInt<30){
            qd="优";
            xx="空气质量非常好，非常适合户外活动，趁机出去多呼吸新鲜空气";
        }else if (anInt>100){
            qd="污染";
            xx="空气质量差，不适合户外活动";
        }else {
            qd="良";
            xx="易感人群应适当减少室外活动";
        }
        bean.setQd(qd);
        bean.setXx(xx);
        if (t)
            list.add(bean);
        adapter.notifyDataSetChanged();
    }

    private void setCo2(int co2) {
        F_11_Bean bean;
        if (t){
            bean=new F_11_Bean();
            bean.setIcon(R.drawable.icon104);
            bean.setTitle("运动指数");
        }else {
            bean=list.get(3);
        }

        String qd="";
        String xx="";
        if (co2<3000){
            qd="适宜";
            xx="气候适宜，推荐您进行户外运动";
        }else if (co2>6000){
            qd="较不宜";
            xx="空气氧气含量低，请在室内进行休闲运动";
        }else {
            qd="中";
            xx="易感人群应适当减少室外活动";
        }
        bean.setQd(qd);
        bean.setXx(xx);
        if (t)
            list.add(bean);
        adapter.notifyDataSetChanged();
    }

    private void setTemperature(int temperature) {
        F_11_Bean bean;
        if (t){
            bean=new F_11_Bean();
            bean.setIcon(R.drawable.icon102);
            bean.setTitle("感冒指数");
        }else {
            bean=list.get(1);
        }
        String qd="";
        String xx="";
        if (temperature<8){
            qd="较易发";
            xx="温度低，风较大，较易发生感冒，注意防护";
        }else {
            qd="少发";
            xx="无明显降温，感冒机率较低";
        }
        bean.setQd(qd);
        bean.setXx(xx);
        if (t)
            list.add(bean);
        adapter.notifyDataSetChanged();

        F_11_Bean bean1;
        if (t){
            bean1=new F_11_Bean();
            bean1.setIcon(R.drawable.icon103);
            bean1.setTitle("穿衣指数");
        }else {
            bean1=list.get(2);
        }

        String qd1="";
        String xx1="";
        if (temperature<8){
            qd1="冷";
            xx1="建议穿长袖衬衫、单裤等服装";
        }else if (temperature>21){
            qd1="热";
            xx1="适合穿T恤、短薄外套等夏季服装";
        }else {
            qd1="舒适";
            xx1="建议穿短袖衬衫、单裤等服装";
        }
        bean1.setQd(qd1);
        bean1.setXx(xx1);
        if (t)
            list.add(bean1);
        adapter.notifyDataSetChanged();
    }

    private void setLightIntensity(int lightIntensity) {
        F_11_Bean bean;
        if (t){
            bean=new F_11_Bean();
            bean.setIcon(R.drawable.icon101);
            bean.setTitle("紫外线指数");
        }else {
            bean=list.get(0);
        }
        String qd="";
        String xx="";
        if (lightIntensity<1000){
            qd="弱";
            xx="辐射较弱，涂擦SPF12~15、PA+护肤品";
        }else if (lightIntensity>3000){
            qd="强";
            xx="尽量减少外出，需要涂抹高倍数防晒霜";
        }else {
            qd="中等";
            xx="涂擦SPF大于15、PA+防晒护肤品";
        }
        bean.setQd(qd);
        bean.setXx(xx);
        if (t){
            list.add(bean);
        }
        adapter.notifyDataSetChanged();
    }

    private void setData() {
        for (int i=0;i<5;i++){
            F_11_Bean bean=new F_11_Bean();
            bean.setIcon(R.drawable.icon101);
            bean.setTitle("感冒指数");
            bean.setQd("弱(2)");
            bean.setXx("温度低，风较大，较易发生感冒，注意防护");
            list.add(bean);

        }
        adapter.notifyDataSetChanged();
    }

    private void iniv() {
        urlBean= Util.loadSetting(getActivity());
        gv_list= (GridView) view.findViewById(R.id.gv_list);
        list=new ArrayList<>();
//        F_11_Bean bean=new F_11_Bean();
//        bean.setIcon(R.drawable.icon101);
//        bean.setTitle("感冒指数");
//        bean.setQd("弱(2)");
//        bean.setXx("温度低，风较大，较易发生感冒，注意防护");
//        list.add(bean);
        adapter=new F_11_list_Adapter(getActivity(),list);
        gv_list.setAdapter(adapter);
        t=true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        s.cancel(false);
    }
}
