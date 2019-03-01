package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("com.example.myapplication.ABC"))
            Log.e("MyReceiver", "OnRecive ABC");
        else
            Log.e("MyReceiver", "OnRecive Airplane");


    }
}
