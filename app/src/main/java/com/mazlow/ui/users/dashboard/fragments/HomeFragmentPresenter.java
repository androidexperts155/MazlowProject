package com.mazlow.ui.users.dashboard.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;

import com.google.gson.JsonElement;
import com.mazlow.Networking.RestApiClientAuth;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.customclasses.M;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragmentPresenter implements HomePresenter {
    Activity activity;
    Dialog dialog;
    HomeFragmentView homeFragmentView;


    public HomeFragmentPresenter(Activity activity, HomeFragmentView homeFragmentView) {
        this.activity = activity;
        this.homeFragmentView = homeFragmentView;
        dialog = M.showloader(activity, "", false, false);
    }





    @Override
    public void updateStatement(String access_token) {

        if (M.isNetworkAvailable(activity)) {

            RestApiInterface apiInterface = RestApiClientAuth.Retrofit().create(RestApiInterface.class);
            Call<JsonElement> call = apiInterface.updateStatement(access_token);
            call.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                    if (response.isSuccessful()) {
                        try {
                            JSONObject jsonObject=new JSONObject(response.body().toString());
                           if(jsonObject.has("success")){

                               if(jsonObject.optBoolean("success")){
                                   homeFragmentView.getUpdateStatementSuccess();
                               }else {
                                   homeFragmentView.getUpdateStatementError();

                               }

                           }else{
                               homeFragmentView.getUpdateStatementError();
                           }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    } else {
                        homeFragmentView.getUpdateStatementError();
                    }
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    Log.e("error", String.valueOf(t));
                    homeFragmentView.getUpdateStatementError();
                }
            });
        }
        else {
            homeFragmentView.getUpdateStatementNoInternet();

        }
    }
}
