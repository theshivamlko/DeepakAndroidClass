package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context, "MyDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.e("Database", "onCreate");
        db.execSQL("CREATE TABLE STUDENT(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SUBJECT TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    void insert(String name, String subject) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e("Database", "insert" + name + " " + subject);

        db.execSQL("INSERT INTO STUDENT(NAME,SUBJECT) VALUES('" + name + "','" + subject + "')");


    }


    ArrayList<String> getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e("Database", "getAll");

        Cursor cursor = db.rawQuery("SELECT * FROM STUDENT", null);

        if (cursor.moveToNext()) {
            Log.e("Database", "getAll Has List");

            ArrayList<String> list = new ArrayList<>();
            do {
                String id = cursor.getString(0);
                String n = cursor.getString(1);
                String s = cursor.getString(2);

                String sum = id + " " + n + " " + s;
                list.add(sum);

            } while (cursor.moveToNext());

            return  list;


        } else {
            Log.e("Database", "getAll No List");
            return new ArrayList<>();
        }

    }
    ArrayList<String> getSubjectData(String subject) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e("Database", "getAll");

        Cursor cursor = db.rawQuery("SELECT * FROM STUDENT WHERE SUBJECT='"+subject+"'", null);

        if (cursor.moveToNext()) {
            Log.e("Database", "getSubjectData Has List");

            ArrayList<String> list = new ArrayList<>();
            do {
                String id = cursor.getString(0);
                String n = cursor.getString(1);
                String s = cursor.getString(2);

                String sum = id + " " + n + " " + s;
                list.add(sum);

            } while (cursor.moveToNext());

            return  list;


        } else {
            Log.e("Database", "getAll No List");
            return new ArrayList<>();
        }

    }
}
