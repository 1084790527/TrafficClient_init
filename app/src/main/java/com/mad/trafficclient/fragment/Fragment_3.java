/**
 * 
 */
package com.mad.trafficclient.fragment;

import com.mad.trafficclient.R;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class Fragment_3 extends Fragment implements View.OnClickListener
{

	String TAG="AAA";
	private View view;
	private Spinner sp_sort;
	private List<String> list;
	private ArrayAdapter<String> adapter;
	private TableLayout t_table;
	private Button btn_inquire;
	private int sort;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		view = inflater.inflate(R.layout.fragment_layout03, container, false);

		init();

		spinner();

		sort=1;
		table();

		return view;
	}

	private void table() {
		String[] s={"序号","车号","充值金额（元）","操作人","充值时间"};

		View view=getActivity().getLayoutInflater().inflate(R.layout.table_layour,null);
		((TextView)view.findViewById(R.id.tv_serialNumber)).setText(s[0]);
		((TextView)view.findViewById(R.id.tv_carNumber)).setText(s[1]);
		((TextView)view.findViewById(R.id.tv_rechargeAmount)).setText(s[2]);
		((TextView)view.findViewById(R.id.tv_operator)).setText(s[3]);
		((TextView)view.findViewById(R.id.tv_rechargeTime)).setText(s[4]);
		t_table.addView(view);

//		for (int i=0;i<30;i++){
//			view=getActivity().getLayoutInflater().inflate(R.layout.table_layour,null);
//			((TextView)view.findViewById(R.id.tv_serialNumber)).setText(i+"");
//			((TextView)view.findViewById(R.id.tv_carNumber)).setText(i+"");
//			((TextView)view.findViewById(R.id.tv_rechargeAmount)).setText(i+"");
//			((TextView)view.findViewById(R.id.tv_operator)).setText(i+"");
//			((TextView)view.findViewById(R.id.tv_rechargeTime)).setText(i+"");
//			t_table.addView(view);
//		}

		SharedPreferences loadSettingLoad = getActivity().getSharedPreferences("recharge", MODE_PRIVATE);
		try {
			JSONArray jsonArray=new JSONArray(loadSettingLoad.getString("data", ""));
			if (jsonArray.length()==0){
				Toast.makeText(getActivity(),"暂无历史记录",Toast.LENGTH_LONG).show();
			}
			for (int i=0;i<jsonArray.length();i++){
				JSONObject object;
				if (sort==0){
					object=jsonArray.getJSONObject(i);
				}else {
					object=jsonArray.getJSONObject(jsonArray.length()-1-i);
				}

				Log.d(TAG, "table: "+object.toString());
				view=getActivity().getLayoutInflater().inflate(R.layout.table_layour,null);
				int x=i+1;
				((TextView)view.findViewById(R.id.tv_serialNumber)).setText(x+"");
				((TextView)view.findViewById(R.id.tv_carNumber)).setText(object.getString("carNumber")+"");
				((TextView)view.findViewById(R.id.tv_rechargeAmount)).setText(object.getString("rechargeAmount")+"");
				((TextView)view.findViewById(R.id.tv_operator)).setText(object.getString("operator")+"");
				((TextView)view.findViewById(R.id.tv_rechargeTime)).setText(object.getString("rechargeTime")+"");
				t_table.addView(view);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}


	private void spinner() {
		list=new ArrayList<String>();
		list.add("时间升序");
		list.add("时间降序");
		adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		sp_sort.setAdapter(adapter);
		sp_sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				sort=i;
//				Toast.makeText(getActivity(),""+i,Toast.LENGTH_LONG).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {
			}
		});
	}

	private void init() {
		sp_sort= (Spinner) view.findViewById(R.id.sp_sort);
		t_table= (TableLayout) view.findViewById(R.id.t_table);
		btn_inquire= (Button) view.findViewById(R.id.btn_inquire);
		btn_inquire.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.btn_inquire:
				t_table.removeAllViews();
				table();
				break;
		}
	}
}
