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

import java.util.List;

/**
 * Create by Admin on 2018/8/27
 *
 * @author Tim
 */
public class ScanBillAdapter extends Adapter<ScanBillAdapter.MyViewHolder> {
    private List<MeterReChargeModel> reChargeList;
    private Context context;
    private LayoutInflater inflater;


    public ScanBillAdapter(Context context, List<MeterReChargeModel> reChargeList) {
        this.context = context;
        this.reChargeList = reChargeList;
        inflater = LayoutInflater.from(context);

    }

    public void setData(List<MeterReChargeModel> list) {
        if (list != null && list.size() > 0) {
            this.reChargeList = list;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View views = inflater.inflate(R.layout.item_recharge, parent, false);
        return new MyViewHolder(views);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MeterReChargeModel reChargeModel = reChargeList.get(position);
        if (reChargeModel == null) {
            return;
        }
        String typeStr = context.getString(R.string.meter_sn);

        holder.tvWaterSn.setText(String.format(typeStr, reChargeModel.getMachine_sn() + ""));
        holder.tvReChargeDate.setText(String.format(context.getString(R.string.recharge_time), reChargeModel.getCreated_at()));
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
