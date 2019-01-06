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
import com.app.watermeter.eventBus.GetDetailReadListEvent;
import com.app.watermeter.eventBus.GetDetailTransactionListEvent;
import com.app.watermeter.manager.MeterManager;
import com.app.watermeter.model.MeterReChargeModel;
import com.app.watermeter.model.MeterReadModel;
import com.app.watermeter.model.MeterTransactionModel;
import com.app.watermeter.view.adapter.ReChargeAdapter;
import com.app.watermeter.view.adapter.ReadAdapter;
import com.app.watermeter.view.adapter.TransactionAdapter;
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

    @BindView(R.id.ivNothing)
    ImageView ivNothing;
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
    private TransactionAdapter transactionAdapter;

    private LinearLayoutManager mLayoutManager;

    private List<MeterReChargeModel> reChargeList = new ArrayList<>();
    private List<MeterReadModel> perSaveList = new ArrayList<>();
    private List<MeterTransactionModel> transactionList = new ArrayList<>();


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
        meterId = intent.getIntExtra(CommonParams.METER_ID, 0);

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
            case CommonParams.PAGE_TYPE_TRANSACTION:
                setHeaderTitle(getString(R.string.monthly_list));
                break;
        }
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);


        if (fromPage == CommonParams.PAGE_TYPE_RECHARGE) {
            reChargeAdapter = new ReChargeAdapter(mContext, reChargeList, meterType);
            recyclerView.setAdapter(reChargeAdapter);
        } else  if (fromPage == CommonParams.PAGE_TYPE_READ) {
            readAdapter = new ReadAdapter(mContext, perSaveList, meterType);
            recyclerView.setAdapter(readAdapter);
        }
        else
        {
            transactionAdapter = new TransactionAdapter(mContext, transactionList, meterType);
            recyclerView.setAdapter(transactionAdapter);
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
                    addListData(fromPage);
                } else {
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
        } else if (type == CommonParams.PAGE_TYPE_RECHARGE) {
            //缴费明细
            MeterManager.getInstance().getRePayList(currentPageSize, dataSize, meterType, meterId, null);
        }
        else
        {
            MeterManager.getInstance().getTransactionList(currentPageSize, dataSize, meterType, meterId);

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

        if (reChargeList.size() > 0) {
            reChargeAdapter.setData(reChargeList);
            reChargeAdapter.notifyDataSetChanged();
        } else {
            smartRefreshLayout.setVisibility(View.GONE);
            ivNothing.setVisibility(View.VISIBLE);
        }

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
        if (perSaveList.size() > 0) {
            readAdapter.setData(perSaveList);
            readAdapter.notifyDataSetChanged();
        } else {
            smartRefreshLayout.setVisibility(View.GONE);
            ivNothing.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 接口返回--月度
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GetDetailTransactionListEvent event) {
        currentPageSize += event.getList().size();
        if (transactionList.size() > 0) {
            transactionList.addAll(event.getList());
        } else {
            transactionList = event.getList();
        }
        if (transactionList.size() > 0) {
            transactionAdapter.setData(transactionList);
            transactionAdapter.notifyDataSetChanged();
        } else {
            smartRefreshLayout.setVisibility(View.GONE);
            ivNothing.setVisibility(View.VISIBLE);
        }
    }


}
