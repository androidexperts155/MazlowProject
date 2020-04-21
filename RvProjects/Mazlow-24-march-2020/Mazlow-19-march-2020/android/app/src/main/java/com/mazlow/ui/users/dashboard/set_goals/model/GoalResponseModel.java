
package com.mazlow.ui.users.dashboard.set_goals.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GoalResponseModel {

    @SerializedName("goalInfo")
    private List<GoalInfo> mGoalInfo;
    @SerializedName("success")
    private Boolean mSuccess;

    public List<GoalInfo> getGoalInfo() {
        return mGoalInfo;
    }

    public void setGoalInfo(List<GoalInfo> goalInfo) {
        mGoalInfo = goalInfo;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

    public void setSuccess(Boolean success) {
        mSuccess = success;
    }

}
