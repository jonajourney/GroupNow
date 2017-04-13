package com.example.ongda.eventplannerapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Ongda on 4/12/17.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("In Receiver");
        Intent i = new Intent();
        i.setClassName("com.example.ongda.eventplannerapp", "com.example.ongda.eventplannerapp.AlarmPopUp");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
