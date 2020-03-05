package com.mazlow.ui.users.addmoney;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;

import com.google.gson.JsonElement;
import com.mazlow.Networking.RestApiClientAuth;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.customclasses.M;
import com.mazlow.login.model.LoginResponseModel;
import com.mazlow.ui.users.addmoney.models.MyCardModel;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdMoneyPresenterImplementation implements AdMoneyPresenter {
    Activity activity;
    AddMoneyActivityView addMoneyActivityView;
    Dialog dialog;

    public AdMoneyPresenterImplementation(Activity activity, AddMoneyActivityView addMoneyActivityView) {
        this.activity = activity;
        this.addMoneyActivityView = addMoneyActivityView;
        dialog = M.showloader(activity, "", false, false);
    }

    @Override
    public void getCard(String access_token) {
        if (M.isNetworkAvailable(activity)) {
            dialog.show();
            RestApiInterface apiInterface = RestApiClientAuth.Retrofit().create(RestApiInterface.class);
            Call<MyCardModel> call = apiInterface.getCard(access_token);
            call.enqueue(new Callback<MyCardModel>() {
                @Override
                public void onResponse(Call<MyCardModel> call, Response<MyCardModel> response) {
                    dialog.dismiss();
                    if (response.isSuccessful()) {



                            if(response.body().getSuccess()){

                                    addMoneyActivityView.getCardOnSuccess(response.body());


                            }else{
                                addMoneyActivityView.getCardOnError();
                            }



                    } else {
                        addMoneyActivityView.getCardOnError();
                    }
                }

                @Override
                public void onFailure(Call<MyCardModel> call, Throwable t) {
                    dialog.dismiss();
                    Log.e("error", String.valueOf(t));
                    addMoneyActivityView.getCardOnError();
                }
            });
        }
        else {
            addMoneyActivityView.getCardNoInternet();

        }
    }
}
