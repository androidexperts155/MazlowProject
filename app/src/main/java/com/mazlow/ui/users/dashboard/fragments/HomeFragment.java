package com.mazlow.ui.users.dashboard.fragments;

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

import com.Mazlow.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.google.android.material.tabs.TabLayout;
import com.mazlow.ui.users.dashboard.adapter.TotalBalanceAdapter;
import com.mazlow.ui.users.dashboard.fragments.adapters.NextChallengesAdapter;
import com.mazlow.ui.users.dashboard.fragments.models.NextChallengesModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    @BindView(R.id.img_gif)
    ImageView img_gif;
    @BindView(R.id.rv_next_challenges)
    RecyclerView rv_next_challenges;
    @BindView(R.id.viewpager_total_balance)
    ViewPager viewpager_total_balance;
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;



    TotalBalanceAdapter totalBalanceAdapter;
    NextChallengesAdapter nextChallengesAdapter;
    ArrayList<NextChallengesModel> nextChallengesArrayList=new ArrayList<>();
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
    }

    private void init() {
        Glide.with(getActivity()).load(R.drawable.gif_circle)
                .transform(new CircleCrop())
                .into(img_gif);
        initChallengesRecycleView();
        initTotalBalanceViewPager();
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
        totalBalanceAdapter =new TotalBalanceAdapter(getActivity());
        viewpager_total_balance.setAdapter(totalBalanceAdapter);

    }
}
