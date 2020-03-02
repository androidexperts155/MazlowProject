
package com.mazlow.ui.users.changephonenumber.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ChangeNumberResponseModel {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("success")
    private Boolean mSuccess;
    @SerializedName("token")
    private String mToken;
    @SerializedName("userEmail")
    private String mUserEmail;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

    public void setSuccess(Boolean success) {
        mSuccess = success;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

    public String getUserEmail() {
        return mUserEmail;
    }

    public void setUserEmail(String userEmail) {
        mUserEmail = userEmail;
    }

}
