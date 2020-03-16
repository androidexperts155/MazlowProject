package com.mazlow.ui.users.selectbeneficiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.Mazlow.R;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.Prefs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectBeneficiaryActivity extends AppCompatActivity implements SelectBeneficiaryView {

    @BindView(R.id.rv_beneficiary_list)
    RecyclerView rv_beneficiary_list;
    @BindView(R.id.back_icon)
    ImageView back_icon;

    SelectBeneficiaryPresenterImplementation selectBeneficiaryPresenterImplementation;
    Prefs prefs;
    String token="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_beneficiary);
        ButterKnife.bind(this);
        init();
        initRecycleView();
    }

    private void init() {
        prefs= new Prefs(this);
        token = prefs.getString(Bean.ACCESS_TOKEN, "");
        selectBeneficiaryPresenterImplementation=new SelectBeneficiaryPresenterImplementation(this,this);
        selectBeneficiaryPresenterImplementation.getBeneficiaryList(token);
    }

    private void initRecycleView() {
        rv_beneficiary_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        rv_beneficiary_list.setNestedScrollingEnabled(true);

       // rv_beneficiary_list.setAdapter(nextChallengesAdapter);
    }


    @OnClick({R.id.back_icon})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.back_icon:
                onBackPressed();
                break;
        }
    }






    @Override
    public void getBeneficiarySuccess() {

    }

    @Override
    public void getBeneficiaryError() {

    }

    @Override
    public void getBeneficiaryNoInternet() {

    }
}
