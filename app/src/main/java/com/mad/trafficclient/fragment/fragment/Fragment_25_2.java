package com.mad.trafficclient.fragment.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.adapter.F_25_2_list_Adapter;
import com.mad.trafficclient.bean.F_25_2_Bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_25_2 extends Fragment {

    private View view;
    private ListView lv_list;
    private F_25_2_list_Adapter adapter;
    private List<F_25_2_Bean> list;
    private String TAG="AAA";

    public Fragment_25_2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_fragment_25_2, container, false);
        iniv();
        init();
        return view;
    }

    private void init() {
//        for (int i=0;i<5;i++){
//            F_25_2_Bean bean=new F_25_2_Bean();
//            list.add(bean);
//            adapter.notifyDataSetInvalidated();
//        }
        setData();
    }

    private void setData() {
        SharedPreferences preferences=getActivity().getSharedPreferences("recharge", Context.MODE_PRIVATE);
        String s=preferences.getString("data","");
//        Log.d(TAG, "iniv: "+s);
        try {
            JSONArray jsonArray=new JSONArray(s);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                F_25_2_Bean bean=new F_25_2_Bean();
                bean.setCzr(object.getString("operator")+"");
                bean.setCz(object.getInt("rechargeAmount"));
                bean.setCzdate(object.getString("rechargeTime")+"");
                list.add(bean);
                adapter.notifyDataSetInvalidated();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void iniv() {
        lv_list= (ListView) view.findViewById(R.id.lv_list);
        list=new ArrayList<>();
        adapter=new F_25_2_list_Adapter(getActivity(),list);
        lv_list.setAdapter(adapter);




    }

}
