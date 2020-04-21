
package com.mazlow.ui.users.dashboard.set_goals.confirm_goals.model;

import com.google.gson.annotations.SerializedName;

public class ConfirmGoalResponse {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("success")
    private Boolean mSuccess;

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

}
