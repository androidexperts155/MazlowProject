package com.mazlow.ui.users.dashboard.fragments.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Mazlow.R;
import com.mazlow.login.model.DayChallenge;
import com.mazlow.ui.users.dashboard.fragments.models.NextChallengesModel;

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
       getIcon(item.getName());
       holder.txt_title.setText(item.getName());
       holder.txt_amount.setText(item.getDuration().toString());

    }

    private void getIcon(String name) {

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       TextView txt_title,txt_amount;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_amount=itemView.findViewById(R.id.txt_amount);
            txt_title=itemView.findViewById(R.id.txt_title);
        }
    }
}
