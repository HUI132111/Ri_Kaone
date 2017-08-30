package com.bwie.aizhonghui.ri_kaone;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

/**
 * Created by DANGEROUS_HUI on 2017/8/30.
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initutils();
        initImage();
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
