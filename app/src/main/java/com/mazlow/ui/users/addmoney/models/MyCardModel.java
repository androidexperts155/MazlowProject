
package com.mazlow.ui.users.addmoney.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyCardModel {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("cardDetails")
    @Expose
    private List<CardDetail> cardDetails = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<CardDetail> getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(List<CardDetail> cardDetails) {
        this.cardDetails = cardDetails;
    }

}
