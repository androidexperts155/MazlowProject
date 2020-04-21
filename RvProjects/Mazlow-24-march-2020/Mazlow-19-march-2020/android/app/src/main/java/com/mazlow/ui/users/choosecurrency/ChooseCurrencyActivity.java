package com.mazlow.ui.users.choosecurrency;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.Mazlow.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ChooseCurrencyActivity extends AppCompatActivity {
    @BindView(R.id.ll_gbp)
    LinearLayout ll_gbp;
    @BindView(R.id.ll_eur)
    LinearLayout ll_eur;
    @BindView(R.id.ll_usd)
    LinearLayout ll_usd;
    @BindView(R.id.imageViewBack)
    ImageView imageViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_currency);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.imageViewBack)
    public void backpress() {
        onBackPressed();
    }
    @OnClick({R.id.ll_usd,R.id.ll_eur,R.id.ll_gbp})
    public void onClick(View view){
        String currency="GBP";


        switch (view.getId()){
            case R.id.ll_gbp:
                currency="GBP";
            break;
            case R.id.ll_usd:
                currency="USD";
            break;
            case R.id.ll_eur:
                currency="EUR";
            break;

        }
        Intent intent=new Intent();
        intent.putExtra("currency",currency);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }

}
