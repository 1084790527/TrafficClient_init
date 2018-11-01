package com.mad.trafficclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.R;
import com.mad.trafficclient.adapter.F_12_list_Adapter;
import com.mad.trafficclient.bean.F_12_Bean;
import com.mad.trafficclient.bean.UserBean;
import com.mad.trafficclient.util.UrlBean;
import com.mad.trafficclient.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_12 extends Fragment {


    private View view;
    private ImageView iv_icon;
    private TextView tv_user,tv_name,tv_sex,tv_num,tv_identification,tv_time;
    private ListView lv_car;
    private List<F_12_Bean> list;
    private F_12_list_Adapter adapter;
    private UrlBean urlBean;
    private final String TAG="AAA";

    public Fragment_12() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_layout12, container, false);

        iniv();

        init();

        return view;
    }

    private void init() {
       /* for (int i=0;i<5;i++){
            F_12_Bean bean=new F_12_Bean();
            list.add(bean);
            adapter.notifyDataSetChanged();
        }*/

        setData();
    }

    private void setData() {
        JSONObject object=new JSONObject();
        try {
            object.put("UserName","user");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://"+urlBean.getUrl()+":"+urlBean.getPort()+"/transportservice/action/GetSUserInfo.do", object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
//                Log.d(TAG, "onResponse: "+jsonObject.toString());
                try {
                    JSONArray jsonArray=jsonObject.getJSONArray("ROWS_DETAIL");
                    Datas(jsonArray.getJSONObject(0));
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

        JsonObjectRequest request1=new JsonObjectRequest(Request.Method.POST, "http://"+urlBean.getUrl()+":"+urlBean.getPort()+"/transportservice/action/GetCarInfo.do", object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
//                Log.d(TAG, "onResponse: "+jsonObject.toString());
                try {
                    setCar(jsonObject.getJSONArray("ROWS_DETAIL"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(request1);
    }

    private void setCar(JSONArray array) throws JSONException {
        for (int i=0;i<array.length();i++){
            JSONObject object=array.getJSONObject(i);
            F_12_Bean bean=new F_12_Bean();
            bean.setBalance("余额：666");
            bean.setLicense(object.getString("carnumber"));
            bean.setIcon(carbrand(object.getString("carbrand")));
            list.add(bean);
            adapter.notifyDataSetChanged();
        }
    }

    private void Datas(JSONObject jsonObject) throws JSONException {
        tv_name.setText("姓名："+jsonObject.getString("pname")+"");
        tv_num.setText("手机："+jsonObject.getString("ptel")+"");
        tv_sex.setText("性别："+jsonObject.getString("psex")+"");
        tv_user.setText("用户名："+jsonObject.getString("username"));
        tv_identification.setText("身份证号码："+jsonObject.getString("pcardid")+"");
        tv_time.setText("注册时间："+jsonObject.getString("pregistdate"+""));
    }

    private void iniv() {
        urlBean= Util.loadSetting(getActivity());
        iv_icon= (ImageView) view.findViewById(R.id.iv_icon);
        tv_user= (TextView) view.findViewById(R.id.tv_user);
        tv_name= (TextView) view.findViewById(R.id.tv_name);
        tv_sex= (TextView) view.findViewById(R.id.tv_sex);
        tv_num= (TextView) view.findViewById(R.id.tv_num);
        tv_identification= (TextView) view.findViewById(R.id.tv_identification);
        tv_time= (TextView) view.findViewById(R.id.tv_time);
        lv_car= (ListView) view.findViewById(R.id.lv_car);
        list=new ArrayList<>();
        adapter=new F_12_list_Adapter(getActivity(),list);
        lv_car.setAdapter(adapter);
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
