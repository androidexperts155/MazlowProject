package com.mazlow.adduserdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.Mazlow.R;
import com.mazlow.onfido.activites.FourthSignupActivity;

public class PrivacyPolicyActivity extends AppCompatActivity implements View.OnClickListener {

    Button acceptbtn;
    TextView  textprivacyplociy;
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        ids();
        setListner();

    }
    private void ids() {
        acceptbtn = findViewById(R.id.acceptbtn);
        img_back = findViewById(R.id.img_back);
        textprivacyplociy = findViewById(R.id.textprivacyplociy);
        textprivacyplociy.setText(getResources().getString(R.string.stylish_text));
    }
    private void setListner() {
        acceptbtn.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.acceptbtn:
                Intent intent =new Intent(PrivacyPolicyActivity.this, FourthSignupActivity.class);
                startActivity(intent);
                break;
            case R.id.img_back:

                finish();
                break;
        }
    }
}
