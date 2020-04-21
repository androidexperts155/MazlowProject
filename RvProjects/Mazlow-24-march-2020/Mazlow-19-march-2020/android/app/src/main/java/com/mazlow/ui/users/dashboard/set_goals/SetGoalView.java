package com.mazlow.ui.users.dashboard.set_goals;


import com.mazlow.ui.users.dashboard.set_goals.model.GoalResponseModel;

public interface SetGoalView {
    void  onSuccess(GoalResponseModel responseGoals);
    void  onError(String error);
    void  onInternet(String tag);
}
