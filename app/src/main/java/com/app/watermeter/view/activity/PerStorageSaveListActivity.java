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
import com.app.watermeter.model.PerSaveModel;
import com.app.watermeter.model.PerStorageModel;
import com.app.watermeter.view.adapter.PerSaveAdapter;
import com.app.watermeter.view.adapter.PerStorageAdapter;
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
public class PerStorageSaveListActivity extends BaseActivity {

    public static final String PAGE_TYPE = "pageType";

    //默认值
    private int fromPage;


    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    Context mContext;
    private PerStorageAdapter perStorageAdapter;
    private PerSaveAdapter perSaveAdapter;
    private LinearLayoutManager mLayoutManager;

    private List<PerStorageModel> perStorageList = new ArrayList<>();
    private List<PerSaveModel> perSaveList = new ArrayList<>();


    public static Intent makeIntent(Context context, int type) {
        Intent intent = new Intent(context, PerStorageSaveListActivity.class);
        intent.putExtra(PAGE_TYPE, type);
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
        fromPage = intent.getIntExtra(PAGE_TYPE, CommonParams.TYPE_WATER);
    }

    /**
     * 加载数据
     */
    private void initData() {
        switch (fromPage) {
            case CommonParams.PAGE_TYPE_STORAGE:
                setHeaderTitle(getString(R.string.mine_per_storage_list));
                break;
            case CommonParams.PAGE_TYPE_SAVE:
                setHeaderTitle(getString(R.string.mine_pay_list));
                break;
        }
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);


        if (fromPage == CommonParams.PAGE_TYPE_STORAGE) {
            perStorageAdapter = new PerStorageAdapter(mContext, perStorageList);
            recyclerView.setAdapter(perStorageAdapter);
        } else {
            perSaveAdapter = new PerSaveAdapter(mContext, perSaveList);
            recyclerView.setAdapter(perSaveAdapter);
        }
        recyclerView.scrollToPosition(0);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (fromPage == CommonParams.PAGE_TYPE_STORAGE) {
                    perStorageList.clear();
                    addListData(fromPage);
                    perStorageAdapter.notifyDataSetChanged();
                } else {
                    perSaveList.clear();
                    addListData(fromPage);
                    perSaveAdapter.notifyDataSetChanged();
                }

                refreshLayout.finishRefresh();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {


                if (fromPage == CommonParams.PAGE_TYPE_STORAGE) {
                    addListData(fromPage);
                    perStorageAdapter.notifyDataSetChanged();
                } else {
                    addListData(fromPage);
                    perSaveAdapter.notifyDataSetChanged();
                }
                refreshLayout.finishLoadMore();
            }
        });
    }


    private void addListData(int type) {
        if (type == CommonParams.PAGE_TYPE_STORAGE) {
            for (int i = 0; i < 10; i++) {
                perStorageList.add(new PerStorageModel("水表编码：21" + i, "2019/02/23", 23 + i));
            }
        } else {
            for (int i = 0; i < 10; i++) {
                perSaveList.add(new PerSaveModel("水表编码：21" + i, "2019/02/23", 23 + i));
            }
        }
    }
}
