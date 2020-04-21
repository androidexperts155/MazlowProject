package com.mazlow.ui.users.dashboard.set_goals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Mazlow.R;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.M;
import com.mazlow.customclasses.Prefs;
import com.mazlow.ui.users.dashboard.set_goals.adapters.GoalAdapter;
import com.mazlow.ui.users.dashboard.set_goals.adapters.GoalAdapterWellbeing;
import com.mazlow.ui.users.dashboard.set_goals.confirm_goals.ConfirmGoalsActivity;
import com.mazlow.ui.users.dashboard.set_goals.model.GoalInfo;
import com.mazlow.ui.users.dashboard.set_goals.model.GoalResponseModel;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectGoalActivity extends AppCompatActivity implements SetGoalView {

    @BindView(R.id.myList)
    RecyclerView recyclerView;
    @BindView(R.id.imageback)
    ImageView backimge;
    @BindView(R.id.myList2)
    RecyclerView recyclerView1;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.tv_select_financal_gaol)
    TextView tv_select_financal_gaol;
    @BindView(R.id.tv_selected2)
    TextView tv_selected2;


    SetGoalPrsenterImplementation setGoalPrsenterImplementation;
    GoalAdapter goalAdapter;
    GoalAdapterWellbeing goalAdapterWellbeing;
    List<GoalInfo> goalInfoList;
    ArrayList<GoalInfo> wellbeingList;
    ArrayList<GoalInfo> financialgoal;

    String goal_id1="",title1,title2,title3,goal_id2="",goal_id3="";

    Prefs prefs;
    String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_goal);
        ButterKnife.bind(this);

        initView();

    }
    @OnClick(R.id.imageback)
    void onbackClick()
    {
        finish();
    }
    @OnClick(R.id.btn_next)
    void btnnextclick()
    {
        if (goal_id1.equals("")||goal_id2.equals("")||goal_id3.equals(""))
        {
            M.showToast(this,"three goals are mandatory");
        }
        else
        {
            confirmgoalActivity();
        }
    }

    private void confirmgoalActivity() {

        Intent intent = new Intent(SelectGoalActivity.this, ConfirmGoalsActivity.class);
        intent.putExtra("goal_id1",goal_id1);
        intent.putExtra("title1",title1);

        intent.putExtra("goal_id2",goal_id2);
        intent.putExtra("title2",title2);

        intent.putExtra("goal_id3",goal_id3);
        intent.putExtra("title3",title3);

        startActivity(intent);
    }

    private void initView() {

        prefs = new Prefs(this);

        token = prefs.getString(Bean.ACCESS_TOKEN,"");

        setGoalPrsenterImplementation = new  SetGoalPrsenterImplementation(this,this);
        setGoalPrsenterImplementation.getGoals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1ZTRiYTRjOTQ5MzIxNjJhNjRmNzdjODAiLCJpYXQiOjE1ODMzODQwNTJ9.f8FyMCs46VABrSwQSCu-RS1BZYMXi5u6rQZ5LsgNL8A");

    }

    @Override
    public void onSuccess(GoalResponseModel responseGoals) {
        goalInfoList= responseGoals.getGoalInfo();
        adapterHande(goalInfoList);

    }


    private void adapterHande(List<GoalInfo> goalInfoList) {
        wellbeingList = new ArrayList<>();
        financialgoal = new ArrayList<>();
        for (int i=0;i<goalInfoList.size();i++)
        {
            if (goalInfoList.get(i).getType().equals("2"))
            {
                wellbeingList.add(goalInfoList.get(i));
            }
            else
            {
                financialgoal.add(goalInfoList.get(i));
            }
        }
        goalAdapter =new GoalAdapter(this,financialgoal,"1");
        recyclerView.setAdapter(goalAdapter);
        goalAdapter.setClickListner(new GoalAdapter.ClickListnerForRecyclerView() {
            @Override
            public void layoutClicked(View view, int position) {

                goal_id1 = financialgoal.get(position).get_id();
                title1 = financialgoal.get(position).getName();
                tv_select_financal_gaol.setTextColor(getResources().getColor(R.color.border_color));

            }
        });
        goalAdapterWellbeing = new GoalAdapterWellbeing(this,wellbeingList,"2");
        recyclerView1.setAdapter(goalAdapterWellbeing);
        goalAdapterWellbeing.setClickListner(new GoalAdapterWellbeing.ClickListnerForRecyclerView() {
            @Override
            public void layoutClickedwllbeing(View view, ArrayList<GoalInfo> list) {

                goal_id2 = list.get(0).get_id();
                title2 = list.get(0).getName();

                if (list.size()==2)
                {
                    goal_id3 = list.get(1).get_id();
                    title3 = list.get(1).getName();
                    tv_selected2.setTextColor(getResources().getColor(R.color.border_color));

                }
            }
        });

    }

    @Override
    public void onError(String error) {


    }

    @Override
    public void onInternet(String tag) {

        M.networkDialog(this);
    }
}
