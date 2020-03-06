package com.mazlow.ui.users.topupmethod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.Mazlow.R;
import com.mazlow.payments_subscription.activities.billing_address.BillingAddress;
import com.mazlow.ui.users.addmoney.models.CardDetail;
import com.mazlow.ui.users.topupmethod.adapter.SaveCardAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectTopupMethodActivity extends AppCompatActivity {
    @BindView(R.id.rv_card_list)
    RecyclerView rv_card_list;
    @BindView(R.id.ll_add_new_card)
    LinearLayout ll_add_new_card;


    SaveCardAdapter saveCardAdapter;
    ArrayList<CardDetail> cardDetailsList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_topup_method);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        ArrayList<CardDetail> tmplist= (ArrayList<CardDetail>) getIntent().getSerializableExtra("cardList");
        cardDetailsList.addAll(tmplist);
        cardDetailsList.remove(0);
        initRecycleView();
    }

    private void initRecycleView() {
        rv_card_list.setLayoutManager(new LinearLayoutManager(this));
        rv_card_list.setNestedScrollingEnabled(true);
        saveCardAdapter=new SaveCardAdapter(this,cardDetailsList);


        rv_card_list.setAdapter(saveCardAdapter);
    }

    @OnClick(R.id.ll_add_new_card)
    void onClick(View view){
        switch (view.getId()) {
            case R.id.ll_add_new_card:
                Intent intent1=new Intent(this, BillingAddress.class);
                intent1.putExtra("data", getIntent().getBundleExtra("data"));
                intent1.putExtra("from", "topup");
                startActivity(intent1);



                break;
        }

    }
}
