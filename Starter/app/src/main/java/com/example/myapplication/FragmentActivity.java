package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class FragmentActivity extends AppCompatActivity {

    private Button mButton8;
    private Button mButton7;
    private FrameLayout mFrame;


    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        mButton8 = findViewById(R.id.button8);
        mButton7 = findViewById(R.id.button7);
        mFrame = findViewById(R.id.frame);


        fragmentManager = getSupportFragmentManager();

        mButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyFragment myFragment = new MyFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", "SHIVAM");
                bundle.putInt("color", Color.RED);
                myFragment.setArguments(bundle);


                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.frame, myFragment);
                transaction.commit();


            }
        });
        mButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFragment myFragment = new MyFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", "Deepak");
                bundle.putInt("color", Color.BLUE);
                myFragment.setArguments(bundle);


                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.frame, myFragment);
                transaction.commit();


            }
        });
    }
}
