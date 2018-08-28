package com.app.watermeter.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.model.PreSaveModel;

import java.util.List;

/**
 * Create by Admin on 2018/8/27
 */
public class PreSaveAdapter extends Adapter<PreSaveAdapter.MyViewHolder> {
    private List<PreSaveModel> preSaveModelList;
    private Context context;
    private LayoutInflater inflater;

    public PreSaveAdapter(Context context, List<PreSaveModel> preSaveModelList) {
        this.context = context;
        this.preSaveModelList = preSaveModelList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View views = inflater.inflate(R.layout.presave_item_view, parent, false);
        return new MyViewHolder(views);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PreSaveModel preSaveModel = preSaveModelList.get(position);
        if (preSaveModel == null) {
            return;
        }
       holder.tvWaterType.setText(preSaveModel.getWaterMeterCode());
       holder.tvSaveTime.setText(preSaveModel.getSaveTime());
       holder.tvSaveMoney.setText(preSaveModel.getSaveMoney()+"");
    }


    @Override
    public int getItemCount() {
        return preSaveModelList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
      TextView tvWaterType;
      TextView tvSaveTime;
      TextView tvSaveMoney;
        public MyViewHolder(View view) {
            super(view);
            tvWaterType =   view.findViewById(R.id.tvWaterType);
            tvSaveTime =   view.findViewById(R.id.tvSaveTime);
            tvSaveMoney =   view.findViewById(R.id.tvSaveMoney);
        }

    }
}
