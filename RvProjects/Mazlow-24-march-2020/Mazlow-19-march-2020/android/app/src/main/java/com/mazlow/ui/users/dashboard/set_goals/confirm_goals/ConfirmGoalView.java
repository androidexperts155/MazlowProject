package com.mazlow.ui.users.dashboard.set_goals.confirm_goals;

import com.mazlow.ui.users.dashboard.set_goals.confirm_goals.model.ConfirmGoalResponse;

public interface ConfirmGoalView {

   void onSuccess(ConfirmGoalResponse confirmGoalResponse);
    void onError(String error);
    void onInternet(String tag);


}
