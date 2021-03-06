package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.common.ComApplication;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.common.Constants;
import com.app.watermeter.eventBus.BindingStatusEvent;
import com.app.watermeter.eventBus.GetChartReadListEvent;
import com.app.watermeter.eventBus.GetMeterInfoEvent;
import com.app.watermeter.eventBus.GetOrderInfoEvent;
import com.app.watermeter.eventBus.UnBindErrEvent;
import com.app.watermeter.eventBus.UnBindEvent;
import com.app.watermeter.manager.MeterManager;
import com.app.watermeter.model.MeterInfoModel;
import com.app.watermeter.model.MeterReadModel;
import com.app.watermeter.utils.DateUtils;
import com.app.watermeter.utils.DialogUtils;
import com.app.watermeter.utils.MyXFormatter;
import com.app.watermeter.utils.PreferencesUtils;
import com.app.watermeter.utils.ToastUtil;
import com.app.watermeter.view.base.BaseActivity;
import com.app.watermeter.view.marker.MeterChartMarkerView;
import com.app.watermeter.view.marker.XAxisEntry;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author admin
 */
public class MeterDetailActivity extends BaseActivity {

    private final int UNBIND_SUCCESS = 0;
    private final int METER_EMPTY = 1;
    private final int BINDED = 2;
    private final int SERVER_ERR = 5;


