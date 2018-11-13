package com.mad.trafficclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.bean.F_25_2_Bean;

import java.util.List;

public class F_25_2_list_Adapter extends BaseAdapter {

    private Context context;
    private List<F_25_2_Bean> list;
    public F_25_2_list_Adapter(Context context, List<F_25_2_Bean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list==null?0:list.size();
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
        view= LayoutInflater.from(context).inflate(R.layout.f_25_2_list_view,null);
        ((TextView)view.findViewById(R.id.tv_czr)).setText("充值人："+list.get(i).getCzr()+"");
        ((TextView)view.findViewById(R.id.tv_cz)).setText("充值："+list.get(i).getCz()+"");
        ((TextView)view.findViewById(R.id.tv_czdate)).setText(list.get(i).getCzdate()+"");
        return view;
    }
}
