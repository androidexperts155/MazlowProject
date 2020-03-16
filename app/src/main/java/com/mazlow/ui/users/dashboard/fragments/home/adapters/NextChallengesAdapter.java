package com.mazlow.ui.users.dashboard.fragments.home.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Mazlow.R;
import com.mazlow.login.model.DayChallenge;

import java.util.ArrayList;

public class NextChallengesAdapter extends RecyclerView.Adapter<NextChallengesAdapter.MyViewHolder> {

   Activity activity;
   ArrayList<DayChallenge> dataList;
    AddItemClickListener listener;
   public interface AddItemClickListener{
       public void OnItemClicked(DayChallenge dayChallenge);

   }


    public NextChallengesAdapter(Activity activity, ArrayList<DayChallenge> dataList, AddItemClickListener listener) {
        this.activity = activity;
        this.dataList = dataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(activity).inflate(R.layout.item_next_challange,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       DayChallenge item=dataList.get(position);



       holder.txt_title.setText(item.getName());
       String amount=item.getDuration().toString();
        int icon=R.drawable.ic_steps;
        switch (item.getName()){
            case "Medidation":
                icon=R.drawable.ic_meditate;
                holder.txt_amount_txt.setText("mts");
                break;

            case "Steps":
                icon=R.drawable.ic_steps;
                break;

            case "Savings Pot":
                icon=R.drawable.ic_budget;
                amount="";
               holder. txt_conming_soon.setVisibility(View.VISIBLE);
                break;

            case "Daily Budget":
                icon=R.drawable.ic_daily_budget;
                amount="";
                break;
        }

        holder.img_icon.setImageResource(icon);

        holder.txt_amount.setText(amount);
    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       TextView txt_title,txt_amount,txt_conming_soon,txt_amount_txt;
       ImageView img_icon;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_conming_soon=itemView.findViewById(R.id.txt_conming_soon);
            txt_amount=itemView.findViewById(R.id.txt_amount);
            txt_amount_txt=itemView.findViewById(R.id.txt_amount_txt);
            txt_title=itemView.findViewById(R.id.txt_title);
            img_icon=itemView.findViewById(R.id.img_icon);
        }
    }

    int getIcon(String type){
       int icon=R.drawable.ic_steps;
        switch (type){
            case "Medidation":
                icon=R.drawable.ic_meditate;

                break;
            case "Steps":
                icon=R.drawable.ic_steps;
                break;
            case "Savings Pot":
                icon=R.drawable.ic_budget;
                break;
            case "Daily Budget":
                icon=R.drawable.ic_daily_budget;
                break;
        }
        return icon;
    }
}
