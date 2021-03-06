/**
 * 
 */
package com.mad.trafficclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.mad.trafficclient.fragment.FragmentHome;
import com.mad.trafficclient.fragment.Fragment_1;
import com.mad.trafficclient.fragment.Fragment_10;
import com.mad.trafficclient.fragment.Fragment_11;
import com.mad.trafficclient.fragment.Fragment_12;
import com.mad.trafficclient.fragment.Fragment_13;
import com.mad.trafficclient.fragment.Fragment_14;
import com.mad.trafficclient.fragment.Fragment_15;
import com.mad.trafficclient.fragment.Fragment_16;
import com.mad.trafficclient.fragment.Fragment_17;
import com.mad.trafficclient.fragment.Fragment_18;
import com.mad.trafficclient.fragment.Fragment_19;
import com.mad.trafficclient.fragment.Fragment_2;
import com.mad.trafficclient.fragment.Fragment_20;
import com.mad.trafficclient.fragment.Fragment_21;
import com.mad.trafficclient.fragment.Fragment_22;
import com.mad.trafficclient.fragment.Fragment_23;
import com.mad.trafficclient.fragment.Fragment_24;
import com.mad.trafficclient.fragment.Fragment_25;
import com.mad.trafficclient.fragment.Fragment_26;
import com.mad.trafficclient.fragment.Fragment_27;
import com.mad.trafficclient.fragment.Fragment_28;
import com.mad.trafficclient.fragment.Fragment_29;
import com.mad.trafficclient.fragment.Fragment_3;
import com.mad.trafficclient.fragment.Fragment_30;
import com.mad.trafficclient.fragment.Fragment_4;
import com.mad.trafficclient.fragment.Fragment_5;
import com.mad.trafficclient.fragment.Fragment_6;
import com.mad.trafficclient.fragment.Fragment_7;
import com.mad.trafficclient.fragment.Fragment_8;
import com.mad.trafficclient.fragment.Fragment_9;

/**
 * @author zhaowei
 *
 */
public class MainActivity extends FragmentActivity 
{
	private SlidingPaneLayout slidepanel;

	private Fragment fragment;
	
	private ListView listView;	
	SimpleAdapter simpleAdapter;
	
    ArrayList<HashMap<String, Object>> actionItems;
    SimpleAdapter actionAdapter;
    
    TextView tV_title;
	ImageView iVSliding;
    ImageView ivHome;
	
	private android.app.FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		setContentView(R.layout.activity_main);
		slidepanel = (SlidingPaneLayout) findViewById(R.id.slidingPL);

