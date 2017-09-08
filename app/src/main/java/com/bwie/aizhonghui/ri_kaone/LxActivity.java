package com.bwie.aizhonghui.ri_kaone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.aizhonghui.ri_kaone.Adapter.MyReadapter;
import com.bwie.aizhonghui.ri_kaone.Bean.Api;
import com.bwie.aizhonghui.ri_kaone.Bean.Catogray;
import com.bwie.aizhonghui.ri_kaone.Bean.LxSj;
import com.bwie.aizhonghui.ri_kaone.MySqlite.SqliteUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class LxActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView lxxz;
    private ImageView lxh;
    private RecyclerView cv;
    private List<Catogray> list;
    private SqliteUtils dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lx);
        initView();
        initData();
    }

    private void initUtils(final String type) {
        RequestParams params=new RequestParams(Api.NEWS);
        params.addParameter("key",Api.APPKEY);
        params.addParameter("type",type);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("_____下载成功_____"+result);
                List<LxSj> select = dao.select();
                for (LxSj lxSj : select) {
                    if(lxSj.lxtype.equals(type)){
                        dao.delete(type);
                    }
                }
                dao.add(type,result);
                System.out.println("存入成功");
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

    private void initData() {
        list=new ArrayList<>();
        Catogray c = new Catogray();
        c.type = "top";
        c.name = "头条";
        list.add(c);
        c = new Catogray();
        c.type = "yule";
        c.name = "娱乐";
        list.add(c);
        c = new Catogray();
        c.type = "shehui";
        c.name = "社会";
        list.add(c);
        c = new Catogray();
        c.type = "tiyu";
        c.name = "体育";
        list.add(c);
        c = new Catogray();
        c.type = "keji";
        c.name = "科技";
        list.add(c);
        c = new Catogray();
        c.type = "caijing";
        c.name = "财经";
        list.add(c);
        c = new Catogray();
        c.type = "shishang";
        c.name = "时尚";
        list.add(c);
        c = new Catogray();
        c.type = "junshi";
        c.name = "军事";
        list.add(c);

        MyReadapter macy=new MyReadapter(this,list);
        cv.setLayoutManager(new LinearLayoutManager(this));
        cv.setAdapter(macy);
        macy.setOnItemClickListener(new MyReadapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int pos, View view) {
                Toast.makeText(LxActivity.this,"sfsdsdfsdfs",Toast.LENGTH_SHORT).show();
                CheckBox checkbox=view.findViewById(R.id.lx_ck);
                Catogray c=list.get(pos);
                if(checkbox.isChecked()){
                    checkbox.setChecked(false);
                    System.out.println("___false____");
                    c.state=false;
                }else{
                    checkbox.setChecked(true);
                    System.out.println("___true____");
                    c.state=true;
                }
                list.set(pos,c);
            }
        });
    }

    private void initView() {
        dao=new SqliteUtils(LxActivity.this);
        lxxz= (TextView) findViewById(R.id.tv_lxxz);
        lxh= (ImageView) findViewById(R.id.iv_lxfinish);
        cv= (RecyclerView) findViewById(R.id.lv_cv);
        lxxz.setOnClickListener(this);
        lxh.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_lxfinish:
                List<LxSj> select = dao.select();
                for (LxSj lxSj : select) {
                    System.out.println("========="+lxSj.lxcontent);
                }
                break;
            case R.id.tv_lxxz:
                Toast.makeText(LxActivity.this,"sfsdsdfsdfs",Toast.LENGTH_SHORT).show();
                if(list!=null&&list.size()>0){
                    for (Catogray catogray : list) {
                        if(catogray.state){
                            System.out.println("______"+catogray.name);
                            initUtils(catogray.type);
                        }
                    }
                }
                for (Catogray catogray : list) {
                    System.out.println("___状态____"+catogray.state);
                }
                break;
        }
    }
}
