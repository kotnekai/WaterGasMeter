package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.eventBus.GetDetailReChargeListEvent;
import com.app.watermeter.eventBus.GetDetailReadListEvent;
import com.app.watermeter.eventBus.GetReChargeListEvent;
import com.app.watermeter.eventBus.GetReadListEvent;
import com.app.watermeter.manager.MeterManager;
import com.app.watermeter.model.MeterReChargeModel;
import com.app.watermeter.model.MeterReadModel;
import com.app.watermeter.view.adapter.ReadAdapter;
import com.app.watermeter.view.adapter.ReChargeAdapter;
import com.app.watermeter.view.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author admin
 */
public class PerStorageSaveListActivity extends BaseActivity {

    //默认值
    private int fromPage;
    private int meterType;
    private int meterId;
    //当前类计数量
    private int currentPageSize = 0;
    //每次请求数量
    private int dataSize = 10;

    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    Context mContext;
    private ReChargeAdapter reChargeAdapter;
    private ReadAdapter readAdapter;
    private LinearLayoutManager mLayoutManager;

    private List<MeterReChargeModel> reChargeList = new ArrayList<>();
    private List<MeterReadModel> perSaveList = new ArrayList<>();


    public static Intent makeIntent(Context context, int meterId, int meterType, int pageType) {
        Intent intent = new Intent(context, PerStorageSaveListActivity.class);
        intent.putExtra(CommonParams.METER_TYPE, meterType);
        intent.putExtra(CommonParams.METER_ID, meterId);
        intent.putExtra(CommonParams.PAGE_TYPE, pageType);
        return intent;
    }

    @Override
    protected int getCenterView() {
        return R.layout.fragment_per_storage;
    }

    @Override
    protected void initHeader() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = PerStorageSaveListActivity.this;
        initIntent();
        initData();
        addListData(fromPage);
    }

    /**
     * 获取类型
     */
    private void initIntent() {
        Intent intent = getIntent();
        fromPage = intent.getIntExtra(CommonParams.PAGE_TYPE, 1);
        meterType = intent.getIntExtra(CommonParams.METER_TYPE, CommonParams.TYPE_WATER);
        meterId = intent.getIntExtra(CommonParams.METER_ID,0);

    }

    /**
     * 加载数据
     */
    private void initData() {
        switch (fromPage) {
            case CommonParams.PAGE_TYPE_RECHARGE:
                setHeaderTitle(getString(R.string.mine_per_storage_list));
                break;
            case CommonParams.PAGE_TYPE_READ:
                setHeaderTitle(getString(R.string.mine_pay_list));
                break;
        }
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);


        if (fromPage == CommonParams.PAGE_TYPE_RECHARGE) {
            reChargeAdapter = new ReChargeAdapter(mContext, reChargeList);
            recyclerView.setAdapter(reChargeAdapter);
        } else {
            readAdapter = new ReadAdapter(mContext, perSaveList);
            recyclerView.setAdapter(readAdapter);
        }
        recyclerView.scrollToPosition(0);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (fromPage == CommonParams.PAGE_TYPE_RECHARGE) {
                    reChargeList.clear();
                    currentPageSize = 0;
                    addListData(fromPage);
                } else {
                    perSaveList.clear();
                    currentPageSize = 0;
                    addListData(fromPage);
                }
                refreshLayout.finishRefresh();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {


                if (fromPage == CommonParams.PAGE_TYPE_RECHARGE) {
                    currentPageSize += dataSize;
                    addListData(fromPage);
                } else {
                    currentPageSize += dataSize;
                    addListData(fromPage);
                }
                refreshLayout.finishLoadMore();
            }
        });
    }


    private void addListData(int type) {
        if (type == CommonParams.PAGE_TYPE_RECHARGE) {
            //预存明细
            MeterManager.getInstance().getReChargeList(currentPageSize, dataSize, meterType, meterId);
        } else {
            //缴费明细
            MeterManager.getInstance().getRePayList(currentPageSize, dataSize, meterType, meterId);
        }
    }

    /**
     * 接口返回--预存
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GetDetailReChargeListEvent event) {
        currentPageSize += event.getList().size();
        if (reChargeList.size() > 0) {
            reChargeList.addAll(event.getList());
        } else {
            reChargeList = event.getList();
        }
        reChargeAdapter.setData(reChargeList);
        reChargeAdapter.notifyDataSetChanged();

    }

    /**
     * 接口返回--缴费
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GetDetailReadListEvent event) {
        currentPageSize += event.getList().size();
        if (perSaveList.size() > 0) {
            perSaveList.addAll(event.getList());
        } else {
            perSaveList = event.getList();
        }
        readAdapter.setData(perSaveList);
        readAdapter.notifyDataSetChanged();
    }

}