		listView = (ListView)findViewById(R.id.listView1);
		ivHome = (ImageView)findViewById(R.id.imageView_home);
		ivHome.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setHome();
			}
		});

		iVSliding = (ImageView)findViewById(R.id.imageView_Sliding) ;
		tV_title = (TextView)findViewById(R.id.tv_title);
		tV_title.setText(getString(R.string.app_title));


		iVSliding.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (slidepanel.isOpen()) {
					slidepanel.closePane();
				} else {
					slidepanel.openPane();
				}
			}
		});
        
      
        final String[] actionTexts = new String[]{
                getString(R.string.res_left_wodezhanghu),
                getString(R.string.res_left_hldgl),
                "账单管理",
                "车辆违章",
				"环境指标",
                "实时显示",
				"阈值设置",
				"出行管理",
				"账户管理",
				"公交查询",
				"生活指数",
				"个人中心",
                "红绿灯管理",
                "账户管理",
                "账户设置",
				"路况查询",
				"生活助手",
				"公交查询",
				"意见反馈",
				"天气信息",
				"二维码支付",
				"红绿灯管理",
				"车辆违章",
				"路况查询",
				"个人中心",
				"f26",
				"f27",
				"f28",
				"f29",
				"f30",
                getString(R.string.res_left_exit)
        };
        final int[]  actionImages = new int[]{
                R.drawable.btn_l_star,
                R.drawable.btn_l_book,
                R.drawable.btn_l_slideshow,
                R.drawable.btn_l_target,
                R.drawable.btn_l_star,
                R.drawable.btn_l_book,
                R.drawable.btn_l_slideshow,
                R.drawable.btn_l_target,
                R.drawable.btn_l_star,
                R.drawable.btn_l_book,
                R.drawable.btn_l_slideshow,
                R.drawable.btn_l_target,
                R.drawable.btn_l_star,
                R.drawable.btn_l_book,
                R.drawable.btn_l_slideshow,
				R.drawable.btn_l_target,
				R.drawable.btn_l_star,
				R.drawable.btn_l_book,
				R.drawable.btn_l_slideshow,
				R.drawable.btn_l_target,
				R.drawable.btn_l_star,
				R.drawable.btn_l_book,
				R.drawable.btn_l_slideshow,
				R.drawable.btn_l_target,
				R.drawable.btn_l_star,
				R.drawable.btn_l_book,
				R.drawable.btn_l_slideshow,
				R.drawable.btn_l_target,
				R.drawable.btn_l_star,
				R.drawable.btn_l_book,
                R.drawable.btn_l_download,
        };

        actionItems = new ArrayList<HashMap<String, Object>>();
        actionAdapter = new SimpleAdapter(getApplicationContext(), actionItems, R.layout.left_list_fragment_item,
                new String[]{"action_icon", "action_name"},
                new int[]{R.id.recharge_method_icon, R.id.recharge_method_name});

        for(int i = 0; i < actionImages.length; ++i) {
            HashMap<String, Object> item1 = new HashMap<String, Object>();
            item1.put("action_icon", actionImages[i]);
            item1.put("action_name", actionTexts[i]);
            actionItems.add(item1);
        }
        listView.setAdapter(actionAdapter);        
        
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				LinearLayout layout= (LinearLayout) findViewById(R.id.ll_t);
				LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) layout.getLayoutParams();
				if (arg2==13||arg2==18||arg2==19){
					params.height=0;
					layout.setLayoutParams(params);
				}else {
					params.height=64;
					layout.setLayoutParams(params);
				}


				switch (arg2) {
				case 0:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_1()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 1:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_2()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 2:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_3()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 3:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_4()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 4:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_5()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
                case 5:
                    getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_6()).commit();
                    tV_title.setText(actionTexts[arg2]);
                    break;
				case 6:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_7()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 7:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_8()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 8:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_9()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 9:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_10()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 10:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_11()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 11:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_12()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 12:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_13()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 13:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_14()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 14:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_15()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 15:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_16()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 16:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_17()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 17:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_18()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 18:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_19()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 19:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_20()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 20:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_21()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 21:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_22()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 22:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_23()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 23:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_24()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 24:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_25()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 25:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_26()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 26:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_27()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 27:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_28()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 28:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_29()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 29:
					getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_30()).commit();
					tV_title.setText(actionTexts[arg2]);
					break;
				case 30:
				case 31:
				case 32:
					exitAppDialog();
					break;
				default:
					break;
				}

			}
		});
        
        fragmentManager = getFragmentManager();
        
        setHome();

		

	}
	
	public void setHome() {
		getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new FragmentHome()).commit();
		tV_title.setText(R.string.app_title);

	}
	
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

        int[] listImg = new int[] { R.drawable.icon_trafic, R.drawable.icon_busstop, R.drawable.icon_lamp, R.drawable.icon_parking, R.drawable.icon_light,R.drawable.icon_etc, R.drawable.icon_speed };
        String[] listName = new String[] { "城市交通", "公交站点", "城市环境", "找车位", "红绿灯管理", "ETC管理", "高速车速监控" };
        for (int i = 0; i < listImg.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("itemImage", listImg[i]);
            item.put("itemName", listName[i]);
            items.add(item);
        }
        return items;
    }

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		// 按下键盘上返回按钮
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			exitAppDialog();

			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}

	}

	public void exitAppDialog() {
		new AlertDialog.Builder(this)
				// .setIcon(android.R.drawable.ic_menu_info_detailsp)
				.setTitle("提示").setMessage("你确定要退出吗").setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		}).setPositiveButton("确定", new DialogInterface.OnClickListener()

		{
			public void onClick(DialogInterface dialog, int whichButton) {
				finish();
			}
		}).show();

	}





}
