package com.mazlow.ui.users.changephonenumber;
import com.mazlow.ui.users.changephonenumber.model.ChangeNumberResponseModel;

public interface ChangePhonnumberView {
    void onSuccess(ChangeNumberResponseModel signupResponseModel);
    void onError(String error);
    void noInternet(String  tag);
}
