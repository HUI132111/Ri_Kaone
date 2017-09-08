package com.bwie.aizhonghui.ri_kaone.MyBroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by DANGEROUS_HUI on 2017/9/5.
 */

public class Myreceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("kson.test")) {
            //走收到广播的逻辑
        }
    }
}
