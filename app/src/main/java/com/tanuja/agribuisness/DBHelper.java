package com.tanuja.agribuisness;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DBHelper extends SQLiteOpenHelper {
            public DBHelper( Context context) {
                super(context,"Login.DB",null,1);
            }

            @Override
            public void onCreate(SQLiteDatabase myDB) {

                myDB.execSQL("create Table users(username Text primary key,password Text)");
            }

            @Override
            public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {

                myDB.execSQL("drop Table if exists users");
            }

            public Boolean insertData(String username,String password)
            {
                SQLiteDatabase myDB = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("username",username);
                contentValues.put("password",password);
                long result = myDB.insert("users",null,contentValues);

                return result != -1;
            }

            public Boolean checkusername(String username)
            {
                SQLiteDatabase myDB = this.getWritableDatabase();
                @SuppressLint("Recycle") Cursor cursor = myDB.rawQuery("select * from users where username = ?",new String[]{username});

                return cursor.getCount() > 0;
            }

            public Boolean checkusernamePassword(String username,String password)
            {
                SQLiteDatabase myDB = this.getWritableDatabase();
                @SuppressLint("Recycle") Cursor cursor = myDB.rawQuery("select * from users where username = ? and password = ?",new String[]{username,password});

                return cursor.getCount() > 0;

            }
        }
