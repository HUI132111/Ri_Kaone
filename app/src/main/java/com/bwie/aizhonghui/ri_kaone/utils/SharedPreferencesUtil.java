package com.bwie.aizhonghui.ri_kaone.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.bwie.aizhonghui.ri_kaone.MyApp;

/**
 * Created by DANGEROUS_HUI on 2017/9/2.
 */

public class SharedPreferencesUtil {
    private final static String KEY="HUI_data";
    //得到SharedPreferences对象
    public static SharedPreferences getPreferences(){
        return MyApp.mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);
    }
    //存一行数据，uid
    public static void putPreferences(String key,String value){
        SharedPreferences.Editor mEditor=getPreferences().edit();
        mEditor.putString(key,value);
        mEditor.commit();
    }
    //获取uid的数据
    public static String getPreferencesValue(String key){
        return getPreferences().getString(key,"");
    }
    //清除指定数据
    public static void clearPreferences(String key){
        SharedPreferences.Editor mEditor = getPreferences().edit();
        mEditor.remove(key);
        mEditor.commit();
    }
    //清空所有数据
    public static void clearPreferences(){
        SharedPreferences.Editor mEditor = getPreferences().edit();
        mEditor.clear();
        mEditor.commit();
    }

}
