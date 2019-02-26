package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.myapplication.adapters.MyAdapters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListsActivity extends AppCompatActivity {

    private ListView mListview;

    String[] s={"SUN","MON","TUE","WED","THU","FRI","SAT"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        mListview = findViewById(R.id.listview);

        List<String> list=new ArrayList<>(Arrays.asList(s));


        MyAdapters myAdapters=new MyAdapters(ListsActivity.this,list);

        mListview.setAdapter(myAdapters);

    }
}
