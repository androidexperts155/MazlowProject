
package com.mazlow.ui.users.dashboard.notification.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class NotificationResponseModel {

    @SerializedName("notificationInfo")
    private List<NotificationInfo> mNotificationInfo;
    @SerializedName("success")
    private Boolean mSuccess;

    public List<NotificationInfo> getNotificationInfo() {
        return mNotificationInfo;
    }

    public void setNotificationInfo(List<NotificationInfo> notificationInfo) {
        mNotificationInfo = notificationInfo;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

    public void setSuccess(Boolean success) {
        mSuccess = success;
    }

}
