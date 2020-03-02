package com.mazlow.signup.postalcode;

import com.mazlow.signup.postalcode.model.PostalCodeResponse;

public interface postalcodeView {
    void onSuccessPostal(PostalCodeResponse postalCodeResponse);
    void onErrorPostal(String error);
    void noInternetPostal(String  tag);
}
