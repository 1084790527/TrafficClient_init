package com.mad.trafficclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.bean.F_22_Bean;

import java.util.List;

public class F_22_list_Adapter extends BaseAdapter {

    private Context context;
    private List<F_22_Bean> list;
    private DelectOnClick delectOnClick;

    public F_22_list_Adapter(Context context, List<F_22_Bean> list){
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
        view= LayoutInflater.from(context).inflate(R.layout.f_22_list_view,null);
        ((TextView)view.findViewById(R.id.tv_id)).setText(list.get(i).getId()+"");
        ((TextView)view.findViewById(R.id.tv_red)).setText(list.get(i).getRed()+"");
        ((TextView)view.findViewById(R.id.tv_green)).setText(list.get(i).getGreen()+"");
        ((TextView)view.findViewById(R.id.tv_yellow)).setText(list.get(i).getYellow()+"");
        CheckBox cb_cb= (CheckBox) view.findViewById(R.id.cb_cb);
        cb_cb.setChecked(list.get(i).isCb());
        cb_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                list.get(i).setCb(b);
            }
        });
        Button btn_setting= (Button) view.findViewById(R.id.btn_setting);
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delectOnClick.onDelectClick(i);
            }
        });
        return view;
    }

    public interface DelectOnClick{
        void onDelectClick(int i);
    }
    public void setOnClickDelect(DelectOnClick delectOnClick){
        this.delectOnClick=delectOnClick;
    }

}
