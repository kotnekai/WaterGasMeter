package com.app.watermeter.view.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.common.ComApplication;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.common.Constants;
import com.app.watermeter.model.MeterInfoModel;
import com.app.watermeter.view.activity.MeterDetailActivity;
import com.app.watermeter.view.activity.MeterListActivity;

import java.util.List;

/**
 *
 * @author admin
 * @date 2018/8/23
 */

public class GasPagerAdapter extends PagerAdapter {
    private List<MeterInfoModel> list;
    private Context context;

    public GasPagerAdapter(Context context, List<MeterInfoModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gas_meter, null);


        TextView tvMeterName = (TextView)view.findViewById(R.id.tvMeterName);
        TextView tvMeterAddress = (TextView)view.findViewById(R.id.tvMeterAddress);
        TextView tvSquare = (TextView)view.findViewById(R.id.tvSquare);
        TextView  tvUnit = (TextView)view.findViewById(R.id.tvUnit);
        TextView  tvLastValue = (TextView)view.findViewById(R.id.tvLastValue);
        TextView  tvBalanceValue = (TextView)view.findViewById(R.id.tvBalanceValue);

        MeterInfoModel info = list.get(position);
        if (info != null) {

            switch (ComApplication.currentLanguage) {
                case Constants.LANGUAGE_CHINA:
                    tvMeterAddress.setText(info.getLocation_zh()+info.getPosition_zh());
                    break;
                case Constants.LANGUAGE_ENGLISH:
                    tvMeterAddress.setText(info.getLocation_en()+info.getPosition_en());
                    break;
                case Constants.LANGUAGE_KH:
                    tvMeterAddress.setText(info.getLocation_kh()+info.getPosition_kh());
                    break;
                default:
                    tvMeterAddress.setText(info.getLocation_zh()+info.getPosition_zh());
            }

            tvMeterName.setText(info.getMachine_sn());

            tvSquare.setText(info.getDegree()+"");
            tvUnit.setText(String.format(context.getString(R.string.square),info.getUnit()+""));
            tvLastValue.setText(info.getOld_degree()+"");
            tvBalanceValue.setText(info.getBalance()+"");
        }

        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                context.startActivity(MeterDetailActivity.makeIntent(context, CommonParams.TYPE_GAS));
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
