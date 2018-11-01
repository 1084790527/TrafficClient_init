package com.mad.trafficclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.bean.F_11_Bean;

import java.util.List;

public class F_11_list_Adapter extends BaseAdapter {

    private Context context;
    private List<F_11_Bean> list;
    public F_11_list_Adapter(Context context, List<F_11_Bean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list==null? 0 : list.size();
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
        view= LayoutInflater.from(context).inflate(R.layout.f_11_list,null);
        ((ImageView)view.findViewById(R.id.iv_icon)).setImageResource(list.get(i).getIcon());
        ((TextView)view.findViewById(R.id.tv_title)).setText(list.get(i).getTitle());
        ((TextView)view.findViewById(R.id.tv_qd)).setText(list.get(i).getQd());
        ((TextView)view.findViewById(R.id.tv_xx)).setText(list.get(i).getXx());
        return view;
    }
}
