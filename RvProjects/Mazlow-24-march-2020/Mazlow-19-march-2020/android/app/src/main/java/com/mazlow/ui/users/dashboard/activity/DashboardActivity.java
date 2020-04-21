package com.mazlow.ui.users.dashboard.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.Mazlow.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mazlow.customclasses.KKViewPager;
import com.mazlow.payments_subscription.adapter.CardsAdapter;
import com.mazlow.ui.users.dashboard.fragments.home.HomeFragment;
import com.mazlow.ui.users.dashboard.fragments.payments.PaymentFragment;
import com.mazlow.ui.users.dashboard.notification.NotificationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.img_nav_bt)
    ImageView img_nav_bt;
    @BindView(R.id.img_user)
    ImageView img_user;
    @BindView(R.id.img_nav_close)
    ImageView img_nav_close;
    @BindView(R.id.rr_menu)
    RelativeLayout rr_menu;
    @BindView(R.id.rr_screen)
    RelativeLayout rr_screen;
    @BindView(R.id.bottomshet_click)
    ImageView bottomshet_click;
    @BindView(R.id.click_notification)
    ImageView click_notification;


    int screenWidth = 1280;
    int screenSpace = 200;
    boolean isOpen = false;
    int navAnimationDuration = 300;
    KKViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        ButterKnife.bind(this);
        init();
    }

    private void init() {


        initBottomNavigationView();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels ;
        screenSpace= (int) (screenWidth*0.15);
        rr_menu.animate().translationX(-screenWidth).setDuration(0).start();

        rr_menu.setPadding(screenSpace,0,0,0);



        Glide.with(this).load(R.drawable.ic_user_avtar_black)
                .transform(new CircleCrop())
                .into(img_user);

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
        Fragment fragment = null;
        setToDefault();
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                menuItem.setIcon(R.drawable.ic_home_selected);
                fragment = new HomeFragment();
                break;
            case R.id.navigation_payment:
                menuItem.setIcon(R.drawable.ic_payment_selected);
                fragment = new PaymentFragment();
                break;
            case R.id.navigation_wellbeing:
                menuItem.setIcon(R.drawable.ic_wellbeing_selected);
                fragment = new HomeFragment();
                break;
        }
        return loadFragment(fragment);
    }

    private void setToDefault() {
        bottomNavigationView.getMenu().getItem(0).setIcon(R.drawable.ic_home_unselected);
        bottomNavigationView.getMenu().getItem(1).setIcon(R.drawable.ic_payment_unselected);
        bottomNavigationView.getMenu().getItem(2).setIcon(R.drawable.ic_wellbeing_unselected);

    }

    @OnClick({R.id.img_nav_bt, R.id.img_nav_close})
    void toggleNavMenu() {
        if (isOpen) {
            isOpen=false;
            rr_menu.animate().translationX(-screenWidth).setDuration(navAnimationDuration).start();
            rr_screen.animate().translationX(0).setDuration(navAnimationDuration).start();
            rr_screen.animate().alpha(1f).setDuration(navAnimationDuration).start();
        }

        else {
            rr_menu.setVisibility(View.VISIBLE);
            isOpen=true;
            rr_menu.animate().translationX((screenWidth-screenWidth)-screenSpace).setDuration(navAnimationDuration).start();
            rr_screen.animate().translationX(screenWidth-screenSpace).setDuration(navAnimationDuration).start();
            rr_screen.animate().alpha(0.5f).setDuration(navAnimationDuration).start();
        }

    }

    @OnClick(R.id.bottomshet_click)
    public void showBottomSheetDialog() {
        final Dialog d = new BottomSheetDialog(this,R.style.BottomSheetDialog);
        d.setContentView(R.layout.home_bottom_sheet_dialog);
         viewPager = d.findViewById(R.id.viewPager);
        setUpViewPager();
        d.show();
    }

    @OnClick(R.id.click_notification)
    void clickNotification()
    {
        startActivity(new Intent(DashboardActivity.this, NotificationActivity.class));
    }

    private void setUpViewPager() {
        CardsAdapter adapter = new CardsAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}
