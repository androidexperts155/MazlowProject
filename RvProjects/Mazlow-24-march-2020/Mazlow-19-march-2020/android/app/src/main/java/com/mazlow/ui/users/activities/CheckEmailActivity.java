package com.mazlow.ui.users.activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.Mazlow.R;
import com.mazlow.customclasses.BaseActivity;
import com.mazlow.customclasses.Bean;

public class CheckEmailActivity extends BaseActivity implements View.OnClickListener {

    ImageView img_back;
    TextView textemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_email);

        String email=getIntent().getStringExtra(Bean.EMAIL_ADDRESS);
        inilize();
        textemail.setText("To change your account's connected mobile number click the link we have sent  to "+email+"  to verify your request. ");
        setListner();

    }



    private void setListner() {
        img_back.setOnClickListener(this);
    }

    private void inilize() {
        img_back =findViewById(R.id.img_back);
        textemail =findViewById(R.id.textemail);
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
