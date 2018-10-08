/**
 *
 */
package com.mad.trafficclient.fragment;

import com.mad.trafficclient.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fragment_4 extends Fragment implements CompoundButton.OnCheckedChangeListener {

    String TAG = "AAA";

    private View view;
    private RadioButton rb_1, rb_2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout04, container, false);

        init();

        return view;
    }

    private void init() {
        rb_1 = (RadioButton) view.findViewById(R.id.rb_1);
        rb_1.setOnCheckedChangeListener(this);
        rb_2 = (RadioButton) view.findViewById(R.id.rb_2);
        rb_2.setOnCheckedChangeListener(this);
        rb_1.setChecked(true);
        rb_2.setBackgroundColor(Color.parseColor("#FF838383"));
//        List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
//        for (int i = 0; i < 5; i++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("img", "www.baidu.com");
//            map.put("text","123321");
//            mList.add(map);
//        }
//        SimpleAdapter adapter = new SimpleAdapter(getActivity(), mList, R.layout.item, new String[]{"img","text"}, new int[]{R.id.iv_item,R.id.tv_item});

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.rb_1:
                if (b) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.rl_rl, new ViolatingVideo()).commit();
                    rb_1.setBackgroundColor(Color.parseColor("#f1f1f1"));
                }else {
                    rb_1.setBackgroundColor(Color.parseColor("#FF838383"));
                }
                break;
            case R.id.rb_2:
                if (b) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.rl_rl, new ViolatingPicture()).commit();
                    rb_2.setBackgroundColor(Color.parseColor("#f1f1f1"));
                }else {
                    rb_2.setBackgroundColor(Color.parseColor("#FF838383"));
                }
                break;
        }
    }
}
