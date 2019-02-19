package com.example.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        TextView tv=findViewById(R.id.text);
        TextView tv2=findViewById(R.id.text2);

        tv.setText("Everyone");
        tv.setTextColor(Color.GREEN);

        tv2.setTextColor(Color.BLUE);



    }
}
