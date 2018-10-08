package com.mad.trafficclient.fragment;


import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.mad.trafficclient.R;

import java.net.URL;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViolatingPicture extends Fragment implements View.OnClickListener {

    String TAG="AAA";
    private View view;
    private ImageView iv_item1,iv_item2,iv_item3,iv_item4,iv_item;

    public ViolatingPicture() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_violating_picture, container, false);

        init();

        return view;
    }

    private void init() {
        iv_item1= (ImageView) view.findViewById(R.id.iv_item1);
        iv_item1.setOnClickListener(this);
        iv_item2= (ImageView) view.findViewById(R.id.iv_item2);
        iv_item2.setOnClickListener(this);
        iv_item3= (ImageView) view.findViewById(R.id.iv_item3);
        iv_item3.setOnClickListener(this);
        iv_item4= (ImageView) view.findViewById(R.id.iv_item4);
        iv_item4.setOnClickListener(this);
        iv_item= (ImageView) view.findViewById(R.id.iv_item);
        iv_item.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_item1:
                iv_item.setImageDrawable(getResources().getDrawable(R.drawable.weizhang01));
                iv_item.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_item2:
                iv_item.setImageDrawable(getResources().getDrawable(R.drawable.weizhang02));
                iv_item.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_item3:
                iv_item.setImageDrawable(getResources().getDrawable(R.drawable.weizhang03));
                iv_item.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_item4:
                iv_item.setImageDrawable(getResources().getDrawable(R.drawable.weizhang04));
                iv_item.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_item:
//                Log.d(TAG, "onClick: item");
                iv_item.setVisibility(View.GONE);
                break;
        }
    }
}
