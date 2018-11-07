package com.mad.trafficclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.bean.F_18_Bean_G;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class F_18_list_Adapter extends BaseExpandableListAdapter {

    private Context context;
    private List<F_18_Bean_G> list;

    public F_18_list_Adapter(Context context, List<F_18_Bean_G> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public int getGroupCount() {
        return list==null?0:list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list.get(i)==null?0:list.get(i).getList().size();
    }

    @Override
    public Object getGroup(int i) {
        return list.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return list.get(i).getList().get(i1);
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
        view= LayoutInflater.from(context).inflate(R.layout.f_18_list_g,null);
        ((TextView)view.findViewById(R.id.tv_title)).setText(list.get(i).getTitle()==1?"一号站台":"二号站台");
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        view=LayoutInflater.from(context).inflate(R.layout.f_18_list_c,null);
        ((TextView)view.findViewById(R.id.tv_t)).setText(list.get(i).getList().get(i1).getT()==1?"一号公交车":"二号公交车");
        ((TextView)view.findViewById(R.id.tv_m)).setText(list.get(i).getList().get(i1).getS()+"米");
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