    Context mContext;
    @BindView(R.id.tvSn)
    TextView tvSn;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvUnit)
    TextView tvUnit;
    @BindView(R.id.tvMeterName)
    TextView tvMeterName;
    @BindView(R.id.tvLastValue)
    TextView tvLastValue;
    @BindView(R.id.tvLastDate)
    TextView tvLastDate;
    @BindView(R.id.tvCurrentValue)
    TextView tvCurrentValue;
    @BindView(R.id.tvCurrentDate)
    TextView tvCurrentDate;
    @BindView(R.id.tvBalance)
    TextView tvBalance;
    @BindView(R.id.tvMoney)
    TextView tvMoney;
    @BindView(R.id.tvCharge)
    TextView tvCharge;
    @BindView(R.id.tvGotoMonthBill)
    TextView tvGotoMonthBill;
    @BindView(R.id.tvGotoPayment)
    TextView tvGotoPayment;
    @BindView(R.id.lineChart)
    LineChart mChart;
    @BindView(R.id.tvNoData)
    TextView tvNoData;
    @BindView(R.id.tvStartMonth)
    TextView tvStartMonth;
    @BindView(R.id.tvMeterCode)
    TextView tvMeterCode;


    public int requestIndex = 0;
    public final int MAX_REQUEST_COUNT = 6;
    private int meterId;
    private int meterType;
    private String mTimeStamp;
    private String meterSn;
    MeterInfoModel model;
    List<MeterReadModel> chartList = new ArrayList<>();
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
    protected String[] values = new String[]{
            "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00",
            "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "12:00", "23:00"
    };


    public static Intent makeIntent(Context context, int type, String meterSn) {
        Intent intent = new Intent(context, MeterDetailActivity.class);
        intent.putExtra(CommonParams.METER_TYPE, type);
        intent.putExtra(CommonParams.METER_SN, meterSn);
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
                DialogUtils.showUnBindingHints(MeterDetailActivity.this, model.getMachine_sn());
            }
        });
    }

    /**
     * 接口返回
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UnBindEvent event) {
        switch (event.getResult().getErr_code()) {
            case UNBIND_SUCCESS:
                EventBus.getDefault().post(new BindingStatusEvent(BindingStatusEvent.UNBINDING_SUCCESS));
                DialogUtils.showUnBindingSuccessHints(MeterDetailActivity.this);
                break;
            case METER_EMPTY:
                ToastUtil.showLong(getString(R.string.bind_empty));
                break;
            case BINDED:
                ToastUtil.showLong(getString(R.string.bind_already));
                break;
            case SERVER_ERR:
                ToastUtil.showLong(getString(R.string.bind_server_error));
                break;
        }


    }

    /**
     * 接口返回
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UnBindErrEvent event) {
        ToastUtil.showLong(event.getResult());
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

        mChart.setExtraOffsets(5f, 5f, 5f, 5f);
        mChart.getAxisRight().setEnabled(false);
        // 拖拽时能否高亮（十字瞄准触摸到的点），默认true
        mChart.setHighlightPerDragEnabled(true);
        mChart.setHighlightPerTapEnabled(false);
        //设置悬浮
        MeterChartMarkerView mv = new MeterChartMarkerView(this, R.layout.custom_marker_view);
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
        xAxis.setYOffset(8f);
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
//        setData(24, 10);
    }


    /**
     * 获取类型
     */
    private void initIntent() {
        Intent intent = getIntent();
        meterType = intent.getIntExtra(CommonParams.METER_TYPE, CommonParams.TYPE_WATER);
        meterSn = intent.getStringExtra(CommonParams.METER_SN);
    }

    /**
     * 水表数据
     */
    private void initData() {
        switch (meterType) {
            case CommonParams.TYPE_WATER:
                setHeaderTitle(getString(R.string.water_meter_detail_title));
                tvMeterCode.setText(getString(R.string.water_meter_code));
                tvMeterName.setText(getString(R.string.water_meter_reading));
                tvBalance.setText(getString(R.string.water_meter_balance));
                break;
            case CommonParams.TYPE_ELECT:
                setHeaderTitle(getString(R.string.elect_meter_detail_title));
                tvMeterCode.setText(getString(R.string.elect_meter_code));
                tvMeterName.setText(getString(R.string.elect_meter_reading));
                tvBalance.setText(getString(R.string.elect_meter_balance));
                break;
            case CommonParams.TYPE_GAS:
                setHeaderTitle(getString(R.string.gas_meter_detail_title));
                tvMeterCode.setText(getString(R.string.gas_meter_code));
                tvMeterName.setText(getString(R.string.gas_meter_reading));
                tvBalance.setText(getString(R.string.gas_meter_balance));
                break;
        }
        MeterManager.getInstance().getMeterDetail(meterSn, false);
    }

    /**
     * 图表数据
     */
    private void initChartData(int meterType, int meterId, String mTimeStamp) {
        MeterManager.getInstance().getRePayList(0, 24, meterType, meterId, mTimeStamp);
    }

    /**
     * 接口返回
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GetMeterInfoEvent event) {
        model = event.getModelInfo();
        if (model != null) {

            if (!TextUtils.isEmpty(model.getFinal_read_at())) {
                mTimeStamp = DateUtils.getDateByDateTime(model.getFinal_read_at());
            } else {
                tvNoData.setVisibility(View.VISIBLE);
                mChart.setVisibility(View.GONE);
            }
            //表ID，用于跳到充值
            meterId = model.getId();

            //读取表数据
            initChartData(meterType, model.getId(), mTimeStamp);

            tvSn.setText(model.getMachine_sn());

            switch (ComApplication.currentLanguage) {
                case Constants.LANGUAGE_CHINA:
                    tvAddress.setText(model.getLocation_zh() + " " + model.getPosition_zh());
                    break;
                case Constants.LANGUAGE_ENGLISH:
                    tvAddress.setText(model.getLocation_en() + " " + model.getPosition_en());
                    break;
                case Constants.LANGUAGE_KH:
                    tvAddress.setText(model.getLocation_kh() + " " + model.getPosition_kh());
                    break;
                default:
                    tvAddress.setText(model.getLocation_zh() + " " + model.getPosition_zh());
            }


            tvUnit.setText(String.format(mContext.getString(R.string.square_detail), model.getUnit() + ""));
            tvLastValue.setText(model.getThis_month_degree() + "");
            tvStartMonth.setText(model.getStart_month_degree() + "");
            tvLastDate.setText(model.getOld_read_at());
            tvCurrentValue.setText(model.getDegree() + "");
            tvCurrentDate.setText(model.getFinal_read_at());
            tvMoney.setText(model.getBalance() + "");

            if (model.getStatus() == 1) {
                //激活，可以点击充值
                tvCharge.setEnabled(true);
                tvCharge.setBackground(getResources().getDrawable(R.mipmap.anniu));
            } else {
                //禁用
                tvCharge.setEnabled(false);
                tvCharge.setBackground(getResources().getDrawable(R.mipmap.anniu_gray));
            }
        }
    }

    /**
     * 接口返回--缴费
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GetChartReadListEvent event) {
        if (event.getList() != null) {
            chartList = event.getList();
            setChartData(chartList);
        } else {
            tvNoData.setVisibility(View.VISIBLE);
            mChart.setVisibility(View.GONE);
        }
    }

    /**
     * 图标填充数据
     *
     * @param chartList
     */
    private void setChartData(List<MeterReadModel> chartList) {

        //自定义x轴显示
        MyXFormatter formatter = new MyXFormatter(values);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter(formatter);

        //X轴数据
        ArrayList<XAxisEntry> xVals = new ArrayList<>();
        //Y轴数据，用来算法最大最小值作为图表的边界
        ArrayList<Float> tempYvals = new ArrayList<>();


        //Y轴数据
        ArrayList<Entry> yVals = new ArrayList<>();

        float minY, maxY;


        for (int j = 0; j < 24; j++) {
            yVals.add(new Entry(j, 0f));
        }


        for (int i = 0; i < chartList.size(); i++) {
            int hour = DateUtils.getHour(chartList.get(i).getCreated_at());
            for (int k = 0; k < yVals.size(); k++) {
                if (k == hour) {
                    Entry entry = yVals.get(k);
                    entry.setY(chartList.get(i).getRead_degree());

                }
            }

            tempYvals.add(chartList.get(i).getRead_degree());
        }


        minY = getMin(tempYvals) - 2;
        maxY = getMax(tempYvals) + 2;


        LineDataSet lineSet;

        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            lineSet = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            lineSet.setValues(yVals);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {

            YAxis leftAxis = mChart.getAxisLeft();
            leftAxis.setAxisMaxValue(maxY);
            leftAxis.setAxisMinValue(minY);
            leftAxis.setSpaceTop(5f);

//            leftAxis.setValueFormatter(new IAxisValueFormatter() {
//                @Override
//                public String getFormattedValue(float value, AxisBase axis) {
//                    return "" + (int) value;//这句是重点!
//                }
//
//            });


            lineSet = new LineDataSet(yVals, "DataSet 1");
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
            lineSet.setCircleColor(getResources().getColor(R.color.white));
            //设置折线宽度
            lineSet.setLineWidth(1f);
            //设置折现点圆点半径
            lineSet.setCircleRadius(3f);
            //设置是否在数据点中间显示一个孔
            lineSet.setDrawCircleHole(false);
            lineSet.setValueTextSize(13f);
            //设置允许填充
            lineSet.setDrawFilled(true);
            lineSet.setFillAlpha(65);
            lineSet.setFillColor(ColorTemplate.getHoloBlue());
            lineSet.setFormLineWidth(1f);
            lineSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            lineSet.setFormSize(15f);
            lineSet.setHighlightEnabled(true);
            lineSet.setDrawHorizontalHighlightIndicator(false);
            lineSet.setDrawVerticalHighlightIndicator(true);

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(lineSet);

            // create a data object with the datasets
            LineData data = new LineData(dataSets);

            data.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return decimalFormat.format(value);
                }
            });

            //设置一页最大显示个数为6，超出部分就滑动
            float ratio = (float) 24 / (float) 6;
            //显示的时候是按照多大的比率缩放显示,1f表示不放大缩小
            mChart.zoom(ratio, 1f, 0, 0);
            //设置从X轴出来的动画时间
