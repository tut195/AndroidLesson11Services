package com.babenkovladimir.androidlesson11services.service;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.babenkovladimir.androidlesson11services.R;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    public static final String ACTION_START_TASK = "start_task";
    public static final String ACTION_STOP_TASK = "stop_task";

    private static final int NOTIFICATION_ID = 123;
    private static final int REQUEST_CODE = 213;
    private static final String CHANNEL_ID = "ServiceProjectChannel";

    boolean isTaskActive = true;


    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        Log.d("ServiceProject", "onCreateService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("ServiceProject", "onStartCommand");
        startForeground(NOTIFICATION_ID, createNotification());
        if (intent.getAction() != null)
            switch (intent.getAction()) {

                case ACTION_START_TASK:
                    isTaskActive = true;
                    someTask();
                    break;

                case ACTION_STOP_TASK:
                    stopSomeTask();
                    break;

            }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("ServiceProject", "onDestroy");
        stopSomeTask();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    void someTask() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 25; i++) {
                    if (!isTaskActive)
                        break;
                    Log.d("ServiceProject", "i = " + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    void stopSomeTask() {
        isTaskActive = false;
    }

    private void createNotificationChannel(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private Notification createNotification(){
        Notification.Builder notificationBuilder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            notificationBuilder = new Notification.Builder(this, CHANNEL_ID);
        else
            notificationBuilder = new Notification.Builder(this);

        Intent intent = new Intent(this, ServiceActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        return notificationBuilder
                .setContentTitle("our title")
                .setContentText("our text")
                .setSmallIcon(R.drawable.ic_notif_icon)
                .setContentIntent(pi)
                .build();
    }
}