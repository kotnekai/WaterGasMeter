package com.app.watermeter.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.common.ComApplication;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.common.Constants;
import com.app.watermeter.model.MeterInfoModel;
import com.app.watermeter.model.MeterReChargeModel;
import com.app.watermeter.view.activity.MeterDetailActivity;
import com.app.watermeter.view.activity.MeterListActivity;

import java.util.List;


/**
 * @author admin
 */
public class MeterRecyclerAdapter extends RecyclerView.Adapter<MeterRecyclerAdapter.MyViewHolder> {

    private View mHeaderView;
    private List<MeterInfoModel> mDatas;
    private Context context;
    private LayoutInflater inflater;
    private int type;

    public MeterRecyclerAdapter(Context context, List<MeterInfoModel> datas, int type) {
        this.context = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(this.context);
        this.type = type;
    }


    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mDatas.size() : mDatas.size() + 1;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final MeterInfoModel info = mDatas.get(getRealPosition(holder));

        switch (type) {
            case CommonParams.TYPE_WATER:
                holder.tvMeterTitle.setText(R.string.water_meter2);
                break;
            case CommonParams.TYPE_ELECT:
                holder.tvMeterTitle.setText(R.string.electricity_meter2);
                break;
            case CommonParams.TYPE_GAS:
                holder.tvMeterTitle.setText(R.string.gas_meter2);
                break;
            default:
                break;
        }

        switch (ComApplication.currentLanguage) {
            case Constants.LANGUAGE_CHINA:
                holder.tvMeterAddress.setText(info.getLocation_zh() + info.getPosition_zh());
                break;
            case Constants.LANGUAGE_ENGLISH:
                holder.tvMeterAddress.setText(info.getLocation_en() + info.getPosition_en());
                break;
            case Constants.LANGUAGE_KH:
                holder.tvMeterAddress.setText(info.getLocation_kh() + info.getPosition_kh());
                break;
            default:
                holder.tvMeterAddress.setText(info.getLocation_zh() + info.getPosition_zh());
        }


        holder.tvMeterName.setText(info.getMachine_sn());
        holder.tvSquare.setText(info.getDegree() + "");
        holder.tvUnit.setText(String.format(context.getString(R.string.square), info.getUnit() + ""));
        holder.tvLastValue.setText(info.getOld_degree() + "");
        holder.tvBalanceValue.setText(info.getBalance() + "");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(MeterDetailActivity.makeIntent(context, info.getMachine_sn(), type));
            }
        });

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_meter_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    public void setData(List<MeterInfoModel> list) {
        if (list != null && list.size() > 0) {
            this.mDatas = list;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tvMeterTitle, tvMeterName, tvMeterAddress, tvSquare, tvUnit, tvLastValue, tvBalanceValue;

        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.cardView);
            tvMeterTitle = view.findViewById(R.id.tvMeterTitle);
            tvMeterName = view.findViewById(R.id.tvMeterName);
            tvMeterAddress = view.findViewById(R.id.tvMeterAddress);
            tvSquare = view.findViewById(R.id.tvSquare);
            tvUnit = view.findViewById(R.id.tvUnit);
            tvLastValue = view.findViewById(R.id.tvLastValue);
            tvBalanceValue = view.findViewById(R.id.tvBalanceValue);


        }

    }
}