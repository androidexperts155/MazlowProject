package com.mazlow.otp;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.Mazlow.R;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.M;
import com.mazlow.customclasses.Prefs;
import com.mazlow.otp.models.CheckOtpResponseModel;
import com.mazlow.otp.resendotp.ResendOtpPresenterImplementation;
import com.mazlow.otp.resendotp.ResendOtpView;
import com.mazlow.signup.models.SignupResponseModel;
import com.mazlow.ui.users.activities.CreateFirstPassword;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;


public class OtpActivity extends AppCompatActivity implements View.OnClickListener,OtpView, ResendOtpView   {
    @BindView(R.id.otp_1)
    EditText oneEditText;
    @BindView(R.id.otp_2)
    EditText twoEditText;
    @BindView(R.id.otp_3)
    EditText threeEditText;
    @BindView(R.id.otp_4)
    EditText fourEditText;
//    @BindView(R.id.otp_5)
    EditText fiveEditText;
//    @BindView(R.id.otp_6)
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

    String getphoenumber,countrycode;
    String text;
    Boolean check = false;
    TextView lastwtodigits;

    ImageView img_back;
    String mOtp;
    String first,secondnum;
    Prefs prefs;

    OtpPresenterImple otpPresenterImple;
    ResendOtpPresenterImplementation resendOtpPresenterImplementation;
    public static OtpActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        instance = this;
        fiveEditText = findViewById(R.id.otp_5);
        sixEditText = findViewById(R.id.otp_6);
        img_back =findViewById(R.id.img_back);

        

        initilize();
        setListeners();

