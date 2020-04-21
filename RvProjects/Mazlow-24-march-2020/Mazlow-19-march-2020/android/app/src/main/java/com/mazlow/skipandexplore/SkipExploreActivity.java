package com.mazlow.skipandexplore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.Mazlow.R;
import com.mazlow.skipandexplore.fragments.MazlowFeatureFirstFragments;
import com.mazlow.skipandexplore.fragments.MezlowFetaureSecondFragment;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;
import com.rd.draw.data.Orientation;

public class SkipExploreActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager viewPager;
    PageIndicatorView pageIndicatorView;
    ImageView img_back;
    ScreenSlidePagerAdapter pagerAdapter;
    ImageView ic_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip_explore);

        findIds();
        initView();
    }
    private void initView() {
        viewPager.setAdapter(pagerAdapter);
        pageIndicatorView.setViewPager(viewPager);
        pageIndicatorView.setOrientation(Orientation.HORIZONTAL);
        ic_close.setOnClickListener(this);

    }

    private void findIds() {
        viewPager = findViewById(R.id.viewPager);
        ic_close = findViewById(R.id.ic_close);
        pageIndicatorView = findViewById(R.id.pageIndicatorView);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    MazlowFeatureFirstFragments tab1 = new MazlowFeatureFirstFragments();
                    return tab1;
                case 1:
                    MezlowFetaureSecondFragment tab3 = new MezlowFetaureSecondFragment();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ic_close:
                finish();
                break;

        }
    }
}


