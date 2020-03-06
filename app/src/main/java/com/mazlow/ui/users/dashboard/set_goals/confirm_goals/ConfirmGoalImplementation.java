package com.mazlow.ui.users.dashboard.set_goals.confirm_goals;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.mazlow.Networking.RestApiClientAuth;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.customclasses.M;
import com.mazlow.ui.users.dashboard.set_goals.confirm_goals.model.ConfirmGoalResponse;
import com.mazlow.ui.users.dashboard.set_goals.model.GoalResponseModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmGoalImplementation implements ConfirmgoalPresenter {

    ConfirmGoalView confirmGoalView;
    Context context;
    Dialog dialog;

    public ConfirmGoalImplementation(ConfirmGoalView confirmGoalView, Context context) {
        this.confirmGoalView = confirmGoalView;
        this.context = context;
        dialog = M.showloader(context, "", false, false);

    }

    @Override
    public void confirmGoal(String token, String ids, String amount) {
        if (M.isNetworkAvailable(context))
        {
            dialog.show();
            RestApiInterface apiInterface = RestApiClientAuth.Retrofit().create(RestApiInterface.class);
            Call<ConfirmGoalResponse> call = apiInterface.addGoal(token,ids);
            call.enqueue(new Callback<ConfirmGoalResponse>() {
                @Override
                public void onResponse(Call<ConfirmGoalResponse> call, Response<ConfirmGoalResponse> response) {
                    dialog.dismiss();

                    if (response.isSuccessful()) {
                        ConfirmGoalResponse responseGoals=response.body();
                        if (response!=null&&response.body().getSuccess()==true) {
                            if (responseGoals!=null)
                            {
                                if (responseGoals.getMessage().equals("Goal update successfully"))
                                {
                                    confirmGoalView.onSuccess(responseGoals);
                                }
                                else
                                {
                                    confirmGoalView.onError(responseGoals.getMessage());
                                }

                            }
                            else
                            {
                                confirmGoalView.onError("Something wrong");
                            }
                        }
                        else
                        {
                            confirmGoalView.onError("Something wrong");
                        }
                    }
                }
                @Override
                public void onFailure(Call<ConfirmGoalResponse> call, Throwable t) {
                    Log.e("error", String.valueOf(t));
                    dialog.dismiss();
                }
            });
        }
        else
        {
            confirmGoalView.onInternet("ok");
        }
    }
}
