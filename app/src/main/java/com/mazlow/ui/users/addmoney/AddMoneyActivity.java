package com.mazlow.ui.users.addmoney;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Mazlow.R;
import com.google.gson.Gson;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.Prefs;
import com.mazlow.login.model.LoginResponseModel;
import com.mazlow.ui.users.addmoney.adapter.CustomArrayAdapter;
import com.mazlow.ui.users.addmoney.models.CardDetail;
import com.mazlow.ui.users.addmoney.models.MyCardModel;
import com.mazlow.ui.users.choosecurrency.ChooseCurrencyActivity;
import com.mazlow.ui.users.dashboard.fragments.models.TotalBalanceModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMoneyActivity extends AppCompatActivity implements AddMoneyActivityView{
    @BindView(R.id.imageViewBack)
    ImageView imageViewBack;

    @BindView(R.id.img_flag)
    ImageView img_flag;

    @BindView(R.id.txt_title)
    TextView txt_title;

    @BindView(R.id.txt_balance)
    TextView txt_balance;
    @BindView(R.id.ll_select_currency)
    LinearLayout ll_select_currency;
    @BindView(R.id.sp_select_card)
    Spinner sp_select_card;
    CustomArrayAdapter spinnerAdapter;

    int currencyRequestCode=1256;
    Prefs prefs;
    String token = "";

    LoginResponseModel profileData;
    ArrayList<TotalBalanceModel> balanceModelArrayList = new ArrayList<>();
    String selected = "GBP";
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

        sp_select_card.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Toast.makeText(AddMoneyActivity.this, "", Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.imageViewBack)
    public void backpress() {
        onBackPressed();
    }
    @OnClick(R.id.ll_select_currency)
    public void selectCurrency() {
        Intent intent=new Intent(this, ChooseCurrencyActivity.class);
        startActivityForResult(intent,currencyRequestCode);
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
            updateUi();
        }
    }

    @Override
    public void getCardOnSuccess(MyCardModel myCardModel) {
        cardDetailsList.clear();
        CardDetail cardDetail= new CardDetail();
        cardDetail.setPfsToken("");
        cardDetailsList.add(cardDetail);
        cardDetailsList.addAll( myCardModel.getCardDetails());


        spinnerAdapter.notifyDataSetChanged();

    }

    @Override
    public void getCardOnError() {

    }

    @Override
    public void getCardNoInternet() {

    }
}
