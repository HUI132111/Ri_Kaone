package com.bwie.aizhonghui.ri_kaone;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.aizhonghui.ri_kaone.Bean.User;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class Otherdeng extends AppCompatActivity implements View.OnClickListener{
    private ImageView qqlogin;
    private TextView tvx;
    private EditText mTel,mCodeInput;
    private TextView yzm;
    private Button mSend;
    private EventHandler eventHandler;

    private  int TIME=5;
    private final int SECOND=1000;

    Handler timerHandler=new Handler();

    Runnable timeRunable=new Runnable() {
        @Override
        public void run() {
         TIME--;
            if(TIME==0){
              timerHandler.removeCallbacks(this);
                TIME=5;
                yzm.setEnabled(true);
                yzm.setText("再次获取");
            }else{
                yzm.setEnabled(false);
                yzm.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                yzm.setText(TIME+"s");
                timerHandler.postDelayed(this,SECOND);

            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otherdeng);

//        View decorView=getWindow().getDecorView();
//        int option=View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(option);
//        ActionBar actionBar=getSupportActionBar();
//        actionBar.hide();

        setTitle("第三方登录");
        initView();
        registerSMS();
        initOnclick();
    }

    private void registerSMS() {
        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable)data;
                    final String msg = throwable.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Otherdeng.this,msg,Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    if(result==SMSSDK.RESULT_COMPLETE){
                        //回调完成，提交验证码成功
                        if(event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Otherdeng.this,"服务器验证成功",Toast.LENGTH_SHORT).show();
                                    User user=new User();
                                    user.uid=mTel.getText().toString();
                                    user.phone=mTel.getText().toString();
                                }
                            });

                        }
                    }else if(event==SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Otherdeng.this,"验证码已发送",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if(event==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){

                    }
                }
            }
        };
           SMSSDK.registerEventHandler(eventHandler);
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
        qqlogin= (ImageView) findViewById(R.id.iv_qqlogin);
        tvx= (TextView) findViewById(R.id.tv_xx);
        mTel= (EditText) findViewById(R.id.et_tel);
        mCodeInput= (EditText) findViewById(R.id.et_code_input);
        yzm= (TextView) findViewById(R.id.et_yzm);
        mSend= (Button) findViewById(R.id.send);
        yzm.setOnClickListener(this);
        mSend.setOnClickListener(this);
        qqlogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send:
                verify();
                SMSSDK.submitVerificationCode("86",mTel.getText().toString(),mCodeInput.getText().toString());
                break;
            case R.id.et_yzm:
                if (TextUtils.isEmpty(mTel.getText().toString())) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                timerHandler.postDelayed(timeRunable,SECOND);
                SMSSDK.getVerificationCode("86", mTel.getText().toString());

                break;
            case R.id.iv_qqlogin:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ,umAuthListener);
                break;
        }
    }
    private void verify() {
        if (TextUtils.isEmpty(mTel.getText().toString())) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mCodeInput.getText().toString())) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        //Toast.makeText(Otherdeng.this,"成功",Toast.LENGTH_SHORT).show();
    }
    UMAuthListener umAuthListener=new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            System.out.println("uid======"+map.get("uid"));
            System.out.println("name======"+map.get("name"));
            System.out.println("gender======"+map.get("gender"));
            System.out.println("iconurl======"+map.get("iconurl"));
            ImageLoader.getInstance().displayImage(map.get("iconurl"),qqlogin);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }
}
