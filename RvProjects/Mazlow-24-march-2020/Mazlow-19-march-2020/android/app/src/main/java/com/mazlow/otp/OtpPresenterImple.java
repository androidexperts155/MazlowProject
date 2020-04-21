package com.mazlow.otp;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.mazlow.Networking.RestApiClientAuth;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.customclasses.M;
import com.mazlow.otp.models.CheckOtpResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpPresenterImple implements OtpPresenter{

    OtpView otpView;
    Context context;
    Dialog dialog;

    public OtpPresenterImple(OtpView otpView, OtpActivity otpActivity1) {
        this.otpView = otpView;
        this.context = otpActivity1;
        dialog = M.showloader(context, "", false, false);

    }

    @Override
    public void doOtp(String phonenumber, String ccod) {
        if (M.isNetworkAvailable(context))
        {
            dialog.show();
            RestApiInterface apiInterface = RestApiClientAuth.Retrofit().create(RestApiInterface.class);
            Call<CheckOtpResponseModel> call = apiInterface.verifyOtp(phonenumber,ccod);
            call.enqueue(new Callback<CheckOtpResponseModel>() {
                @Override
                public void onResponse(Call<CheckOtpResponseModel> call, Response<CheckOtpResponseModel> response) {
                    dialog.dismiss();
                    CheckOtpResponseModel checkOtpResponseModel = response.body();
                    if (response != null && response.body().getSuccess() == true) {
                        if (response.body().getMessage().equals("Verification successfully")) {
                            otpView.onSuccess(checkOtpResponseModel);
                        }
                        else {
                            otpView.onError(checkOtpResponseModel.getMessage());
                        }
                    }
                    else {
                        otpView.onError(checkOtpResponseModel.getMessage());
                    }

                }
                @Override
                public void onFailure(Call<CheckOtpResponseModel> call, Throwable t) {
                    Log.e("error", String.valueOf(t));
                    dialog.dismiss();
                }
            });
        }
        else
        {
            otpView.noInternet("ok");
        }

    }

}
