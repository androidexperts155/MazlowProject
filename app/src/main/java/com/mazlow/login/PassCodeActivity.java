package com.mazlow.login;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.Mazlow.R;
import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.mazlow.Networking.RestApiClientAuth;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.M;
import com.mazlow.signup.models.SignupResponseModel;
import com.mazlow.ui.users.activities.SignupPage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassCodeActivity extends AppCompatActivity implements LoginView {

    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;
    public static final String TAG = "PinLockView";
    String phone,countycode;
    LoginPresenterImple loginPresenterImple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_code);
        initView();
        getDataFromIntent();
    }

    private void initView()
    {
        PinLockListener mPinLockListener = new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                Login(phone,countycode,pin);
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

    @Override
    public void onSuccess(SignupResponseModel signupResponseModel) {
        redirectuserToNextScreen();
    }

    private void redirectuserToNextScreen() {
        Intent i=new Intent(getApplicationContext(),SignupPage.class);
        startActivity(i);
        finish();
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
