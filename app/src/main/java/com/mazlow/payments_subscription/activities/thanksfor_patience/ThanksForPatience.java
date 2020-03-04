package com.mazlow.payments_subscription.activities.thanksfor_patience;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.Mazlow.R;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.Prefs;
import com.mazlow.login.model.LoginResponseModel;
import com.mazlow.onfido.verification_identity.GetProfileImaplentation;
import com.mazlow.onfido.verification_identity.GetProfileView;
import com.mazlow.payments_subscription.activities.subcription_fee.SubCriptionFeeView;
import com.mazlow.payments_subscription.activities.subcription_fee.SubCriptionFeesImplementation;
import com.mazlow.payments_subscription.activities.thanksfor_patience.model.SubcriptionResponsemodel;

import okhttp3.ResponseBody;

public class ThanksForPatience extends AppCompatActivity implements GetProfileView,GetSubcriptionView, SubCriptionFeeView {

    GetProfileImaplentation getProfileImaplentation;
    GetSubriptionImplementation getSubriptionImplementation;
    SubCriptionFeesImplementation subCriptionFeesImplementation;
    Prefs prefs;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks_for_patience);
        initView();
        checkSubcription_id();
    }

    private void initView() {
        prefs = new Prefs(this);
        token=prefs.getString(Bean.ACCESS_TOKEN,"");
    }

    private void checkSubcription_id() {
        getProfileImaplentation =new GetProfileImaplentation(this,this);
        getProfileImaplentation.onGetProfile(token);
    }


    @Override
    public void Sucess(LoginResponseModel loginResponseModel) {
        if (loginResponseModel.getUserInfo().getSubscriptionId().equals(""))
        {
            if (loginResponseModel.getUserInfo().getCardHolderId().equals(""))
            getSubcription();
            else
            {
                checkSubcription_id();
            }

        }
    }

    private void getSubcription() {
        getSubriptionImplementation =new GetSubriptionImplementation(this,this);
        getSubriptionImplementation.getsubCription(token);
    }

    @Override
    public void error(String error) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void noInternet(String tag) {

    }

    @Override
    public void onSuccessSubcription(SubcriptionResponsemodel response) {

        if (response.getMessage().equals("cardHolderId is not allowed to be empty"))
        {
            checkSubcription_id();
        }
        else
        {
            hitCharge();
        }
    }

    private void hitCharge() {

        subCriptionFeesImplementation =new SubCriptionFeesImplementation(this,this);
        subCriptionFeesImplementation.hitCharge(token,"","","");
    }

    @Override
    public void onErrorSubcription(String error) {

    }

    @Override
    public void noInternetSubcription(String tag) {

    }

    @Override
    public void onSuccessSubrionfee(ResponseBody responseBody) {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onNetworkSubcription(String tag) {

    }
}

