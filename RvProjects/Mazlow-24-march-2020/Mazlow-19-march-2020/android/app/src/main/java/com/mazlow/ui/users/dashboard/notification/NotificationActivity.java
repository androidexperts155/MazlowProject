package com.mazlow.ui.users.dashboard.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.Mazlow.R;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.M;
import com.mazlow.customclasses.Prefs;
import com.mazlow.ui.users.dashboard.notification.model.NotificationInfo;
import com.mazlow.ui.users.dashboard.notification.model.NotificationResponseModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationActivity extends AppCompatActivity implements NotificationView {


    @BindView(R.id.recycler_view)
     RecyclerView recyclerView;
    @BindView(R.id.img_back)
    ImageView img_back;

    NotificationAdapter notificationAdapter;
    NotificationPrsenterImplementation notificationPrsenterImplementation;

    List<NotificationInfo> notificationInfoList;
    Prefs prefs;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);

        prefs =new Prefs(this);
        token =prefs.getString(Bean.ACCESS_TOKEN,"");

        getuserNotification();

    }

    private void getuserNotification() {

        notificationPrsenterImplementation = new NotificationPrsenterImplementation(this,this);
        notificationPrsenterImplementation.getNotification("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1ZTRiYTRjOTQ5MzIxNjJhNjRmNzdjODAiLCJpYXQiOjE1ODM0OTAyMzl9.j55-uzs22b5EKXPf3auVstvGfln63P9sfj2m77s4v-8");

    }

    @OnClick(R.id.img_back)
    void backclick()
    {
        finish();
    }


    @Override
    public void onSuccess(NotificationResponseModel notificationResponseModel) {
        notificationInfoList = notificationResponseModel.getNotificationInfo();
        notificationAdapter =new NotificationAdapter(this,notificationInfoList);
        recyclerView.setAdapter(notificationAdapter);
    }

    @Override
    public void onError(String error) {
      M.showToast(this,error);
    }
    @Override
    public void onInternet(String tag) {
        M.networkDialog(this);
    }
}
