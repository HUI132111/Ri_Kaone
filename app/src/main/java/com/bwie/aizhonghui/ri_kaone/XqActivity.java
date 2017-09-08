package com.bwie.aizhonghui.ri_kaone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class XqActivity extends BaseActivity {
    private WebView wv;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xq);
        initView();
        initgetIntent();
        initLoadUrl();
    }

    private void initLoadUrl() {

        wv.loadUrl(url);
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
    }

    private void initgetIntent() {
        Intent intent = getIntent();
        url=intent.getStringExtra("murl");

    }

    private void initView() {
        wv= (WebView) findViewById(R.id.wv);
    }
}
