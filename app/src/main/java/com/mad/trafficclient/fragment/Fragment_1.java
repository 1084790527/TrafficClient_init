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

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_PRIVATE;


public class Fragment_1 extends Fragment implements View.OnClickListener
{

	String TAG = "AAA";
	private View view;
	private TextView tv_accountBalance;
	private EditText tv_rechargeAmount;
	private Spinner sp_numberPlate;
	private Button btn_inquire,btn_recharge;
	private List<String> list;
	private ArrayAdapter<String> adapter;
	private int numberPlate;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_layout01, container, false);

		//初始化控件
		init();

		spinner();
		balanceData(1);

		//单个线程
//		Executors.newCachedThreadPool().execute(new Runnable() {
//			@Override
//			public void run() {
//				Log.d("AAA", "run: ttt");
//			}
//		});

		//定时器
//		Executors.newScheduledThreadPool(3).scheduleWithFixedDelay(new Runnable() {
//			@Override
//			public void run() {
//				Log.d("AAA", "run: rrr");
//			}
//		},0,1,TimeUnit.SECONDS);

		//延迟启动
//		Executors.newScheduledThreadPool(1).schedule(new Runnable() {
//			@Override
//			public void run() {
//				Log.d("AAA", "run: xxx");
//			}
//		},1,TimeUnit.SECONDS);

		return view;
	}

	private void setData(final int CarId, final int Money){
		final String UserName="dsdsd";
		JSONObject jsonObject=new JSONObject();
		try {
			jsonObject.put("CarId",CarId);
			jsonObject.put("Money",Money);
			jsonObject.put("UserName",UserName);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		RequestQueue queue= Volley.newRequestQueue(getActivity());
		JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://172.168.30.65:8080/transportservice/action/SetCarAccountRecharge.do", jsonObject,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject jsonObject) {
//						Log.d("AAA", "onResponse: "+jsonObject.toString());

						JSONArray jsonArray=new JSONArray();
						try {
							SharedPreferences loadSettingLoad = getActivity().getSharedPreferences("recharge", MODE_PRIVATE);
							JSONArray j=new JSONArray(loadSettingLoad.getString("data", ""));
							for (int i=0;i<j.length();i++){
								jsonArray.put(j.getJSONObject(i));
							}

						}catch (Exception e){
							e.printStackTrace();
						}
						JSONObject object=new JSONObject();
						try {
							Date date=new Date();
							SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm");
							object.put("serialNumber",jsonArray.length()+1);
							object.put("carNumber",CarId);
							object.put("rechargeAmount",Money);
							object.put("operator",UserName);
							object.put("rechargeTime",format.format(date));
							jsonArray.put(object);
							SharedPreferences spSettingSave = getActivity().getSharedPreferences("recharge", MODE_PRIVATE);
							SharedPreferences.Editor editor = spSettingSave.edit();
							editor.putString("data", jsonArray.toString());
							editor.commit();
						} catch (JSONException e) {
							e.printStackTrace();
						}
//						SharedPreferences loadSettingLoad = getActivity().getSharedPreferences("recharge", MODE_PRIVATE);
//						String s=loadSettingLoad.getString("data", "");
//							Log.d(TAG, "SP: "+s);
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {

			}
		});
		queue.add(request);
	}

	private void balanceData(int CarId){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("CarId",CarId);
            jsonObject.put("UserName","sdsd");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue queue= Volley.newRequestQueue(getActivity());
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, "http://172.168.30.65:8080/transportservice/action/GetCarAccountBalance.do", jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("AAA", "onResponse: "+jsonObject.toString());
						try {
							tv_accountBalance.setText(jsonObject.getString("Balance")+"元");
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(request);
    }

	private void spinner() {
		list=new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

		sp_numberPlate.setAdapter(adapter);

		sp_numberPlate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//				Log.d("AAA", "adapterView:"+adapterView+",view:"+view+",i:"+i+",l:"+l);
				numberPlate=i+1;
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	private void init() {
		numberPlate=1;
		tv_accountBalance = (TextView) view.findViewById(R.id.tv_accountBalance);
		tv_rechargeAmount = (EditText) view.findViewById(R.id.tv_rechargeAmount);
		sp_numberPlate = (Spinner) view.findViewById(R.id.sp_numberPlate);
		btn_inquire = (Button) view.findViewById(R.id.btn_inquire);
		btn_inquire.setOnClickListener(this);
		btn_recharge = (Button) view.findViewById(R.id.btn_recharge);
		btn_recharge.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()){
            case R.id.btn_inquire:
                //查询
				balanceData(numberPlate);
                break;
            case R.id.btn_recharge:
				//充值
				int money=Integer.parseInt(tv_rechargeAmount.getText().toString());
				if (money<=0||money>999){
					Toast.makeText(getActivity(),"只能输入1到999之间的整数",Toast.LENGTH_LONG).show();
					return;
				}
				setData(numberPlate, money);
                break;
        }
	}
}
