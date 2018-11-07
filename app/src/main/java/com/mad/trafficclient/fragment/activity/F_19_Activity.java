package com.mad.trafficclient.fragment.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.adapter.F_19_list_Adapter;

import org.json.JSONArray;
import org.json.JSONException;

public class F_19_Activity extends Activity implements View.OnClickListener {

    private ImageView imageView_Sliding;
    private SharedPreferences preferences;
    private F_19_list_Adapter adapter;
    private JSONArray jsonArray;
    private ListView lv_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_f_19);

        iniv();

        init();
    }

    private void init() {

    }

    private void iniv() {
        lv_list= (ListView) findViewById(R.id.lv_list);
        imageView_Sliding= (ImageView) findViewById(R.id.imageView_Sliding);
        imageView_Sliding.setOnClickListener(this);
        preferences=getApplication().getSharedPreferences("F_19",MODE_PRIVATE);
        try {
            jsonArray=new JSONArray(preferences.getString("data",""));
        } catch (JSONException e) {
            e.printStackTrace();
            jsonArray=new JSONArray();
        }
        adapter=new F_19_list_Adapter(getApplicationContext(),jsonArray);
        lv_list.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
        case R.id.imageView_Sliding:
            finish();
            break;
        }
    }
}
