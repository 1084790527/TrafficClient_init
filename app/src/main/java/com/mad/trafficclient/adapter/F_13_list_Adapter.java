package com.mad.trafficclient.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.bean.F_13_Bean;

import java.util.List;

import static android.media.CamcorderProfile.get;

public class F_13_list_Adapter extends BaseAdapter {

    private Context context;
    private List<F_13_Bean> list;

    public F_13_list_Adapter(Context context,List<F_13_Bean> list){
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
        view= LayoutInflater.from(context).inflate(R.layout.f_13_list_view,null);
        ((TextView)view.findViewById(R.id.tv_id)).setText(list.get(i).getId()+"");
        ((TextView)view.findViewById(R.id.tv_red)).setText(list.get(i).getRed()+"");
        ((TextView)view.findViewById(R.id.tv_green)).setText(list.get(i).getGreen()+"");
        ((TextView)view.findViewById(R.id.tv_yellow)).setText(list.get(i).getYellow()+"");
        return view;
    }
}
