package com.babenkovladimir.androidlesson11services.service;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.babenkovladimir.androidlesson11services.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ServiceActivity extends AppCompatActivity {

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        unbinder = ButterKnife.bind(this);
        configureBroadcastReceiver();
    }

    @OnClick(R.id.bt_start_service)
    void btStartService(){
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        sendLocalBroadcast(StartUpBroadcastReceiver.START_ACTION);
    }

    @OnClick(R.id.bt_start_task)
    void btStartTask(){
        Intent intent = new Intent(this, MyService.class);
        intent.setAction(MyService.ACTION_START_TASK);
        startService(intent);
        sendLocalBroadcast(StartUpBroadcastReceiver.START_TASK_ACTION);
    }

    @OnClick(R.id.bt_pause_task)
    void btPauseTask(){
        Intent intent = new Intent(this, MyService.class);
        intent.setAction(MyService.ACTION_STOP_TASK);
        startService(intent);
        sendLocalBroadcast(StartUpBroadcastReceiver.STOP_TASK_ACTION);
    }

    @OnClick(R.id.bt_stop_service)
    void btStopService(){
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
        sendLocalBroadcast(StartUpBroadcastReceiver.STOP_ACTION);
    }

    @Override
    protected void onDestroy() {
       unbinder.unbind();
        super.onDestroy();
    }

    private void sendLocalBroadcast(String action){
        Intent broadcastIntent = new Intent(action);
        sendBroadcast(broadcastIntent);
    }

    private void configureBroadcastReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(StartUpBroadcastReceiver.START_ACTION);
        intentFilter.addAction(StartUpBroadcastReceiver.STOP_ACTION);
        intentFilter.addAction(StartUpBroadcastReceiver.START_TASK_ACTION);
        intentFilter.addAction(StartUpBroadcastReceiver.STOP_TASK_ACTION);
        registerReceiver(new StartUpBroadcastReceiver(), intentFilter);
    }


}
