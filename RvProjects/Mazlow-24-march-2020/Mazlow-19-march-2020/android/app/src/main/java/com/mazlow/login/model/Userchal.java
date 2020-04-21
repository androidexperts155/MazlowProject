
package com.mazlow.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Userchal {

    @SerializedName("dailyBudget")
    @Expose
    private Boolean dailyBudget;
    @SerializedName("chalAmount")
    @Expose
    private String chalAmount;
    @SerializedName("chalCurrencyCode")
    @Expose
    private String chalCurrencyCode;

    public Boolean getDailyBudget() {
        return dailyBudget;
    }

    public void setDailyBudget(Boolean dailyBudget) {
        this.dailyBudget = dailyBudget;
    }

    public String getChalAmount() {
        return chalAmount;
    }

    public void setChalAmount(String chalAmount) {
        this.chalAmount = chalAmount;
    }

    public String getChalCurrencyCode() {
        return chalCurrencyCode;
    }

    public void setChalCurrencyCode(String chalCurrencyCode) {
        this.chalCurrencyCode = chalCurrencyCode;
    }

}
