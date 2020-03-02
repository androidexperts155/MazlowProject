package com.mazlow.payments_subscription.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Mazlow.R;
import com.mazlow.customclasses.LightTextView;
import com.mazlow.customclasses.MediumTextView;
import com.mazlow.payments_subscription.adapter.FeaturesAdapter;
import com.mazlow.payments_subscription.models.FeatureDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubscriptionFeatureList extends AppCompatActivity {
    List<FeatureDetail> featureDetailList = new ArrayList<>();


    @BindView(R.id.textViewTitle)
    MediumTextView textViewTitle;

    @BindView(R.id.textViewDescription)
    LightTextView textViewDescription;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    int type;  // 0-> premium 1->standard

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_feature_list);
        type = getIntent().getIntExtra("type", 0);
        ButterKnife.bind(this);
        setData();
        setUpFeatureList();
        setUpRecyclerView();
    }

    @OnClick(R.id.ic_close)
    void icCloseClick(){
        finish();
    }

    private void setData() {
        if (type == 0) {
            textViewTitle.setText(getString(R.string.premium));
            textViewDescription.setText(getString(R.string.you_will_get_access_to_mazlow_s_full_range_of_feature));
        } else {
            textViewDescription.setText(getString(R.string.you_will_have_limited_access));
            textViewTitle.setText(getString(R.string.standard));
        }
    }

    void setUpFeatureList() {
        String[] items = getResources().getStringArray(R.array.feature_list);
        for (int i = 0; i < items.length; i++) {
            if (type == 0) {
                FeatureDetail detail = new FeatureDetail();
                detail.setName(items[i]);
                detail.setEnabled(true);
                featureDetailList.add(detail);
            } else {
                if (i <= 7) {
                    FeatureDetail detail = new FeatureDetail();
                    detail.setName(items[i]);
                    detail.setEnabled(true);
                    featureDetailList.add(detail);
                } else {
                    FeatureDetail detail = new FeatureDetail();
                    detail.setName(items[i]);
                    detail.setEnabled(false);
                    featureDetailList.add(detail);
                }

            }
        }
    }

    void setUpRecyclerView() {
        FeaturesAdapter adapter = new FeaturesAdapter(this,featureDetailList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
