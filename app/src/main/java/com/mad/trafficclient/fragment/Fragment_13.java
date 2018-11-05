package com.mad.trafficclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.R;
import com.mad.trafficclient.adapter.F_13_list_Adapter;
import com.mad.trafficclient.bean.F_13_Bean;
import com.mad.trafficclient.util.UrlBean;
import com.mad.trafficclient.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_13 extends Fragment implements  AdapterView.OnItemSelectedListener {

    private static final String TAG = "AAA";
    private View view;
    private Spinner sp_sort;
    private ListView lv_sort;
    private UrlBean urlBean;
    private List list;
    private ArrayAdapter<String> adapter;
    private F_13_list_Adapter listAdapter;
    private List<F_13_Bean> beanList;

    public Fragment_13() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_fragment_13, container, false);

        iniv();

        init();

        return view;
    }

    private void init() {
        list.add("路口升序");
        list.add("路口降序");
        list.add("红灯升序");
        list.add("红灯降序");
        list.add("绿灯升序");
        list.add("绿灯降序");
        list.add("黄灯升序");
        list.add("黄灯降序");
        adapter=new ArrayAdapter<>(getActivity(),R.layout.f_13_sp,list);
        adapter.setDropDownViewResource(R.layout.f_13_sp);
        sp_sort.setAdapter(adapter);

        setData();
    }

    private void setData() {
       /* for (int i=0;i<3;i++){
            F_13_Bean bean=new F_13_Bean();
            bean.setId(i);
            bean.setGreen(i);
            bean.setRed(i);
            bean.setYellow(i);
            beanList.add(bean);
//            Log.d(TAG, "setData: "+beanList.toString());
            listAdapter.notifyDataSetInvalidated();
        }*/
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        for (int i=0;i<5;i++){
            JSONObject object=new JSONObject();
            try {
                object.put("TrafficLightId",i+1);
                object.put("UserName","user");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            final int finalI = i;
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/action/GetTrafficLightConfigAction.do", object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
//                    Log.d(TAG, "onResponse: "+jsonObject.toString());
                    try {
                        Data(jsonObject, finalI);
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

    private void Data(JSONObject jsonObject, int i) throws JSONException {
        F_13_Bean bean=new F_13_Bean();
        bean.setId(i+1);
        bean.setRed(jsonObject.getInt("RedTime"));
        bean.setGreen(jsonObject.getInt("GreenTime"));
        bean.setYellow(jsonObject.getInt("YellowTime"));
        beanList.add(bean);
        sort(0);
        listAdapter.notifyDataSetInvalidated();
    }

    private void iniv() {
        sp_sort= (Spinner) view.findViewById(R.id.sp_sort);
        lv_sort= (ListView) view.findViewById(R.id.lv_sort);
        urlBean= Util.loadSetting(getActivity());
        list=new ArrayList();
        sp_sort.setOnItemSelectedListener(this);
        beanList=new ArrayList<>();
        listAdapter=new F_13_list_Adapter(getActivity(),beanList);
        lv_sort.setAdapter(listAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        Log.d(TAG, "onItemSelected: "+i);

        if (beanList.size()==0){
            return;
        }

//        Log.d(TAG, "onItemSelected: "+i);
        sort(i);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void sort(int t){
        for (int x=0;x<beanList.size();x++){
            for (int i=0;i<beanList.size()-1;i++){
                switch (t){
                    case 0:
//        路口升序
                        if (beanList.get(i).getId() > beanList.get(i+1).getId()){
                            px(i);
                        }
                        break;
                    case 1:
//        路口降序
                        if (beanList.get(i).getId() < beanList.get(i+1).getId()){
                            px(i);
                        }
                        break;
                    case 2:
//        红灯升序
                        if (beanList.get(i).getRed() > beanList.get(i+1).getRed()){
                            px(i);
                        }
                        break;
                    case 3:
//        红灯降序
                        if (beanList.get(i).getRed() < beanList.get(i+1).getRed()){
                            px(i);
                        }
                        break;
                    case 4:
//        绿灯升序
                        if (beanList.get(i).getGreen() > beanList.get(i+1).getGreen()){
                            px(i);
                        }
                        break;
                    case 5:
//        绿灯降序
                        if (beanList.get(i).getGreen() < beanList.get(i+1).getGreen()){
                            px(i);
                        }
                        break;
                    case 6:
//        黄灯升序
                        if (beanList.get(i).getYellow() > beanList.get(i+1).getYellow()){
                            px(i);
                        }
                        break;
                    case 7:
//        黄灯降序
                        if (beanList.get(i).getYellow() < beanList.get(i+1).getYellow()){
                            px(i);
                        }
                        break;
                }

            }
        }

        listAdapter.notifyDataSetInvalidated();



    }
    private void px(int t){
        F_13_Bean bean=new F_13_Bean();
        bean=beanList.get(t);
        beanList.set(t,beanList.get(t+1));
        beanList.set(t+1,bean);
    }
}
