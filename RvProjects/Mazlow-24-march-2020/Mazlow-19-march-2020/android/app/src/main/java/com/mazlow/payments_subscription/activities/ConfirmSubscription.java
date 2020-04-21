package com.mazlow.payments_subscription.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Mazlow.R;
import com.mazlow.adduserdetails.FirstPageActivity;
import com.mazlow.adduserdetails.SecondPageActivity;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.MediumTextView;
import com.mazlow.payments_subscription.activities.billing_address.BillingAddress;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmSubscription extends AppCompatActivity implements View.OnClickListener {

    private double price;
    @BindView(R.id.textViewPrice)
    MediumTextView textViewPrice;

    @BindView(R.id.tv_addpeyment)
    MediumTextView tv_addpeyment;


    String subscriptionid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_subscription);
        ButterKnife.bind(this);
        price=getIntent().getDoubleExtra("price",0.0);
        textViewPrice.setText("£" + price + "");
        tv_addpeyment.setOnClickListener(this);
        
        if (price==199.0)
        {
            subscriptionid= "5d9c788c7f4f0b25490558d2";
        }
        else if (price==18.99)
        {
            subscriptionid ="5d9c78ed7f4f0b25490558d3";
        }
        else 
        {
            subscriptionid = "5d9c79107f4f0b25490558d4";
        }
    }

    @OnClick(R.id.imageViewBack)
    void imageViewBackClick(){
        finish();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.tv_addpeyment:


                Intent intent= new Intent(this, BillingAddress.class);
                intent.putExtra("subcription_id", subscriptionid);
                intent.putExtra("amount", String.valueOf(price));
                intent.putExtra("from", "subcription");
                startActivity(intent);

                break;
        }
    }
}

