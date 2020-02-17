package com.mazlow.ui.users.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.Mazlow.R;
import com.mazlow.ui.users.fragments.FirstFragmentSignup;
import com.mazlow.ui.users.fragments.SecondFragmentSignup;
import com.mazlow.ui.users.fragments.ThirdFragmentSignup;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;
import com.rd.draw.data.Orientation;


public class SignupPage extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewPager;
    PageIndicatorView pageIndicatorView;
    ImageView img_back;
    ScreenSlidePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        findIds();
        initView();
    }

    private void initView() {
        viewPager.setAdapter(pagerAdapter);
        pageIndicatorView.setViewPager(viewPager);
        pageIndicatorView.setAnimationType(AnimationType.THIN_WORM);
        pageIndicatorView.setOrientation(Orientation.HORIZONTAL);
        img_back.setOnClickListener(this);
    }

    private void findIds() {
        viewPager = findViewById(R.id.viewPager);
        pageIndicatorView = findViewById(R.id.pageIndicatorView);
        img_back = findViewById(R.id.img_back);
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
                    SecondFragmentSignup tab1 = new SecondFragmentSignup();
                    return tab1;
                case 1:
                    FirstFragmentSignup tab2 = new FirstFragmentSignup();
                    return tab2;
                case 2:
                    ThirdFragmentSignup tab3 = new ThirdFragmentSignup();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_back:
                finish();
                break;
        }
    }
}
