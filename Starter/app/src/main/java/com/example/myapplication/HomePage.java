package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

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
        mSql = findViewById(R.id.sql);

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

               /* Intent intent = new Intent(HomePage.this, GridActivity.class);
                startActivity(intent);*/
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


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TRY_CATCH && resultCode == Activity.RESULT_OK) { //try catch
            String s = data.getStringExtra("data");
            mTextView3.setText(s);
        }

    }
}
