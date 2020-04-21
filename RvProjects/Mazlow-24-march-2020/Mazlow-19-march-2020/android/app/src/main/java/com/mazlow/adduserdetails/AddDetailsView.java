package com.mazlow.adduserdetails;

import com.mazlow.adduserdetails.model.UpdateUserDetails;

public interface AddDetailsView {

    void onSucess(UpdateUserDetails updateUserDetails);
    void onError(String error);
    void noInternet(String  tag);
}
