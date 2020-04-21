package com.mazlow.payments_subscription.activities.select_cards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.Mazlow.R;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.KKViewPager;
import com.mazlow.customclasses.LightTextView;
import com.mazlow.customclasses.M;
import com.mazlow.customclasses.MediumEdittext;
import com.mazlow.customclasses.MediumTextView;
import com.mazlow.customclasses.Prefs;
import com.mazlow.login.PassCodeActivity;
import com.mazlow.payments_subscription.activities.ConfirmSubscription;
import com.mazlow.payments_subscription.activities.SubscriptionFeatureList;
import com.mazlow.payments_subscription.adapter.CardsAdapter;
import com.mazlow.payments_subscription.models.apply_coupon_code.ApplyCouponCodeResponse;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectCards extends AppCompatActivity implements SelectCardView {

    @BindView(R.id.viewPager)
    KKViewPager viewPager;

    @BindView(R.id.textViewCardName)
    MediumTextView textViewCardName;

    @BindView(R.id.linearLayoutMonthly)
    LinearLayout linearLayoutMonthly;

    @BindView(R.id.textViewMonthlty)
    MediumTextView textViewMonthlty;

    @BindView(R.id.imageViewTickMonthly)
    ImageView imageViewTickMonthly;

    @BindView(R.id.linearLayoutAnnual)
    LinearLayout linearLayoutAnnual;
    @BindView(R.id.textViewAnnual)
    MediumTextView textViewAnnual;

    @BindView(R.id.imageViewTickAnnual)
    ImageView imageViewTickAnnual;

    @BindView(R.id.textViewAnnualDiscount)
    MediumTextView textViewAnnualDiscount;

    @BindView(R.id.textViewPrice)
    MediumTextView textViewPrice;

    @BindView(R.id.textViewSubscriptionType)
    MediumTextView textViewSubscriptionType;

    @BindView(R.id.textViewDiscription)
    MediumTextView textViewDiscription;

    @BindView(R.id.linearLayoutCard)
    LinearLayout linearLayoutCard;

    @BindView(R.id.textViewCardDescription)
    MediumTextView textViewCardDescription;

    @BindView(R.id.editTextReferralCode)
    MediumEdittext editTextReferralCode;

    @BindView(R.id.textViewContinue)
    MediumTextView textViewContinue;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_cards);
        ButterKnife.bind(this);
        presenter = new SelectCardPresenterImple(this, this);
        prefs = new Prefs(this);
        accesstoken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1ZTU3YjMxOGE0ZDRmNTMzYzU2ODhhOTIiLCJpYXQiOjE1ODI4NjM5MDh9.4VrEBk1o2KvEEavIU2RBb8QzEa3YjB6Z4magWw4--jo";//prefs.getString(Bean.ACCESS_TOKEN,"");
        setUpViewPager();
        handleMonthlyYearlyClick();
    }

    @OnClick(R.id.relativeLayoutMonthly)
    void onRelativeLayoutMonthlyClick()
    {
        isMonthlyActive = true;
        handleMonthlyYearlyClick();

    }

    @OnClick(R.id.relativeLayoutAnnual)
    void onRelativeLayoutAnnualClick() {
        isMonthlyActive = false;
        handleMonthlyYearlyClick();
    }

    @OnClick(R.id.textViewContinue)
    void textViewContinueClick(){
        startActivity(new Intent(this, ConfirmSubscription.class).putExtra("price",price));
    }

    @OnClick(R.id.imageViewInfo)
    void imageViewInfoClick() {
        startActivity(new Intent(this, SubscriptionFeatureList.class).putExtra("type", viewPager.getCurrentItem()));
    }

    @OnClick(R.id.textViewApply)
    void textViewApplyClick() {
        if (editTextReferralCode.getText().toString().trim().isEmpty()) {
            M.showToast(SelectCards.this, getString(R.string.empty_referral_code));
        } else {
            presenter.applyCoponCode(accesstoken, subscriptionId, editTextReferralCode.getText().toString());
        }
    }

    void setUpViewPager() {
        CardsAdapter adapter = new CardsAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        textViewCardName.setText(getString(R.string.premiu_metal));
                        linearLayoutCard.setVisibility(View.VISIBLE);
                        textViewCardDescription.setText(getString(R.string.you_will_get_access_to_mazlow_s_full_range_of_feature));
                        handleMonthlyYearlyClick();
                        break;
                    case 1:
                        subscriptionId = standardCardSubscriptionId;
                        price = standardPrice;
                        textViewCardName.setText(getString(R.string.standard_card));
                        linearLayoutCard.setVisibility(View.GONE);
                        textViewPrice.setText("£" + price + "");
                        textViewSubscriptionType.setText(getString(R.string.initial_card_fee));
                        textViewCardDescription.setText(getString(R.string.standar_card_discription));
                        textViewDiscription.setText(getString(R.string.deducted_from_your_account));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
        }
        else
            {
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

    @Override
    public void onSuccess(ApplyCouponCodeResponse response) {
        applyCouponCode(response.getData().getPercentage());
        editTextReferralCode.setText("");
    }

    @Override
    public void onError(String error) {
        M.showToast(SelectCards.this, error);
    }

    @Override
    public void noInternet(String tag) {
        M.networkDialog(this);
    }

    void applyCouponCode(String percentage) {
        price = (price - (price * Double.parseDouble(percentage)) / 100);
        textViewPrice.setText("£" + price + "");
    }
}
