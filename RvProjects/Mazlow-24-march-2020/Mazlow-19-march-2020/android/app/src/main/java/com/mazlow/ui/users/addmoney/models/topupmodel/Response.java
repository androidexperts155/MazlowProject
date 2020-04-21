
package com.mazlow.ui.users.addmoney.models.topupmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("errorCode")
    @Expose
    private String errorCode;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("paymentUrl")
    @Expose
    private String paymentUrl;
    @SerializedName("referenceCode")
    @Expose
    private String referenceCode;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

}
