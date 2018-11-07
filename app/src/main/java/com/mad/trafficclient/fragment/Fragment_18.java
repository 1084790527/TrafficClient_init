package com.mad.trafficclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.R;
import com.mad.trafficclient.adapter.F_18_list_Adapter;
import com.mad.trafficclient.bean.F_18_Bean_C;
import com.mad.trafficclient.bean.F_18_Bean_G;
import com.mad.trafficclient.util.UrlBean;
import com.mad.trafficclient.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_18 extends Fragment {

    private static final String TAG = "AAA";
    private View view;
    private ExpandableListView elb_list;
    private F_18_list_Adapter adapter;
    private List<F_18_Bean_G> list;
    private UrlBean urlBean;
    private ScheduledFuture<?> scheduledFuture;

    public Fragment_18() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_fragment_18, container, false);
        iniv();
        init();
        return view;
    }

    private void init() {
        /*for (int i=0;i<5;i++){
            F_18_Bean_G bean_g=new F_18_Bean_G();
            bean_g.setTitle(i+"号站台");
            List<F_18_Bean_C> bean_cs=new ArrayList<>();
            for (int x=0;x<3;x++){
                F_18_Bean_C bean_c=new F_18_Bean_C();
                bean_c.setT(x+"号公交车");
                bean_c.setS((int)(Math.random()*10));
                bean_cs.add(bean_c);
                Collections.sort(bean_cs);
            }
            bean_g.setList(bean_cs);
            list.add(bean_g);
            adapter.notifyDataSetInvalidated();
        }*/
        getData(true);
        scheduledFuture=Executors.newScheduledThreadPool(3).scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                getData(false);
            }
        },3,3, TimeUnit.SECONDS);


    }

    @Override
    public void onDestroy() {
        scheduledFuture.cancel(false);
        super.onDestroy();
    }

    private void getData(final boolean b) {
        for(int i=0;i<2;i++) {
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            JSONObject object = new JSONObject();
            final int x = i + 1;
            try {
                object.put("BusStationId", x);
                object.put("UserName", "user");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            final int finalI = i;
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/action/GetBusStationInfo.do", object,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
//                            Log.d(TAG, "onResponse: " + jsonObject.toString());
                            try {
                                JSONArray jsonArray=jsonObject.getJSONArray("ROWS_DETAIL");
                                if (b){
                                    F_18_Bean_G beanG=new F_18_Bean_G();
                                    beanG.setTitle(x);
                                    List<F_18_Bean_C> bean_cs=new ArrayList<>();
                                    for (int t=0;t<jsonArray.length();t++){
                                        F_18_Bean_C beanC=new F_18_Bean_C();
                                        beanC.setT(jsonArray.getJSONObject(t).getInt("BusId"));
                                        beanC.setS(jsonArray.getJSONObject(t).getInt("Distance"));
                                        bean_cs.add(beanC);
                                        Collections.sort(bean_cs);
                                    }
                                    beanG.setList(bean_cs);
                                    list.add(beanG);
                                    Collections.sort(list);
                                    adapter.notifyDataSetInvalidated();
                                }else {
                                    for (int y=0;y<jsonArray.length();y++){
                                        if(list.get(finalI).getTitle()==x){
                                            List<F_18_Bean_C> bean_cs=list.get(finalI).getList();
                                            for (int z=0;z<bean_cs.size();z++){
                                                if (bean_cs.get(z).getT()==jsonArray.getJSONObject(y).getInt("BusId")){
//                                                    bean_cs.get(z).setS(jsonArray.getJSONObject(y).getInt("Distance"));
                                                    list.get(finalI).getList().get(z).setS(jsonArray.getJSONObject(y).getInt("Distance"));
                                                }
                                            }
                                        }
                                    }
                                    for (int z=0;z<list.size();z++){
                                        Collections.sort(list.get(z).getList());
                                    }
                                    Collections.sort(list);
                                    adapter.notifyDataSetInvalidated();
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

    private void iniv() {
        elb_list= (ExpandableListView) view.findViewById(R.id.elb_list);
        list=new ArrayList<>();
        adapter=new F_18_list_Adapter(getActivity(),list);
        elb_list.setAdapter(adapter);
        urlBean= Util.loadSetting(getActivity());
    }

}
