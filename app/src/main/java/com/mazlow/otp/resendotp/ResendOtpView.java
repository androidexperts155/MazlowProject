package com.mazlow.otp.resendotp;

import com.mazlow.signup.models.SignupResponseModel;

public interface ResendOtpView {

    void onSuccessResend(SignupResponseModel checkOtpResponseModel);
    void onErrorResend(String error);
    void noInternetResend(String  tag);


}
