package com.mazlow.onfido.verification_identity;

import com.mazlow.login.model.LoginResponseModel;
import com.mazlow.onfido.model.OnFidoResponseModel;

public interface GetProfileView {

    void Sucess(LoginResponseModel loginResponseModel);
    void error(String error);
    void noInternet(String  tag);
}