        oneEditText.addTextChangedListener(new GenericTextWatcher(oneEditText));
        twoEditText.addTextChangedListener(new GenericTextWatcher(twoEditText));
        threeEditText.addTextChangedListener(new GenericTextWatcher(threeEditText));
        fourEditText.addTextChangedListener(new GenericTextWatcher(fourEditText));
        fiveEditText.addTextChangedListener(new GenericTextWatcher(fiveEditText));
        sixEditText.addTextChangedListener(new GenericTextWatcher(sixEditText));


    }





    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    public String parseCode(String message) {
        Pattern p = Pattern.compile("\\b\\d{4}\\b");
        Matcher m = p.matcher(message);
        String code = "";
        while (m.find()) {
            code = m.group(0);
        }

        return code;
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
        img_back.setOnClickListener(this);
    }



    @Override
    protected void onResume() {
        super.onResume();
    }



    private void initilize() {

        prefs=new Prefs(this);
        lastwtodigits = findViewById(R.id.lasttwodigits);
        getphoenumber = getIntent().getStringExtra(Bean.MOBILE_NUMBER);
        countrycode = getIntent().getStringExtra(Bean.COUNTRYCODE);
        int firstnumber = Integer.parseInt(getphoenumber.substring(0, 1));
        first = String.valueOf(firstnumber);
        int firstsecon = Integer.parseInt(getphoenumber.substring(1, 2));
        secondnum = String.valueOf(firstsecon);
        lastwtodigits.setText(getResources().getString(R.string.entersixdigitscode)+first + secondnum );

        submitlay = findViewById(R.id.continue_btn);
        resendotp = findViewById(R.id.resendotp);
        oneEditText = findViewById(R.id.otp_1);
        twoEditText = findViewById(R.id.otp_2);
        threeEditText = findViewById(R.id.otp_3);
        fourEditText = findViewById(R.id.otp_4);


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

        oneEditText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.onTouchEvent(event);   // handle the event first
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {

                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);  // hide the soft keyboard
                    oneEditText.setCursorVisible(true); //This is to display cursor when upon onTouch of Edittext
                }
                return true;
            }
        });
        twoEditText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.onTouchEvent(event);   // handle the event first
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {

                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);  // hide the soft keyboard
                    twoEditText.setCursorVisible(true); //This is to display cursor when upon onTouch of Edittext
                }
                return true;
            }
        });
        threeEditText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.onTouchEvent(event);   // handle the event first
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {

                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);  // hide the soft keyboard
                    threeEditText.setCursorVisible(true); //This is to display cursor when upon onTouch of Edittext
                }
                return true;
            }
        });
        fourEditText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.onTouchEvent(event);   // handle the event first
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {

                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);  // hide the soft keyboard
                    fourEditText.setCursorVisible(true); //This is to display cursor when upon onTouch of Edittext
                }
                return true;
            }
        });

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.img_back:
                finish();
                break;
            case R.id.continue_btn:
                verifyOtp();
                break;
            case R.id.one:
                if (this.getCurrentFocus().getId() == oneEditText.getId()) {
                    // your view is in focus
                    oneEditText.setText("1");
                } else if (this.getCurrentFocus().getId() == twoEditText.getId()) {
                    // your view is in focus
                    twoEditText.setText("1");
                } else if (this.getCurrentFocus().getId() == threeEditText.getId()) {
                    // your view is in focus
                    threeEditText.setText("1");
                } else if (this.getCurrentFocus().getId() == fourEditText.getId()) {
                    // your view is in focus
                    fourEditText.setText("1");
                } else if (this.getCurrentFocus().getId() == fiveEditText.getId()) {
                    // your view is in focus
                    fiveEditText.setText("1");
                } else if (this.getCurrentFocus().getId() == sixEditText.getId()) {
                    // your view is in focus
                    sixEditText.setText("1");
                } else {

                }
                break;
            case R.id.two:
                if (this.getCurrentFocus().getId() == oneEditText.getId()) {
                    // your view is in focus
                    oneEditText.setText("2");
                } else if (this.getCurrentFocus().getId() == twoEditText.getId()) {
                    // your view is in focus
                    twoEditText.setText("2");
                } else if (this.getCurrentFocus().getId() == threeEditText.getId()) {
                    // your view is in focus
                    threeEditText.setText("2");
                } else if (this.getCurrentFocus().getId() == fourEditText.getId()) {
                    // your view is in focus
                    fourEditText.setText("2");
                } else if (this.getCurrentFocus().getId() == fiveEditText.getId()) {
                    // your view is in focus
                    fiveEditText.setText("2");
                } else if (this.getCurrentFocus().getId() == sixEditText.getId()) {
                    // your view is in focus
                    sixEditText.setText("2");
                } else {

                }
                break;
            case R.id.three:
                if (this.getCurrentFocus().getId() == oneEditText.getId()) {
                    // your view is in focus
                    oneEditText.setText("3");
                } else if (this.getCurrentFocus().getId() == twoEditText.getId()) {
                    // your view is in focus
                    twoEditText.setText("3");
                } else if (this.getCurrentFocus().getId() == threeEditText.getId()) {
                    // your view is in focus
                    threeEditText.setText("3");
                } else if (this.getCurrentFocus().getId() == fourEditText.getId()) {
                    // your view is in focus
                    fourEditText.setText("3");
                } else if (this.getCurrentFocus().getId() == fiveEditText.getId()) {
                    // your view is in focus
                    fiveEditText.setText("3");
                } else if (this.getCurrentFocus().getId() == sixEditText.getId()) {
                    // your view is in focus
                    sixEditText.setText("3");
                } else {

                }
                break;
            case R.id.four:
                if (this.getCurrentFocus().getId() == oneEditText.getId()) {
                    // your view is in focus
                    oneEditText.setText("4");
                } else if (this.getCurrentFocus().getId() == twoEditText.getId()) {
                    // your view is in focus
                    twoEditText.setText("4");
                } else if (this.getCurrentFocus().getId() == threeEditText.getId()) {
                    // your view is in focus
                    threeEditText.setText("4");
                } else if (this.getCurrentFocus().getId() == fourEditText.getId()) {
                    // your view is in focus
                    fourEditText.setText("4");
                } else if (this.getCurrentFocus().getId() == fiveEditText.getId()) {
                    // your view is in focus
                    fiveEditText.setText("4");
                } else if (this.getCurrentFocus().getId() == sixEditText.getId()) {
                    // your view is in focus
                    sixEditText.setText("4");
                } else {

                }
                break;
            case R.id.five:
                if (this.getCurrentFocus().getId() == oneEditText.getId()) {
                    // your view is in focus
                    oneEditText.setText("5");
                } else if (this.getCurrentFocus().getId() == twoEditText.getId()) {
                    // your view is in focus
                    twoEditText.setText("5");
                } else if (this.getCurrentFocus().getId() == threeEditText.getId()) {
                    // your view is in focus
                    threeEditText.setText("5");
                } else if (this.getCurrentFocus().getId() == fourEditText.getId()) {
                    // your view is in focus
                    fourEditText.setText("5");
                } else if (this.getCurrentFocus().getId() == fiveEditText.getId()) {
                    // your view is in focus
                    fiveEditText.setText("5");
                } else if (this.getCurrentFocus().getId() == sixEditText.getId()) {
                    // your view is in focus
                    sixEditText.setText("5");
                } else {

                }
                break;
            case R.id.six:
                if (this.getCurrentFocus().getId() == oneEditText.getId()) {
                    // your view is in focus
                    oneEditText.setText("6");
                } else if (this.getCurrentFocus().getId() == twoEditText.getId()) {
                    // your view is in focus
                    twoEditText.setText("6");
                } else if (this.getCurrentFocus().getId() == threeEditText.getId()) {
                    // your view is in focus
                    threeEditText.setText("6");
                } else if (this.getCurrentFocus().getId() == fourEditText.getId()) {
                    // your view is in focus
                    fourEditText.setText("6");
                } else if (this.getCurrentFocus().getId() == fiveEditText.getId()) {
                    // your view is in focus
                    fiveEditText.setText("6");
                } else if (this.getCurrentFocus().getId() == sixEditText.getId()) {
                    // your view is in focus
                    sixEditText.setText("6");
                } else {

                }
                break;
            case R.id.seven:
                if (this.getCurrentFocus().getId() == oneEditText.getId()) {
                    // your view is in focus
                    oneEditText.setText("7");
                } else if (this.getCurrentFocus().getId() == twoEditText.getId()) {
                    // your view is in focus
                    twoEditText.setText("7");
                } else if (this.getCurrentFocus().getId() == threeEditText.getId()) {
                    // your view is in focus
                    threeEditText.setText("7");
                } else if (this.getCurrentFocus().getId() == fourEditText.getId()) {
                    // your view is in focus
                    fourEditText.setText("7");
                } else if (this.getCurrentFocus().getId() == fiveEditText.getId()) {
                    // your view is in focus
                    fiveEditText.setText("7");
                } else if (this.getCurrentFocus().getId() == sixEditText.getId()) {
                    // your view is in focus
                    sixEditText.setText("7");
                } else {

                }
                break;
            case R.id.eight:
                if (this.getCurrentFocus().getId() == oneEditText.getId()) {
                    // your view is in focus
                    oneEditText.setText("8");
                } else if (this.getCurrentFocus().getId() == twoEditText.getId()) {
                    // your view is in focus
                    twoEditText.setText("8");
                } else if (this.getCurrentFocus().getId() == threeEditText.getId()) {
                    // your view is in focus
                    threeEditText.setText("8");
                } else if (this.getCurrentFocus().getId() == fourEditText.getId()) {
                    // your view is in focus
                    fourEditText.setText("8");
                } else if (this.getCurrentFocus().getId() == fiveEditText.getId()) {
                    // your view is in focus
                    fiveEditText.setText("8");
                } else if (this.getCurrentFocus().getId() == sixEditText.getId()) {
                    // your view is in focus
                    sixEditText.setText("8");
                } else {

                }

                break;
            case R.id.nine:
                if (this.getCurrentFocus().getId() == oneEditText.getId()) {
                    // your view is in focus
                    oneEditText.setText("9");
                } else if (this.getCurrentFocus().getId() == twoEditText.getId()) {
                    // your view is in focus
                    twoEditText.setText("9");
                } else if (this.getCurrentFocus().getId() == threeEditText.getId()) {
                    // your view is in focus
                    threeEditText.setText("9");
                } else if (this.getCurrentFocus().getId() == fourEditText.getId()) {
                    // your view is in focus
                    fourEditText.setText("9");
                } else if (this.getCurrentFocus().getId() == fiveEditText.getId()) {
                    // your view is in focus
                    fiveEditText.setText("9");
                } else if (this.getCurrentFocus().getId() == sixEditText.getId()) {
                    // your view is in focus
                    sixEditText.setText("9");
                } else {

                }

                break;
            case R.id.zero:
                if (this.getCurrentFocus().getId() == oneEditText.getId()) {
                    // your view is in focus
                    oneEditText.setText("0");
                } else if (this.getCurrentFocus().getId() == twoEditText.getId()) {
                    // your view is in focus
                    twoEditText.setText("0");
                } else if (this.getCurrentFocus().getId() == threeEditText.getId()) {
                    // your view is in focus
                    threeEditText.setText("0");
                } else if (this.getCurrentFocus().getId() == fourEditText.getId()) {
                    // your view is in focus
                    fourEditText.setText("0");
                } else if (this.getCurrentFocus().getId() == fiveEditText.getId()) {
                    // your view is in focus
                    fiveEditText.setText("0");
                } else if (this.getCurrentFocus().getId() == sixEditText.getId()) {
                    // your view is in focus
                    sixEditText.setText("0");
                } else {

                }

                break;
            case R.id.clear:
                int mValue = oneEditText.getId();
                int mVal = this.getCurrentFocus().getId();
                Log.i("", "onClick: " + mValue);
                Log.i("", "onClick: " + mVal);
                if (this.getCurrentFocus().getId() == oneEditText.getId()) {
                    // your view is in focus
                    oneEditText.setText("");
                    oneEditText.requestFocus();
                } else if (this.getCurrentFocus().getId() == twoEditText.getId()) {
                    // your view is in focus
                    if (twoEditText.getText().length() == 1) {
                        twoEditText.setText("");

                        oneEditText.requestFocus();
                    } else {
                        oneEditText.setText("");

                        oneEditText.requestFocus();
                    }

                } else if (this.getCurrentFocus().getId() == threeEditText.getId()) {
                    // your view is in focus
                    if (threeEditText.getText().length() == 1) {
                        threeEditText.setText("");

                        twoEditText.requestFocus();
                    } else {
                        twoEditText.setText("");
                        twoEditText.requestFocus();
                    }
                }
                else if (this.getCurrentFocus().getId() == fourEditText.getId()) {
                    // your view is in focus
                    if (fourEditText.getText().length() == 1) {
                        fourEditText.setText("");

                        threeEditText.requestFocus();
                    } else {
                        threeEditText.setText("");
                        threeEditText.requestFocus();
                    }
                }
                else if (this.getCurrentFocus().getId() == fiveEditText.getId()) {
                    // your view is in focus
                    if (fiveEditText.getText().length() == 1) {
                        fiveEditText.setText("");

                        fourEditText.requestFocus();
                    } else {
                        fourEditText.setText("");
                        fourEditText.requestFocus();
                    }
                }
                else if (this.getCurrentFocus().getId() == sixEditText.getId()) {
                    // your view is in focus
                    if (sixEditText.getText().length() == 1) {
                        sixEditText.setText("");

                        fiveEditText.requestFocus();
                    } else {
                        fiveEditText.setText("");
                        fiveEditText.requestFocus();
                    }
                }
                break;

            case R.id.resendotp: {
                resendOtpPresenterImplementation = new ResendOtpPresenterImplementation(this,this);
                resendOtpPresenterImplementation.ResendOtp(countrycode,getphoenumber);

            }
            break;
        }
    }

    private void verifyOtp() {
        mOtp = oneEditText.getText().toString() + "" + twoEditText.getText().toString() + "" + threeEditText.getText().toString() + "" + fourEditText.getText().toString() +""+fiveEditText.getText().toString() +""+ sixEditText.getText().toString();
        if (mOtp.equals("")) {
            Toast.makeText(OtpActivity.this, getResources().getString(R.string.valid_otp), Toast.LENGTH_SHORT).show();
        } else {
            if (mOtp.length() == 6) {
                otpPresenterImple =new OtpPresenterImple(this,this);
                otpPresenterImple.doOtp(getphoenumber,mOtp);

            } else {
                Toast.makeText(OtpActivity.this, getResources().getString(R.string.otp6_digit), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onSuccess(CheckOtpResponseModel checkOtpResponseModel) {
        redirectuserToNextScreen(checkOtpResponseModel);
    }

    private void redirectuserToNextScreen(CheckOtpResponseModel checkOtpResponseModel) {
        prefs.clearPref();
        M.showToast(OtpActivity.this,checkOtpResponseModel.getMessage());
        prefs.setString(Bean.ACCESS_TOKEN, checkOtpResponseModel.getToken());
        Intent intent =new Intent(OtpActivity.this, CreateFirstPassword.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(String error) {
        M.showToast(OtpActivity.this,error);
    }

    @Override
    public void noInternet(String tag) {
        M.networkDialog(this);
    }

    @Override
    public void onSuccessResend(SignupResponseModel checkOtpResponseModel) {
        M.showToast(OtpActivity.this,checkOtpResponseModel.getMessage());
    }

    @Override
    public void onErrorResend(String error) {
        M.showToast(OtpActivity.this,error);
    }

    @Override
    public void noInternetResend(String tag) {
        M.networkDialog(this);
    }

    public void parsingcode(String otp) {

        if (otp.length()==6)
        {
            int num = Integer.parseInt(otp);
            String number = String.valueOf(num);
            int first = Integer.parseInt(number.substring(0, 1));
            oneEditText.setText(String.valueOf(first));
            int second = Integer.parseInt(number.substring(1, 2));
            twoEditText.setText(String.valueOf(second));
            int third = Integer.parseInt(number.substring(2, 3));
            threeEditText.setText(String.valueOf(third));
            int four = Integer.parseInt(number.substring(3, 4));
            fourEditText.setText(String.valueOf(four));
            int five = Integer.parseInt(number.substring(4, 5));
            fiveEditText.setText(String.valueOf(five));
            int six = Integer.parseInt(number.substring(5, 6));
            sixEditText.setText(String.valueOf(six));

            mOtp=otp;
            otpPresenterImple =new OtpPresenterImple(this,this);
            otpPresenterImple.doOtp(getphoenumber,mOtp);
        }
        else
        {

        }

    }


    public class GenericTextWatcher implements TextWatcher {
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

                    if (text.length() == 0)
                        fiveEditText.requestFocus();

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