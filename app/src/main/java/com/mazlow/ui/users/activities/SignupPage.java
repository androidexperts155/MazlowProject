package com.mazlow.ui.users.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.Mazlow.R;
import com.mazlow.ui.users.fragments.SecondFragmentSignup;
import com.rd.PageIndicatorView;

public class SignupPage extends AppCompatActivity implements View.OnClickListener {

    PageIndicatorView pageIndicatorView;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        findIds();
        initView();
        callFirstFragment();
    }

    private void callFirstFragment() {
        SecondFragmentSignup firstFragmentSignup=new SecondFragmentSignup();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content_frame, firstFragmentSignup);
        transaction.attach(firstFragmentSignup);
        transaction.commit();
    }

    private void initView() {
        fragmentManager = this.getSupportFragmentManager();
    }

    private void findIds() {
        pageIndicatorView = findViewById(R.id.pageIndicatorView);

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
