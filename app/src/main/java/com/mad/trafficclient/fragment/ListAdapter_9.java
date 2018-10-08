package com.mad.trafficclient.fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.bean.UserBean;

import org.w3c.dom.Text;

import java.util.List;

public class ListAdapter_9 extends BaseAdapter {

    String TAG = "AAA";

    private List<UserBean> viewList;
    private Context context;
    private onItemDeleteListener mOnItemDeleteListener;

    public ListAdapter_9(List<UserBean> viewList, Context context) {
        this.viewList = viewList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return viewList == null ? 0 : viewList.size();
    }

    @Override
    public Object getItem(int i) {
        return viewList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final UserBean bean = viewList.get(i);
        view = LayoutInflater.from(context).inflate(R.layout.item_9, null);
        Button btn = (Button) view.findViewById(R.id.btn_cz);
        TextView tv_ph = (TextView) view.findViewById(R.id.tv_ph);
        TextView tv_cph = (TextView) view.findViewById(R.id.tv_cph);
        TextView tv_cz = (TextView) view.findViewById(R.id.tv_cz);
        TextView tv_ye = (TextView) view.findViewById(R.id.tv_ye);
        ImageView iv_cp = (ImageView) view.findViewById(R.id.iv_cp);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.cb_dxk);

        tv_ph.setText((i+1)+"");
        iv_cp.setImageResource(bean.getImg());
        tv_cph.setText(bean.getCarNum());
        tv_cz.setText(bean.getCarUseName());
        tv_ye.setText(bean.getMonery());
        checkBox.setChecked(bean.getFlag());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    bean.setFlag(b);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemDeleteListener.onDeleteClick(i);
            }
        });
        return view;
    }

    public interface onItemDeleteListener {
        void onDeleteClick(int i);
    }


    public void setOnItemDeleteClickListener(onItemDeleteListener mOnItemDeleteListener) {
        this.mOnItemDeleteListener = mOnItemDeleteListener;
    }

}
