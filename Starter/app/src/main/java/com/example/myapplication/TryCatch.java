package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TryCatch extends AppCompatActivity {

    private TextView mTextView2;
    private Button mButton3;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_catch);
        mTextView2 = findViewById(R.id.textView2);

        final Intent intent = getIntent();

        if (intent != null) {

            String s = intent.getStringExtra("name");

            try {
                s = s.substring(0, 2);
                mTextView2.setText(s);
                data="Success";
            } catch (Exception e) {
                data="Has Error";
                Log.e("Error", e.getLocalizedMessage());
                mTextView2.setText("Something gone wrong");

            }/*catch (ArithmeticException e){
                Log.e("Error",e.getLocalizedMessage());
                mTextView2.setText("Something gone wrong");

            }*/
        }
        mButton3 = findViewById(R.id.button3);

        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2=new Intent();
                intent2.putExtra("data",data);
                setResult(Activity.RESULT_OK,intent2);
                finish();
            }
        });
    }
}
