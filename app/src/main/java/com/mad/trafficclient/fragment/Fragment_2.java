/**
 * 
 */
package com.mad.trafficclient.fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.R;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;


public class Fragment_2 extends Fragment implements View.OnClickListener{
	private View view;
	private TextView tb_1_1,tb_1_2,tb_1_3,tb_1_4,tb_2_1,tb_2_2,tb_2_3,tb_2_4,tb_3_1,tb_3_2,tb_3_3,tb_3_4,tb_4_1,tb_4_2,tb_4_3,tb_4_4,tb_5_1,tb_5_2,tb_5_3,tb_5_4;
	private List<TextView> list_table;
	private List<List<TextView>> lists_table;
	private Spinner sp_sort;
	private Button btn_inquire;
	private List<String> list;
	private ArrayAdapter<String> adapter;
	private int sp_item;
	private JSONArray jsonArray;
	private Handler handler = new Handler();
	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			if (jsonArray.length()==5){
//				Log.d("AAA", "run: "+jsonArray.toString());
				switch (sp_item){
					case 0:
						//路口升序
						Ascending("Id");
						break;
					case 1:
						//路口降序
						Descending("Id");
						break;
					case 2:
						//红灯升序
						Ascending("RedTime");
						break;
					case 3:
						//红灯降序
						Descending("RedTime");
						break;
					case 4:
						//绿灯升序
						Ascending("GreenTime");
						break;
					case 5:
						//绿灯降序
						Descending("GreenTime");
						break;
					case 6:
						//黄灯升序
						Ascending("YellowTime");
						break;
					case 7:
						//黄灯降序
						Descending("YellowTime");
						break;
				}
				jsonArray=new JSONArray();
			}
//			Log.d("AAA", "run: "+jsonArray.toString());
			handler.postDelayed(this, 1000);
		}
	};


	private void Descending(String type) {
		try {
			JSONObject j=new JSONObject();
			for (int i=0;i<4;i++){
				for (int t=i;t<5;t++){
					if (jsonArray.getJSONObject(i).getInt(type)<jsonArray.getJSONObject(t).getInt(type)){
						j=jsonArray.getJSONObject(i);
						jsonArray.put(i,jsonArray.getJSONObject(t));
						jsonArray.put(t,j);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		tableSetText(jsonArray);
	}
	private void Ascending(String type) {
		try {
			JSONObject j=new JSONObject();
			for (int i=0;i<4;i++){
				for (int t=i;t<5;t++){
					if (jsonArray.getJSONObject(i).getInt(type)>jsonArray.getJSONObject(t).getInt(type)){
						j=jsonArray.getJSONObject(i);
						jsonArray.put(i,jsonArray.getJSONObject(t));
						jsonArray.put(t,j);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		tableSetText(jsonArray);
	}
	private void tableSetText(JSONArray jsonArray) {
		for (int i=0;i<5;i++){
			try {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				lists_table.get(i).get(0).setText(jsonObject.getString("Id"));
				lists_table.get(i).get(1).setText(jsonObject.getString("RedTime"));
				lists_table.get(i).get(2).setText(jsonObject.getString("YellowTime"));
				lists_table.get(i).get(3).setText(jsonObject.getString("GreenTime"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_layout02, container, false);
		init();
		volley();
		spinner();
		return view;
	}
	private void volley() {
		for (int i=1;i<=5;i++){
			JSONObject jsonObject=new JSONObject();
			try {
				jsonObject.put("RoadLightId",i);
				jsonObject.put("UserName","sdsd");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			RequestQueue queue= Volley.newRequestQueue(getActivity());
			final int finalI = i;
			JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://172.168.30.65:8080/transportservice/action/GetTrafficLightConfigAction.do", jsonObject,
					new Response.Listener<JSONObject>() {
						@Override
						public void onResponse(JSONObject jsonObject) {
//							Log.d("AAA", "onResponse: "+jsonObject.toString());
							JSONObject j=jsonObject;
							try {
								j.put("Id", finalI);

							} catch (JSONException e) {
								e.printStackTrace();
							}
							jsonArray.put(j);
						}
					}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError volleyError) {
				}
			});
			queue.add(request);
		}
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
//		Log.d("AAA", "onViewCreated: "+jsonArray.toString());
		handler.postDelayed(runnable,0);
	}
	private void spinner() {
		list=new ArrayList<String>();
		list.add("路口升序");
		list.add("路口降序");
		list.add("红灯升序");
		list.add("红灯降序");
		list.add("绿灯升序");
		list.add("绿灯降序");
		list.add("黄灯升序");
		list.add("黄灯降序");
		adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		sp_sort.setAdapter(adapter);
		sp_sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				sp_item=i;
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {
			}
		});
	}
	private void init() {
		tb_1_1= (TextView) view.findViewById(R.id.tb_1_1);
		tb_1_2= (TextView) view.findViewById(R.id.tb_1_2);
		tb_1_3= (TextView) view.findViewById(R.id.tb_1_3);
		tb_1_4= (TextView) view.findViewById(R.id.tb_1_4);
		tb_2_1= (TextView) view.findViewById(R.id.tb_2_1);
		tb_2_2= (TextView) view.findViewById(R.id.tb_2_2);
		tb_2_3= (TextView) view.findViewById(R.id.tb_2_3);
		tb_2_4= (TextView) view.findViewById(R.id.tb_2_4);
		tb_3_1= (TextView) view.findViewById(R.id.tb_3_1);
		tb_3_2= (TextView) view.findViewById(R.id.tb_3_2);
		tb_3_3= (TextView) view.findViewById(R.id.tb_3_3);
		tb_3_4= (TextView) view.findViewById(R.id.tb_3_4);
		tb_4_1= (TextView) view.findViewById(R.id.tb_4_1);
		tb_4_2= (TextView) view.findViewById(R.id.tb_4_2);
		tb_4_3= (TextView) view.findViewById(R.id.tb_4_3);
		tb_4_4= (TextView) view.findViewById(R.id.tb_4_4);
		tb_5_1= (TextView) view.findViewById(R.id.tb_5_1);
		tb_5_2= (TextView) view.findViewById(R.id.tb_5_2);
		tb_5_3= (TextView) view.findViewById(R.id.tb_5_3);
		tb_5_4= (TextView) view.findViewById(R.id.tb_5_4);
		list_table=new ArrayList<TextView>();
		lists_table=new ArrayList<List<TextView>>();
		list_table.add(tb_1_1);
		list_table.add(tb_1_2);
		list_table.add(tb_1_3);
		list_table.add(tb_1_4);
		lists_table.add(list_table);
		list_table=new ArrayList<TextView>();
		list_table.add(tb_2_1);
		list_table.add(tb_2_2);
		list_table.add(tb_2_3);
		list_table.add(tb_2_4);
		lists_table.add(list_table);
		list_table=new ArrayList<TextView>();
		list_table.add(tb_3_1);
		list_table.add(tb_3_2);
		list_table.add(tb_3_3);
		list_table.add(tb_3_4);
		lists_table.add(list_table);
		list_table=new ArrayList<TextView>();
		list_table.add(tb_4_1);
		list_table.add(tb_4_2);
		list_table.add(tb_4_3);
		list_table.add(tb_4_4);
		lists_table.add(list_table);
		list_table=new ArrayList<TextView>();
		list_table.add(tb_5_1);
		list_table.add(tb_5_2);
		list_table.add(tb_5_3);
		list_table.add(tb_5_4);
		lists_table.add(list_table);
		btn_inquire= (Button) view.findViewById(R.id.btn_inquire);
		btn_inquire.setOnClickListener(this);
        sp_sort= (Spinner) view.findViewById(R.id.sp_sort);
		sp_item=0;
		jsonArray = new JSONArray();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.btn_inquire:
				Log.d("AAA", "onClick: "+sp_item);
				jsonArray=new JSONArray();
				volley();
				break;
		}
	}
}
