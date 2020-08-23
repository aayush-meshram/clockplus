package com.myapp.clock;

import android.app.Notification;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {

    public static final String channelID = "";
    public static final String name = "";

    public NotificationHelper(Context base) {
        super(base);
    }

}
