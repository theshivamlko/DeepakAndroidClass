package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Networking extends AppCompatActivity {

    private TextView mText;
    private Button mBtn;
    private ImageView mPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking);
        mText = findViewById(R.id.text);
        mBtn = findViewById(R.id.btn);
        mPic = findViewById(R.id.pic);


        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("Reposne", "1111");
                MyTask myTask = new MyTask();
                myTask.execute();
            }
        });
    }


    class MyTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... s) {


            return fecthData();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s != null) {

                try {
                    JSONObject jsonObject = new JSONObject(s);

                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    JSONObject userData = jsonArray.getJSONObject(0);
                    JSONObject name = userData.getJSONObject("name");
                    String fname = name.getString("first");
                    String lname = name.getString("last");
                    Log.e("Reposne", fname + "  " + lname);

                    String email = userData.getString("email");

                    String picURL = userData.getJSONObject("picture").getString("large");


                    mText.setText(fname + " " + lname + " \n Email: " + email);

                    Picasso.get().load(picURL).placeholder(R.drawable.ic_image_black_24dp).into(mPic);

                    showNotification(fname + " " + lname, email);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else
                Toast.makeText(Networking.this, "Unablr to connect", Toast.LENGTH_SHORT).show();
        }
    }

    String fecthData() {
        try {
            URL url = new URL("https://randomuser.me/api/");
            Log.e("Reposne", "1111");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            Scanner scanner = new Scanner(httpURLConnection.getInputStream());
            scanner.useDelimiter("\\A");
            String response = scanner.next();

            Log.e("Reposne", response + "");
            return response;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    private NotificationManager notifManager;

    void showNotification(String name1, String email) {
        NotificationChannel mChannel=null;
        if (notifManager == null) {
            notifManager = (NotificationManager) getSystemService
                    (Context.NOTIFICATION_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder builder;
            Intent intent = new Intent(this, Networking.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent;
            int importance = NotificationManager.IMPORTANCE_HIGH;
            if (mChannel == null) {
                mChannel = new NotificationChannel
                        ("0", "1221", importance);
                mChannel.setDescription("4444");
                mChannel.enableVibration(true);
                notifManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(this, "0");

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 1251, intent, PendingIntent.FLAG_ONE_SHOT);
            builder.setContentTitle(name1)
                    .setSmallIcon(R.drawable.ic_battery_alert_black_24dp) // required
                    .setContentText(email)  // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setLargeIcon(BitmapFactory.decodeResource
                            (getResources(), R.mipmap.ic_launcher))
                    .setBadgeIconType(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)
                    .setSound(RingtoneManager.getDefaultUri
                            (RingtoneManager.TYPE_NOTIFICATION));
            Notification notification = builder.build();
            notifManager.notify(0, notification);
        } else {

            Intent intent = new Intent(this, Networking.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = null;

            pendingIntent = PendingIntent.getActivity(this, 1251, intent, PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setContentTitle("111")
                    .setContentText("5555")
                    .setAutoCancel(true)
                    .setColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimary))
                    .setSound(defaultSoundUri)
                    .setSmallIcon(R.drawable.ic_battery_alert_black_24dp)
                    .setContentIntent(pendingIntent)
                    .setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle("666").bigText("8888"));

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1251, notificationBuilder.build());
        }
    }
}
