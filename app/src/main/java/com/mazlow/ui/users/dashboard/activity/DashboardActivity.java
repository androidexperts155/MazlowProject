package com.mazlow.ui.users.dashboard.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.Mazlow.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.mazlow.ui.users.dashboard.adapter.TotalBalanceAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;


public class DashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.viewpager_total_balance)
    ViewPager viewpager_total_balance;
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.img_gif)
    ImageView img_gif;


    TotalBalanceAdapter totalBalanceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initBottomNavigationView();
        initTotalBalanceViewPager();

        Glide.with(this).load(R.drawable.gif_circle)

                .transform(new CircleCrop()) // or bitmapTransform, whichever compiles
                .into(img_gif);

    }


    private void initBottomNavigationView() {
        bottomNavigationView.getMenu().add(0, R.id.navigation_home, 0, "Home").setIcon(R.drawable.ic_home_unselected);
        bottomNavigationView.getMenu().add(1, R.id.navigation_payment, 1, "Payment").setIcon(R.drawable.ic_payment_unselected);
        bottomNavigationView.getMenu().add(2, R.id.navigation_wellbeing, 2, "Wellbeing").setIcon(R.drawable.ic_wellbeing_unselected);

        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
      setToDefault();
      switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                menuItem.setIcon(R.drawable.ic_home_selected);
                break;
            case R.id.navigation_payment:
                menuItem.setIcon(R.drawable.ic_payment_selected);
                break;
            case R.id.navigation_wellbeing:
                menuItem.setIcon(R.drawable.ic_wellbeing_selected);
                break;
        }
        return true;
    }
    private void setToDefault() {
        bottomNavigationView.getMenu().getItem(0).setIcon(R.drawable.ic_home_unselected);
        bottomNavigationView.getMenu().getItem(1).setIcon(R.drawable.ic_payment_unselected);
        bottomNavigationView.getMenu().getItem(2).setIcon(R.drawable.ic_wellbeing_unselected);

    }


    private void initTotalBalanceViewPager() {

        tab_layout.setupWithViewPager(viewpager_total_balance, true);
        totalBalanceAdapter =new TotalBalanceAdapter(this);
        viewpager_total_balance.setAdapter(totalBalanceAdapter);

    }

}
