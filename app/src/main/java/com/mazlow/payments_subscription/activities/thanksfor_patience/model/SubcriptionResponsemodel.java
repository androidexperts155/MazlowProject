
package com.mazlow.payments_subscription.activities.thanksfor_patience.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class SubcriptionResponsemodel {

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
