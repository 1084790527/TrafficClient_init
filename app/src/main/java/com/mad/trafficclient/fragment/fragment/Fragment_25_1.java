package com.mad.trafficclient.fragment.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.GuideActivity;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.UrlBean;
import com.mad.trafficclient.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_25_1 extends Fragment {

    private static final String TAG = "AAA";
    private View view;
    private GridLayout gl_gl;
    private UrlBean urlBean;
    private ImageView iv_icon;
    private TextView tv_name,tv_sex,tv_num,tv_sfz,tv_zcstime;

    public Fragment_25_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_fragment_25_1, container, false);
        iniv();
        init();
        return view;
    }

    private void init() {
        getNet();
    }

    private void getNet() {
        JSONObject object=new JSONObject();
        try {
            object.put("UserName","user");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/action/GetSUserInfo.do", object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
//                Log.d(TAG, "onResponse: "+jsonObject.toString());
                try {
                    JSONArray jsonArray=jsonObject.getJSONArray("ROWS_DETAIL");
                    JSONObject jb=jsonArray.getJSONObject(0);
                    tv_name.setText("姓名："+jb.getString("pname")+"");
                    tv_sex.setText("性别："+jb.getString("psex")+"");
                    tv_num.setText("手机号码："+jb.getString("ptel")+"");
                    tv_sfz.setText("身份证："+jb.getString("pcardid")+"");
                    tv_zcstime.setText("注册时间："+jb.getString("pregistdate")+"");
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

        RequestQueue queue1=Volley.newRequestQueue(getActivity());
        JsonObjectRequest request1=new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/action/GetCarInfo.do", object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
//                Log.d(TAG, "onResponse: "+jsonObject.toString());
                try {
                    JSONArray jsonArray=jsonObject.getJSONArray("ROWS_DETAIL");
                    for (int i=0;i<jsonArray.length();i++){
                        View view=LayoutInflater.from(getActivity()).inflate(R.layout.f_25_1_gl_view,null);
                        ((ImageView)view.findViewById(R.id.iv_icon)).setImageResource(carbrand(jsonArray.getJSONObject(i).getString("carbrand")));
                        ((TextView)view.findViewById(R.id.tv_cph)).setText(jsonArray.getJSONObject(i).getString("carnumber")+"");
                        GridLayout.LayoutParams params=new GridLayout.LayoutParams();
                        params.setMargins(0,0,100,50);
                        gl_gl.addView(view,params);
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
        queue1.add(request1);
    }

    private void iniv() {
        urlBean= Util.loadSetting(getActivity());
        gl_gl= (GridLayout) view.findViewById(R.id.gl_gl);
        gl_gl.setColumnCount(4);
//        GridLayout.LayoutParams params=new GridLayout.LayoutParams();
//        params.setMargins(0,0,250,50);
       /* gl_gl.addView(LayoutInflater.from(getActivity()).inflate(R.layout.f_25_1_gl_view,null),params);
        params=new GridLayout.LayoutParams();
        params.setMargins(0,0,250,50);
        gl_gl.addView(LayoutInflater.from(getActivity()).inflate(R.layout.f_25_1_gl_view,null),params);
        params=new GridLayout.LayoutParams();
        params.setMargins(0,0,250,50);
        gl_gl.addView(LayoutInflater.from(getActivity()).inflate(R.layout.f_25_1_gl_view,null),params);
        params=new GridLayout.LayoutParams();
        params.setMargins(0,0,250,50);
        gl_gl.addView(LayoutInflater.from(getActivity()).inflate(R.layout.f_25_1_gl_view,null),params);
        params=new GridLayout.LayoutParams();
        params.setMargins(0,0,250,50);
        gl_gl.addView(LayoutInflater.from(getActivity()).inflate(R.layout.f_25_1_gl_view,null),params);
        params=new GridLayout.LayoutParams();
        params.setMargins(0,0,250,50);
        gl_gl.addView(LayoutInflater.from(getActivity()).inflate(R.layout.f_25_1_gl_view,null),params);*/

        iv_icon= (ImageView) view.findViewById(R.id.iv_icon);
        tv_name= (TextView) view.findViewById(R.id.tv_name);
        tv_sex= (TextView) view.findViewById(R.id.tv_sex);
        tv_num= (TextView) view.findViewById(R.id.tv_num);
        tv_sfz= (TextView) view.findViewById(R.id.tv_sfz);
        tv_zcstime= (TextView) view.findViewById(R.id.tv_zcstime);
    }
    private int carbrand(String s){
        switch (s){
            case "audi":
                return R.drawable.audi;
            case "baoma":
                return R.drawable.baoma;
            case "benchi":
                return R.drawable.benchi;
            case "bentian":
                return R.drawable.bentian;
            case "biaozhi":
                return R.drawable.biaozhi;
            case "bieke":
                return R.drawable.bieke;
            case "biyadi":
                return R.drawable.biyadi;
            case "dazhong":
                return R.drawable.dazhong;
            case "fengtian":
                return R.drawable.fengtian;
            case "fute":
                return R.drawable.fute;
            case "mazhida":
                return R.drawable.mazhida;
            case "qirui":
                return R.drawable.qirui;
            case "richan":
                return R.drawable.richan;
            case "sanling":
                return R.drawable.sanling;
            case "sibalu":
                return R.drawable.sibalu;
            case "tesila":
                return R.drawable.tesila;
            case "voervo":
                return R.drawable.voervo;
            case "xiandai":
                return R.drawable.xiandai;
            case "xuefulan":
                return R.drawable.xuefulan;
            case "zhonghua":
                return R.drawable.zhonghua;
            default:
                return R.drawable.zhonghua;
        }
    }
}
