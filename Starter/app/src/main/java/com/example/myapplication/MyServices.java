package com.example.myapplication;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyServices extends IntentService {


    public MyServices() {
        super("Service");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(  @Nullable Intent intent) {

    }


    @Override
    public void onStart(  @Nullable Intent intent, int startId) {
        super.onStart(intent, startId);

        for (int i = 1; i <= 10000; i++) {

            Log.e("MSG",i+"");
        }


    }


}
