package com.mazlow.Firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;


import com.Mazlow.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Date;
import java.util.Map;

public class MyFireBaseMessagingService extends FirebaseMessagingService {
    Notification notification;
    String image, title, message, id, time, body;
    private static final String TAG = "Automobileparking";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();

        if (data!=null)
        {
            message = remoteMessage.getData().get("body");
            title = remoteMessage.getData().get("title");
        }
    }
    Intent notificationIntent;


}