
package com.mazlow.login.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponseModel {

    @SerializedName("success")
    @Expose
    private Boolean success;


    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("token")
    @Expose
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("userInfo")
    @Expose
    private UserInfo userInfo;
    @SerializedName("todaySpend")
    @Expose
    private TodaySpend todaySpend;
    @SerializedName("savingPotInfo")
    @Expose
    private Object savingPotInfo;
    @SerializedName("isGoalSet")
    @Expose
    private Boolean isGoalSet;
    @SerializedName("dayChallenges")
    @Expose
    private List<DayChallenge> dayChallenges = null;
    @SerializedName("userchal")
    @Expose
    private Userchal userchal;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public TodaySpend getTodaySpend() {
        return todaySpend;
    }

    public void setTodaySpend(TodaySpend todaySpend) {
        this.todaySpend = todaySpend;
    }

    public Object getSavingPotInfo() {
        return savingPotInfo;
    }

    public void setSavingPotInfo(Object savingPotInfo) {
        this.savingPotInfo = savingPotInfo;
    }

    public Boolean getIsGoalSet() {
        return isGoalSet;
    }

    public void setIsGoalSet(Boolean isGoalSet) {
        this.isGoalSet = isGoalSet;
    }

    public List<DayChallenge> getDayChallenges() {
        return dayChallenges;
    }

    public void setDayChallenges(List<DayChallenge> dayChallenges) {
        this.dayChallenges = dayChallenges;
    }

    public Userchal getUserchal() {
        return userchal;
    }

    public void setUserchal(Userchal userchal) {
        this.userchal = userchal;
    }

}
