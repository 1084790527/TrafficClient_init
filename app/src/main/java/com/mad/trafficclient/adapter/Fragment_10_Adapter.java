package com.mad.trafficclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.bean.BusBean;

import java.util.ArrayList;
import java.util.List;

public class Fragment_10_Adapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> strings;
    private List<List<BusBean>> list;

    public Fragment_10_Adapter(Context context, List<String> strings,List<List<BusBean>> list){
        this.context=context;
        this.strings=strings;
        this.list=list;
    }
    @Override
    public int getGroupCount() {
        return strings==null ? 0 : strings.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list==null ? 0 : list.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return strings.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return list.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.fragment_10_g_view,null);
        ((TextView)view.findViewById(R.id.tv_title)).setText(strings.get(i));
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        view=LayoutInflater.from(context).inflate(R.layout.fragment_10_c_view,null);
        ((TextView)view.findViewById(R.id.tv_busid)).setText(list.get(i).get(i1).getBusId());
        ((TextView)view.findViewById(R.id.tv_time)).setText(list.get(i).get(i1).getTime());
        ((TextView)view.findViewById(R.id.tv_distance)).setText(list.get(i).get(i1).getDistance());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
