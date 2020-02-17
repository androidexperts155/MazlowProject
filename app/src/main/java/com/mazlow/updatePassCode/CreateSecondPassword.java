package com.mazlow.updatePassCode;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.Mazlow.R;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.M;
import com.mazlow.customclasses.Prefs;
import com.mazlow.signup.models.SignupResponseModel;
import com.mazlow.ui.users.activities.SignupPage;

public class CreateSecondPassword extends AppCompatActivity implements View.OnClickListener,PassCodeView {


    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;
    public static final String TAG = "PinLockView";
    TextView textView;
    LinearLayout  toolbarlayout;
    ImageView img_back;
    String firstcoode,access_token;
    Prefs prefs;
    Dialog dialog;
    PasscodePresenterImple passcodePresenterImple;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_first_otp);
        initView();
        setListner();
        getDataFromIntent();

    }

    private void getDataFromIntent() {
        firstcoode = getIntent().getStringExtra(Bean.PASSCODE);
        access_token = prefs.getString(Bean.ACCESS_TOKEN,"");
    }

    private void setListner() {
        img_back.setOnClickListener(this);
    }

    private void initView() {
        PinLockListener mPinLockListener = new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                Log.d(TAG, "Pin complete: " + pin);
                clickPerform(pin);
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

        img_back = findViewById(R.id.img_back);
        prefs= new Prefs(this);
        dialog = M.showloader(this, "", false, false);
        textView = findViewById(R.id.lasttwodigits);
        toolbarlayout = findViewById(R.id.toolbarlayout);
        toolbarlayout.setVisibility(View.VISIBLE);
        textView.setText(getResources().getString(R.string.reenterpasscode));
    }

    private void clickPerform(String pin) {
        if (pin.equals(firstcoode))
        updatepasscode(pin);
        else
            M.showToast(CreateSecondPassword.this,getResources().getString(R.string.codenotmatched));
    }

    private void updatepasscode(String pin) {
        passcodePresenterImple = new PasscodePresenterImple(this,this);
        passcodePresenterImple.dopasscode(access_token,pin);
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

    @Override
    public void onSuccess(SignupResponseModel signupResponseModel) {
        redirectuserToNextScreen();
    }

    private void redirectuserToNextScreen() {
        startActivity(new Intent(CreateSecondPassword.this, SignupPage.class));
    }

    @Override
    public void onError(String error) {
        M.showToast(CreateSecondPassword.this,error);
    }

    @Override
    public void noInternet(String tag) {
        M.networkDialog(this);
    }
}