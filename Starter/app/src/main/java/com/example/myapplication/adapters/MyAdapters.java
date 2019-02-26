package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.HomePage;
import com.example.myapplication.LayoutActivity;
import com.example.myapplication.R;
import com.example.myapplication.TryCatch;

import java.util.List;

public class MyAdapters extends ArrayAdapter {

    List<String> list;

    Context context;
    public MyAdapters(  @NonNull Context context, List<String> list) {
        super(context, R.layout.item,list);
        this.list=list;
        this.context=context;
    }


     @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView=View.inflate(context,R.layout.item,null);
         TextView tv=convertView.findViewById(R.id.text);

        final String s=list.get(position);
         tv.setText(s);

         if(position%2==0){
             tv.setBackgroundColor(Color.BLUE);
         }


         tv.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(context,"Position "+position,Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(context, LayoutActivity.class);
                 intent.putExtra("name", s);
                 context.startActivity(intent);
             }
         });


        return convertView;
    }


    @Override
    public int getCount() {
        return list.size();
    }
}
