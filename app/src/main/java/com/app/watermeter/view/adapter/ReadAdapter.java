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
import com.app.watermeter.model.MeterReadModel;

import java.util.List;

/**
 * Create by Admin on 2018/8/27
 */
public class ReadAdapter extends Adapter<ReadAdapter.MyViewHolder> {
    private List<MeterReadModel> readList;
    private Context context;
    private LayoutInflater inflater;
    private int meterType;

    public ReadAdapter(Context context, List<MeterReadModel> readList, int meterType) {
        this.context = context;
        this.readList = readList;
        this.meterType = meterType;
        inflater = LayoutInflater.from(context);

    }

    public void setData(List<MeterReadModel> list) {
        if (list != null && list.size() > 0)
            this.readList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View views = inflater.inflate(R.layout.item_pre_save, parent, false);
        return new MyViewHolder(views);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MeterReadModel readModel = readList.get(position);
        if (readModel == null) {
            return;
        }
        String typeStr = null;
        String unitStr = null;
        if (meterType == CommonParams.TYPE_ELECT) {
            typeStr = context.getString(R.string.electricity_sn);
            unitStr = "kw/h";
        } else if (meterType == CommonParams.TYPE_GAS) {
            typeStr = context.getString(R.string.gas_sn);
            unitStr = "m³";
        } else {
            typeStr = context.getString(R.string.water_sn);
            unitStr = "m³";
        }

        holder.tvWaterSn.setText(String.format(typeStr, readModel.getMachine_sn()));
        holder.tvSaveMeasure.setText(String.format(context.getString(R.string.measurement), readModel.getRead_degree() + "")+unitStr);
        holder.tvSaveDate.setText(String.format(context.getString(R.string.payment_time), readModel.getCreated_at()));

        float fee = readModel.getRead_fee();
        holder.tvSaveMoney.setText(fee + context.getString(R.string.unit_yuan));
        holder.tvSaveMoney.setText(fee + context.getString(R.string.unit_yuan));
        if (fee > 0) {
            holder.tvSaveHints.setText(context.getString(R.string.payed));
            holder.tvSaveHints.setTextColor(context.getResources().getColor(R.color.text_color_green));

        } else {
            holder.tvSaveHints.setText(context.getString(R.string.waited_payed));
            holder.tvSaveHints.setTextColor(context.getResources().getColor(R.color.text_color_red));
        }
    }


    @Override
    public int getItemCount() {
        return readList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvWaterSn;
        TextView tvSaveMeasure;
        TextView tvSaveDate;
        TextView tvSaveHints;
        TextView tvSaveMoney;

        public MyViewHolder(View view) {
            super(view);
            tvWaterSn = view.findViewById(R.id.tvWaterSn);
            tvSaveMeasure = view.findViewById(R.id.tvSaveMeasure);
            tvSaveDate = view.findViewById(R.id.tvReChargeDate);
            tvSaveHints = view.findViewById(R.id.tvSaveHints);
            tvSaveMoney = view.findViewById(R.id.tvSaveMoney);
        }

    }
}
