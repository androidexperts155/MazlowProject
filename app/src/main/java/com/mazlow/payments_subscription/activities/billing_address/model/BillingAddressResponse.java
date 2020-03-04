
package com.mazlow.payments_subscription.activities.billing_address.model;

import com.google.gson.annotations.SerializedName;


public class BillingAddressResponse {

    @SerializedName("response")
    private Response mResponse;
    @SerializedName("success")
    private Boolean mSuccess;

    public Response getResponse() {
        return mResponse;
    }

    public void setResponse(Response response) {
        mResponse = response;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

    public void setSuccess(Boolean success) {
        mSuccess = success;
    }

}
