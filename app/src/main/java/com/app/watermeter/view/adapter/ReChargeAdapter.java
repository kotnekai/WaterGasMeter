package com.app.watermeter.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.model.MeterReChargeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Admin on 2018/8/27
 *
 * @author Tim
 */
public class ReChargeAdapter extends Adapter<ReChargeAdapter.MyViewHolder> {
    private List<MeterReChargeModel> reChargeList;
    private Context context;
    private LayoutInflater inflater;
    private int mTabType;
    private int meterType;


    public ReChargeAdapter(Context context, List<MeterReChargeModel> reChargeList, int meterType) {
        this.context = context;
        this.reChargeList = reChargeList;
        inflater = LayoutInflater.from(context);
        this.meterType = meterType;

    }

    public void setData(List<MeterReChargeModel> list) {
        if (list != null && list.size() > 0) {
            this.reChargeList = list;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View views = inflater.inflate(R.layout.item_per_storage, parent, false);
        return new MyViewHolder(views);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MeterReChargeModel reChargeModel = reChargeList.get(position);
        if (reChargeModel == null) {
            return;
        }
        String typeStr;
        if (meterType == CommonParams.TYPE_ELECT) {
            typeStr = context.getString(R.string.electricity_sn);
        } else if (mTabType == CommonParams.TYPE_GAS) {
            typeStr = context.getString(R.string.gas_sn);
        } else {
            typeStr = context.getString(R.string.water_sn);
        }
        holder.tvWaterSn.setText(String.format(typeStr, reChargeModel.getMachine_sn() + ""));
        holder.tvReChargeDate.setText(String.format(context.getString(R.string.storage_time), reChargeModel.getCreated_at()));
        holder.tvSaveMoney.setText("+" + reChargeModel.getRecharge_fee() + context.getString(R.string.unit_yuan));
    }


    @Override
    public int getItemCount() {
        return reChargeList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvWaterSn;
        TextView tvReChargeDate;
        TextView tvSaveMoney;

        public MyViewHolder(View view) {
            super(view);
            tvWaterSn = view.findViewById(R.id.tvWaterSn);
            tvReChargeDate = view.findViewById(R.id.tvReChargeDate);
            tvSaveMoney = view.findViewById(R.id.tvSaveMoney);
        }

    }
}
