package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.model.MeterInfoModel;
import com.app.watermeter.view.adapter.MeterRecyclerAdapter;
import com.app.watermeter.view.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author admin
 */
public class MeterListActivity extends BaseActivity {

    public static final String METER_TYPE = "meterType";

    //默认值
    private int typeValue;


    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    Context mContext;
    MeterRecyclerAdapter adapter;
    LinearLayoutManager mLayoutManager;

    List<MeterInfoModel> meterLists = new ArrayList<>();


    public static Intent makeIntent(Context context, int type) {
        Intent intent = new Intent(context, MeterListActivity.class);
        intent.putExtra(METER_TYPE, type);
        return intent;
    }

    @Override
    protected int getCenterView() {
        return R.layout.activity_meter_list;
    }

    @Override
    protected void initHeader() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = MeterListActivity.this;
        initIntent();
        initData();
        addMeterData();
    }

    /**
     * 获取类型
     */
    private void initIntent() {
        Intent intent = getIntent();
        typeValue = intent.getIntExtra(METER_TYPE, CommonParams.TYPE_WATER);
    }

    /**
     * 加载数据
     */
    private void initData() {
        switch (typeValue) {
            case CommonParams.TYPE_WATER:
                setHeaderTitle(getString(R.string.water_meter));
                break;
            case CommonParams.TYPE_ELECT:
                setHeaderTitle(getString(R.string.electricity_meter));
                break;
            case CommonParams.TYPE_GAS:
                setHeaderTitle(getString(R.string.gas_meter));
                break;
        }


        adapter = new MeterRecyclerAdapter(mContext, meterLists,typeValue);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mLayoutManager.setStackFromEnd(true);
        //mLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                meterLists.clear();
                addMeterData();
                adapter.notifyDataSetChanged();
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                addMore();
                adapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore();
            }
        });

    }

    private void addMeterData() {
        for (int i = 0; i < 10; i++) {
            MeterInfoModel model = new MeterInfoModel();
            meterLists.add(model);
        }
    }

    private void addMore() {
        for (int i = 0; i < 10; i++) {
            MeterInfoModel model = new MeterInfoModel();
            meterLists.add(model);
        }
    }
}
