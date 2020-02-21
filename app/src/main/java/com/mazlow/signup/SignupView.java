package com.mazlow.signup;

import com.mazlow.signup.models.SignupResponseModel;

public interface SignupView {
    void onSuccess(SignupResponseModel signupResponseModel);
    void onError(String error);
    void noInternet(String  tag);

}
