package com.mazlow.payments_subscription.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Mazlow.R;
import com.mazlow.customclasses.MediumTextView;
import com.mazlow.payments_subscription.models.FeatureDetail;

import java.util.List;

public class FeaturesAdapter extends RecyclerView.Adapter<FeaturesAdapter.ViewHolder> {
    Activity activity;
    List<FeatureDetail> featureDetailList;

    public FeaturesAdapter(Activity activity, List<FeatureDetail> featureDetailList) {
      this.activity=activity;
      this.featureDetailList=featureDetailList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.adapter_feature, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.textViewTitle.setText(featureDetailList.get(position).getName());
            if(featureDetailList.get(position).isEnabled()){
                holder.imageViewTick.setImageResource(R.drawable.ic_tick);
            }else{
                holder.imageViewTick.setImageResource(R.drawable.ic_tick_gray);
            }
    }

    @Override
    public int getItemCount() {
        return featureDetailList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        MediumTextView textViewTitle;
        ImageView imageViewTick;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle= itemView.findViewById(R.id.textViewTitle);
            imageViewTick=itemView.findViewById(R.id.imageViewTick);
        }
    }
}
