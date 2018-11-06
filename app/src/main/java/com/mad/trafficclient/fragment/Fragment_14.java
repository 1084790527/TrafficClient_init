package com.mad.trafficclient.fragment;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.R;
import com.mad.trafficclient.adapter.F_14_list_Adapter;
import com.mad.trafficclient.bean.F_14_Bean;
import com.mad.trafficclient.util.UrlBean;
import com.mad.trafficclient.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_14 extends Fragment implements F_14_list_Adapter.onDeleteListener, View.OnClickListener {

    private static final String TAG = "AAA";
    private View view;
    private ListView lv_list;
    private List<F_14_Bean> list;
    private F_14_list_Adapter adapter;
    private UrlBean urlBean;
    private TextView tv_recharge_record;

    public Fragment_14() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_14, container, false);
        iniv();
        init();
        return view;
    }

    private void init() {
//        for (int i=0;i<10;i++){
//            F_14_Bean bean=new F_14_Bean();
//            bean.setId(i+1);
//            bean.setMoney(100);
//            bean.setT(true);
//            list.add(bean);
//            adapter.notifyDataSetInvalidated();
//        }
        getData(true);
    }

    private void getData(final boolean t) {
        for (int i=0;i<4;i++){
            RequestQueue queue= Volley.newRequestQueue(getActivity());
            JSONObject object=new JSONObject();
            try {
                object.put("CarId",i+1);
                object.put("UserName","user");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            final int finalI = i;
            JsonObjectRequest reques=new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/action/GetCarAccountBalance.do", object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Log.d(TAG, "onResponse: "+jsonObject.toString());
                    if (t){
                        F_14_Bean bean=new F_14_Bean();
                        bean.setId(finalI+1);
                        try {
                            bean.setMoney(jsonObject.getInt("Balance"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        bean.setT(false);
                        list.add(bean);
                        Collections.sort(list);
                    }else {
                        try {
                            list.get(finalI).setMoney(jsonObject.getInt("Balance"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetInvalidated();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
            queue.add(reques);
        }

    }

    private void iniv() {
        urlBean= Util.loadSetting(getActivity());
        lv_list= (ListView) view.findViewById(R.id.lv_list);
        list=new ArrayList<>();
        adapter=new F_14_list_Adapter(getActivity(),list);
        adapter.setOnDeleteOnClickListener(this);
        lv_list.setAdapter(adapter);
        tv_recharge_record= (TextView) view.findViewById(R.id.tv_recharge_record);
        tv_recharge_record.setOnClickListener(this);
    }

    @Override
    public void onDeleteOnClick(int i) {
        Log.d(TAG, "onDeleteOnClick: "+i);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View dialog=LayoutInflater.from(getActivity()).inflate(R.layout.dialog_14,null);
        final int x=i+1;
        ((TextView)dialog.findViewById(R.id.tv_numberPlate)).setText("车牌编号："+x);
        final EditText number= (EditText) dialog.findViewById(R.id.et_morey);
        Button btn_recharge= (Button) dialog.findViewById(R.id.btn_recharge);
        Button btn_cancel= (Button) dialog.findViewById(R.id.btn_cancel);

        builder.setView(dialog);
        final AlertDialog alertDialog=builder.show();
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        btn_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: "+number.getText());
                String s="0";
                try {
                    s= String.valueOf(number.getText());
                }catch (Exception e){
                    Toast.makeText(getActivity(),"请先输入！",Toast.LENGTH_LONG).show();
                    return;
                }
                double d= Double.parseDouble(s);
                int y= (int) d;
                if (y<1||y>999){
                    Toast.makeText(getActivity(),"请输入1~999之间的数字！",Toast.LENGTH_LONG).show();
                    return;
                }
                RequestQueue queue=Volley.newRequestQueue(getActivity());
                JSONObject object=new JSONObject();
                try {
                    object.put("CarId",x);
                    object.put("Money",y);
                    object.put("UserName","user");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/action/SetCarAccountRecharge.do", object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                try {
                                    String s=jsonObject.getString("RESULT");
                                    if (s.equals("S")){
                                        Toast.makeText(getActivity(),"充值成功" ,Toast.LENGTH_LONG).show();
                                        getData(false);
                                        alertDialog.dismiss();
                                    }else {
                                        Toast.makeText(getActivity(),"充值失败",Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getActivity(),"充值失败!",Toast.LENGTH_LONG).show();
                    }
                });
                queue.add(request);
            }
        });
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: "+list.toString());
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View dialog=LayoutInflater.from(getActivity()).inflate(R.layout.dialog_14,null);

        final List<Integer> integers=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            if (list.get(i).getT()){
                integers.add(list.get(i).getId());
            }
        }
        String s="";
        if (integers.size()==0){
            Toast.makeText(getActivity(),"请选择车辆",Toast.LENGTH_LONG).show();
            return;
        }
        for (int i=0;i<integers.size();i++){
            s+=integers.get(i)+"   ";
        }
        ((TextView)dialog.findViewById(R.id.tv_numberPlate)).setText("车牌编号："+s);

        final EditText et_morey= (EditText) dialog.findViewById(R.id.et_morey);
        Button btn_recharge= (Button) dialog.findViewById(R.id.btn_recharge);
        Button btn_cancel= (Button) dialog.findViewById(R.id.btn_cancel);
        builder.setView(dialog);
        final AlertDialog alertDialog=builder.show();
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        btn_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s="0";
                try {
                    s= String.valueOf(et_morey.getText());
                }catch (Exception e){
                    Toast.makeText(getActivity(),"请先输入！",Toast.LENGTH_LONG).show();
                    return;
                }
                double d= Double.parseDouble(s);
                int y= (int) d;
                if (y<1||y>999){
                    Toast.makeText(getActivity(),"请输入1~999之间的数字！",Toast.LENGTH_LONG).show();
                    return;
                }
                for (int i=0;i<integers.size();i++){
                    RequestQueue queue=Volley.newRequestQueue(getActivity());
                    JSONObject object=new JSONObject();
                    try {
                        int t=integers.get(i);
                        object.put("CarId",t);
                        object.put("Money",y);
                        object.put("UserName","user");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/action/SetCarAccountRecharge.do", object,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        String s=jsonObject.getString("RESULT");
                                        if (s.equals("S")){
                                            Toast.makeText(getActivity(),"充值成功" ,Toast.LENGTH_LONG).show();
                                            getData(false);
                                            alertDialog.dismiss();
                                        }else {
                                            Toast.makeText(getActivity(),"充值失败",Toast.LENGTH_LONG).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(getActivity(),"充值失败!",Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(request);
                }
            }
        });
    }
}
