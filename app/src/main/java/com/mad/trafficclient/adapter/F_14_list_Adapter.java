package com.mad.trafficclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.bean.F_14_Bean;

import java.util.List;

public class F_14_list_Adapter extends BaseAdapter {
    private Context context;
    private List<F_14_Bean> list;
    private onDeleteListener listener;
    public F_14_list_Adapter(Context context, List<F_14_Bean> list){
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.f_14_list,null);
        ((TextView)view.findViewById(R.id.tv_id)).setText(list.get(i).getId()+"");
        ((TextView)view.findViewById(R.id.tv_money)).setText(list.get(i).getMoney()+"元");
        CheckBox cb_t=(CheckBox)view.findViewById(R.id.cb_t);
        cb_t.setChecked(list.get(i).getT());
        cb_t.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                list.get(i).setT(b);
            }
        });
        Button btn_recharge=(Button)view.findViewById(R.id.btn_recharge);
        btn_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDeleteOnClick(i);
            }
        });
        return view;
    }

    public interface onDeleteListener{
        void onDeleteOnClick(int i);

    }

    public void setOnDeleteOnClickListener(onDeleteListener listener){
        this.listener=listener;
    }
}

