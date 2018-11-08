package com.mad.trafficclient.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.R;
import com.mad.trafficclient.adapter.F_22_list_Adapter;
import com.mad.trafficclient.bean.F_22_Bean;
import com.mad.trafficclient.util.UrlBean;
import com.mad.trafficclient.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_22 extends Fragment implements F_22_list_Adapter.DelectOnClick, AdapterView.OnItemSelectedListener, View.OnClickListener {

    private ListView lv_list;
    private View view;
    private List<F_22_Bean> list;
    private F_22_list_Adapter adapter;
    private final String TAG="AAA";
    private Spinner sp_sp;
    private UrlBean urlBean;
    private Button btn_inquire,btn_batch;
    private int ff;

    public Fragment_22() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_22, container, false);
        iniv();
        init();
        return view;
    }

    private void init() {
        /*for (int i=0;i<10;i++){
            F_22_Bean bean=new F_22_Bean();
            bean.setId(i+1);
            bean.setGreen(i+1);
            bean.setRed(i+1);
            bean.setYellow(i+1);
            bean.setCb(false);
            list.add(bean);
            adapter.notifyDataSetInvalidated();
        }*/
        getNetData();
    }

    private void getNetData() {
        int size=list.size();
        for(int i=size-1;i>=0;i--){
            list.remove(i);
        }
        for (int i=0;i<5;i++){
            RequestQueue queue= Volley.newRequestQueue(getActivity());
            JSONObject object=new JSONObject();
            final int x=i+1;
            try {
                object.put("TrafficLightId",x);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/action/GetTrafficLightConfigAction.do", object,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
//                            Log.d(TAG, "onResponse: "+jsonObject.toString());
                            F_22_Bean bean=new F_22_Bean();
                            bean.setId(x);
                            try {
                                bean.setYellow(jsonObject.getInt("YellowTime"));
                                bean.setRed(jsonObject.getInt("RedTime"));
                                bean.setGreen(jsonObject.getInt("GreenTime"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            list.add(bean);
                            px();
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
        ff=0;
        urlBean= Util.loadSetting(getActivity());
        lv_list= (ListView) view.findViewById(R.id.lv_list);
        list=new ArrayList<>();
        adapter=new F_22_list_Adapter(getActivity(),list);
        adapter.setOnClickDelect(this);
        lv_list.setAdapter(adapter);
        sp_sp= (Spinner) view.findViewById(R.id.sp_sp);
        ArrayList<String> strings=new ArrayList<>();
        strings.add("路口升序");
        strings.add("路口降序");
        strings.add("红灯升序");
        strings.add("红灯降序");
        strings.add("绿灯升序");
        strings.add("绿灯降序");
        strings.add("黄灯升序");
        strings.add("黄灯降序");
        ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(),R.layout.f_22_sp,strings);
        arrayAdapter.setDropDownViewResource(R.layout.f_22_sp);
        sp_sp.setAdapter(arrayAdapter);
        sp_sp.setOnItemSelectedListener(this);
        btn_inquire= (Button) view.findViewById(R.id.btn_inquire);
        btn_inquire.setOnClickListener(this);
        btn_batch= (Button) view.findViewById(R.id.btn_batch);
        btn_batch.setOnClickListener(this);
    }

    @Override
    public void onDelectClick(final int i) {
        Log.d(TAG, "onDelectClick: "+i);
        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.f_22_dialog,null);
        final EditText et_red= (EditText) view.findViewById(R.id.et_red);
        final EditText et_yellow= (EditText) view.findViewById(R.id.et_yellow);
        final EditText et_green= (EditText) view.findViewById(R.id.et_green);
        Button btn_determine= (Button) view.findViewById(R.id.btn_determine);
        Button btn_cancel= (Button) view.findViewById(R.id.btn_cancel);
        builder.setView(view);
        final AlertDialog alertDialog=builder.show();
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        btn_determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String red=et_red.getText().toString();
                String yellow=et_yellow.getText().toString();
                String green=et_green.getText().toString();
                if (red==""){
                    Toast.makeText(getActivity(),"红灯不能为空！",Toast.LENGTH_LONG).show();
                    return;
                }
                if (yellow==""){
                    Toast.makeText(getActivity(),"红灯不能为空！",Toast.LENGTH_LONG).show();
                    return;
                }
                if (green==""){
                    Toast.makeText(getActivity(),"红灯不能为空！",Toast.LENGTH_LONG).show();
                    return;
                }
                JSONObject object=new JSONObject();
                try {
                    object.put("TrafficLightId",list.get(i).getId());
                    object.put("RedTime",(int)(Double.parseDouble(red)));
                    object.put("RedTime",(int)(Double.parseDouble(yellow)));
                    object.put("RedTime",(int)(Double.parseDouble(green)));
                    object.put("UserName","user");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestQueue queue=Volley.newRequestQueue(getActivity());
                JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/action/SetTrafficLightConfig.do", object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                try {
                                    if ("S".equals(jsonObject.getString("RESULT"))){
                                        alertDialog.dismiss();
                                        getNetData();
                                        Toast.makeText(getActivity(),"设置成功",Toast.LENGTH_LONG).show();
                                    }else {
                                        Toast.makeText(getActivity(),"设置失败",Toast.LENGTH_LONG).show();
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
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d(TAG, "onItemSelected: "+i);
        ff=i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_inquire:
                getNetData();
                break;
            case R.id.btn_batch:
                final List<Integer> ids=new ArrayList();
                for (int i=0;i<list.size();i++){
                    if (list.get(i).isCb()){
                        ids.add(list.get(i).getId());
                    }
                }
                if (ids.size()==0){
                    Toast.makeText(getActivity(),"请选择用户",Toast.LENGTH_LONG).show();
                    return;
                }
                final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                View inflate=LayoutInflater.from(getActivity()).inflate(R.layout.f_22_dialog,null);
                final EditText et_red= (EditText) inflate.findViewById(R.id.et_red);
                final EditText et_yellow= (EditText) inflate.findViewById(R.id.et_yellow);
                final EditText et_green= (EditText) inflate.findViewById(R.id.et_green);
                Button btn_determine= (Button) inflate.findViewById(R.id.btn_determine);
                Button btn_cancel= (Button) inflate.findViewById(R.id.btn_cancel);
                builder.setView(inflate);
                final AlertDialog alertDialog=builder.show();
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                btn_determine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String red=et_red.getText().toString();
                        String yellow=et_yellow.getText().toString();
                        String green=et_green.getText().toString();
                        if (red==""){
                            Toast.makeText(getActivity(),"红灯不能为空！",Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (yellow==""){
                            Toast.makeText(getActivity(),"红灯不能为空！",Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (green==""){
                            Toast.makeText(getActivity(),"红灯不能为空！",Toast.LENGTH_LONG).show();
                            return;
                        }
                        final int[] x = {0};
                        for (int i=0;i<ids.size();i++){
                            JSONObject object=new JSONObject();
                            try {
                                object.put("TrafficLightId",ids.get(i));
                                object.put("RedTime",(int)(Double.parseDouble(red)));
                                object.put("RedTime",(int)(Double.parseDouble(yellow)));
                                object.put("RedTime",(int)(Double.parseDouble(green)));
                                object.put("UserName","user");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            RequestQueue queue=Volley.newRequestQueue(getActivity());
                            final int finalI = i;
                            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/action/SetTrafficLightConfig.do", object,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject jsonObject) {
                                            x[0]++;
                                            try {
                                                if ("S".equals(jsonObject.getString("RESULT"))){
                                                    if (x[0] ==ids.size()-1){
                                                        alertDialog.dismiss();
                                                        getNetData();
                                                    }
                                                    Toast.makeText(getActivity(),"设置成功",Toast.LENGTH_LONG).show();
                                                }else {
                                                    Toast.makeText(getActivity(),"设置失败",Toast.LENGTH_LONG).show();
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

                    }
                });
                break;
        }
    }

    private void px(){
        if (list.size()==0){
            return;
        }
            for (int i = 0; i < list.size(); i++) {
                for (int t = 0; t < list.size() - 1; t++) {
                    switch (ff) {
                        case 0://路口升序
                            if (list.get(t).getId() > list.get(t + 1).getId()) {
                                jh(t);
                            }
                            break;
                        case 1://路口降序
                            if (list.get(t).getId() < list.get(t + 1).getId()) {
                                jh(t);
                            }
                            break;
                        case 2://红灯升序
                            if (list.get(t).getRed() > list.get(t + 1).getRed()) {
                                jh(t);
                            }
                            break;
                        case 3://红灯降序
                            if (list.get(t).getRed() < list.get(t + 1).getRed()) {
                                jh(t);
                            }
                            break;
                        case 4://绿灯升序
                            if (list.get(t).getGreen() > list.get(t + 1).getGreen()) {
                                jh(t);
                            }
                            break;
                        case 5://绿灯降序
                            if (list.get(t).getGreen() < list.get(t + 1).getGreen()) {
                                jh(t);
                            }
                            break;
                        case 6://黄灯升序
                            if (list.get(t).getYellow() > list.get(t + 1).getYellow()) {
                                jh(t);
                            }
                            break;
                        case 7://黄灯降序
                            if (list.get(t).getYellow() < list.get(t + 1).getYellow()) {
                                jh(t);
                            }
                            break;
                    }

                }
            }
        adapter.notifyDataSetInvalidated();
    }

    private void jh(int i){
        F_22_Bean bean=new F_22_Bean();
        bean=list.get(i);
        list.set(i,list.get(i+1));
        list.set(i+1,bean);

    }
}
