package com.mazlow.otp.resendotp;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.mazlow.Networking.RestApiClientAuth;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.customclasses.M;
import com.mazlow.otp.OtpActivity;
import com.mazlow.signup.models.SignupResponseModel;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ResendOtpPresenterImplementation implements ResendOtpPresenter {


    ResendOtpView resendOtpView;
    Context context;
    Dialog dialog;

    public ResendOtpPresenterImplementation(ResendOtpView otpView, OtpActivity otpActivity1) {
        this.resendOtpView = otpView;
        this.context = otpActivity1;
        dialog = M.showloader(context, "", false, false);

    }
    @Override
    public void ResendOtp(String coutrycode, String phone) {

        if (M.isNetworkAvailable(context))
        {
            dialog.show();
            RestApiInterface apiInterface = RestApiClientAuth.Retrofit().create(RestApiInterface.class);

            Call<SignupResponseModel> call = apiInterface.resendotp(coutrycode,phone);
            call.enqueue(new Callback<SignupResponseModel>() {
                @Override
                public void onResponse(Call<SignupResponseModel> call, Response<SignupResponseModel> response) {
                    dialog.dismiss();
                    if ((response.isSuccessful())) {
                        SignupResponseModel signupResponseModel = response.body();

                        if (signupResponseModel.getSuccess()) {

                            if (signupResponseModel.getMessage().equals("Code sent successfully")) {
                                resendOtpView.onSuccessResend(signupResponseModel);
                            } else {
                                resendOtpView.onErrorResend(signupResponseModel.getMessage());
                            }
                        } else {
                            resendOtpView.onErrorResend(response.body().getMessage());
                        }

                    }
                    else{
                        resendOtpView.onErrorResend("Something is wrong");
                    }
                }

                @Override
                public void onFailure(Call<SignupResponseModel> call, Throwable t) {
                    Log.e("error", String.valueOf(t));
                    dialog.dismiss();
                }
            });
        }
        else
        {
            resendOtpView.noInternetResend("ok");
        }

    }
}

