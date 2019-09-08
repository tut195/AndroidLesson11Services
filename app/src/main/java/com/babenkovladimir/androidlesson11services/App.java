package com.babenkovladimir.androidlesson11services;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;

import com.babenkovladimir.androidlesson11services.simplebroadcastReceiver.PowerConnectionReceiver;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        registerReceiver();
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        BroadcastReceiver battaryChargingReceiver = new PowerConnectionReceiver();
        registerReceiver(battaryChargingReceiver, intentFilter);
    }
}
