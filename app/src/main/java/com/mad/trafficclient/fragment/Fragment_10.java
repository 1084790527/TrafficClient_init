package com.mad.trafficclient.fragment;


import android.app.AlertDialog;
import android.app.DownloadManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.R;
import com.mad.trafficclient.adapter.Frafment_10_xq_Adapter;
import com.mad.trafficclient.adapter.Fragment_10_Adapter;
import com.mad.trafficclient.adapter.ListAdapter;
import com.mad.trafficclient.bean.BusBean;
import com.mad.trafficclient.bean.F_10_xq_Bean;
import com.mad.trafficclient.util.UrlBean;
import com.mad.trafficclient.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_10 extends Fragment implements View.OnClickListener {

    private static final String TAG = "AAA";
    private View view;

    private Button btn_details;
    private UrlBean urlBean;
    private ExpandableListView elv_list;
    private Fragment_10_Adapter adapter;
    private List<List<BusBean>> lists;
    private List<String> strings;
    private ScheduledFuture scheduledFuture;

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

        scheduledFuture=Executors.newScheduledThreadPool(3).scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                getData(false);
            }
        },3,3,TimeUnit.SECONDS);
//        scheduledFuture.cancel(true);
        return view;
    }

    private void init() {

        btn_details.setOnClickListener(this);

        strings=new ArrayList<>();
        strings.add("        中医院站");
        strings.add("        联想大厦站");
        lists=new ArrayList<>();
        elv_list= (ExpandableListView) view.findViewById(R.id.elv_list);
        adapter=new Fragment_10_Adapter(getActivity(),strings,lists);
        elv_list.setAdapter(adapter);

        getData(true);
//        setData();
    }

    private void getData(final boolean b) {

        for (int i=0;i<2;i++){
            JSONObject object=new JSONObject();
            try {
                object.put("BusStationId",i+1);
                object.put("UserName","user");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            RequestQueue queue=Volley.newRequestQueue(getActivity());
            final int finalI = i;
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/action/GetBusStationInfo.do", object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
//                    Log.d(TAG, "onResponse: "+jsonObject.toString());
                    try {
                        JSONArray array=jsonObject.getJSONArray("ROWS_DETAIL");
                        List<BusBean> busBeans=new ArrayList<>();
                        for (int t=0;t<array.length();t++){
                            JSONObject o=array.getJSONObject(t);
                            int Distance=o.getInt("Distance");
                            if (b){
                                BusBean bean=new BusBean();
                                bean.setBusId(o.getString("BusId")+"号（101人）");
                                bean.setTime(Distance/4000+"分钟到达");
                                bean.setDistance("距离站台"+Distance+"米");
                                busBeans.add(bean);
                            }else {
                                lists.get(finalI).get(t).setBusId(o.getString("BusId")+"号（101人）");
                                lists.get(finalI).get(t).setTime(Distance/4000+"分钟到达");
                                lists.get(finalI).get(t).setDistance("距离站台"+Distance+"米");
                            }
                        }
                        if (b){
                            lists.add(busBeans);
                        }
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
        for (int i=0;i<2;i++){
            List<BusBean> busBeans=new ArrayList<>();
            for (int t=0;t<3;t++){
                BusBean bean=new BusBean();
                bean.setBusId(t+"号（101人）");
                bean.setTime("xxx分钟到达");
                bean.setDistance("距离站台xxx米");
                busBeans.add(bean);
            }
            lists.add(busBeans);
            adapter.notifyDataSetChanged();
        }
    }

    private void iniv() {
        urlBean= Util.loadSetting(getActivity());
        btn_details= (Button) view.findViewById(R.id.btn_details);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_details:
                final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                View v=LayoutInflater.from(getActivity()).inflate(R.layout.layout10_xq,null);

                ListView lv_xq= (ListView) v.findViewById(R.id.lv_xq);
                Button btn_return= (Button) v.findViewById(R.id.btn_return);

                final AlertDialog dialog=builder.setView(v).create();
//                builder.show();
                dialog.show();
                btn_return.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                setXQList(lv_xq);
                break;
        }

    }

    private void setXQList(ListView lv_xq) {
        List<F_10_xq_Bean> list=new ArrayList<>();
        F_10_xq_Bean f_10_xq_bean=new F_10_xq_Bean();
        f_10_xq_bean.setId("序号");
        f_10_xq_bean.setBusId("公交车编号");
        f_10_xq_bean.setNum("承载人数");
        list.add(f_10_xq_bean);
        Frafment_10_xq_Adapter frafment_10_xq_adapter=new Frafment_10_xq_Adapter(getActivity(),list);
        lv_xq.setAdapter(frafment_10_xq_adapter);

        for (int i=1;i<100;i++){
            F_10_xq_Bean bean=new F_10_xq_Bean();
            bean.setId(i+"");
            bean.setBusId(i+"");
            bean.setNum((int)(Math.random()*100)+"");
            list.add(bean);
            frafment_10_xq_adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        scheduledFuture.cancel(false);
    }
}
