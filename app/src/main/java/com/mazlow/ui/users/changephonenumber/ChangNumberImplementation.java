package com.mazlow.ui.users.changephonenumber;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.mazlow.Networking.RestApiClientAuth;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.customclasses.M;
import com.mazlow.signup.models.SignupResponseModel;
import com.mazlow.ui.users.changephonenumber.model.ChangeNumberResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangNumberImplementation implements ChangenumberPrsenter {

    ChangePhonnumberView changePhonnumberView;
    Context context;
    Dialog dialog;


    public ChangNumberImplementation(Activity changePhonenumber, ChangePhonenumber changePhonenumber1) {
        this.changePhonnumberView = changePhonenumber1;
        this.context = changePhonenumber;
        dialog = M.showloader(context, "", false, false);
    }

    @Override
    public void dochangeNumner(String phonenumber, String countrycode) {
        if (M.isNetworkAvailable(context))
        {
            dialog.show();
            RestApiInterface apiInterface = RestApiClientAuth.Retrofit().create(RestApiInterface.class);
            Call<ChangeNumberResponseModel> call = apiInterface.changeNumber(phonenumber,countrycode);
            call.enqueue(new Callback<ChangeNumberResponseModel>() {
                @Override
                public void onResponse(Call<ChangeNumberResponseModel> call, Response<ChangeNumberResponseModel> response) {
                    dialog.dismiss();

                    if (response.isSuccessful()) {
                        ChangeNumberResponseModel signupResponseModel=response.body();
                        if (response!=null&&response.body().getSuccess()==true) {
                            if (response.body().getMessage().equals("Email sent successfully"))
                            {
                                changePhonnumberView.onSuccess(signupResponseModel);
                            }
                            else
                            {
                                changePhonnumberView.onError(signupResponseModel.getMessage());
                            }
                        }
                        else
                        {
                            changePhonnumberView.onError(signupResponseModel.getMessage());
                        }
                    }
                }
                @Override
                public void onFailure(Call<ChangeNumberResponseModel> call, Throwable t) {
                    Log.e("error", String.valueOf(t));
                    dialog.dismiss();
                }
            });
        }
        else
        {
            changePhonnumberView.noInternet("ok");
        }

    }
    }

