package com.mad.trafficclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

public class Fragment_10_Adapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> strings;
    private List<Fragment_10_Adapter> list;

    public Fragment_10_Adapter(Context context, List<String> strings,List<Fragment_10_Adapter> list){
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
        return list==null ? 0 : list.size();
    }

    @Override
    public Object getGroup(int i) {
        return strings.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return list.get(i);
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
        return null;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
