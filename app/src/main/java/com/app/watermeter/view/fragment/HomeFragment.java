package com.app.watermeter.view.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.eventBus.GetMeterTypeEvent;
import com.app.watermeter.eventBus.LoginEvent;
import com.app.watermeter.manager.MeterManager;
import com.app.watermeter.model.MeterInfoModel;
import com.app.watermeter.model.MeterTypeModel;
import com.app.watermeter.view.activity.MeterListActivity;
import com.app.watermeter.view.adapter.ElectricityPagerAdapter;
import com.app.watermeter.view.adapter.GasPagerAdapter;
import com.app.watermeter.view.adapter.WaterPagerAdapter;
import com.app.watermeter.view.base.BaseFragment;
import com.app.watermeter.view.views.AlphaTransformer;
import com.app.watermeter.view.views.ScaleTransformer;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.tvMoreWater)
    TextView tvMoreWater;
    @BindView(R.id.tvMoreElectricity)
    TextView tvMoreElectricity;
    @BindView(R.id.tvMoreGas)
    TextView tvMoreGas;

    @BindView(R.id.vpWater)
    ViewPager vpWater;
    @BindView(R.id.vpElectricity)
    ViewPager vpElectricity;
    @BindView(R.id.vpGas)
    ViewPager vpGas;

    WaterPagerAdapter waterAdapter;
    ElectricityPagerAdapter electricityAdapter;
    GasPagerAdapter gasAdapter;

    List<MeterTypeModel> typeList = new ArrayList<>();

    @Override
    protected void initView() {

    }


    @Override
    protected void initData() {
        MeterManager.getInstance().getMeterType();


    }


    /**
     * 水表数据
     */
    private void initWaterData() {
        vpWater.setPageMargin(5);
        vpWater.setOffscreenPageLimit(3);
        List<MeterInfoModel> list = new ArrayList<>();
        list.add(new MeterInfoModel());
        list.add(new MeterInfoModel());
        list.add(new MeterInfoModel());
        list.add(new MeterInfoModel());
        list.add(new MeterInfoModel());
        list.add(new MeterInfoModel());

        waterAdapter = new WaterPagerAdapter(getContext(), list);
        vpWater.setPageTransformer(false, new ScaleTransformer());
//        vpWater.setPageTransformer(false, new AlphaTransformer());
        vpWater.setAdapter(waterAdapter);
        vpWater.setCurrentItem(1);
    }

    /**
     * 接口返回
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetDataEvent(GetMeterTypeEvent event) {
        typeList = event.getTypeList();
        if (typeList.size() > 0) {
            try {
//                for (MeterTypeModel model : typeList) {
                for (int i = 0; i < typeList.size(); i++) {
                    MeterTypeModel model = typeList.get(i);
                    switch (model.getId()) {
                        case MeterTypeModel.METER_WATER:
                            initWaterData();
                            break;
                        case MeterTypeModel.METER_ELECT:
                            initElectricityData();
                            break;
                        case MeterTypeModel.METER_GAS:
                            initGasData();
                            break;
                        default:
                            break;
                    }
                    System.out.println("=========" + model);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 电表
     */
    private void initElectricityData() {
        vpElectricity.setPageMargin(5);
        vpElectricity.setOffscreenPageLimit(3);
        List<MeterInfoModel> list = new ArrayList<>();
        list.add(new MeterInfoModel());
        list.add(new MeterInfoModel());
        list.add(new MeterInfoModel());
        list.add(new MeterInfoModel());
        list.add(new MeterInfoModel());
        list.add(new MeterInfoModel());

        electricityAdapter = new ElectricityPagerAdapter(getContext(), list);
        vpElectricity.setPageTransformer(false, new ScaleTransformer());
//        vpWater.setPageTransformer(false, new AlphaTransformer());
        vpElectricity.setAdapter(electricityAdapter);
        vpElectricity.setCurrentItem(1);
    }

    /**
     * 燃气表
     */
    private void initGasData() {
        vpGas.setPageMargin(5);
        vpGas.setOffscreenPageLimit(3);
        List<MeterInfoModel> list = new ArrayList<>();
        list.add(new MeterInfoModel());
        list.add(new MeterInfoModel());
        list.add(new MeterInfoModel());
        list.add(new MeterInfoModel());
        list.add(new MeterInfoModel());
        list.add(new MeterInfoModel());

        gasAdapter = new GasPagerAdapter(getContext(), list);
        vpGas.setPageTransformer(false, new ScaleTransformer());
//        vpWater.setPageTransformer(false, new AlphaTransformer());
        vpGas.setAdapter(gasAdapter);
        vpGas.setCurrentItem(1);
    }


    @Override
    protected void reLoadData() {

    }

    @Override
    protected int setFrgContainView() {
        return R.layout.fragment_home;
    }


    @OnClick({R.id.tvMoreWater, R.id.tvMoreElectricity, R.id.tvMoreGas})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvMoreWater:
                startActivity(MeterListActivity.makeIntent(getContext(), CommonParams.TYPE_WATER));
                break;
            case R.id.tvMoreElectricity:
                startActivity(MeterListActivity.makeIntent(getContext(), CommonParams.TYPE_ELECT));
                break;
            case R.id.tvMoreGas:
                startActivity(MeterListActivity.makeIntent(getContext(), CommonParams.TYPE_GAS));
                break;

        }
    }
}
