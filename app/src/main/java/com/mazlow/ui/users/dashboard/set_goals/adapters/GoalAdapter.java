package com.mazlow.ui.users.dashboard.set_goals.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Mazlow.R;
import com.mazlow.ui.users.dashboard.set_goals.model.GoalInfo;

import java.util.ArrayList;


public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.MyViewHolder> {

    Context context;
    ArrayList<GoalInfo> List;
    String type;
    ClickListnerForRecyclerView clickListnerForRecyclerView;
    int lastChekPostion=-1;



    public GoalAdapter(Activity selectGoalActivity, ArrayList<GoalInfo> goalInfo, String s) {
        context =selectGoalActivity;
        List = goalInfo;
        type =s;
    }

    @NonNull
    @Override
    public GoalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.goal_adapter_view,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GoalAdapter.MyViewHolder holder, int position) {


         GoalInfo responseGoals=List.get(position);
          holder.textView.setText(responseGoals.getName());

        if(!responseGoals.isChecked){
            holder.layout_relative.setBackgroundResource(R.drawable.lightgray_circleshape);
            holder.imageView.setVisibility(View.VISIBLE);

        }else {
            holder.layout_relative.setBackgroundResource(R.drawable.goal_seletced);
            holder.imageView.setVisibility(View.GONE);
            responseGoals.isChecked=true;

        }

          holder.layout_relative.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                 if(responseGoals.isChecked){
                     holder.layout_relative.setBackgroundResource(R.drawable.lightgray_circleshape);
                     holder.imageView.setVisibility(View.VISIBLE);
                     responseGoals.isChecked=false;
                     lastChekPostion=-1;
                 }else {
                     holder.layout_relative.setBackgroundResource(R.drawable.goal_seletced);
                     holder.imageView.setVisibility(View.GONE);
                     responseGoals.isChecked=true;
                     if(lastChekPostion!=-1){
                         List.get(lastChekPostion).isChecked=false;
                     }
                     lastChekPostion=position;
                 }

                 notifyDataSetChanged();

                  if (clickListnerForRecyclerView!=null){
                      clickListnerForRecyclerView.layoutClicked(v, lastChekPostion);
                  }

              }
          });
    }



    @Override
    public int getItemCount() {
        return List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RelativeLayout layout_relative;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView= itemView.findViewById(R.id.tvtitle);
            layout_relative= itemView.findViewById(R.id.layout_relative);
            imageView =itemView.findViewById(R.id.imagView);
        }
    }

    public interface ClickListnerForRecyclerView {
        void layoutClicked(View view, int position);
    }

    public void setClickListner(ClickListnerForRecyclerView clickListner) {
        this.clickListnerForRecyclerView = clickListner;
    }
}
