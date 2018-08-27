package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.app.watermeter.R;
import com.app.watermeter.view.adapter.PerStorageAdapter;
import com.app.watermeter.view.base.BaseActivity;
import com.app.watermeter.view.fragment.PerStorageFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author admin
 */
public class PreStorageActivity extends BaseActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private PerStorageAdapter adapter;
    private String[] mTitles = new String[]{"水表", "电表", "燃气表"};
    private ArrayList<PerStorageFragment> mViewPagerFragments = new ArrayList<>();

    private Context mContext;



    @Override
    protected int getCenterView() {
        return R.layout.activity_pre_storage;
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, PreStorageActivity.class);
    }


    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.mine_per_storage_list));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = PreStorageActivity.this;
        initView();
        initData();
    }

    private void initData() {
        for (int i = 0; i < mTitles.length; i++) {
            mViewPagerFragments.add(PerStorageFragment.newInstance(mTitles[i]));
        }
        adapter = new PerStorageAdapter(getSupportFragmentManager());
        adapter.setTitles(mTitles);
        adapter.setFragments(mViewPagerFragments);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //tab被选的时候回调
                viewPager.setCurrentItem(tab.getPosition(),true);
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