//            mChart.animateX(1500);
            //设置XY轴动画
            mChart.animateXY(1500, 1500, Easing.EasingOption.EaseInSine, Easing.EasingOption.EaseInSine);
            // set data


            mChart.setData(data);
            mChart.invalidate();
        }


    }


    /**
     * 获取最大值
     *
     * @param arr
     * @return
     */
    private float getMax(List<Float> arr) {
        float max = Float.MIN_VALUE;

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) > max) {
                max = arr.get(i);
            }
        }

        return max;
    }

    /**
     * 获取最小值
     *
     * @param arr
     * @return
     */
    private float getMin(List<Float> arr) {
        float min = Float.MAX_VALUE;

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) < min) {
                min = arr.get(i);
            }
        }

        return min;
    }


    @OnClick({R.id.tvGotoPayment, R.id.tvGotoMonthBill, R.id.tvCharge})
    public void onClick(View view) {
        switch (view.getId()) {
            // 缴费明细
            case R.id.tvGotoPayment:
                startActivity(PerStorageSaveListActivity.makeIntent(mContext, model.getId(), meterType, CommonParams.PAGE_TYPE_READ));
                break;
//            // 预存明细
//            case R.id.tvGotoPerStorage:
//                startActivity(PerStorageSaveListActivity.makeIntent(mContext, model.getId(), meterType, CommonParams.PAGE_TYPE_RECHARGE));
//                break;
            // 月度明细
            case R.id.tvGotoMonthBill:
                startActivity(PerStorageSaveListActivity.makeIntent(mContext, model.getId(), meterType, CommonParams.PAGE_TYPE_TRANSACTION));
                break;
            case R.id.tvCharge:
                startActivityForResult(PayActionActivity.makeIntent(mContext, meterId, false, model.getMachine_sn(),model), CommonParams.PAY_RESULT);
                break;

        }
    }


    /**
     * 接口返回--查询订单
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GetOrderInfoEvent event) {
        if (event.getOrderInfo() != null) {
            int status = event.getOrderInfo().getStatus();
            switch (status) {
                case CommonParams.PAY_RESULT_PENDING:
                    //todo 1秒后请求多一次结果，7次超时
                    requestIndex++;
                    if (requestIndex > MAX_REQUEST_COUNT) {
                        //弹出请求超时
                    } else {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                String orderNo = PreferencesUtils.getString("orderNo");
                                MeterManager.getInstance().getOrderInfo(orderNo);
                            }
                        }, 1000);

                    }

                    break;
                case CommonParams.PAY_RESULT_SUCCESS:
                    //todo 支付成功，刷新数据
                    PreferencesUtils.putString("orderNo", "");
                    MeterManager.getInstance().getMeterDetail(meterSn, false);
                    break;
                case CommonParams.PAY_RESULT_CANCEL:
                    //todo 支付取消
                    break;

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == CommonParams.PAY_RESULT) {
            String orderNo = PreferencesUtils.getString("orderNo");
            System.out.println("===@@@@@===orderNo======" + orderNo);
            MeterManager.getInstance().getOrderInfo(orderNo);
            //请求一次
            requestIndex = 1;
        }
    }
}
