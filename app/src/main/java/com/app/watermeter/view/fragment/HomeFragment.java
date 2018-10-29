package com.app.watermeter.view.fragment;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.eventBus.BindingStatusEvent;
import com.app.watermeter.eventBus.GetHomeMeterListEvent;
import com.app.watermeter.eventBus.GetMeterListEvent;
import com.app.watermeter.eventBus.GetMeterTypeEvent;
import com.app.watermeter.eventBus.LoginEvent;
import com.app.watermeter.manager.MeterManager;
import com.app.watermeter.model.MeterInfoModel;
import com.app.watermeter.model.MeterTypeModel;
import com.app.watermeter.utils.DateUtils;
import com.app.watermeter.utils.PreferencesUtils;
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
    @BindView(R.id.tvWaterNoData)
    TextView tvWaterNoData;
    @BindView(R.id.tvElectNoData)
    TextView tvElectNoData;
    @BindView(R.id.tvGasNoData)
    TextView tvGasNoData;

    int meterType;

    WaterPagerAdapter waterAdapter;
    ElectricityPagerAdapter electricityAdapter;
    GasPagerAdapter gasAdapter;

    List<MeterTypeModel> typeList = new ArrayList<>();
    List<MeterInfoModel> meterList = new ArrayList<>();
    List<MeterInfoModel> electList = new ArrayList<>();
    List<MeterInfoModel> gasList = new ArrayList<>();

    @Override
    protected void reLoadData() {

    }

    @Override
    protected int setFrgContainView() {
        return R.layout.fragment_home;
    }


    @Override
    protected void initView() {

    }


    @Override
    protected void initData() {
        MeterManager.getInstance().getMeterType();
    }

    /**
     * 接口返回
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GetMeterTypeEvent event) {
        typeList = event.getTypeList();
        if (typeList.size() > 0) {
            try {
                for (MeterTypeModel model : typeList) {
                    MeterManager.getInstance().getMeterList(model.getId(), 0, 0, true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 接口返回--首页数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GetHomeMeterListEvent event) {
        List<MeterInfoModel> list = event.getList();
        Log.d("admin", "onEvent: list=" + list.size());
        initListData(list, event.getMeterType());
    }

    /**
     * 接口返回--绑定解绑
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BindingStatusEvent event) {
        MeterManager.getInstance().getMeterType();
    }


    /**
     * 水表数据
     */
    private void initListData(List<MeterInfoModel> list, int type) {

        switch (type) {
            case CommonParams.TYPE_WATER:
                meterList = list;
                if (list.size() == 0) {
                    tvWaterNoData.setVisibility(View.VISIBLE);
                } else {
                    tvWaterNoData.setVisibility(View.GONE);
                }
                vpWater.setPageMargin(5);
                vpWater.setOffscreenPageLimit(3);
                waterAdapter = new WaterPagerAdapter(getContext(), meterList);
                vpWater.setPageTransformer(false, new ScaleTransformer());
                //vpWater.setPageTransformer(false, new AlphaTransformer());
                vpWater.setAdapter(waterAdapter);
                vpWater.setCurrentItem(1);
                break;
            case CommonParams.TYPE_ELECT:
                electList = list;
                if (list.size() == 0) {
                    tvElectNoData.setVisibility(View.VISIBLE);
                } else {
                    tvElectNoData.setVisibility(View.GONE);
                }
                vpElectricity.setPageMargin(5);
                vpElectricity.setOffscreenPageLimit(3);

                electricityAdapter = new ElectricityPagerAdapter(getContext(), electList);
                vpElectricity.setPageTransformer(false, new ScaleTransformer());
                //vpElectricity.setPageTransformer(false, new AlphaTransformer());
                vpElectricity.setAdapter(electricityAdapter);
                vpElectricity.setCurrentItem(1);
                break;
            case CommonParams.TYPE_GAS:
                gasList = list;
                if (list.size() == 0) {
                    tvGasNoData.setVisibility(View.VISIBLE);
                } else {
                    tvGasNoData.setVisibility(View.GONE);
                }
                vpGas.setPageMargin(5);
                vpGas.setOffscreenPageLimit(3);
                gasAdapter = new GasPagerAdapter(getContext(), gasList);
                vpGas.setPageTransformer(false, new ScaleTransformer());
                //vpGas.setPageTransformer(false, new AlphaTransformer());
                vpGas.setAdapter(gasAdapter);
                vpGas.setCurrentItem(1);
                break;
            default:
                break;
        }


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
