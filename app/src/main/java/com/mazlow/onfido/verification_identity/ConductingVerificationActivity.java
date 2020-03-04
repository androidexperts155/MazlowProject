package com.mazlow.onfido.verification_identity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.Mazlow.R;
import com.google.gson.JsonArray;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.M;
import com.mazlow.customclasses.Prefs;
import com.mazlow.login.model.LoginResponseModel;
import com.mazlow.onfido.OnfidoPresenterImplementation;
import com.mazlow.onfido.OnfidoView;
import com.mazlow.onfido.activites.FourthSignupActivity;
import com.mazlow.onfido.creating_check.CheckImplementationPresenter;
import com.mazlow.onfido.creating_check.CreatingCheckview;
import com.mazlow.onfido.model.OnFidoResponseModel;
import com.mazlow.onfido.verification_failed.OndidoVerificationFailed;
import com.mazlow.onfido.verification_success.VerificationSuccessed;
import com.mazlow.payments_subscription.activities.select_cards.SelectCards;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConductingVerificationActivity extends AppCompatActivity implements GetProfileView, OnfidoView, CreatingCheckview {

    GetProfileImaplentation getProfileImaplentation;
    String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1ZTU1MDRkZDNlNmQ5MTY4MmRjOTRmNWMiLCJpYXQiOjE1ODI4MDMxODZ9.rRZkIQW29Oit-cERCkNtkBAxaLnmjuGn7F_hp6aGMeY";
    CheckImplementationPresenter checkImplementationPresenter;
    String application_id="f0c2a104-14e3-4e1a-af28-90aff2034554";
    JSONArray jsonArray= new JSONArray();
    JSONObject jsonObject1 =new JSONObject();
    JSONObject Mainjson =new JSONObject();

    Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conducting_verification);
        inilize();
        getProfile();

    }

    private void inilize() {
        prefs =new Prefs(this);

        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("name","facial_similarity");
            jsonObject.put("variant","standard");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            jsonObject1.put("name","document");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonArray.put(jsonObject1);
        jsonArray.put(jsonObject);
        try {
            Mainjson.put("reports",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void getProfile() {
        getProfileImaplentation =new GetProfileImaplentation(this,this);
        getProfileImaplentation.onGetProfile(token);
    }

    @Override
    public void Sucess(LoginResponseModel loginResponseModel) {
        if (loginResponseModel.getUserInfo().getOnfido().getCheckId().equals(""))
        {
            checkImplementationPresenter =new CheckImplementationPresenter(this,this);
            checkImplementationPresenter.doCheck("Token token="+ Bean.ONFIDO_API_TOKEN,application_id,"express",Mainjson);
            Intent intent = new Intent(ConductingVerificationActivity.this, VerificationSuccessed.class);
            startActivity(intent);
            prefs.setString("type","1");
        }
        else
        {


        }

    }

    @Override
    public void Sucess(OnFidoResponseModel onFidoResponseModel) {

    }

    @Override
    public void error(String error) {

        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noInternet(String tag) {

        M.networkDialog(this);
    }

    @Override
    public void onsucessCheck() {

    }

    @Override
    public void onErrorCheck() {

    }

    @Override
    public void onInternet() {

    }
}
