package com.app.watermeter.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.model.PreSaveModel;
import com.app.watermeter.view.adapter.PreSaveAdapter;
import com.app.watermeter.view.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 缴费明细
 *
 * @author Tim
 */
public class PerStorageFragment extends BaseFragment {

    private static final String KEY = "extra";
    private static final String KEY_PAGE = "page";
    private String mMessage;
    private int fromPage = 0;
    private PreSaveAdapter preSaveAdapter;
    private LinearLayoutManager mLayoutManager;

    public PerStorageFragment() {
    }


    @BindView(R.id.smartFresh)
    SmartRefreshLayout smartFresh;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    public static PerStorageFragment newInstance(String extra, int fromPageType) {
        Bundle args = new Bundle();
        args.putString(KEY, extra);
        args.putInt(KEY_PAGE, fromPageType);
        PerStorageFragment fragment = new PerStorageFragment();
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
   private List<PreSaveModel> preSaveModels;
    @Override
    protected void initData() {
        preSaveModels  = new ArrayList<>();
        for(int i=0;i<20;i++){
            preSaveModels.add(new PreSaveModel("水表编码：21"+i,"2019/02/23",23+i));
        }
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        preSaveAdapter = new PreSaveAdapter(getActivity(),preSaveModels);
        recyclerView.setAdapter(preSaveAdapter);
    }

    @Override
    protected void reLoadData() {

    }

    @Override
    protected int setFrgContainView() {
        Log.d("xyc", "setFrgContainView: 3" + mMessage);
            return R.layout.fragment_per_storage;
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