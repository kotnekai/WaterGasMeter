package com.app.watermeter.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.view.activity.PersonInfoActivity;
import com.app.watermeter.view.activity.PreStorageSaveActivity;
import com.app.watermeter.view.activity.ScanBillListActivity;
import com.app.watermeter.view.activity.SettingActivity;
import com.app.watermeter.view.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFragment extends BaseFragment {

    @BindView(R.id.rlPreSave)
    RelativeLayout rlPreSave;

    @BindView(R.id.rlPayDes)
    RelativeLayout rlPayDes;

    @BindView(R.id.rlPersonInfo)
    RelativeLayout rlPersonInfo;

    @BindView(R.id.rlOtherSet)
    RelativeLayout rlOtherSet;
    @BindView(R.id.rlScanBill)
    RelativeLayout rlScanBill;

    @BindView(R.id.rlTransaction)
    RelativeLayout rlTransaction;


    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void reLoadData() {

    }

    @Override
    protected int setFrgContainView() {
        return R.layout.fragment_mine;

    }

    @OnClick({R.id.rlPreSave, R.id.rlPayDes, R.id.rlPersonInfo, R.id.rlScanBill, R.id.rlOtherSet, R.id.rlTransaction})
    public void onClick(View view) {
        switch (view.getId()) {
            // 预存明细
            case R.id.rlPreSave:
                startActivity(PreStorageSaveActivity.makeIntent(getContext(), CommonParams.PAGE_TYPE_RECHARGE));
                break;
            // 缴费明细
            case R.id.rlPayDes:
                startActivity(PreStorageSaveActivity.makeIntent(getContext(), CommonParams.PAGE_TYPE_READ));
                break;
            case R.id.rlTransaction:
                startActivity(PreStorageSaveActivity.makeIntent(getContext(), CommonParams.PAGE_TYPE_TRANSACTION));
                break;
            // 扫码充值明细
            case R.id.rlScanBill:
                startActivity(ScanBillListActivity.makeIntent(getContext()));
                break;
            // 个人资料
            case R.id.rlPersonInfo:
                startActivity(PersonInfoActivity.makeIntent(getContext()));
                break;
            // 其他设置
            case R.id.rlOtherSet:
                startActivity(SettingActivity.makeIntent(getContext()));
                break;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
