package com.bwie.aizhonghui.ri_kaone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwie.aizhonghui.ri_kaone.Adapter.MyAdapter;
import com.bwie.aizhonghui.ri_kaone.Bean.Mybean;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import view.xlistview.XListView;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
@ViewInject(R.id.lv_xlv)
    XListView lvv;
    private String url="http://v.juhe.cn/toutiao/index";
    private String key="22a108244dbb8d1f49967cd74a0c144d";
    private List<Mybean> mblist=new ArrayList<>();
    private MyAdapter ma;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        x.view().inject(this);
        initpost();
       // initSliding();
    }

    private void initSliding() {

    }

    private void initpost() {
        RequestParams params=new RequestParams(url);
        params.addBodyParameter("key",key);
        params.addBodyParameter("type","yule");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("___post___"+result);
                JSONjie(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private void JSONjie(String result) {
        try {
            JSONObject obj = new JSONObject(result);
            JSONObject result1 = obj.getJSONObject("result");
            JSONArray data = result1.getJSONArray("data");
            if(data!=null&&data.length()>0){
                for (int i = 0; i <data.length(); i++) {
                    JSONObject js = data.getJSONObject(i);
                    Mybean mb=new Mybean();
                    mb.title=js.optString("title");
                    mb.author_name=js.optString("author_name");
                    mb.date=js.optString("date");
                    mb.pics=js.optString("thumbnail_pic_s");
                    mblist.add(mb);
                }
            }if(mblist!=null){
               setDate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDate() {
        ma=new MyAdapter(MainActivity.this,mblist);
        lvv.setAdapter(ma);
    }
}
