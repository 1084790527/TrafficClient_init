package com.mad.trafficclient.fragment;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mad.trafficclient.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

public class Fragment_6 extends Fragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {


    String TAG="AAA";
    private View view,vp_humidity,vp_temperature,vp_illumination,vp_co2,vp_pm25,vp_roadstatus;
    private ViewPager vp_vp;
    private List<View> listView;
    private RadioGroup rg_rg;
    private List<Integer> lists = new ArrayList<Integer>();
    public Fragment_6() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_layout06, container, false);

        init();
        adapter();

        return view;
    }

    private void adapter() {
        LayoutInflater inflater=getActivity().getLayoutInflater();
        vp_temperature=inflater.inflate(R.layout.vp_temperature,null);
        vp_humidity=inflater.inflate(R.layout.vp_humidity,null);
        vp_illumination=inflater.inflate(R.layout.vp_illumination,null);
        vp_co2=inflater.inflate(R.layout.vp_co2,null);
        vp_pm25=inflater.inflate(R.layout.vp_pm25,null);
        vp_roadstatus=inflater.inflate(R.layout.vp_roadstatus,null);

        listView=new ArrayList<View>();
        listView.add(vp_temperature);
        listView.add(vp_humidity);
        listView.add(vp_illumination);
        listView.add(vp_co2);
        listView.add(vp_pm25);
        listView.add(vp_roadstatus);

        PagerAdapter adapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return listView.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(listView.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(listView.get(position));
                return listView.get(position);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
        };
        vp_vp.setAdapter(adapter);
        vp_vp.setOnPageChangeListener(this);
    }

    private void init() {
        vp_vp= (ViewPager) view.findViewById(R.id.vp_vp);
        rg_rg= (RadioGroup) view.findViewById(R.id.rg_rg);
        rg_rg.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
//        Log.d(TAG, "onCheckedChanged: "+radioGroup.getCheckedRadioButtonId());
        switch (i){
            case R.id.rb_1:
                vp_vp.setCurrentItem(0);
                LinearLayout ll_temperature = (LinearLayout) vp_temperature.findViewById(R.id.ll_temperature);
                ll_temperature.removeAllViews();

                XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
                mRenderer.setMarginsColor(Color.parseColor("#f1f1f1f1"));
                // 设置背景颜色
                mRenderer.setApplyBackgroundColor(true);
                mRenderer.setBackgroundColor(Color.WHITE);

                //设置Title的内容和大小
//                mRenderer.setChartTitle("温度");
//                mRenderer.setChartTitleTextSize(50);

                //图表与四周的边距
                mRenderer.setMargins(new int[]{80, 80, 50, 50});

                //设置X,Y轴title的内容和大小
//                mRenderer.setXTitle("");
//                mRenderer.setYTitle("");
//                mRenderer.setAxisTitleTextSize(30);
                //renderer.setAxesColor(Color.WHITE);
//                mRenderer.setLabelsColor(Color.BLACK);

                //图例文字的大小
//                mRenderer.setLegendTextSize(20);

                // x、y轴上刻度颜色和大小
                mRenderer.setXLabelsColor(Color.BLACK);
                mRenderer.setYLabelsColor(0, Color.BLACK);
                mRenderer.setLabelsTextSize(20);
                mRenderer.setYLabelsPadding(30);

                // 设置X轴的最小数字和最大数字，由于我们的数据是从1开始，所以设置为0.5就可以在1之前让出一部分
                // 有兴趣的童鞋可以删除下面两行代码看一下效果
                mRenderer.setPanEnabled(false, false);

                //显示网格
                mRenderer.setShowGrid(true);

                //X,Y轴上的数字数量
                mRenderer.setXLabels(20);
                mRenderer.setYLabels(10);

                // 设置X轴的最小数字和最大数字
//                mRenderer.setXAxisMin(1);
//                mRenderer.setXAxisMax(20);
                // 设置Y轴的最小数字和最大数字
//                mRenderer.setYAxisMin(0);
//                mRenderer.setYAxisMax(100);

                // 设置渲染器显示缩放按钮
                mRenderer.setZoomButtonsVisible(false);
                // 设置渲染器允许放大缩小
                mRenderer.setZoomEnabled(false);
                // 消除锯齿
                mRenderer.setAntialiasing(true);

                // 刻度线与X轴坐标文字左侧对齐
                mRenderer.setXLabelsAlign(Paint.Align.LEFT);
                // Y轴与Y轴坐标文字左对齐
                mRenderer.setYLabelsAlign(Paint.Align.LEFT);

                // 允许左右拖动,但不允许上下拖动.
                mRenderer.setPanEnabled(false, false);

                XYSeriesRenderer renderer = new XYSeriesRenderer();
                //设置折线宽度
                renderer.setLineWidth(2);
                //设置折线颜色
                renderer.setColor(Color.GRAY);
                renderer.setDisplayBoundingPoints(true);
                //点的样式
                renderer.setPointStyle(PointStyle.CIRCLE);
                //设置点的大小
                renderer.setPointStrokeWidth(10);
                //设置数值显示的字体大小
//                renderer.setChartValuesTextSize(30);
                //显示数值
                renderer.setDisplayChartValues(false);

                mRenderer.addSeriesRenderer(renderer);

                List<Integer> lists = new ArrayList<Integer>();
                lists.clear();
                for (int t = 0; t < 20; t++) {
                    int value = ((int) (Math.random() * 100));
                    lists.add(value);
                }

                XYMultipleSeriesDataset barDataset  = new XYMultipleSeriesDataset();
                CategorySeries barSeries = new CategorySeries("");

                for (int t = 0; t < lists.size(); t++) {
                    barSeries.add(lists.get(t));
                }

                barDataset.addSeries(barSeries.toXYSeries());

                GraphicalView chartView = ChartFactory.getLineChartView(getActivity(), barDataset , mRenderer);

                ll_temperature.addView(chartView);
                break;
            case R.id.rb_2:
                vp_vp.setCurrentItem(1);
                break;
            case R.id.rb_3:
                vp_vp.setCurrentItem(2);
                break;
            case R.id.rb_4:
                vp_vp.setCurrentItem(3);
                break;
            case R.id.rb_5:
                vp_vp.setCurrentItem(4);
                break;
            case R.id.rb_6:
                vp_vp.setCurrentItem(5);
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
