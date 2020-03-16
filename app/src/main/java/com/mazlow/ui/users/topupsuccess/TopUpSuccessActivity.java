package com.mazlow.ui.users.topupsuccess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.Mazlow.R;
import com.google.gson.Gson;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.M;
import com.mazlow.customclasses.Prefs;
import com.mazlow.login.model.LoginResponseModel;
import com.mazlow.onfido.verification_identity.GetProfileImaplentation;
import com.mazlow.onfido.verification_identity.GetProfileView;
import com.mazlow.ui.users.dashboard.activity.DashboardActivity;
import com.mazlow.ui.users.dashboard.fragments.home.HomeFragmentPresenter;
import com.mazlow.ui.users.dashboard.fragments.home.HomeFragmentView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopUpSuccessActivity extends AppCompatActivity implements HomeFragmentView, GetProfileView {
    @BindView(R.id.cl_root)
    ConstraintLayout cl_root;
    @BindView(R.id.txt_msg)
    TextView txt_msg;

    HomeFragmentPresenter homeFragmentPresenter;
    GetProfileImaplentation getProfileImaplentation;
    Prefs prefs;
    String token;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up_success);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        prefs = new Prefs(this);
        token=prefs.getString(Bean.ACCESS_TOKEN,"");
        dialog = M.showloader(this, "", false, false);

        homeFragmentPresenter=new HomeFragmentPresenter(this,this);
        getProfileImaplentation =new GetProfileImaplentation(this,this);



        Bundle bundle = getIntent().getBundleExtra("data");
        String msg="You've topped up your account by "+bundle.getString("currencySymbol")+bundle.getString("amount");

        txt_msg.setText(msg);
    }

    @OnClick(R.id.cl_root)
    void onClick(View view){
        dialog.show();
        homeFragmentPresenter.updateStatement(token);

    }


    @Override
    public void Sucess(LoginResponseModel loginResponseModel) {
        saveProfileData(loginResponseModel);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(i);
                finishAffinity();
            }
        },200);



    }

    @Override
    public void error(String error) {
        dialog.hide();

    }

    @Override
    public void noInternet(String tag) {

        dialog.hide();
    }

    @Override
    public void getUpdateStatementSuccess() {
      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
              getProfileImaplentation.onGetProfile(token);
          }
      },3000);
    }

    @Override
    public void getUpdateStatementError() {
        dialog.hide();

    }

    @Override
    public void getUpdateStatementNoInternet() {
        dialog.hide();

    }
    private void saveProfileData(LoginResponseModel loginResponseModel) {
        Gson gson = new Gson();
        String prfiledata = gson.toJson(loginResponseModel);
        prefs.setString(Bean.PROFILE_DATA,prfiledata);
    }
}
   /* Intent i=new Intent(getApplicationContext(), DashboardActivity.class);
    startActivity(i);*/