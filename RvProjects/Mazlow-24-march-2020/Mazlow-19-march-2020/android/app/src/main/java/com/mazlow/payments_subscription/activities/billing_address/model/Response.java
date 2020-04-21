
package com.mazlow.payments_subscription.activities.billing_address.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Response {

    @SerializedName("description")
    private String mDescription;
    @SerializedName("errorCode")
    private String mErrorCode;
    @SerializedName("paymentUrl")
    private String mPaymentUrl;
    @SerializedName("referenceCode")
    private String mReferenceCode;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(String errorCode) {
        mErrorCode = errorCode;
    }

    public String getPaymentUrl() {
        return mPaymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        mPaymentUrl = paymentUrl;
    }

    public String getReferenceCode() {
        return mReferenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        mReferenceCode = referenceCode;
    }

}
