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
import com.app.watermeter.model.PerSaveModel;
import com.app.watermeter.model.PerStorageModel;
import com.app.watermeter.view.adapter.PerSaveAdapter;
import com.app.watermeter.view.adapter.PerStorageAdapter;
import com.app.watermeter.view.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 缴费明细
 *
 * @author Tim
 */
public class PerStorageSaveFragment extends BaseFragment {

    private static final String KEY = "extra";
    private static final String KEY_PAGE = "page";
    private String mMessage;
    private int fromPage = 0;
    private PerStorageAdapter perStorageAdapter;
    private PerSaveAdapter perSaveAdapter;
    private LinearLayoutManager mLayoutManager;

    private List<PerStorageModel> perStorageList = new ArrayList<>();
    private List<PerSaveModel> perSaveList = new ArrayList<>();


    public PerStorageSaveFragment() {
    }


    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    public static PerStorageSaveFragment newInstance(String extra, int fromPageType) {
        Bundle args = new Bundle();
        args.putString(KEY, extra);
        args.putInt(KEY_PAGE, fromPageType);
        PerStorageSaveFragment fragment = new PerStorageSaveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mMessage = bundle.getString(KEY);
            fromPage = bundle.getInt(KEY_PAGE, 0);
        }
        Log.d("xyc", "onCreate: 1" + mMessage);
    }

    @Override
    protected void initView() {
        Log.d("xyc", "initView: 2" + mMessage);
    }


    @Override
    protected void initData() {

        addListData(fromPage);

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);


        if (fromPage == CommonParams.PAGE_TYPE_STORAGE) {
            perStorageAdapter = new PerStorageAdapter(getActivity(), perStorageList);
            recyclerView.setAdapter(perStorageAdapter);
        } else {
            perSaveAdapter = new PerSaveAdapter(getActivity(), perSaveList);
            recyclerView.setAdapter(perSaveAdapter);
        }
        recyclerView.scrollToPosition(0);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
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
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
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

    @Override
    protected void reLoadData() {

    }

    @Override
    protected int setFrgContainView() {
        Log.d("xyc", "setFrgContainView: 3" + mMessage);
        return R.layout.fragment_per_storage;
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

/*
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("xyc", "onCreate: 2");

        View view = inflater.inflate(R.layout.fragment_per_storage, container, false);
        TextView textView = (TextView) view.findViewById(R.id.fragment_text);

        return view;
    }*/
}