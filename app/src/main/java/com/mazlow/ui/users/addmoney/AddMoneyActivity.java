package com.mazlow.ui.users.addmoney;

import androidx.annotation.Nullable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.Mazlow.R;
import com.google.gson.Gson;
import com.mazlow.customclasses.BaseActivity;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.Prefs;
import com.mazlow.customviews.CurrencyEditText;
import com.mazlow.login.model.LoginResponseModel;
import com.mazlow.payments_subscription.activities.payment.PaymentActivity;
import com.mazlow.ui.users.addmoney.adapter.CustomArrayAdapter;
import com.mazlow.ui.users.addmoney.models.CardDetail;
import com.mazlow.ui.users.addmoney.models.MyCardModel;
import com.mazlow.ui.users.addmoney.models.topupmodel.TopupWallet;
import com.mazlow.ui.users.choosecurrency.ChooseCurrencyActivity;
import com.mazlow.ui.users.dashboard.fragments.home.models.TotalBalanceModel;
import com.mazlow.ui.users.topupmethod.SelectTopupMethodActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMoneyActivity extends BaseActivity implements AddMoneyActivityView, AdapterView.OnItemSelectedListener {
    @BindView(R.id.imageViewBack)
    ImageView imageViewBack;

    @BindView(R.id.img_flag)
    ImageView img_flag;

    @BindView(R.id.txt_title)
    TextView txt_title;

    @BindView(R.id.txt_selected_card)
    TextView txt_selected_card;

    @BindView(R.id.txt_balance)
    TextView txt_balance;
    @BindView(R.id.ll_select_currency)
    LinearLayout ll_select_currency;

    @BindView(R.id.ll_select_card)
    LinearLayout ll_select_card;

    @BindView(R.id.sp_select_card)
    Spinner sp_select_card;

    @BindView(R.id.et_price)
    CurrencyEditText et_price;

    @BindView(R.id.btn_topup)
    Button btn_topup;




    CustomArrayAdapter spinnerAdapter;
    int currencyRequestCode=1256;
    int selectCardRequest=1251;
    Prefs prefs;
    String token = "";

    LoginResponseModel profileData;
    ArrayList<TotalBalanceModel> balanceModelArrayList = new ArrayList<>();
    String selected = "GBP";
    String currencySymbol = "";
    int selectedCardPostion=0;
    AdMoneyPresenterImplementation adMoneyPresenterImplementation;

    ArrayList<CardDetail> cardDetailsList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
        ButterKnife.bind(this);
        init();
        updateUi();
    }

    private void init() {
        prefs = new Prefs(this);
        token = prefs.getString(Bean.ACCESS_TOKEN, "");
        adMoneyPresenterImplementation=new AdMoneyPresenterImplementation(this,this);
        profileData = getSaveProfile();
        parseTotleBalance(profileData);
        adMoneyPresenterImplementation.getCard(token);
        CardDetail cardDetail= new CardDetail();
        cardDetail.setPfsToken("");
        cardDetailsList.add(cardDetail);
        initSpinner();
    }

    private void initSpinner() {
        //sp_select_card
        spinnerAdapter = new CustomArrayAdapter(this,
                R.layout.item_spinner,cardDetailsList );



        sp_select_card.setAdapter(spinnerAdapter);
    }

    @OnClick({R.id.imageViewBack,R.id.ll_select_card,R.id.ll_select_currency,R.id.btn_topup,R.id.txt_selected_card})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageViewBack:
                onBackPressed();
                break;
            case R.id.ll_select_currency:
                Intent intent=new Intent(this, ChooseCurrencyActivity.class);
                startActivityForResult(intent,currencyRequestCode);
                break;
            case R.id.ll_select_card:
                sp_select_card.performClick();
                break;
            case R.id.btn_topup:
                requestTopUp();
                break;

            case R.id.txt_selected_card:
                String tmpAmount=et_price.getText().toString();
                if(tmpAmount.equals("")){
                    tmpAmount="0.00";
                }
                double amount= Double.parseDouble(tmpAmount);
                if(amount<10.00){
                    showAlertDialog(null,"Please add minimum amount of "+currencySymbol+"10.00");
                }else{


                    Intent intent1=new Intent(this,SelectTopupMethodActivity.class);
                    intent1.putExtra("cardList",cardDetailsList);

                    Bundle bundle=new Bundle();
                    bundle.putString("amount", String.valueOf(amount));
                    bundle.putString("currencySymbol",currencySymbol);
                    bundle.putString("currency",selected);

                    intent1.putExtra("data", bundle);
                    intent1.putExtra("from", "topup");


                    startActivityForResult(intent1,selectCardRequest);




                }
                break;
        }

    }

    private void requestTopUp() {
        String tmpAmount=et_price.getText().toString();
        if(tmpAmount.equals("")){
            tmpAmount="0.00";
        }
        double amount= Double.parseDouble(tmpAmount);
        if(amount<10.00){
            showAlertDialog(null,"Please add minimum amount of "+currencySymbol+"10.00");
        }else{

            if(selectedCardPostion==0){
                showAlertDialog(null,"Please select a card to add amount.");

            }else {
                adMoneyPresenterImplementation.topUpWallet(token, String.valueOf(amount),selected,cardDetailsList.get(selectedCardPostion).getPfsToken());
            }
        }

    }


    private void updateUi() {



        switch (selected) {
            case "GBP":
                img_flag.setImageResource(R.drawable.ic_gbp_circle_flag);
                txt_title.setText("GBP");

                break;
            case "USD":
                img_flag.setImageResource(R.drawable.ic_usd_circle_flag);
                txt_title.setText("USD");

                break;
            case "EUR":
                img_flag.setImageResource(R.drawable.ic_eur_circle_flag);
                txt_title.setText("EUR");

                break;
        }

        setBalanceData(selected);
    }

    void setBalanceData(String currency){
        for(TotalBalanceModel item:balanceModelArrayList ){
            if(item.currency.equals(currency)){
                String price= String.valueOf((item.balance/100))+"."+String.valueOf((item.balance%100));
                txt_balance.setText( "Balance "+item.currencySymbol+" "+price);
                currencySymbol=item.currencySymbol;
                break;
            }
        }

    }

    private LoginResponseModel getSaveProfile() {

        String data = prefs.getString(Bean.PROFILE_DATA, "");

        return new Gson().fromJson(data, LoginResponseModel.class);
    }

    private void parseTotleBalance(LoginResponseModel loginResponseModel) {
        if (loginResponseModel == null) {
            return;
        }
        balanceModelArrayList.clear();

        TotalBalanceModel totalBalanceModel = new TotalBalanceModel();

        totalBalanceModel.balance = loginResponseModel.getUserInfo().getGBPBalance();
        totalBalanceModel.todaySpend = loginResponseModel.getTodaySpend().getTodaySpendGBP();
        totalBalanceModel.active = loginResponseModel.getUserInfo().getGBPWallet();
        totalBalanceModel.currency = "GBP";
        totalBalanceModel.currencySymbol = "£";

        balanceModelArrayList.add(totalBalanceModel);

        totalBalanceModel = new TotalBalanceModel();
        totalBalanceModel.balance = loginResponseModel.getUserInfo().getUSDBalance();
        totalBalanceModel.todaySpend = loginResponseModel.getTodaySpend().getTodaySpendUSD();
        totalBalanceModel.active = loginResponseModel.getUserInfo().getUSDWallet();
        totalBalanceModel.active = loginResponseModel.getUserInfo().getUSDWallet();
        totalBalanceModel.currency = "USD";
        totalBalanceModel.currencySymbol = "$";
        balanceModelArrayList.add(totalBalanceModel);

        totalBalanceModel = new TotalBalanceModel();
        totalBalanceModel.balance = loginResponseModel.getUserInfo().getEURBalance();
        totalBalanceModel.todaySpend = loginResponseModel.getTodaySpend().getTodaySpendEUR();
        totalBalanceModel.active = loginResponseModel.getUserInfo().getEURWallet();
        totalBalanceModel.currency = "EUR";
        totalBalanceModel.currencySymbol = "€";
        balanceModelArrayList.add(totalBalanceModel);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== Activity.RESULT_OK){
            if(requestCode==currencyRequestCode){
                if(data!=null){
                    selected=data.getStringExtra("currency");
                }

            }

            if(requestCode==selectCardRequest){
                if(data!=null){
                  int  postion=data.getIntExtra("cardPostion",0);
                  sp_select_card.setSelection(postion);
                }

            }
            updateUi();
        }
    }

    @Override
    public void getCardOnSuccess(MyCardModel myCardModel) {
        cardDetailsList.clear();
        CardDetail cardDetail= new CardDetail();
        cardDetail.setPfsToken("");
        cardDetailsList.add(cardDetail);
        cardDetailsList.addAll(myCardModel.getCardDetails());

        sp_select_card.setOnItemSelectedListener(this);
    }

    @Override
    public void getCardOnError() {
        sp_select_card.setOnItemSelectedListener(this);
    }

    @Override
    public void getCardNoInternet() {
        sp_select_card.setOnItemSelectedListener(this);
    }

    @Override
    public void topUpOnSuccess(TopupWallet model) {
        Log.e("Topup","topUpOnSuccess") ;
        String tmpAmount=et_price.getText().toString();
        if(tmpAmount.equals("")){
            tmpAmount="0.00";
        }
        double amount= Double.parseDouble(tmpAmount);
        Bundle bundle=new Bundle();
        bundle.putString("amount", String.valueOf(amount));
        bundle.putString("currencySymbol",currencySymbol);

        Intent intent= new Intent(this, PaymentActivity.class);
        intent.putExtra("data", bundle);
        intent.putExtra("from", "topup");
        intent.putExtra("url", model.getResponse().getPaymentUrl());
        intent.putExtra("referenceCode", model.getResponse().getReferenceCode());
        startActivity(intent);




    }

    @Override
    public void topUpOnError() {
        Log.e("Topup","topUpOnError") ;
    }

    @Override
    public void topUpNoInternet() {
        Log.e("Topup","topUpNoInternet") ;
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        selectedCardPostion=pos;
        if(selectedCardPostion==0){
            txt_selected_card.setText(getResources().getString(R.string.add_topup_msg));
        }else {
            txt_selected_card.setText(cardDetailsList.get(selectedCardPostion).getCardNumber());
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
