package com.mazlow.ui.users.activities;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Mazlow.R;
import com.mazlow.adduserdetails.FirstPageActivity;
import com.mazlow.customclasses.BaseActivity;
import com.mazlow.customclasses.Bean;
import com.mazlow.interfaces.PermissionListner;
import com.mazlow.login.model.LoginWithPhone;
import com.mazlow.signup.SignupActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    Button btn_login;
    Button  tv_join;
    boolean permissincheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findIds();
        setListener();
        askPermision();



    }
    private void askPermision() {
        checkMultiplePermisions(MainActivity.this, Manifest.permission.RECEIVE_SMS,Manifest.permission.READ_SMS, new PermissionListner() {
            @Override
            public void OnPermissionGranted() {
                permissincheck=true;
            }

            @Override
            public void OnPermsionDenied() {
                showSettingsDialog(MainActivity.this);
            }
        });
    }


    private void setListener() {
        btn_login.setOnClickListener(this);
        tv_join.setOnClickListener(this);
    }

    private void findIds() {
        btn_login = findViewById(R.id.btn_login);
        tv_join = findViewById(R.id.tv_join);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_login:
                intentTosignupScreen();
                break;
            case  R.id.tv_join:
                intentToWelcomeScreen();
                break;
        }
    }

    private void intentToWelcomeScreen() {
        Intent intent =new Intent(MainActivity.this, WelcomeActivity.class);
        startActivity(intent);
    }

    private void intentTosignupScreen() {
        Intent intent =new Intent(MainActivity.this, LoginWithPhone.class);
        intent.putExtra(Bean.LOGINTYPE,"1");
        startActivity(intent);

//        Intent intent =new Intent(MainActivity.this, FirstPageActivity.class);
//        intent.putExtra(Bean.LOGINTYPE,"1");
//        startActivity(intent);

    }
}
