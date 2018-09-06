package com.app.watermeter.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.model.MeterReChargeModel;

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

    public ReChargeAdapter(Context context, List<MeterReChargeModel> reChargeList) {
        this.context = context;
        this.reChargeList = reChargeList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View views = inflater.inflate(R.layout.item_per_storage, parent, false);
        return new MyViewHolder(views);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MeterReChargeModel preSaveModel = reChargeList.get(position);
        if (preSaveModel == null) {
            return;
        }
//        holder.tvWaterType.setText(preSaveModel.getMeterSn());
//        holder.tvSaveDate.setText(preSaveModel.getSaveTime());
//        holder.tvSaveMoney.setText(preSaveModel.getSaveMoney() + "");
    }


    @Override
    public int getItemCount() {
        return reChargeList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvWaterType;
        TextView tvSaveDate;


        TextView tvSaveMoney;

        public MyViewHolder(View view) {
            super(view);
            tvWaterType = view.findViewById(R.id.tvWaterType);
            tvSaveDate = view.findViewById(R.id.tvSaveDate);
            tvSaveMoney = view.findViewById(R.id.tvSaveMoney);
        }

    }
}
