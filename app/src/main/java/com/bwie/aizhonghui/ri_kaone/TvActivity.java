package com.bwie.aizhonghui.ri_kaone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.bwie.aizhonghui.ri_kaone.R;

import java.util.ArrayList;
import java.util.List;

public class TvActivity extends AppCompatActivity {
    private List<ChannelBean> list;
    private TextView tTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);
        initView();
    }

    private void initView() {
        tTv= (TextView) findViewById(R.id.tv_TV);
    }


}
