package com.mad.trafficclient;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.mad.trafficclient.login.LoginActivity;

public class GuideActivity extends Activity {

	RelativeLayout guide_RL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		SharedPreferences jame=getSharedPreferences("jame",0);
		boolean isFirst=jame.getBoolean("isFirst",true);
		if (isFirst){
			setContentView(R.layout.activity_guide);
			SharedPreferences.Editor editor=jame.edit();
			editor.putBoolean("isFirst",false);
			editor.commit();
			guide_RL = (RelativeLayout) findViewById(R.id.guide_RL);
			guide_RL.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(GuideActivity.this,
							LoginActivity.class);
					startActivity(intent);
					finish();
				}
			});
		}else {
			Intent intent = new Intent(GuideActivity.this,
					LoginActivity.class);
			startActivity(intent);
			finish();
		}


	}

}
