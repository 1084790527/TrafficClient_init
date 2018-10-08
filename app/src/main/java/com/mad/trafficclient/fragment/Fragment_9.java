package com.mad.trafficclient.fragment;


import android.app.AlertDialog;
import android.app.Notification;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
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
import com.mad.trafficclient.bean.UserBean;
import com.mad.trafficclient.util.LoadingDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.mad.trafficclient.util.LoadingDialog.dialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_9 extends Fragment implements ListAdapter_9.onItemDeleteListener {


    private static final String TAG = "AAA";
    private View view;
    private ListView lv_lv;
    private List<UserBean> viewList;
    private ListAdapter_9 adapter;

    public Fragment_9() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_layout09, container, false);

        iniv();

        init();

        test();

        return view;
    }

    private void test() {

    }


    private void init() {

        viewList = new ArrayList<UserBean>();
        adapter = new ListAdapter_9(viewList, getActivity());

        adapter.setOnItemDeleteClickListener(this);

        lv_lv.setAdapter(adapter);

        setUserName();

    }

    private void setBalance() {
        for (int i=0;i<viewList.size();i++){
            JSONObject object=new JSONObject();
            try {
                object.put("CarId",(i+1));
                object.put("UserName","dsds");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestQueue queue=Volley.newRequestQueue(getActivity());
            final int finalI = i;
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://172.168.30.65:8080/transportservice/action/GetCarAccountBalance.do", object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
//                    Log.d(TAG, "onResponse: "+jsonObject.toString());
                    try {
                        viewList.get(finalI).setMonery("余额："+jsonObject.getString("Balance"));
                        adapter.notifyDataSetChanged();
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

    private void setData() {
        JSONObject object=new JSONObject();
        try {
            object.put("UserName","aaaa");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue queue=Volley.newRequestQueue(getActivity());
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://172.168.30.65:8080/transportservice/action/GetCarInfo.do", object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
//                Log.d(TAG, "onResponse: "+jsonObject.toString());
                try {
                    JSONArray jsonArray=jsonObject.getJSONArray("ROWS_DETAIL");
                    for (int i=0;i<jsonArray.length();i++){
                        if (viewList.size()<=i){
//                            Log.d(TAG, "onResponse: "+jsonObject.toString());
                            UserBean bean=new UserBean();
                            bean.setImg(toCarnumber(jsonArray.getJSONObject(i).getString("carbrand")));
                            bean.setId(jsonArray.getJSONObject(i).getInt("number"));
                            bean.setCarNum(jsonArray.getJSONObject(i).getString("carnumber"));
                            viewList.add(bean);
                            adapter.notifyDataSetChanged();
                        }else {
                            viewList.get(i).setId(jsonArray.getJSONObject(i).getInt("number"));
                            viewList.get(i).setCarNum(jsonArray.getJSONObject(i).getString("carnumber"));
                            viewList.get(i).setImg(toCarnumber(jsonArray.getJSONObject(i).getString("carbrand")));
                            adapter.notifyDataSetChanged();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setBalance();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(request);
    }

    private void setUserName() {

        JSONObject object=new JSONObject();
        try {
            object.put("UserName","sdsds");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue queue=Volley.newRequestQueue(getActivity());
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://172.168.30.65:8080/transportservice/action/GetSUserInfo.do", object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
//                Log.d(TAG, "onResponse: "+jsonObject.toString());
                try {
                    JSONArray jsonArray=jsonObject.getJSONArray("ROWS_DETAIL");
                    for (int i=0;i<jsonArray.length();i++){
                        if (viewList.size()<=i){
                            UserBean bean=new UserBean();
                            bean.setCarUseName("车主："+jsonArray.getJSONObject(i).getString("pname"));
                            viewList.add(bean);
                            adapter.notifyDataSetChanged();
                        }else {
                            viewList.get(i).setCarUseName("车主："+jsonArray.getJSONObject(i).getString("pname"));
                            adapter.notifyDataSetChanged();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setData();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        queue.add(request);

    }

    private int toCarnumber(String carnumber){
        switch (carnumber){
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
        }
        return 0;
    }

    private void iniv() {
        lv_lv = (ListView) view.findViewById(R.id.lv_lv);
    }

    @Override
    public void onDeleteClick(final int i) {
//        Log.d(TAG, "onDeleteClick: " + i);
//        Log.d(TAG,"list>>>" + viewList.toString());
        String carNum = "";
        final List<Boolean> booleans=new ArrayList<>();
        for(int t=0;t<viewList.size();t++){
            booleans.add(viewList.get(t).getFlag());
            if (viewList.get(t).getFlag()){
                carNum=carNum+viewList.get(i).getCarNum()+"   ";
            }
        }

        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View view=View.inflate(getActivity(),R.layout.dialog_9,null);

//        Log.d(TAG, "onDeleteClick: "+carNum);
        if (!carNum.equals("")){
            ((TextView)view.findViewById(R.id.tv_numberPlate)).setText("车牌号："+carNum);
        }else {
            ((TextView)view.findViewById(R.id.tv_numberPlate)).setText("车牌号："+viewList.get(i).getCarNum());
        }

        final EditText et_morey= (EditText) view.findViewById(R.id.et_morey);
        Button btn_recharge=(Button)view.findViewById(R.id.btn_recharge);
        Button btn_cancel= (Button) view.findViewById(R.id.btn_cancel);

        builder.setView(view).create();
        final AlertDialog dialog=builder.show();

        final String finalCarNum = carNum;
        btn_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String x="0";
                if (!et_morey.getText().toString().equals("")){
                    x=et_morey.getText().toString();
                }
                int morey= Integer.parseInt(x);
                if (morey<=0||morey>999){
                    Toast.makeText(getActivity(),"只能输入1到999之间的整数",Toast.LENGTH_LONG).show();
                    return;
                }
                Log.d(TAG, "onClick: "+booleans);
                if (!finalCarNum.equals("")){
                    for (int t=0;t<booleans.size();t++){
                        if (booleans.get(t)){
                            rechargeMorey(t,morey);
                        }
                    }
                }else {
                    rechargeMorey(i,morey);
                }

                dialog.dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void rechargeMorey( int i,int morey) {
        LoadingDialog.showDialog(getActivity());
        JSONObject object=new JSONObject();
        try {
            object.put("CarId",(i+1));
            object.put("Money",morey);
            object.put("UserName","aaa");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue queue=Volley.newRequestQueue(getActivity());
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://172.168.30.65:8080/transportservice/action/SetCarAccountRecharge.do", object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                LoadingDialog.disDialog();
                setBalance();
                Toast.makeText(getActivity(),"充值成功",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                LoadingDialog.disDialog();
                Toast.makeText(getActivity(),"充值失败",Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);
    }

}
