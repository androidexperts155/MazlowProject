package com.mazlow.ui.users.dashboard.fragments.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Mazlow.R;
import com.mazlow.ui.users.dashboard.fragments.models.NextChallengesModel;

import java.util.ArrayList;

public class NextChallengesAdapter extends RecyclerView.Adapter<NextChallengesAdapter.MyViewHolder> {

   Activity activity;
   ArrayList<NextChallengesModel> dataList;
    AddItemClickListener listener;
   public interface AddItemClickListener{
       public void OnItemClicked(NextChallengesModel nextChallengesModel);

   }


    public NextChallengesAdapter(Activity activity, ArrayList<NextChallengesModel> dataList, AddItemClickListener listener) {
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

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
