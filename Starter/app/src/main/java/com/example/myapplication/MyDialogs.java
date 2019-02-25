package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyDialogs extends AppCompatActivity {

    private Button mCustom;
    private Button mSimple;
    private Button mButton6;
    AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dialogs);
        mCustom = findViewById(R.id.custom);
        mSimple = findViewById(R.id.simple);


        mSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        mCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomDialog();
            }
        });

    }


    void openDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MyDialogs.this);

        builder.setTitle("Messgae");
        builder.setMessage("Please rate our app");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MyDialogs.this, "OK pressed", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
                startActivity(intent);
            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MyDialogs.this, "Cancel pressed", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNeutralButton("MayBe Later", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MyDialogs.this, "MayBe Later pressed", Toast.LENGTH_SHORT).show();
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    void openCustomDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MyDialogs.this);

        View view = View.inflate(MyDialogs.this, R.layout.dilaog_custom, null);
        Button mButton6 = view.findViewById(R.id.button6);
        final EditText mEmail = view.findViewById(R.id.email);

        mButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = mEmail.getText().toString();
                Toast.makeText(MyDialogs.this, e, Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();


            }
        });

        builder.setView(view);

        alertDialog = builder.create();
        alertDialog.show();
    }
}
