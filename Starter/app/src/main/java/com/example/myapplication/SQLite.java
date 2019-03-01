package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.myapplication.adapters.MyAdapters;

import java.util.ArrayList;

public class SQLite extends AppCompatActivity {

    private EditText mEdtsubject;
    private Button mFetchAll;
    private EditText mSubject;
    private EditText mName;
    private Button mInsert;
    private Button mSelectSubject;

    Database database;
    private ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        mEdtsubject = findViewById(R.id.edtsubject);
        mFetchAll = findViewById(R.id.fetchAll);
        mSubject = findViewById(R.id.subject);
        mName = findViewById(R.id.name);
        mInsert = findViewById(R.id.insert);
        mSelectSubject = findViewById(R.id.selectSubject);
        mList = findViewById(R.id.list);

        database = new Database(SQLite.this);

        ArrayList<String> list = database.getAll();
        MyAdapters myAdapters=new MyAdapters(SQLite.this,list);
        mList.setAdapter(myAdapters);


        mInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.insert(mName.getText().toString(), mSubject.getText().toString());
            }
        });

        mFetchAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list = database.getAll();
                MyAdapters myAdapters=new MyAdapters(SQLite.this,list);
                mList.setAdapter(myAdapters);
            }
        });

        mSelectSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list = database.getSubjectData(mEdtsubject.getText().toString());
                MyAdapters myAdapters=new MyAdapters(SQLite.this,list);
                mList.setAdapter(myAdapters);
            }
        });


    }
}
