package com.app.watermeter.view.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.view.adapter.WaterPagerAdapter;
import com.app.watermeter.view.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

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


    @Override
    protected void initView() {

    }


    @Override
    protected void initData() {
        initWaterData();
    }

   void initWaterData()
    {
        vpWater.setPageMargin(60);
        vpWater.setOffscreenPageLimit(3);
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.ic1);
        list.add(R.mipmap.ic2);
        list.add(R.mipmap.ic3);
        list.add(R.mipmap.ic4);
        list.add(R.mipmap.ic5);
        waterAdapter = new WaterPagerAdapter(getContext(), list);
        vpWater.setAdapter(waterAdapter);
        vpWater.setCurrentItem(1);

    }

    @Override
    protected void reLoadData() {

    }

    @Override
    protected int setFrgContainView() {
        return R.layout.fragment_home;
    }


    @Override
    @OnClick({R.id.tvMoreWater, R.id.tvMoreElectricity, R.id.tvMoreGas,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvMoreWater:
                break;
            case R.id.tvMoreElectricity:
                break;
            case R.id.tvMoreGas:
                break;

        }
    }
}
