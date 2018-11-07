package com.mad.trafficclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mad.trafficclient.R;

import org.json.JSONArray;
import org.json.JSONException;

public class F_19_list_Adapter extends BaseAdapter {

    private Context context;
    private JSONArray jsonArray;

    public F_19_list_Adapter(Context context, JSONArray jsonArray){
        this.context=context;
        this.jsonArray=jsonArray;
    }
    @Override
    public int getCount() {
        return jsonArray==null?0:jsonArray.length();
    }

    @Override
    public Object getItem(int i) {
        try {
            return jsonArray.getJSONObject(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.f_19_list,null);
        try {
            ((TextView)view.findViewById(R.id.tv_title)).setText(jsonArray.getJSONObject(i).getString("title")+"");
            ((TextView)view.findViewById(R.id.tv_date)).setText(jsonArray.getJSONObject(i).getString("date")+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }
}
