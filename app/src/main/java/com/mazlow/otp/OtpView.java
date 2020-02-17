package com.mazlow.otp;


import com.mazlow.otp.models.CheckOtpResponseModel;

public interface OtpView {
    void onSuccess(CheckOtpResponseModel checkOtpResponseModel);
    void onError(String error);
    void noInternet(String  tag);
}
