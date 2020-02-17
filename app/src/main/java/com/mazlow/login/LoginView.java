package com.mazlow.login;

import com.mazlow.signup.models.SignupResponseModel;

public interface LoginView {
    void onSuccess(SignupResponseModel signupResponseModel);
    void onError(String error);
    void noInternet(String  tag);
}
