package com.mad.trafficclient.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mad.trafficclient.R;
import com.mad.trafficclient.fragment.activity.F_19_Activity;
import com.mad.trafficclient.util.LoadingDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_19 extends Fragment implements View.OnClickListener {

    private View view;
    private TextView tv_my_opinion;
    private EditText et_title,et_opinion,et_num;
    private Button btn_submit;
    private SharedPreferences preferences;
    private String TAG="AAA";

    public Fragment_19() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_fragment_19, container, false);
        iniv();
        init();
        return view;
    }

    private void init() {

    }

    private void iniv() {
        preferences=getActivity().getSharedPreferences("F_19",MODE_PRIVATE);
        tv_my_opinion= (TextView) view.findViewById(R.id.tv_my_opinion);
        tv_my_opinion.setOnClickListener(this);
        et_title= (EditText) view.findViewById(R.id.et_title);
        et_opinion= (EditText) view.findViewById(R.id.et_opinion);
        et_num= (EditText) view.findViewById(R.id.et_num);
        btn_submit= (Button) view.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_my_opinion:
                startActivity(new Intent(getActivity(), F_19_Activity.class));
                break;
            case R.id.btn_submit:
                String title = String.valueOf(et_title.getText());
                if (title.equals("")){
                    Toast.makeText(getActivity(),"标题不能为空！",Toast.LENGTH_LONG).show();
                    return;
                }
                String  opinion = String.valueOf(et_opinion.getText());
                if (opinion.equals("")){
                    Toast.makeText(getActivity(),"意见不能为空！",Toast.LENGTH_LONG).show();
                    return;
                }
                String num = String.valueOf(et_num.getText());
                if(num.equals("")){
                    Toast.makeText(getActivity(),"手机号不能为空！",Toast.LENGTH_LONG).show();
                    return;
                }
                if (num.length()!=11){
                    Toast.makeText(getActivity(),"手机号错误！",Toast.LENGTH_LONG).show();
                    return;
                }
                JSONArray jsonArray;
                try {
                    jsonArray=new JSONArray(preferences.getString("data",""));
                } catch (JSONException e) {
                    jsonArray=new JSONArray();
                    e.printStackTrace();
                }

                JSONObject object=new JSONObject();
                try {
                    object.put("title",title);
                    object.put("opinion",opinion);
                    object.put("num",num);
                    object.put("date", LoadingDialog.getTime());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jsonArray.put(object);

                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("data",jsonArray.toString());
                editor.commit();

                break;
        }
    }

}
