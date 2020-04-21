package com.mazlow.ui.users.dashboard.set_goals.confirm_goals;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.Mazlow.R;
import com.mazlow.customclasses.M;
import com.mazlow.ui.users.dashboard.set_goals.confirm_goals.model.ConfirmGoalResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmGoalsActivity extends AppCompatActivity implements ConfirmGoalView {

    @BindView(R.id.tvtitle)
    TextView title;
    @BindView(R.id.description_tv1)
    TextView description_tv1;

    @BindView(R.id.tvtitle_wellbeing1)
    TextView title2;
    @BindView(R.id.tv_descriptio2)
    TextView tv_descriptio2;

    @BindView(R.id.tvtitle_wellbeing2)
    TextView title3;
    @BindView(R.id.tv_description3)
    TextView tv_description3;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.back_icon)
    ImageView back_icon;
    String ids="";
    ConfirmGoalImplementation confirmGoalImplementation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_goals);
        ButterKnife.bind(this);

        getdataFromIntent();

    }

    @OnClick(R.id.btn_next)
    void btnnextclick()
    {
        confirmGoalImplementation =new ConfirmGoalImplementation(this,this);
        confirmGoalImplementation.confirmGoal("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1ZTRiYTRjOTQ5MzIxNjJhNjRmNzdjODAiLCJpYXQiOjE1ODMzODQwNTJ9.f8FyMCs46VABrSwQSCu-RS1BZYMXi5u6rQZ5LsgNL8A",ids,"");
    }
    @OnClick(R.id.back_icon)
        void onbackclick()
        {
            finish();
        }


    private void getdataFromIntent() {

        String goalid1=getIntent().getStringExtra("goal_id1");
        String goalid2=getIntent().getStringExtra("goal_id2");
        String goalid3=getIntent().getStringExtra("goal_id3");

        ids=goalid1+","+goalid2+","+goalid3;


        String title1=getIntent().getStringExtra("title1");
        String title2_text=getIntent().getStringExtra("title2");
        String title3_text=getIntent().getStringExtra("title3");

        title.setText(title1);
        title2.setText(title2_text);
        title3.setText(title3_text);


        if (goalid1.equals("5d0e0b0f08c0622bff4eeb2e"))
        {
            description_tv1.setText("Setting your daily budget and keeping up with it will help you spend less");
        }
        else if(goalid1.equals("5d0e0b1b08c0622bff4eeb2f"))
        {
            description_tv1.setText("By setting your daily budget you can be in control of your finances and prevent over-spending");
        }
        else if (goalid1.equals("5d0e0be308c0622bff4eeb36"))
        {
            description_tv1.setText("You will start saving money by staying on track with your daily budget, MoodTagging your spending will help you stay motivated!");
        }

        if (goalid2.equals("5d0e0bcc08c0622bff4eeb34"))
        {
            tv_descriptio2.setText("Completing your daily meditation challenge will increase your focus and decrease your stress. It helps you with better financial decision making.");
        }
        else if (goalid2.equals("5d0e0bd808c0622bff4eeb35"))
        {
            tv_description3.setText("Your daily meditation challenge will help you decrease stress and become mindful");
        }
        else
        {
            tv_description3.setText("Complete your daily meditation and step challenges. These will put you on your path of becoming more motivated.");
        }

        if (goalid3.equals("5d0e0bd808c0622bff4eeb35"))
        {
            tv_descriptio2.setText( "Your daily meditation challenge will help you decrease stress and become mindful");
        }
        else
        {
            tv_description3.setText("Complete your daily meditation and step challenges. These will put you on your path of becoming more motivated.");
        }

    }

    @Override
    public void onSuccess(ConfirmGoalResponse confirmGoalResponse) {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onInternet(String tag) {

    }
}
