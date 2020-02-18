package com.mazlow.login;

import com.mazlow.login.model.LoginResponseModel;

public interface LoginView {
    void onSuccess(LoginResponseModel LoginResponseModel);
    void onError(String error);
    void noInternet(String  tag);
}
