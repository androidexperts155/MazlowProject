package com.mazlow.adduserdetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.Mazlow.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.hbb20.CountryCodePicker;
import com.mazlow.adduserdetails.model.UpdateUserDetails;
import com.mazlow.customclasses.BaseActivity;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.M;
import com.mazlow.customclasses.Prefs;
import com.mazlow.signup.models.SignupResponseModel;
import com.mazlow.signup.postalcode.PostalCodeImplementation;
import com.mazlow.signup.postalcode.model.PostalCodeResponse;
import com.mazlow.signup.postalcode.postalcodeView;


public class SecondPageActivity extends BaseActivity implements View.OnClickListener ,AddDetailsView, postalcodeView {

    Button continue_btn;
    String firstname,lastname,email, datebirth;
    EditText et_pstalcoe,et_streetand_number,et_address2,et_city;
    Spinner et_country;
    LinearLayout rootlayout;
    UpdateDetailPresenterImple updateDetailPresenterImple;
    Prefs prefs;
    String accesstoken;
    String refreshedToken;
    String country_code="";
    CountryCodePicker ccp;
    ImageView img_back;
    PostalCodeImplementation postalCodeImplementation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        findIds();
        initView();
        setListner();

        if (getIntent()!=null){
            getDataFromintent();
        }

        setData();
    }

    private void setData() {
        if (prefs.getString(Bean.FIRST_NAME," ")!=null)
        {
            et_city.setText(prefs.getString(Bean.CITY,""));
            et_streetand_number.setText(prefs.getString(Bean.ADDRESS,""));
            et_pstalcoe.setText(prefs.getString(Bean.POSTALCODE,""));
            et_address2.setText(prefs.getString(Bean.ADDRESS_LINE2,""));
            ccp.setCountryForNameCode(prefs.getString(Bean.COUNTRY,""));
        }
    }


    private void getDataFromintent() {
        firstname = getIntent().getStringExtra(Bean.FIRST_NAME);
        lastname = getIntent().getStringExtra(Bean.LAST_NAME);
        email = getIntent().getStringExtra(Bean.EMAIL_ADDRESS);
        datebirth = getIntent().getStringExtra(Bean.DATEOF_BIRTH);
        accesstoken= prefs.getString(Bean.ACCESS_TOKEN,"");

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                refreshedToken = instanceIdResult.getToken();
                Log.e("newToken",refreshedToken);

            }
        });
    }

    private void initView() {
        prefs= new Prefs(this);
        et_pstalcoe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (et_pstalcoe.getRight() - et_pstalcoe.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        {
                            if (!isEditTextEmpty(et_pstalcoe))
                            {
                                showSnackbar(rootlayout,getResources().getString(R.string.enterpostalcode),"");
                            }
                            else
                            {
                                ApiTogetAddress();
                            }
                        }
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void ApiTogetAddress() {
        postalCodeImplementation =new PostalCodeImplementation(this,this);
        postalCodeImplementation.dopostalSearch("c62df-c96c7-0f79a-0390b",et_pstalcoe.getText().toString());

    }

    private void setListner() {
        continue_btn.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    private void findIds() {
        continue_btn =findViewById(R.id.continue_btn);
        rootlayout = findViewById(R.id.rootlayout);
        et_country = findViewById(R.id.et_country);
        et_pstalcoe = findViewById(R.id.et_pstalcoe);
        et_streetand_number = findViewById(R.id.et_streetand_number);
        et_address2 = findViewById(R.id.et_address2);
        et_city = findViewById(R.id.et_city);
        ccp= findViewById(R.id.listcountry);
        img_back= findViewById(R.id.img_back);
    }

    private void callFragment() {
        Intent intent =new Intent(SecondPageActivity.this,PrivacyPolicyActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.continue_btn:
                validateData();
                break;
             case R.id.img_back:
                finish();
                break;
        }
    }

    private void validateData() {

        if (ccp.getSelectedCountryEnglishName()!=null)
        country_code=ccp.getDefaultCountryNameCode();
        else
            country_code=ccp.getDefaultCountryNameCode();



        if (country_code.equals(""))
            showSnackbar(rootlayout,getResources().getString(R.string.choosecountry),"");
        else if (!isEditTextEmpty(et_pstalcoe))
            showSnackbar(rootlayout,getResources().getString(R.string.enterpostalcode),"");
        else if (!isEditTextEmpty(et_streetand_number))
            showSnackbar(rootlayout,getResources().getString(R.string.enteremailandphone),"");
        else if (!isEditTextEmpty(et_city))
            showSnackbar(rootlayout,getResources().getString(R.string.entercity),"");
        else
            apiCall();
    }

    private void apiCall() {
        updateDetailPresenterImple =new UpdateDetailPresenterImple(this,this);
        String postalcode = et_pstalcoe.getText().toString();
        String address1 = et_streetand_number.getText().toString();
        String address2 = et_address2.getText().toString();
        String city = et_city.getText().toString();
        updateDetailPresenterImple.doUpdate(accesstoken,firstname,lastname,datebirth,email,"USA",postalcode,address1,address2,city,refreshedToken,Bean.DEVICETYPE);

    }

    @Override
    public void onSucess(UpdateUserDetails updateUserDetails) {
        callFragment();
        prefs.setString(Bean.ACCESS_TOKEN, accesstoken);
        prefs.setString(Bean.FIRST_NAME,firstname);
        prefs.setString(Bean.LAST_NAME,lastname);
        prefs.setString(Bean.EMAIL_ADDRESS,email);
        prefs.setString(Bean.COUNTRY,country_code);
        prefs.setString(Bean.DATEOF_BIRTH,datebirth);
        prefs.setString(Bean.CITY,et_city.getText().toString());
        prefs.setString(Bean.POSTALCODE,et_pstalcoe.getText().toString());
        prefs.setString(Bean.ADDRESS,et_streetand_number.getText().toString());
        prefs.setString(Bean.ADDRESS_LINE2,et_address2.getText().toString());
    }

    @Override
    public void onError(String error) {
        showToast(error);
    }
    @Override
    public void noInternet(String tag) {
        M.networkDialog(this);
    }

    @Override
    public void onSuccessPostal(PostalCodeResponse postalCodeResponse) {

        et_city.setText(postalCodeResponse.getTown());
        et_streetand_number.setText(postalCodeResponse.getThoroughfares().get(0).getDeliveryPoints().get(0).getBuildingNumber()+" "+postalCodeResponse.getThoroughfares().get(0).getThoroughfareName()+ ""+postalCodeResponse.getThoroughfares().get(0).getThoroughfareDescriptor());
    }

    @Override
    public void onErrorPostal(String error) {

        et_city.setText("");
        et_streetand_number.setText("");
    }

    @Override
    public void noInternetPostal(String tag) {
        M.networkDialog(this);
    }
}
