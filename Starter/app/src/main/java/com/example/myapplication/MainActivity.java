package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        TextView tv = findViewById(R.id.text);
        TextView tv2 = findViewById(R.id.text2);
        Button call = findViewById(R.id.call1);
        Button browser = findViewById(R.id.broswer);
        Button gps = findViewById(R.id.gps);

        tv.setText("Everyone");
        tv.setTextColor(Color.GREEN);

        tv2.setTextColor(Color.BLUE);


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LayoutActivity.class);
                intent.putExtra("name", "Hello");
                intent.putExtra("roll", 123456);
                intent.putExtra("present", true);
                startActivity(intent);
                finish(); //close current activity
            }
        });


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] per = {Manifest.permission.CALL_PHONE};

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    requestPermissions(per, 1);
                else {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:9898989898"));
                    startActivity(intent);
                }
            }
        });


        browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
                startActivity(intent);
            }
        });

        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:9898989898"));
                startActivity(intent);
        }else
            Toast.makeText(MainActivity.this,"permission denied",Toast.LENGTH_LONG).show();
    }
}
