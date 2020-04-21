package com.mazlow.ui.users.topupmethod.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Mazlow.R;
import com.mazlow.ui.users.addmoney.models.CardDetail;

import java.util.ArrayList;

public class SaveCardAdapter extends RecyclerView.Adapter<SaveCardAdapter.MyViewHolder> {

    Activity activity;
    ArrayList<CardDetail> cardDetailsList;



    public SaveCardAdapter(Activity activity, ArrayList<CardDetail> cardDetailsList) {
        this.activity = activity;
        this.cardDetailsList = cardDetailsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(activity).inflate(R.layout.item_card_name,null);
        return new SaveCardAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CardDetail cardDetail=cardDetailsList.get(position);
        holder.txt_card.setText(cardDetail.getCardNumber());
        holder.txt_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("cardPostion",(position+1));
                activity.setResult(Activity.RESULT_OK,intent);
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardDetailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_card=itemView.findViewById(R.id.txt_card);
        }
    }
}
