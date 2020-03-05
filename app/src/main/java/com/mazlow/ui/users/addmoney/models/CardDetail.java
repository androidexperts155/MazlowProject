
package com.mazlow.ui.users.addmoney.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardDetail {

    @SerializedName("pfsToken")
    @Expose
    private String pfsToken;
    @SerializedName("cardNumber")
    @Expose
    private String cardNumber;
    @SerializedName("cardType")
    @Expose
    private String cardType;
    @SerializedName("cardExpMM")
    @Expose
    private String cardExpMM;
    @SerializedName("cardExpYYYY")
    @Expose
    private String cardExpYYYY;

    public String getPfsToken() {
        return pfsToken;
    }

    public void setPfsToken(String pfsToken) {
        this.pfsToken = pfsToken;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardExpMM() {
        return cardExpMM;
    }

    public void setCardExpMM(String cardExpMM) {
        this.cardExpMM = cardExpMM;
    }

    public String getCardExpYYYY() {
        return cardExpYYYY;
    }

    public void setCardExpYYYY(String cardExpYYYY) {
        this.cardExpYYYY = cardExpYYYY;
    }

}
