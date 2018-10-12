package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.common.ApplicationHolder;
import com.app.watermeter.common.ChangeLanguageHelper;
import com.app.watermeter.eventBus.LanguageChangedEvent;
import com.app.watermeter.utils.LanguageUtils;
import com.app.watermeter.view.adapter.FragmentAdapter;
import com.app.watermeter.view.base.BaseActivity;
import com.app.watermeter.view.fragment.HomeFragment;
import com.app.watermeter.view.fragment.BingingFragment;
import com.app.watermeter.view.fragment.MineFragment;
import com.app.watermeter.view.views.NoScrollViewPager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author admin
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;
    @BindView(R.id.tvHomeTab)
    TextView tvHomeTab;
    @BindView(R.id.tvMeterTab)
    TextView tvMeterTab;
    @BindView(R.id.tvMineTab)
    TextView tvMineTab;
    @BindView(R.id.tvFourthTab)
    TextView tvFourthTab;
    private List<Fragment> fragmentList;
    private FragmentAdapter adapter;

    Context mContext;

    @Override
    protected int getCenterView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.main_home));
        setLeftIconVisibility(View.GONE);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LanguageChangedEvent event) {
        if (mContext instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) mContext;
            mainActivity.finish();

            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChangeLanguageHelper.init(this);
        mContext = MainActivity.this;
        initData();
    }

    public static Intent makeIntent(Context context){
        return new Intent(context,MainActivity.class);
    }
    private void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new BingingFragment());
        fragmentList.add(new MineFragment());
        tvFourthTab.setVisibility(View.GONE);
        adapter = new FragmentAdapter(fragmentList, getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);
        tvHomeTab.setSelected(true);

    }

    @OnClick({R.id.tvHomeTab, R.id.tvMeterTab, R.id.tvMineTab, R.id.tvFourthTab})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvHomeTab:
                viewPager.setCurrentItem(0);
                tvHomeTab.setSelected(true);
                tvMeterTab.setSelected(false);
                tvMineTab.setSelected(false);
                setHeaderTitle(getString(R.string.main_home));

                break;
            case R.id.tvMeterTab:
                viewPager.setCurrentItem(1);
                tvHomeTab.setSelected(false);
                tvMeterTab.setSelected(true);
                tvMineTab.setSelected(false);
                setHeaderTitle(getString(R.string.binding));

                break;
            case R.id.tvMineTab:
                viewPager.setCurrentItem(2);
                tvHomeTab.setSelected(false);
                tvMeterTab.setSelected(false);
                tvMineTab.setSelected(true);
                setHeaderTitle(getString(R.string.mine));

                break;
            case R.id.tvFourthTab:
                break;
        }

    }
}
