package com.mazlow.ui.users.activities;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.Mazlow.R;
import com.mazlow.ui.users.adapters.ViewPagerAdapter;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;
import com.rd.draw.data.Orientation;
import com.rd.draw.data.RtlMode;

public class WelcomeActivity extends AppCompatActivity {
    ViewPager viewPager;
    PageIndicatorView pageIndicatorView;
    private int[] imagesArray = {R.drawable.bg_splashimg, R.drawable.bg_splashimg, R.drawable.bg_splashimg,R.drawable.bg_splashimg};
    private String[] title_array = {"With great \n" +
            "wellbeing, \n" +
            "comes great\n" +
            "finance","Your full potential","Empowering Tools", "Community"};
    private String[] message_array={"","MasLife is not just a bank, we believe that our customers deserve more than just financial services. Our goal is to enable you to get the most out of life.","The MshLife app provides tools for managing your spending but also your wellbeing. It’s a personal mentor and one stop house for your financial, physical and mental wellbeing.","Community is at the centre of what MasLife does. Your MasLife card is not just a keycard to success, but to a likeminded community."};
    ViewPagerAdapter viewPagerAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_onboard);
        initView();
    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        pageIndicatorView = findViewById(R.id.pageIndicatorView);
        viewPagerAdapter = new ViewPagerAdapter(WelcomeActivity.this, imagesArray,title_array,message_array);
        viewPager.setAdapter(viewPagerAdapter);
        pageIndicatorView.setViewPager(viewPager);
        pageIndicatorView.setAnimationType(AnimationType.THIN_WORM);
        pageIndicatorView.setOrientation(Orientation.HORIZONTAL);
        pageIndicatorView.setRtlMode(RtlMode.Off);
        pageIndicatorView.setInteractiveAnimation(true);
        pageIndicatorView.setAutoVisibility(false);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
