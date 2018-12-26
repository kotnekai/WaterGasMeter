package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.model.MeterTypeModel;
import com.app.watermeter.utils.PreferencesUtils;
import com.app.watermeter.view.adapter.PerStorageFragmentAdapter;
import com.app.watermeter.view.base.BaseActivity;
import com.app.watermeter.view.base.BaseFragment;
import com.app.watermeter.view.fragment.ElectReadAndReChargeFragment;
import com.app.watermeter.view.fragment.GasReadAndReChargeFragment;
import com.app.watermeter.view.fragment.WaterReadAndReChargeFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author admin
 */
public class PreStorageSaveActivity extends BaseActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.ivNothing)
    ImageView ivNothing;


    private PerStorageFragmentAdapter adapter;
    private List<String> mTitles = new ArrayList<>();
    private ArrayList<BaseFragment> mViewPagerFragments = new ArrayList<>();

    private Context mContext;
    int pageType;


    @Override
    protected int getCenterView() {
        return R.layout.activity_per_storage_save;
    }

    public static Intent makeIntent(Context context, int pageType) {
        Intent intent = new Intent(context, PreStorageSaveActivity.class);
        intent.putExtra(CommonParams.PAGE_TYPE, pageType);
        return intent;
    }


    @Override
    protected void initHeader() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = PreStorageSaveActivity.this;
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        pageType = intent.getIntExtra("pageType", 1);
        if (pageType == CommonParams.PAGE_TYPE_RECHARGE) {
            setHeaderTitle(getString(R.string.mine_per_storage_list));
        } else if (pageType == CommonParams.PAGE_TYPE_READ) {
            setHeaderTitle(getString(R.string.mine_pay_list));
        } else {
            setHeaderTitle(getString(R.string.mine_transaction_list));
        }

        String typeJsonStr = PreferencesUtils.getString(CommonParams.METTER_TYPE_JSON);

        List<MeterTypeModel> list = new Gson().fromJson(typeJsonStr, new TypeToken<List<MeterTypeModel>>() {
        }.getType());

        if (list.size() > 0) {
            for (MeterTypeModel model : list) {
                switch (model.getId()) {
                    case CommonParams.TYPE_WATER:
                        mViewPagerFragments.add(WaterReadAndReChargeFragment.newInstance(model.getId(), model.getName_zh(), pageType));
                        mTitles.add(model.getName_zh());
                        break;
                    case CommonParams.TYPE_ELECT:
                        mViewPagerFragments.add(ElectReadAndReChargeFragment.newInstance(model.getId(), model.getName_zh(), pageType));
                        mTitles.add(model.getName_zh());
                        break;
                    case CommonParams.TYPE_GAS:
                        mViewPagerFragments.add(GasReadAndReChargeFragment.newInstance(model.getId(), model.getName_zh(), pageType));
                        mTitles.add(model.getName_zh());
                        break;
                }
            }
        } else {

        }
        adapter = new PerStorageFragmentAdapter(getSupportFragmentManager(), pageType, mViewPagerFragments);
        adapter.setTitles(mTitles);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //tab被选的时候回调
                viewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //tab未被选择的时候回调
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //tab重新选择的时候回调
            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }
}
