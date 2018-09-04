package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.utils.ToastUtil;
import com.app.watermeter.view.base.BaseActivity;
import com.app.watermeter.view.marker.MeterChartMarkerView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author admin
 */
public class MeterDetailActivity extends BaseActivity {

    public static final String METER_TYPE = "meterType";

    Context mContext;
    @BindView(R.id.tvSn)
    TextView tvSn;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvMeterName)
    TextView tvMeterName;
    @BindView(R.id.tvLastValue)
    TextView tvLastValue;
    @BindView(R.id.tvLastDte)
    TextView tvLastDte;
    @BindView(R.id.tvCurrentValue)
    TextView tvCurrentValue;
    @BindView(R.id.tvCurrentDte)
    TextView tvCurrentDte;
    @BindView(R.id.tvBalance)
    TextView tvBalance;
    @BindView(R.id.tvMoney)
    TextView tvMoney;
    @BindView(R.id.tvCharge)
    TextView tvCharge;
    @BindView(R.id.tvGotoPerStorage)
    TextView tvGotoPerStorage;
    @BindView(R.id.tvGotoPayment)
    TextView tvGotoPayment;
    @BindView(R.id.lineChart)
    LineChart mChart;

    private int typeValue;



    public static Intent makeIntent(Context context, int type) {
        Intent intent = new Intent(context, MeterDetailActivity.class);
        intent.putExtra(METER_TYPE, type);
        return intent;
    }


    @Override
    protected int getCenterView() {
        return R.layout.activity_meter_detail;
    }

    @Override
    protected void initHeader() {
        setHeader_RightText(getString(R.string.cancel_binding));
        setHeader_RightTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showLong("取消绑定");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = MeterDetailActivity.this;
        initIntent();
        initData();
        initMPAndroidChart();

    }

    private void initMPAndroidChart() {
//        mChart.setIsDrag(true);
        //设置是否显示表格背景
        mChart.setDrawGridBackground(true);
//        mChart.setDrawHighlightCenter(true);
        // no description text
        Description description = new Description();
        description.setText("");
        mChart.setDescription(description);
//        mChart.setNoDataTextDescription("");
//        mChart.setNoDataText(getContext().getString(R.string.history_unrecorded));
//        mChart.setNoDataTextSize(13);
        mChart.setNoDataTextColor(0x85FFFFFF);
        //设置是否可以触摸
        mChart.setTouchEnabled(true);
        //设置是否可以拖拽
        mChart.setDragEnabled(true);
        //设置是否可以缩放
        mChart.setScaleEnabled(false);
        mChart.setPinchZoom(true);
        mChart.setDragDecelerationEnabled(false);
        mChart.setDoubleTapToZoomEnabled(false);
        mChart.setMinOffset(0);

        mChart.setExtraOffsets(10f,10f,10f,10f);
        mChart.getAxisRight().setEnabled(false);
        // 拖拽时能否高亮（十字瞄准触摸到的点），默认true
        mChart.setHighlightPerDragEnabled(true);
        mChart.setHighlightPerTapEnabled(false);
        //设置悬浮
        MeterChartMarkerView mv = new MeterChartMarkerView(this,R.layout.custom_marker_view);
        mChart.setMarker(mv);



        //获取图例对象
        Legend legend = mChart.getLegend();
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        legend.setForm(Legend.LegendForm.LINE);
        //设置图例不显示
        legend.setEnabled(false);
        mChart.setMarkerView(null);
//        mChart.setOnChartValueSelectedListener(this);


        //X轴
        XAxis xAxis = mChart.getXAxis();
        //设置显示X轴
        xAxis.setDrawAxisLine(false);
        xAxis.setTextSize(12);
        xAxis.setYOffset(20f);
        //设置线为虚线
        xAxis.enableGridDashedLine(5f, 5f, 0f);
        //设置X轴显示的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置从X轴发出横线
        xAxis.setDrawGridLines(true);
        xAxis.setAxisLineColor(getResources().getColor(R.color.gridlinehorizoncolor));
        //设置为true当一个页面显示条目过多，X轴值隔一个显示一个
        xAxis.setGranularityEnabled(true);


        //Y轴
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setAxisMaximum(50f);
        leftAxis.setAxisMinimum(0f);
        //leftAxis.setYOffset(20f);
//        leftAxis.enableGridDashedLine(5f, 5f, 0f);
        leftAxis.setDrawZeroLine(false);

        leftAxis.setDrawLimitLinesBehindData(true);
        leftAxis.setTextSize(11);
        //如果沿着轴线的线应该被绘制，则将其设置为true,隐藏Y轴
        leftAxis.setDrawAxisLine(false);
        leftAxis.setEnabled(true);
        leftAxis.setGridColor(getResources().getColor(R.color.gridlinehorizoncolor));


        LimitLine llXAxis = new LimitLine(10f, "Index 10");
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);


        // add data
        setData(24, 24);
    }

    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range) + 3;
            values.add(new Entry(i, val));
        }

        LineDataSet lineSet;

        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            lineSet = (LineDataSet)mChart.getData().getDataSetByIndex(0);
            lineSet.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            lineSet = new LineDataSet(values, "DataSet 1");
            //设置图标不显示
            lineSet.setDrawIcons(false);
            //设置Y值使用左边Y轴的坐标值
            lineSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            //画虚线
//            set1.enableDashedLine(10f, 5f, 0f);
//            set1.enableDashedHighlightLine(10f, 5f, 0f);
            //设置线的颜色
            lineSet.setColor(getResources().getColor(R.color.main_blue_color));
            //设置数据点圆形的颜色
            lineSet.setCircleColor(getResources().getColor(R.color.colorAccent));
            //设置折线宽度
            lineSet.setLineWidth(2f);
            //设置折现点圆点半径
            lineSet.setCircleRadius(4f);
            //设置是否在数据点中间显示一个孔
            lineSet.setDrawCircleHole(false);
            lineSet.setValueTextSize(13f);
            //设置允许填充
            lineSet.setDrawFilled(false);
            lineSet.setFormLineWidth(1f);
            lineSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            lineSet.setFormSize(15.f);
            lineSet.setHighlightEnabled(true);
            lineSet.setDrawHorizontalHighlightIndicator(false);
            lineSet.setDrawVerticalHighlightIndicator(true);

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(lineSet);

            // create a data object with the datasets
            LineData data = new LineData(dataSets);

            //设置一页最大显示个数为6，超出部分就滑动
            float ratio = (float) count/(float) 6;
            //显示的时候是按照多大的比率缩放显示,1f表示不放大缩小
            mChart.zoom(ratio,1f,0,0);
            //设置从X轴出来的动画时间
//            mChart.animateX(1500);
            //设置XY轴动画
            mChart.animateXY(1500,1500, Easing.EasingOption.EaseInSine, Easing.EasingOption.EaseInSine);
            // set data
            mChart.setData(data);
            mChart.invalidate();
        }
    }


    /**
     * 获取类型
     */
    private void initIntent() {
        Intent intent = getIntent();
        typeValue = intent.getIntExtra(METER_TYPE, CommonParams.TYPE_WATER);
    }

    /**
     * 水表数据
     */
    private void initData() {
        switch (typeValue) {
            case CommonParams.TYPE_WATER:
                setHeaderTitle(getString(R.string.water_meter_detail_title));
                break;
            case CommonParams.TYPE_ELECT:
                setHeaderTitle(getString(R.string.elect_meter_detail_title));
                break;
            case CommonParams.TYPE_GAS:
                setHeaderTitle(getString(R.string.gas_meter_detail_title));
                break;
        }
    }


}
