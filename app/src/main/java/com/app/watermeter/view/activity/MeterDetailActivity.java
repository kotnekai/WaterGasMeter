package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.watermeter.R;
import com.app.watermeter.utils.ToastUtil;
import com.app.watermeter.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * @author admin
 */
public class MeterDetailActivity extends BaseActivity {

    public static final String METER_TYPE = "meterType";
    public static final int TYPE_WATER = 97;
    public static final int TYPE_ELECT = 98;
    public static final int TYPE_GAS = 99;

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
    @BindView(R.id.lineChartView)
    LineChartView lineChartView;

    private int typeValue;

    private LineChartData data;
    private int numberOfLines = 1;
    private int maxNumberOfLines = 4;
    private int numberOfPoints = 12;

    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];

    private boolean hasAxes = true;
    private boolean hasAxesNames = true;

    String[] date = {"2", "4", "6", "8", "10", "12", "14", "16", "18", "20","22","24"};//X轴的标注
    int[] score = {50, 42, 90, 33, 10, 74, 22, 18, 79, 20,45,65};//图表的数据点
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();


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
        initChart();
    }

    /**
     * 获取类型
     */
    private void initIntent() {
        Intent intent = getIntent();
        typeValue = intent.getIntExtra(METER_TYPE, TYPE_WATER);
    }

    /**
     * 水表数据
     */
    private void initData() {
        switch (typeValue) {
            case TYPE_WATER:
                setHeaderTitle(getString(R.string.water_meter_detail_title));
                break;
            case TYPE_ELECT:
                setHeaderTitle(getString(R.string.elect_meter_detail_title));
                break;
            case TYPE_GAS:
                setHeaderTitle(getString(R.string.gas_meter_detail_title));
                break;
        }
    }

    /**
     * 图表
     */
    private void initChart() {
        lineChartView.setOnValueTouchListener(new ValueTouchListener());
        //缩放平移
        lineChartView.setInteractive(true);
        //放大
        lineChartView.setZoomEnabled(false);
        //横向滚动
        lineChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);

        generateValues();

        generateData();
    }

    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(mContext, "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }

    private void generateValues() {
        for (int i = 0; i < maxNumberOfLines; ++i) {
            for (int j = 0; j < numberOfPoints; ++j) {
                randomNumbersTab[i][j] = (float) Math.random() * 100f;
            }
        }

        for (int i = 0; i < date.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
        }

        for (int i = 0; i < score.length; i++) {
            mPointValues.add(new PointValue(i, score[i]));

        }

    }

    private void generateData() {

        List<Line> lines = new ArrayList<Line>();
        for (int i = 0; i < numberOfLines; ++i) {

            List<PointValue> values = new ArrayList<PointValue>();
            for (int j = 0; j < numberOfPoints; ++j) {
                values.add(new PointValue(j, randomNumbersTab[i][j]));
            }

            Line line = new Line(mPointValues);
            line.setColor(ChartUtils.COLORS[i]);
            line.setShape(ValueShape.CIRCLE);
            line.setCubic(false);
            line.setFilled(false);
            line.setHasLabels(true);
            line.setHasLabelsOnlyForSelected(false);
            line.setHasLines(true);
            line.setHasPoints(true);
            line.setHasGradientToTransparent(false);
            line.setStrokeWidth(2);
            line.setPointRadius(4);
            lines.add(line);
        }

        data = new LineChartData(lines);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
//            if (hasAxesNames) {
//                axisX.setName("时间");
//                axisY.setName("数值");
//            }

            axisX.setValues(mAxisXValues);

            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);
        lineChartView.setLineChartData(data);

    }


}
