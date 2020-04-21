package com.mazlow.ui.users.selectbeneficiary;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;

import com.google.gson.JsonElement;
import com.mazlow.Networking.RestApiClientAuth;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.customclasses.M;
import com.mazlow.ui.users.dashboard.fragments.home.HomeFragmentView;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectBeneficiaryPresenterImplementation implements SelectBeneficiaryPresenter {
    Activity activity;
    Dialog dialog;
    SelectBeneficiaryView selectBeneficiaryView;

    public SelectBeneficiaryPresenterImplementation(Activity activity, SelectBeneficiaryView selectBeneficiaryView) {
        this.activity = activity;
        this.selectBeneficiaryView = selectBeneficiaryView;
    }

    @Override
    public void getBeneficiaryList(String access_token) {
        if (M.isNetworkAvailable(activity)) {

            RestApiInterface apiInterface = RestApiClientAuth.Retrofit().create(RestApiInterface.class);
            Call<JsonElement> call = apiInterface.getBeneficiaries(access_token);
            call.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                    if (response.isSuccessful()) {
                        try {
                            JSONObject jsonObject=new JSONObject(response.body().toString());
                            if(jsonObject.has("success")){

                                if(jsonObject.optBoolean("success")){
                                    selectBeneficiaryView.getBeneficiarySuccess();
                                }else {
                                    selectBeneficiaryView.getBeneficiaryError();

                                }

                            }else{
                                selectBeneficiaryView.getBeneficiaryError();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    } else {
                        selectBeneficiaryView.getBeneficiaryError();
                    }
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    Log.e("error", String.valueOf(t));
                    selectBeneficiaryView.getBeneficiaryError();
                }
            });
        }
        else {
            selectBeneficiaryView.getBeneficiaryNoInternet();

        }
    }
}
