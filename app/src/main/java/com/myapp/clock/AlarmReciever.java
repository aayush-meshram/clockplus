package com.myapp.clock;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.myapp.clock.App.CHANNEL_ID;


@RequiresApi(api = Build.VERSION_CODES.N)
public class AlarmReciever extends BroadcastReceiver {

    public NotificationManagerCompat notificationManager;
    public Context ctx;

    public AlarmReciever() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ctx = context;
        Toast.makeText(context, "Pst, I am here at AlarmReciever!", Toast.LENGTH_SHORT).show();
        notificationManager = NotificationManagerCompat.from(context);
        //STOP = findViewById(R.id.stop_alarm);
        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_alarm)
                .setContentTitle("Your Alarm has been fired!")
                .setContentText("Tap this notification to stop")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true)
                .build();
        notificationManager.notify(1, notification);
        Intent intent1 = new Intent(context, AlarmService.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }

}