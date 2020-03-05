package com.mazlow.ui.users.dashboard.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.mazlow.login.model.LoginResponseModel;
import com.mazlow.onfido.verification_identity.GetProfileImaplentation;
import com.mazlow.onfido.verification_identity.GetProfileView;
import com.mazlow.ui.users.addmoney.AddMoneyActivity;
import com.mazlow.ui.users.dashboard.adapter.TotalBalanceAdapter;
import com.mazlow.ui.users.dashboard.fragments.adapters.NextChallengesAdapter;
import com.mazlow.ui.users.dashboard.fragments.models.NextChallengesModel;
import com.mazlow.ui.users.dashboard.fragments.models.TotalBalanceModel;

import java.util.ArrayList;

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

    @BindView(R.id.rr_spent)
    RelativeLayout rr_spent;


    LoginResponseModel profileData;
    TotalBalanceAdapter totalBalanceAdapter;
    NextChallengesAdapter nextChallengesAdapter;
    ArrayList<NextChallengesModel> nextChallengesArrayList=new ArrayList<>();

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
            public void OnItemClicked(NextChallengesModel nextChallengesModel){

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



    private void updateui() {
        profileData= getSaveProfile();

        parseTotleBalance(profileData);



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

        String price= String.valueOf((item.balance/100))+"."+String.valueOf((item.balance%100));
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
