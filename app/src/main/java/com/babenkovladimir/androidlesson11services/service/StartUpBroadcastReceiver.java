package com.babenkovladimir.androidlesson11services.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.babenkovladimir.androidlesson11services.MainActivity;

public class StartUpBroadcastReceiver extends BroadcastReceiver {

    private static final String BOOT_ACTION = "android.intent.action.BOOT_COMPLETED";
    public static final String START_ACTION = "android.intent.action.START_ACTION";
    public static final String START_TASK_ACTION = "android.intent.action.START_TASK_ACTION";
    public static final String STOP_TASK_ACTION = "android.intent.action.STOP_TASK_ACTION";
    public static final String STOP_ACTION = "android.intent.action.STOP_ACTION";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action) {
            case BOOT_ACTION:
                Log.d("ServiceProject", "onBootAction");
                Intent activityIntent = new Intent(context, MainActivity.class);
                activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(activityIntent);
                break;
            case START_ACTION:
                Log.d("ServiceProject", "START_ACTION");
                break;
            case START_TASK_ACTION:
                Log.d("ServiceProject", "START_TASK_ACTION");
                break;
            case STOP_TASK_ACTION:
                Log.d("ServiceProject", "STOP_TASK_ACTION");
                break;
            case STOP_ACTION:
                Log.d("ServiceProject", "STOP_ACTION");
                break;
        }
    }
}
