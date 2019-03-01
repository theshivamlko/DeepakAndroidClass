package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class Broadcast extends AppCompatActivity {

    BroadcastReceiver broadcastReceiver;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        Log.e("MSG", "OnCReate");
        mImage = findViewById(R.id.image);


        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Log.e("MSG", "OnRecive");
          /*      if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
                    BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
                    int batLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
*/
                if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
                    mImage.setImageResource(R.drawable.ic_battery_charging_50_black_24dp);
                } else {
                    intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED);
                    mImage.setImageResource(R.drawable.ic_battery_alert_black_24dp);
                }
                  /* else if(batLevel<10){
                        mImage.setImageResource(R.drawable.ic_battery_alert_black_24dp);
                    }
                    */


                //   }

            }
        };



        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Broadcast.this,MyReceiver.class);
                intent.setAction("com.example.myapplication.ABC");
                sendBroadcast(intent);
            }
        });
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("MSG", "OnRestart");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MSG", "OnStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MSG", "OnResume");

        IntentFilter ifilter = new IntentFilter();
        ifilter.addAction(Intent.ACTION_POWER_CONNECTED);
        ifilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(broadcastReceiver, ifilter);

        //  registerReceiver(broadcastReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e("MSG", "OnPause");

        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onStop() {
        Log.e("MSG", "OnStop");
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e("MSG", "OnDestry");

    }
}
