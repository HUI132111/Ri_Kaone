package com.bwie.aizhonghui.ri_kaone.MySqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bwie.aizhonghui.ri_kaone.Bean.LxSj;
import com.bwie.aizhonghui.ri_kaone.Bean.TVtitle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DANGEROUS_HUI on 2017/9/7.
 */

public class SqliteUtilsheng {
    private  MySqliteHelper helper;
    private List<TVtitle> list;

    public SqliteUtilsheng(Context context) {
        helper=new MySqliteHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
    }
    public void add(String mytitle){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("tvtitle",mytitle);
        db.insert("touheng",null,values);
        db.close();
    }
    public void delete(){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("touheng",null,null);
        db.close();
    }
    public String select(){
        String kkk=null;
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("touheng", null, null, null, null, null, null);
       //list=new ArrayList<>();
        while(cursor.moveToNext()){
            String tvtitle = cursor.getString(cursor.getColumnIndex("tvtitle"));
            kkk=tvtitle;
        }
        return  kkk;
    }
}
