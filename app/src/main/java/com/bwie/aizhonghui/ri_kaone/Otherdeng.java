package com.bwie.aizhonghui.ri_kaone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Otherdeng extends AppCompatActivity {
   private TextView tvx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otherdeng);
        initView();
        initOnclick();
    }

    private void initOnclick() {
        tvx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        tvx= (TextView) findViewById(R.id.tv_xx);
    }
}
