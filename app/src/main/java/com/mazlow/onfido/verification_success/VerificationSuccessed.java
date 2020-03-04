package com.mazlow.onfido.verification_success;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.Mazlow.R;
import com.mazlow.payments_subscription.activities.payment.PaymentActivity;
import com.mazlow.payments_subscription.activities.select_cards.SelectCards;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerificationSuccessed extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.main_cliklayout)
    RelativeLayout main_cliklayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_successed);
        ButterKnife.bind(this);
        setListner();

    }
    private void setListner() {
        main_cliklayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.main_cliklayout:
                intenentogoNextActivity();
                break;
        }
    }

    private void intenentogoNextActivity() {
        Intent intent= new Intent(this, SelectCards.class);
        startActivity(intent);
    }
}
