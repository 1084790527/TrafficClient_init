package com.mad.trafficclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mad.trafficclient.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViolatingVideo extends Fragment {

    private View view;

    public ViolatingVideo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_violating_video, container, false);
        return view;
    }

}
