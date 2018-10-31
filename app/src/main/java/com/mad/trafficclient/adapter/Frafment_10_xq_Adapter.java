package com.mad.trafficclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.bean.F_10_xq_Bean;

import java.util.List;

public class Frafment_10_xq_Adapter extends BaseAdapter {

    private Context context;
    private List<F_10_xq_Bean> list;
    public Frafment_10_xq_Adapter(Context context, List<F_10_xq_Bean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.layout10_xq_list,null);
        ((TextView)view.findViewById(R.id.tv_id)).setText(list.get(i).getId());
        ((TextView)view.findViewById(R.id.tv_busId)).setText(list.get(i).getBusId());
        ((TextView)view.findViewById(R.id.tv_num)).setText(list.get(i).getNum());
        return view;
    }
}
