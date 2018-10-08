package com.mad.trafficclient.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.mad.trafficclient.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ListViewAcitivity extends Activity implements com.mad.trafficclient.adapter.ListAdapter.onItemDeleteListener {
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        listView = (ListView) findViewById(R.id.lv_show);
        final List<String> mList = new ArrayList<String>();
        mList.add("1");
        mList.add("2");
        final com.mad.trafficclient.adapter.ListAdapter adapter = new com.mad.trafficclient.adapter.ListAdapter(mList, this);
        adapter.setOnItemDeleteClickListener(this);
        listView.setAdapter(adapter);

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListViewAcitivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mList.clear();
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    @Override
    public void onDeleteClick(int i) {
        Toast.makeText(this, "当前点击" + i, Toast.LENGTH_SHORT).show();
    }

}
