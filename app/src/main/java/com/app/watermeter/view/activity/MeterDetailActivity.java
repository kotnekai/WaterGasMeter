package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.view.base.BaseActivity;

import butterknife.BindView;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * @author admin
 */
public class MeterDetailActivity extends BaseActivity {


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


    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, MeterDetailActivity.class);
        return intent;
    }

    @Override
    protected int getCenterView() {
        return R.layout.activity_meter_detail;
    }

    @Override
    protected void initHeader() {
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
     * 水表数据
     */
    private void initData() {
    }

    /**
     * 图表
     */
    private void initChart() {
    }

    /**
     * 获取类型
     */
    private void initIntent() {
        Intent intent = getIntent();
    }

}
