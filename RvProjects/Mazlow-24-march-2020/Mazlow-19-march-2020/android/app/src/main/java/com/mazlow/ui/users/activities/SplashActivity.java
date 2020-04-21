package com.mazlow.ui.users.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.Mazlow.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        splash();
    }

    private void splash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                    Intent intent = new Intent(SplashActivity.this, DashBoardActivity.class);
//                    intent.putExtra("back", "dashboard");
                    startActivity(intent);
//                    finish();
                }
        }, 3000);
    }
}
