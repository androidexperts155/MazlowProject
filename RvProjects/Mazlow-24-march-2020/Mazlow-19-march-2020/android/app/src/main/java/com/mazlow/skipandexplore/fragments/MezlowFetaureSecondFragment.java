package com.mazlow.skipandexplore.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Mazlow.R;
import com.mazlow.customclasses.KKViewPager;
import com.mazlow.customclasses.LightTextView;
import com.mazlow.customclasses.MediumEdittext;
import com.mazlow.customclasses.MediumTextView;
import com.mazlow.customclasses.Prefs;
import com.mazlow.payments_subscription.activities.select_cards.SelectCardPresenterImple;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class MezlowFetaureSecondFragment extends Fragment {



    @BindView(R.id.linearLayoutMonthly)
    LinearLayout linearLayoutMonthly;

    @BindView(R.id.textViewMonthlty)
    TextView textViewMonthlty;

    @BindView(R.id.imageViewTickMonthly)
    ImageView imageViewTickMonthly;

    @BindView(R.id.linearLayoutAnnual)
    LinearLayout linearLayoutAnnual;
    @BindView(R.id.textViewAnnual)
    MediumTextView textViewAnnual;

    @BindView(R.id.imageViewTickAnnual)
    ImageView imageViewTickAnnual;

    @BindView(R.id.textViewAnnualDiscount)
    TextView textViewAnnualDiscount;

    @BindView(R.id.textViewPrice)
    TextView textViewPrice;

    @BindView(R.id.textViewSubscriptionType)
    TextView textViewSubscriptionType;

    @BindView(R.id.textViewDiscription)
    TextView textViewDiscription;

    @BindView(R.id.linearLayoutCard)
    LinearLayout linearLayoutCard;


    boolean isMonthlyActive = true;
    private Prefs prefs;
    private String accesstoken;
    SelectCardPresenterImple presenter;

    String monthlySubscriptionId = "5d9c78ed7f4f0b25490558d3";
    String yearlySubscriptionId = "5d9c788c7f4f0b25490558d2";
    String standardCardSubscriptionId = "5d9c788c7f4f0b25490558d2";

    String subscriptionId;

    double monthlyPrice = 18.99;
    double yearlyPrice = 199.00;
    double standardPrice = 7.99;

    double price;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mezlow_fetaure_second, container, false);

        ButterKnife.bind(this,view);
        handleMonthlyYearlyClick();




        return  view;

    }

    @OnClick(R.id.relativeLayoutMonthly)
    void onRelativeLayoutMonthlyClick() {
        isMonthlyActive = true;
        handleMonthlyYearlyClick();

    }

    @OnClick(R.id.relativeLayoutAnnual)
    void onRelativeLayoutAnnualClick() {
        isMonthlyActive = false;
        handleMonthlyYearlyClick();
    }

    void handleMonthlyYearlyClick() {
        if (isMonthlyActive) {
            price = monthlyPrice;
            subscriptionId = monthlySubscriptionId;
            linearLayoutAnnual.setBackgroundResource(R.drawable.shape_rectangle_light_black);
            linearLayoutMonthly.setBackgroundResource(R.drawable.shape_rectangle_black_stroke);

            imageViewTickAnnual.setVisibility(View.GONE);
            imageViewTickMonthly.setVisibility(View.VISIBLE);

            textViewMonthlty.setTextColor(getResources().getColor(R.color.blue_color));
            textViewAnnual.setTextColor(getResources().getColor(R.color.color_light_black));
            textViewAnnualDiscount.setTextColor(getResources().getColor(R.color.color_light_black));

            textViewPrice.setText("£" + price + "");
            textViewSubscriptionType.setText("/month");
            textViewDiscription.setText(getString(R.string.your_monthly_fee_will_be_taken_out_of_your_mazlow_account_after_topping_up_and_each_month_around_the_10th));
        } else {
            price = yearlyPrice;
            subscriptionId = yearlySubscriptionId;
            linearLayoutAnnual.setBackgroundResource(R.drawable.shape_rectangle_black_stroke);
            linearLayoutMonthly.setBackgroundResource(R.drawable.shape_rectangle_light_black);

            imageViewTickAnnual.setVisibility(View.VISIBLE);
            imageViewTickMonthly.setVisibility(View.GONE);

            textViewAnnual.setTextColor(getResources().getColor(R.color.blue_color));
            textViewAnnualDiscount.setTextColor(getResources().getColor(R.color.blue_color));
            textViewMonthlty.setTextColor(getResources().getColor(R.color.color_light_black));

            textViewPrice.setText("£" + price + "");
            textViewSubscriptionType.setText("/year");
            textViewDiscription.setText(getString(R.string.annual_subscription_descrition));
        }
    }

}
