package com.example.wakeup.ui.main.alarms;

import static android.provider.Settings.System.getString;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.wakeup.R;

import java.util.Random;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String alarmMessage = "Your task should be done!";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "taskChannel")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Alarm")
                .setContentText(alarmMessage)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        Random random = new Random();
        notificationManager.notify(random.nextInt(), builder.build());
    }
}