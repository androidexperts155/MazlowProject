package com.mazlow.ui.users.dashboard.notification;

import com.mazlow.ui.users.dashboard.notification.model.NotificationResponseModel;

public interface NotificationView  {
    void onSuccess(NotificationResponseModel notificationResponseModel);
    void onError(String error);
    void onInternet(String tag);
}
