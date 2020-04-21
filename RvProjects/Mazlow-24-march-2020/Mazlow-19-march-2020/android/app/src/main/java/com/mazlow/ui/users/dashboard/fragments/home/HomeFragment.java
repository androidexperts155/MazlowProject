package com.mazlow.ui.users.dashboard.fragments.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Mazlow.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.Prefs;
import com.mazlow.login.model.DayChallenge;
import com.mazlow.login.model.LoginResponseModel;
import com.mazlow.onfido.verification_identity.GetProfileImaplentation;
import com.mazlow.onfido.verification_identity.GetProfileView;
import com.mazlow.ui.users.addmoney.AddMoneyActivity;
import com.mazlow.ui.users.dashboard.adapter.TotalBalanceAdapter;
import com.mazlow.ui.users.dashboard.fragments.home.adapters.NextChallengesAdapter;
import com.mazlow.ui.users.dashboard.fragments.home.models.TotalBalanceModel;
import com.mazlow.ui.users.dashboard.set_goals.SelectGoalActivity;
import com.mazlow.ui.users.selectbeneficiary.SelectBeneficiaryActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment implements HomeFragmentView, GetProfileView {

    @BindView(R.id.img_gif)
    ImageView img_gif;
    @BindView(R.id.rv_next_challenges)
    RecyclerView rv_next_challenges;
    @BindView(R.id.viewpager_total_balance)
    ViewPager viewpager_total_balance;
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.txt_total_balance_title)
    TextView txt_total_balance_title;
    @BindView(R.id.txt_today_spent)
    TextView txt_today_spent;
    @BindView(R.id.txt_exchange)
    TextView txt_exchange;
    @BindView(R.id.txt_transfer)
    TextView txt_transfer;
    @BindView(R.id.rr_spent)
    RelativeLayout rr_spent;
    @BindView(R.id.ll_activate_card)
    LinearLayout ll_activate_card;
    @BindView(R.id.ll_activad_card)
    LinearLayout ll_activad_card;
    @BindView(R.id.cv_set_gole)
    CardView cv_set_gole;
    @BindView(R.id.ll_daily_challenge)
    LinearLayout ll_daily_challenge;
    @BindView(R.id.txt_challenge_count)
    TextView txt_challenge_count;
    @BindView(R.id.bt_select_gole)
    Button bt_select_gole;
    @BindView(R.id.cv_transfer)
    CardView cv_transfer;
    LoginResponseModel profileData;
    TotalBalanceAdapter totalBalanceAdapter;
    NextChallengesAdapter nextChallengesAdapter;
    ArrayList<DayChallenge> nextChallengesArrayList=new ArrayList<>();
    HomeFragmentPresenter homeFragmentPresenter;
    GetProfileImaplentation getProfileImaplentation;
    Prefs prefs;
    String token="";
    ArrayList<TotalBalanceModel> balanceModelArrayList= new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

       init();
       updateui();
    }


    private void init() {
        prefs= new Prefs(getActivity());
        homeFragmentPresenter=new HomeFragmentPresenter(getActivity(),this);
        getProfileImaplentation =new GetProfileImaplentation(this,getActivity());

        token = prefs.getString(Bean.ACCESS_TOKEN, "");

        Glide.with(getActivity()).load(R.drawable.gif_circle)
                .transform(new CircleCrop())
                .into(img_gif);
        initChallengesRecycleView();
        initTotalBalanceViewPager();

        homeFragmentPresenter.updateStatement(token);
    }

    private void initChallengesRecycleView() {
        rv_next_challenges.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        rv_next_challenges.setNestedScrollingEnabled(true);
        nextChallengesAdapter=new NextChallengesAdapter(getActivity(), nextChallengesArrayList, new NextChallengesAdapter.AddItemClickListener() {
            @Override
            public void OnItemClicked(DayChallenge nextChallengesModel){

            }
        });
        rv_next_challenges.setAdapter(nextChallengesAdapter);
    }
    private void initTotalBalanceViewPager() {

        tab_layout.setupWithViewPager(viewpager_total_balance, true);
        totalBalanceAdapter =new TotalBalanceAdapter(getActivity(),balanceModelArrayList);
        viewpager_total_balance.setAdapter(totalBalanceAdapter);

        viewpager_total_balance.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TotalBalanceModel item = balanceModelArrayList.get(position);


                setTodaySpentData(item);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @OnClick(R.id.txt_topup)
    void openTopup(){
        startActivity(new Intent(getActivity(), AddMoneyActivity.class));
    }

    @OnClick(R.id.bt_select_gole)
    void selectGole(){
        startActivity(new Intent(getActivity(), SelectGoalActivity.class));
    }

    @OnClick(R.id.cv_transfer)
    void transfer(){
        startActivity(new Intent(getActivity(), SelectBeneficiaryActivity.class));
    }

    @Override
    public void getUpdateStatementSuccess() {
        getProfileImaplentation.onGetProfile(token);
    }

    @Override
    public void getUpdateStatementError() {
        String df="";
    }

    @Override
    public void getUpdateStatementNoInternet() {
        String df="";
    }

    @Override
    public void Sucess(LoginResponseModel loginResponseModel) {
        saveProfileData(loginResponseModel);

        profileData=loginResponseModel;
       parseTotleBalance(profileData);
    }



    @Override
    public void error(String error) {
        String df="";
    }

    @Override
    public void noInternet(String tag) {
        String df="";
    }

    private void saveProfileData(LoginResponseModel loginResponseModel) {
        Gson gson = new Gson();
        String prfiledata = gson.toJson(loginResponseModel);
        prefs.setString(Bean.PROFILE_DATA,prfiledata);
    }


    @Override
    public void onResume() {
        super.onResume();
//        homeFragmentPresenter.updateStatement(token);

    }

    private void updateui() {
        profileData= getSaveProfile();

        if (profileData!=null)
        {
            parseTotleBalance(profileData);
        }




        if(profileData!=null)
        {
            if (profileData.getUserInfo()!=null)
            {
                if(profileData.getUserInfo().getCardStatus().equals("1")){
                    ll_activate_card.setVisibility(View.GONE);
                    ll_activad_card.setVisibility(View.VISIBLE);

                }
                else {
                    ll_activate_card.setVisibility(View.VISIBLE);
                    ll_activad_card.setVisibility(View.GONE);
                }

                if(profileData.getIsGoalSet()){
                    cv_set_gole.setVisibility(View.GONE);
                    ll_daily_challenge.setVisibility(View.VISIBLE);
                }
                else{
                    cv_set_gole.setVisibility(View.VISIBLE);
                    ll_daily_challenge.setVisibility(View.GONE);
                }

                List<DayChallenge> tmpList=profileData.getDayChallenges();
                tmpList.remove(0);
                nextChallengesArrayList.addAll(tmpList);

                sortNextChallengesArrayList();
                txt_challenge_count.setText(nextChallengesArrayList.size()+"");
                nextChallengesAdapter.notifyDataSetChanged();
            }

        }




    }

    private void sortNextChallengesArrayList() {


        ArrayList<DayChallenge> tmp=new ArrayList<>();
        for(int i=0;i<nextChallengesArrayList.size();i++){
            DayChallenge item=nextChallengesArrayList.get(i);
            switch (item.getName()){
                case "Medidation":
                    tmp.add(item);
                    break;

                case "Steps":

                    tmp.add(item);
                    break;

                case "Savings Pot":

                    tmp.add(item);
                    break;

                case "Daily Budget":
                    tmp.add(item);
                    break;
                default:
                    tmp.add(item);
                    break;
            }


        }
        nextChallengesArrayList.clear();
        nextChallengesArrayList.addAll(tmp);

    }

    private LoginResponseModel getSaveProfile() {

      String data=  prefs.getString(Bean.PROFILE_DATA,"");

        return   new Gson().fromJson(data, LoginResponseModel.class);
    }


    private void parseTotleBalance(LoginResponseModel loginResponseModel) {
        if(loginResponseModel==null){
            return;
        }
        balanceModelArrayList.clear();

        TotalBalanceModel totalBalanceModel=new TotalBalanceModel();

        totalBalanceModel.balance=loginResponseModel.getUserInfo().getGBPBalance();
        totalBalanceModel.todaySpend=loginResponseModel.getTodaySpend().getTodaySpendGBP();
        totalBalanceModel.active=loginResponseModel.getUserInfo().getGBPWallet();
        totalBalanceModel.currency="GBP";
        totalBalanceModel.currencySymbol="£";

        balanceModelArrayList.add(totalBalanceModel);

        totalBalanceModel=new TotalBalanceModel();
        totalBalanceModel.balance=loginResponseModel.getUserInfo().getUSDBalance();
        totalBalanceModel.todaySpend=loginResponseModel.getTodaySpend().getTodaySpendUSD();
        totalBalanceModel.active=loginResponseModel.getUserInfo().getUSDWallet();
        totalBalanceModel.active=loginResponseModel.getUserInfo().getUSDWallet();
        totalBalanceModel.currency="USD";
        totalBalanceModel.currencySymbol="$";
        balanceModelArrayList.add(totalBalanceModel);

        totalBalanceModel=new TotalBalanceModel();
        totalBalanceModel.balance=loginResponseModel.getUserInfo().getEURBalance();
        totalBalanceModel.todaySpend=loginResponseModel.getTodaySpend().getTodaySpendEUR();
        totalBalanceModel.active=loginResponseModel.getUserInfo().getEURWallet();
        totalBalanceModel.currency="EUR";
        totalBalanceModel.currencySymbol="€";
        balanceModelArrayList.add(totalBalanceModel);




        totalBalanceAdapter.notifyDataSetChanged();

      setTodaySpentData(balanceModelArrayList.get(0));

    }

    private void setTodaySpentData(TotalBalanceModel item) {
        String tmpPrice = String.valueOf((item.balance/100))+"."+String.valueOf((item.balance%100));
        txt_exchange.setText(item.currencySymbol+" "+tmpPrice);
        txt_transfer.setText(item.currencySymbol+" "+tmpPrice);

        String price="";
        if(item.currency.equals("GBP")){
             price= String.valueOf((profileData.getTodaySpend().getTodaySpendGBP()/100))+"."+String.valueOf((profileData.getTodaySpend().getTodaySpendGBP()%100));

        }else   if(item.currency.equals("EUR")){
            price= String.valueOf((profileData.getTodaySpend().getTodaySpendEUR()/100))+"."+String.valueOf((profileData.getTodaySpend().getTodaySpendEUR()%100));

        }else   if(item.currency.equals("USD")){
            price= String.valueOf((profileData.getTodaySpend().getTodaySpendUSD()/100))+"."+String.valueOf((profileData.getTodaySpend().getTodaySpendUSD()%100));

        }


        txt_today_spent.setText(item.currencySymbol+" "+price);
        if(item.active){
            rr_spent.setAlpha(1);
            txt_total_balance_title.setAlpha(1);

        }else {
            rr_spent.setAlpha(0.5f);
            txt_total_balance_title.setAlpha(0.5f);
        }
    }

}
