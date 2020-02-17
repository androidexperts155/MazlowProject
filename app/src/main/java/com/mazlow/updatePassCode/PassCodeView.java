package com.mazlow.updatePassCode;

import com.mazlow.signup.models.SignupResponseModel;

public interface PassCodeView {
    void  onSuccess(SignupResponseModel signupResponseModel);
    void  onError(String error);
    void noInternet(String  tag);
}
