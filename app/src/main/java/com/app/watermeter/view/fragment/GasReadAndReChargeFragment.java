package com.app.watermeter.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.eventBus.GetGasReChargeListEvent;
import com.app.watermeter.eventBus.GetGasReadListEvent;
import com.app.watermeter.manager.MeterManager;
import com.app.watermeter.model.MeterReChargeModel;
import com.app.watermeter.model.MeterReadModel;
import com.app.watermeter.view.adapter.ReChargeAdapter;
import com.app.watermeter.view.adapter.ReadAdapter;
import com.app.watermeter.view.base.BaseFragment;
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
 * 缴费明细,充值
 *
 * @author admin
 */
public class GasReadAndReChargeFragment extends BaseFragment {

    private static final String KEY_TITLE = "meter_title";
    private static final String KEY_PAGE = "page";
    private static final String KEY_METER_TYPE = "meter_type";


    private String mTitle;
    private int fromPage;
    private int meterType;
    //当前类计数量
    private int currentPageSize = 0;
    //每次请求数量
    private int dataSize = 10;
    //预存明细
    private List<MeterReChargeModel> reChargeList = new ArrayList<>();
    //缴费明细
    private List<MeterReadModel> perReadList = new ArrayList<>();

    private ReChargeAdapter reChargeAdapter;
    private ReadAdapter readAdapter;
    private LinearLayoutManager mLayoutManager;


    public GasReadAndReChargeFragment() {
    }


    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    public static GasReadAndReChargeFragment newInstance(int meterType, String meterTitle, int fromPageType) {
        Bundle args = new Bundle();
        args.putString(KEY_TITLE, meterTitle);
        args.putInt(KEY_METER_TYPE, meterType);
        args.putInt(KEY_PAGE, fromPageType);
        GasReadAndReChargeFragment fragment = new GasReadAndReChargeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTitle = bundle.getString(KEY_TITLE);
            meterType = bundle.getInt(KEY_METER_TYPE);
            fromPage = bundle.getInt(KEY_PAGE, 1);
        }
    }

    @Override
    protected void initView() {
    }


    @Override
    protected void initData() {

        initListData(meterType, fromPage);

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);

        //预存明细
        if (fromPage == CommonParams.PAGE_TYPE_RECHARGE) {
            reChargeAdapter = new ReChargeAdapter(getActivity(), reChargeList);
            recyclerView.setAdapter(reChargeAdapter);
        } else {
            //缴费明细
            readAdapter = new ReadAdapter(getActivity(), perReadList);
            recyclerView.setAdapter(readAdapter);
        }
        recyclerView.scrollToPosition(0);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (fromPage == CommonParams.PAGE_TYPE_RECHARGE) {
                    reChargeList.clear();
                    currentPageSize = 0;
                    initListData(meterType, fromPage);
                } else {
                    perReadList.clear();
                    currentPageSize = 0;
                    initListData(meterType, fromPage);
                }

                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                if (fromPage == CommonParams.PAGE_TYPE_RECHARGE) {
                    currentPageSize += dataSize;
                    initListData(meterType, fromPage);
                } else {
                    currentPageSize += dataSize;
                    initListData(meterType, fromPage);
                }
                refreshLayout.finishLoadMore();
            }
        });

    }

    @Override
    protected void reLoadData() {

    }

    @Override
    protected int setFrgContainView() {
        Log.d("xyc", "setFrgContainView: 3" + mTitle);
        return R.layout.fragment_per_storage;
    }

    /**
     * 初始化获取数据
     *
     * @param meterType
     * @param pageType
     */
    private void initListData(int meterType, int pageType) {
        if (pageType == CommonParams.PAGE_TYPE_RECHARGE) {
            //预存明细
            MeterManager.getInstance().getReChargeList(currentPageSize, dataSize, meterType,0);
        } else {
            //缴费明细
            MeterManager.getInstance().getRePayList(currentPageSize, dataSize, meterType,0);
        }
    }


    /**
     * 接口返回--预存
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GetGasReChargeListEvent event) {
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
    public void onEvent(GetGasReadListEvent event) {
        currentPageSize += event.getList().size();
        if (perReadList.size() > 0) {
            perReadList.addAll(event.getList());
        } else {
            perReadList = event.getList();
        }
        readAdapter.setData(perReadList);
        readAdapter.notifyDataSetChanged();
    }
}
/*
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("xyc", "onCreate: 2");

        View view = inflater.inflate(R.layout.fragment_per_storage, container, false);
        TextView textView = (TextView) view.findViewById(R.id.fragment_text);

        return view;
    }*/