package com.babenkovladimir.androidlesson11services.simplebroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PowerConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        assert action != null;
        if (action.equals(Intent.ACTION_POWER_CONNECTED)) {
            Toast.makeText(context, "Power connected", Toast.LENGTH_SHORT).show();
        } else if (action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
            Toast.makeText(context, "Power disconected", Toast.LENGTH_SHORT).show();
        }
    }
}
