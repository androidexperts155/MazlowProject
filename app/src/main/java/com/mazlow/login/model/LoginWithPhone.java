package com.mazlow.login.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Mazlow.R;
import com.hbb20.CountryCodePicker;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.M;
import com.mazlow.login.LoginPresenterImple;
import com.mazlow.login.LoginView;
import com.mazlow.login.PassCodeActivity;


import butterknife.BindView;

public class LoginWithPhone extends AppCompatActivity implements View.OnClickListener, LoginView {
    EditText oneEditText;
    @BindView(R.id.otp_2)
    EditText twoEditText;
    @BindView(R.id.otp_3)
    EditText threeEditText;
    @BindView(R.id.otp_4)
    EditText fourEditText;
    EditText fiveEditText;
    EditText sixEditText;
    @BindView(R.id.one)
    LinearLayout one;
    @BindView(R.id.two)
    LinearLayout two;
    @BindView(R.id.three)
    LinearLayout three;
    @BindView(R.id.four)
    LinearLayout four;
    @BindView(R.id.five)
    LinearLayout five;
    @BindView(R.id.six)
    LinearLayout six;
    @BindView(R.id.seven)
    LinearLayout seven;
    @BindView(R.id.eight)
    LinearLayout eight;
    @BindView(R.id.nine)
    LinearLayout nine;
    @BindView(R.id.zero)
    LinearLayout zero;
    @BindView(R.id.clear)
    LinearLayout clear;
    @BindView(R.id.continue_btn)
    Button submitlay;
    @BindView(R.id.resendotp)
    TextView resendotp;
    String text;
    Boolean check = false;
    TextView lastwtodigits;
    ImageView ic_arrow_back;
    String phonenumber,country_code;
    CountryCodePicker ccp;
    String TYPE;
    LoginPresenterImple loginPresenterImple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_phone);
        oneEditText = findViewById(R.id.et_phone);
        initilize();
        setListeners();


    }

    private void setListeners() {
        submitlay.setOnClickListener(this);
        resendotp.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        clear.setOnClickListener(this);
        ic_arrow_back.setOnClickListener(this);
    }

    private void initilize() {


        ccp=findViewById(R.id.cpp);
        TYPE = getIntent().getStringExtra(Bean.LOGINTYPE);

        lastwtodigits = findViewById(R.id.lasttwodigits);
        ic_arrow_back = findViewById(R.id.img_back);
        submitlay = findViewById(R.id.continue_btn);
        resendotp = findViewById(R.id.resendotp);

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        clear = findViewById(R.id.clear);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.continue_btn:
                SignupApi();
                break;
            case R.id.one:
                oneEditText.append("1");
                break;
            case R.id.two:
                oneEditText.append("2");
                break;
            case R.id.three:
                oneEditText.append("3");
                break;
            case R.id.four:
                oneEditText.append("4");
                break;
            case R.id.five:
                oneEditText.append("5");
                break;
            case R.id.six:
                oneEditText.append("6");
                break;
            case R.id.seven:
                oneEditText.append("7");
                break;
            case R.id.eight:
                oneEditText.append("8");
                break;
            case R.id.nine:
                oneEditText.append("9");
                break;
            case R.id.zero:
                oneEditText.append("0");
                break;
            case R.id.clear:
                crealLastDigits();
                break;
            case R.id.resendotp:
                check = true;
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }
    private void SignupApi() {
        country_code="+"+ccp.getSelectedCountryCode().toString();
        phonenumber = oneEditText.getText().toString().trim();
        if (phonenumber.length()<10)
            M.showToast(this,getResources().getString(R.string.valid_phone));
        else
        {
            LoginApi();
        }
    }

    private void LoginApi() {
        loginPresenterImple=new LoginPresenterImple(this,this);
        loginPresenterImple.doLoginWithPhone(phonenumber,country_code);
    }


    private void crealLastDigits() {
        phonenumber = oneEditText.getText().toString().trim();
        if (phonenumber.length() > 0) {
            if (oneEditText.getText().toString().endsWith("-")) {
                String tmp = oneEditText.getText().delete(phonenumber.length() - 2, Integer.parseInt(phonenumber)).toString();
                oneEditText.setText(tmp);
            } else {
                oneEditText.getText().delete(oneEditText.length() - 1, phonenumber.length());
            }
        }
    }

    private void redirectuserToNextScreen() {
        Intent intent =new Intent(LoginWithPhone.this, PassCodeActivity.class);
        intent.putExtra(Bean.MOBILE_NUMBER,phonenumber);
        intent.putExtra(Bean.COUNTRYCODE,country_code);
        startActivity(intent);
    }


    @Override
    public void onSuccess(LoginResponseModel LoginResponseModel) {
        redirectuserToNextScreen();
    }

    @Override
    public void onError(String error) {
        M.showToast(this,error);
    }


    @Override
    public void noInternet(String tag) {
        M.networkDialog(this);
    }

    class GenericTextWatcher implements TextWatcher {
        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            text = editable.toString();
            switch (view.getId()) {
                case R.id.otp_1:
                    if (text.length() == 1)
                        twoEditText.requestFocus();

                    if (text.length() == 0)
                        oneEditText.requestFocus();
                    break;

                case R.id.otp_2:
                    if (text.length() == 1)
                        threeEditText.requestFocus();

                    if (text.length() == 0)
                        oneEditText.requestFocus();
                    break;

                case R.id.otp_3:
                    if (text.length() == 1)
                        fourEditText.requestFocus();

                    if (text.length() == 0)
                        twoEditText.requestFocus();
                    break;

                case R.id.otp_4:

                    if (text.length() == 1)
                        fiveEditText.requestFocus();

                    if (text.length() == 0)
                        fourEditText.requestFocus();
                    break;

                case R.id.otp_5:

                    if (text.length() == 1)
                        sixEditText.requestFocus();

                    if (text.length() == 0)
                        fiveEditText.requestFocus();
                    break;

                case R.id.otp_6:
                    if (text.length() == 0) {
                        fiveEditText.requestFocus();
                    }
                    break;
            }
        }
        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

    }

}