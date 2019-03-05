package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class HomePage extends AppCompatActivity implements MediaPlayer.OnPreparedListener {

    private Button mTryCatch;
    private TextView mTextView3;
    int TRY_CATCH = 10;
    private Button mSharedpref;
    private Button mDilaogs;
    private Button mList;
    private Button mGrid;
    private Button mServices;
    private Button mBrodcast;
    private Button mSql;
    private Button fragment;
    private Button mMusicPlay;
    private Button mMusicPause;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mTryCatch = findViewById(R.id.tryCatch);
        mTextView3 = findViewById(R.id.textView3);
        mSharedpref = findViewById(R.id.sharedpref);
        mDilaogs = findViewById(R.id.dilaogs);
        mList = findViewById(R.id.list);
        mGrid = findViewById(R.id.grid);
        mServices = findViewById(R.id.services);
        mBrodcast = findViewById(R.id.brodcast);
        fragment = findViewById(R.id.fragment);
        mSql = findViewById(R.id.sql);

        mMusicPlay = findViewById(R.id.musicPlay);
        mMusicPause = findViewById(R.id.musicPause);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource("https://jk9nj200-a.akamaihd.net/downloads/ringtones/files/mp3/apple-iphone-501.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                Log.e("SONG",percent+" "+ mp.getCurrentPosition());

            }
        });
        mediaPlayer.prepareAsync();


        mTryCatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, TryCatch.class);
                intent.putExtra("name", "Shivam");
                startActivityForResult(intent, TRY_CATCH);
            }
        });

        mSharedpref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, SharedPref.class);
                startActivity(intent);
            }
        });

        mDilaogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, MyDialogs.class);
                startActivity(intent);
            }
        });

        mList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, ListsActivity.class);
                startActivity(intent);
            }
        });
        mGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, GridActivity.class);
                startActivity(intent);
            }
        });


        mServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(HomePage.this, MyServices.class);
                startService(intent2);

            }
        });


        mBrodcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Broadcast.class);
                startActivity(intent);
            }
        });


        mSql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, SQLite.class);
                startActivity(intent);
            }
        });

        fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, FragmentActivity.class);
                startActivity(intent);
            }
        });


        mMusicPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  mediaPlayer.start();
            }
        });

        mMusicPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer.pause();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TRY_CATCH && resultCode == Activity.RESULT_OK) { //try catch
            String s = data.getStringExtra("data");
            mTextView3.setText(s);
        }

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.e("onPrepared","111");
        mp.start();


    }
}
