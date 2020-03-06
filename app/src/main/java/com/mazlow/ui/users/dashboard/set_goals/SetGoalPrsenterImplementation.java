package com.mazlow.ui.users.dashboard.set_goals;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.mazlow.Networking.RestApiClientAuth;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.customclasses.M;
import com.mazlow.ui.users.dashboard.set_goals.model.GoalResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetGoalPrsenterImplementation implements SetGoalPresenter {

    SetGoalView setGoalView;
    Context context;
    Dialog dialog;

    public SetGoalPrsenterImplementation(SetGoalView setGoalView, Context context) {
        this.setGoalView = setGoalView;
        this.context = context;
      dialog = M.showloader(context, "", false, false);

    }

    @Override
    public void getGoals(String token) {
        if (M.isNetworkAvailable(context))
        {
            dialog.show();
            RestApiInterface apiInterface = RestApiClientAuth.Retrofit().create(RestApiInterface.class);
            Call<GoalResponseModel> call = apiInterface.getGoals(token);
            call.enqueue(new Callback<GoalResponseModel>() {
                @Override
                public void onResponse(Call<GoalResponseModel> call, Response<GoalResponseModel> response) {
                    dialog.dismiss();

                    if (response.isSuccessful()) {
                        GoalResponseModel responseGoals=response.body();
                        if (response!=null&&response.body().getSuccess()==true) {
                            if (responseGoals.getGoalInfo()!=null)
                            {
                                setGoalView.onSuccess(responseGoals);
                            }
                            else
                            {
                                setGoalView.onError("Something wrong");
                            }
                        }
                        else
                        {
                            setGoalView.onError("Something wrong");
                        }
                    }
                }
                @Override
                public void onFailure(Call<GoalResponseModel> call, Throwable t) {
                    Log.e("error", String.valueOf(t));
                    dialog.dismiss();
                }
            });
        }
        else
        {
            setGoalView.onInternet("ok");
        }

    }

    }
