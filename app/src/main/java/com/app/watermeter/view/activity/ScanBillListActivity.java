package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.eventBus.GetDetailReChargeListEvent;
import com.app.watermeter.eventBus.GetReChargeListFromScanEvent;
import com.app.watermeter.manager.MeterManager;
import com.app.watermeter.model.MeterReChargeModel;
import com.app.watermeter.view.adapter.ScanBillAdapter;
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
public class ScanBillListActivity extends BaseActivity {


    //当前类计数量
    private int currentPageSize = 0;
    //每次请求数量
    private int dataSize = 10;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ivNothing)
    ImageView ivNothing;


    Context mContext;
    ScanBillAdapter scanBillAdapter;
    LinearLayoutManager mLayoutManager;
    private List<MeterReChargeModel> reChargeList = new ArrayList<>();


    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, ScanBillListActivity.class);
        return intent;
    }

    @Override
    protected int getCenterView() {
        return R.layout.activity_scan_bill_list;
    }

    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.mine_scan_bill_info));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = ScanBillListActivity.this;
        initData();
        addListData();
    }


    /**
     * 加载数据
     */
    private void initData() {

        scanBillAdapter = new ScanBillAdapter(mContext, reChargeList);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mLayoutManager.setStackFromEnd(true);
        //mLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(scanBillAdapter);
        recyclerView.scrollToPosition(0);


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                reChargeList.clear();
                currentPageSize = 0;
                addListData();
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                currentPageSize += dataSize;
                addListData();
                refreshLayout.finishLoadMore();
            }
        });

    }

    private void addListData() {
        MeterManager.getInstance().getReChargeListFromScan(currentPageSize, dataSize);
    }

    /**
     * 接口返回
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GetReChargeListFromScanEvent event) {
        currentPageSize += event.getList().size();
        if (reChargeList.size() > 0) {
            reChargeList.addAll(event.getList());
        } else {
            reChargeList = event.getList();
        }

        if (reChargeList.size() > 0) {
            scanBillAdapter.setData(reChargeList);
            scanBillAdapter.notifyDataSetChanged();
        } else {
            ivNothing.setVisibility(View.VISIBLE);
            refreshLayout.setVisibility(View.GONE);
        }
    }

}
