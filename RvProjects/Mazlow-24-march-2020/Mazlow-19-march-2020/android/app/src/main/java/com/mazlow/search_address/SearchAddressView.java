package com.mazlow.search_address;

import com.mazlow.search_address.model.AddressZipcodeResponse;

public interface SearchAddressView {
    void onSucess(AddressZipcodeResponse addressZipcodeResponse);
    void onError(String error1);
    void noInternet(String tag);
}
