package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPref extends AppCompatActivity {

    private Button mButton4;
    private Button mButton5;
    private TextView mTextView4;
    private EditText mEditText;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref);
        mButton4 = findViewById(R.id.button4);
        mButton5 = findViewById(R.id.button5);
        mTextView4 = findViewById(R.id.textView4);
        mEditText = findViewById(R.id.editText);

        sharedPreferences=getSharedPreferences("MyData",MODE_PRIVATE);

        String s= sharedPreferences.getString("phone","NA");
        boolean b= sharedPreferences.getBoolean("present",false);
        mTextView4.setText(s+" "+b);

        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= mEditText.getText().toString();

                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("phone",s);
                editor.putBoolean("present",true);
                editor.commit();

            }
        });

        mButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String s= sharedPreferences.getString("phone","NA");
               mTextView4.setText(s);
            }
        });




    }
}
