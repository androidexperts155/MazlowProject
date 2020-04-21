package com.mazlow.login;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.Mazlow.R;
import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.mazlow.adduserdetails.FirstPageActivity;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.M;
import com.mazlow.customclasses.Prefs;
import com.mazlow.login.model.LoginResponseModel;
import com.mazlow.onfido.activites.FourthSignupActivity;

public class PassCodeActivity extends AppCompatActivity implements LoginView {

    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;
    public static final String TAG = "PinLockView";
    String phone,countycode;
    LoginPresenterImple loginPresenterImple;
    Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_code);

        initView();
        getDataFromIntent();

    }

    private void initView()
      {
          prefs = new Prefs(PassCodeActivity.this);
          PinLockListener mPinLockListener = new PinLockListener() {
            @Override
            public void onComplete(String pin) {

                if(M.isNetworkAvailable(PassCodeActivity.this))
                    Login(phone,countycode,pin);
                else {
                    M.networkDialog(PassCodeActivity.this);
                }

            }
            @Override
            public void onEmpty() {
                Log.d(TAG, "Pin empty");
            }
            @Override
            public void onPinChange(int pinLength, String intermediatePin) {

            }
        };
        mPinLockView = findViewById(R.id.pin_lock_view);
        mIndicatorDots = findViewById(R.id.indicator_dots);
        mPinLockView.attachIndicatorDots(mIndicatorDots);
        mPinLockView.setPinLockListener(mPinLockListener);
        mPinLockView.setPinLength(4);
        mPinLockView.setTextColor(ContextCompat.getColor(this, R.color.textcolor));
    }

    private void getDataFromIntent() {
        phone = getIntent().getStringExtra(Bean.MOBILE_NUMBER);
        countycode = getIntent().getStringExtra(Bean.COUNTRYCODE);
    }

    private void Login(String phone, String countycode, String pin) {
        loginPresenterImple=new LoginPresenterImple(this,this);
        loginPresenterImple.doLogin(phone,countycode,pin);
    }

    private void redirectuserToNextScreen(LoginResponseModel loginResponseModel) {
        prefs.setString(Bean.ACCESS_TOKEN, loginResponseModel.getToken());
        prefs.setString(Bean.FIRST_NAME,loginResponseModel.getUserInfo().getFirstName());
        prefs.setString(Bean.LAST_NAME,loginResponseModel.getUserInfo().getLastName());
        prefs.setString(Bean.EMAIL_ADDRESS,loginResponseModel.getUserInfo().getEmail());
        prefs.setString(Bean.COUNTRY,loginResponseModel.getUserInfo().getCountry());
        prefs.setString(Bean.CITY,loginResponseModel.getUserInfo().getCity());
        prefs.setString(Bean.COUNTRYCODE,loginResponseModel.getUserInfo().getCountryCode());
        prefs.setString(Bean.POSTALCODE,loginResponseModel.getUserInfo().getPostalCode());
        prefs.setString(Bean.ADDRESS,loginResponseModel.getUserInfo().getAddress());
        prefs.setString(Bean.ADDRESS_LINE2,loginResponseModel.getUserInfo().getAddressline2());
        prefs.setString(Bean.DATEOF_BIRTH,loginResponseModel.getUserInfo().getDob());
        Intent i=new Intent(getApplicationContext(), FirstPageActivity.class);
        startActivity(i);
        finish();

    }

    @Override
    public void onSuccess(LoginResponseModel LoginResponseModel) {

        String type=prefs.getString("type","");

        if (type.equals("1"))
        {
            fourthActivity(LoginResponseModel);
        }
        else {
            redirectuserToNextScreen(LoginResponseModel);
        }
//        redirectuserToNextScreen(LoginResponseModel);

    }

    private void fourthActivity(LoginResponseModel loginResponseModel) {
        prefs.setString(Bean.ACCESS_TOKEN, loginResponseModel.getToken());
        prefs.setString(Bean.FIRST_NAME,loginResponseModel.getUserInfo().getFirstName());
        prefs.setString(Bean.LAST_NAME,loginResponseModel.getUserInfo().getLastName());
        prefs.setString(Bean.EMAIL_ADDRESS,loginResponseModel.getUserInfo().getEmail());
        prefs.setString(Bean.COUNTRY,loginResponseModel.getUserInfo().getCountry());
        prefs.setString(Bean.CITY,loginResponseModel.getUserInfo().getCity());
        prefs.setString(Bean.COUNTRYCODE,loginResponseModel.getUserInfo().getCountryCode());
        prefs.setString(Bean.POSTALCODE,loginResponseModel.getUserInfo().getPostalCode());
        prefs.setString(Bean.ADDRESS,loginResponseModel.getUserInfo().getAddress());
        prefs.setString(Bean.ADDRESS_LINE2,loginResponseModel.getUserInfo().getAddressline2());
        prefs.setString(Bean.DATEOF_BIRTH,loginResponseModel.getUserInfo().getDob());
        Intent i=new Intent(getApplicationContext(), FourthSignupActivity.class);
        startActivity(i);
    }

    @Override
    public void onError(String error) {
      M.showToast(PassCodeActivity.this,error);
    }

    @Override
    public void noInternet(String tag) {
        M.networkDialog(this);
    }
}
