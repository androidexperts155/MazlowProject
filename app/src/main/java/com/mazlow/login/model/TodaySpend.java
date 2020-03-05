
package com.mazlow.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodaySpend {

    @SerializedName("todaySpendGBP")
    @Expose
    private Integer todaySpendGBP;
    @SerializedName("todaySpendEUR")
    @Expose
    private Integer todaySpendEUR;
    @SerializedName("todaySpendUSD")
    @Expose
    private Integer todaySpendUSD;

    public Integer getTodaySpendGBP() {
        return todaySpendGBP;
    }

    public void setTodaySpendGBP(Integer todaySpendGBP) {
        this.todaySpendGBP = todaySpendGBP;
    }

    public Integer getTodaySpendEUR() {
        return todaySpendEUR;
    }

    public void setTodaySpendEUR(Integer todaySpendEUR) {
        this.todaySpendEUR = todaySpendEUR;
    }

    public Integer getTodaySpendUSD() {
        return todaySpendUSD;
    }

    public void setTodaySpendUSD(Integer todaySpendUSD) {
        this.todaySpendUSD = todaySpendUSD;
    }

}
