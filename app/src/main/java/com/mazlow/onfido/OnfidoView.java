package com.mazlow.onfido;

import com.mazlow.onfido.model.OnFidoResponseModel;

public interface OnfidoView {

    void Sucess(OnFidoResponseModel onFidoResponseModel);
    void error(String error);
    void noInternet(String  tag);
}
