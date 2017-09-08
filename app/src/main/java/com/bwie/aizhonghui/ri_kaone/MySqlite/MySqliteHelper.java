package com.bwie.aizhonghui.ri_kaone.MySqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DANGEROUS_HUI on 2017/9/5.
 */

public class MySqliteHelper extends SQLiteOpenHelper{
    public MySqliteHelper(Context context) {
        super(context,"Todaytou",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("走了Oncreate方法");
        db.execSQL("create table toutitle (type varchar,content text)");
        System.out.println("走了第二个Oncreate方法");
        db.execSQL("create table touheng (tvtitle text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
