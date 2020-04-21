package com.mazlow.ui.users.dashboard.notification;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.mazlow.Networking.RestApiClientAuth;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.customclasses.M;
import com.mazlow.ui.users.dashboard.notification.model.NotificationResponseModel;
import com.mazlow.ui.users.dashboard.set_goals.model.GoalResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationPrsenterImplementation implements NotificationPrsenter {

    NotificationView notificationView;
    Context context;
    Dialog dialog;

    public NotificationPrsenterImplementation(NotificationView notificationView, Context context) {
        this.notificationView = notificationView;
        this.context = context;
        dialog = M.showloader(context, "", false, false);
    }



    @Override
    public void getNotification(String token) {

        if (M.isNetworkAvailable(context))
        {
            dialog.show();
            RestApiInterface apiInterface = RestApiClientAuth.Retrofit().create(RestApiInterface.class);
            Call<NotificationResponseModel> call = apiInterface.userNotification(token);
            call.enqueue(new Callback<NotificationResponseModel>() {
                @Override
                public void onResponse(Call<NotificationResponseModel> call, Response<NotificationResponseModel> response) {
                    dialog.dismiss();

                    if (response.isSuccessful()) {
                        NotificationResponseModel responseGoals=response.body();
                        if (response!=null&&response.body().getSuccess()==true) {
                            if (responseGoals!=null&&responseGoals.getNotificationInfo().size()>0)
                            {
                                notificationView.onSuccess(responseGoals);
                            }
                            else
                            {
                                notificationView.onError("Something wrong");
                            }
                        }
                        else
                        {
                            notificationView.onError("Something wrong");
                        }
                    }
                }
                @Override
                public void onFailure(Call<NotificationResponseModel> call, Throwable t) {
                    Log.e("error", String.valueOf(t));
                    dialog.dismiss();
                }
            });
        }
        else
        {
            notificationView.onInternet("ok");
        }



}
}
