package com.mazlow.payments_subscription.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.Mazlow.R;
import com.mazlow.customclasses.MediumTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmSubscription extends AppCompatActivity {

    private double price;
    @BindView(R.id.textViewPrice)
    MediumTextView textViewPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_subscription);
        ButterKnife.bind(this);
        price=getIntent().getDoubleExtra("price",0.0);
        textViewPrice.setText("£" + price + "");
    }

    @OnClick(R.id.imageViewBack)
    void imageViewBackClick(){
        finish();
    }
}
