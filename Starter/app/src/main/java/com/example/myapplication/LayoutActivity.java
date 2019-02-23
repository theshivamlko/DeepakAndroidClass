package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class LayoutActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyaout);

        textView=findViewById(R.id.textView);


        Intent intent=getIntent();

        if(intent!=null){
            String s=intent.getStringExtra("name");
            int n=intent.getIntExtra("roll",0);
            boolean b=intent.getBooleanExtra("present",false);

            double marks=intent.getDoubleExtra("marks",0.0);

            Log.e("Intent",s+" "+n+" "+b+" "+marks);
            textView.setText(s+" "+n+" "+b+" "+marks);

        }



    }
}
