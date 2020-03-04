package com.mazlow.payments_subscription.activities;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.Mazlow.R;
import com.mazlow.ui.users.dashboard.activity.DashboardActivity;
public class ThanksForPatience extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks_for_patience);
        timer();
    }

    private void timer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent= new Intent(ThanksForPatience.this, DashboardActivity.class);
                startActivity(intent);
            }
        }, 7000);
    }
    }

