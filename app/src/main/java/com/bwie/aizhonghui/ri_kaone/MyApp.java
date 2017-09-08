package com.bwie.aizhonghui.ri_kaone;

import android.app.Application;
import android.content.Context;

import com.bwie.aizhonghui.ri_kaone.Bean.Constants;
import com.mob.MobSDK;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;
import cn.smssdk.SMSSDK;

/**
 * Created by DANGEROUS_HUI on 2017/8/30.
 */

public class MyApp extends Application{

    public static Context mContext;
    {
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        mContext=this;
        UMShareAPI.get(this);
        initutils();
        initSMSS();
        initImage();
    }

    private void initSMSS() {
        MobSDK.init(this, Constants.Appkey,Constants.Appsecret);
    }

    private void initImage() {
        DisplayImageOptions options=new DisplayImageOptions.Builder().build();
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(config);
    }

    private void initutils() {
        x.Ext.init(this);
        x.Ext.setDebug(false);
    }
}
