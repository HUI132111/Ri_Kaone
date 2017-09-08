package com.bwie.aizhonghui.ri_kaone.MySqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bwie.aizhonghui.ri_kaone.Bean.LxSj;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DANGEROUS_HUI on 2017/9/5.
 */

public class SqliteUtils {
    private  MySqliteHelper helper;
    private List<LxSj> list;

    public SqliteUtils(Context context) {
         helper=new MySqliteHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
    }
    public void add(String type,String content){
        SQLiteDatabase db = helper.getWritableDatabase();
        //create table toutitle (type varchar,content text)");
        ContentValues values=new ContentValues();
        values.put("type",type);
        values.put("content",content);
        db.insert("toutitle",null,values);
        db.close();
    }
    public void delete(String type){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("toutitle","type=?",new String[]{type});
        db.close();
    }
    public List<LxSj> select(){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("toutitle", null, null, null, null, null, null);
        list=new ArrayList<>();
        while(cursor.moveToNext()){
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            LxSj ls=new LxSj();
            ls.lxtype=type;
            ls.lxcontent=content;
            list.add(ls);
        }
        return list;
    }
}
