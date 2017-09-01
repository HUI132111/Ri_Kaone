package com.bwie.aizhonghui.ri_kaone;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.aizhonghui.ri_kaone.Adapter.MyAdapter;
import com.bwie.aizhonghui.ri_kaone.Bean.CategoryBean;
import com.bwie.aizhonghui.ri_kaone.Bean.Mybean;
import com.bwie.aizhonghui.ri_kaone.fragment.Fragmentcaijing;
import com.bwie.aizhonghui.ri_kaone.fragment.Fragmentjunshi;
import com.bwie.aizhonghui.ri_kaone.fragment.Fragmentkeji;
import com.bwie.aizhonghui.ri_kaone.fragment.Fragmentshehui;
import com.bwie.aizhonghui.ri_kaone.fragment.Fragmentshishang;
import com.bwie.aizhonghui.ri_kaone.fragment.Fragmenttiyu;
import com.bwie.aizhonghui.ri_kaone.fragment.Fragmenttop;
import com.bwie.aizhonghui.ri_kaone.fragment.Fragmentyule;
import com.bwie.aizhonghui.ri_kaone.fragment.RighyMenuFragment;
import com.bwie.aizhonghui.ri_kaone.fragment.leftFragment;
import com.bwie.aizhonghui.ri_kaone.view.HorizontalScollTabhost;
import com.kson.slidingmenu.SlidingMenu;
import com.kson.slidingmenu.app.SlidingFragmentActivity;

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
public class MainActivity extends SlidingFragmentActivity implements View.OnClickListener {
@ViewInject(R.id.iv_sr)
    ImageView ivsr;
@ViewInject(R.id.iv_cd)
    ImageView  ivcd;
    private HorizontalScollTabhost mTabhost;
    private List<CategoryBean> beans;
    private List<Fragment> fragmentList;
    private String url="http://v.juhe.cn/toutiao/index";
    private String key="22a108244dbb8d1f49967cd74a0c144d";
    private  SlidingMenu menu;
    public void onCreate(Bundle state) {
        super.onCreate(state);
        //setContentView(R.layout.activity_main);
        x.view().inject(this);
        initView();
        initData();
        initSliding();

    }

    private void initData() {
        fragmentList=new ArrayList<>();
        beans=new ArrayList<>();
        CategoryBean bean=new CategoryBean();
        bean.id="top";
        bean.name="头条";
        beans.add(bean);
        bean = new CategoryBean();
        bean.name = "娱乐";
        bean.id = "yule";
        beans.add(bean);
        bean = new CategoryBean();
        bean.name = "社会";
        bean.id = "shehui";
        beans.add(bean);
        bean = new CategoryBean();
        bean.name = "体育";
        bean.id = "tiyu";
        beans.add(bean);
        bean = new CategoryBean();
        bean.name = "科技";
        bean.id = "keji";
        beans.add(bean);
        bean = new CategoryBean();
        bean.name = "财经";
        bean.id = "caijing";
        beans.add(bean);
        bean = new CategoryBean();
        bean.name = "时尚";
        bean.id = "shishang";
        beans.add(bean);
        bean = new CategoryBean();
        bean.name = "军事";
        bean.id = "junshi";
        beans.add(bean);
        fragmentList.add(new Fragmenttop());
        fragmentList.add(new Fragmentyule());
        fragmentList.add(new Fragmentshehui());
        fragmentList.add(new Fragmenttiyu());
        fragmentList.add(new Fragmentkeji());
        fragmentList.add(new Fragmentcaijing());
        fragmentList.add(new Fragmentshishang());
        fragmentList.add(new Fragmentjunshi());
        mTabhost.ccc(beans,fragmentList);
    }


    private void initSliding() {
        //添加左菜单
        //setBehindContentView(R.layout.left_menu_content);
        //getSupportFragmentManager().beginTransaction().replace(R.id.left_menu_content, new leftFragment()).commit();
        setBehindContentView(R.layout.left_menu_layout);
        getSupportFragmentManager().beginTransaction().replace(R.id.left_menu_layout, new leftFragment()).commit();

        //设置slidingmenu相关属性
        menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setBehindOffsetRes(R.dimen.BehindOffsetRes);
        //设置右菜单
        menu.setSecondaryMenu(R.layout.right_menu_content);
        getSupportFragmentManager().beginTransaction().replace(R.id.right_menu_content,new RighyMenuFragment()).commit();

    }






    private void initView() {
        mTabhost= (HorizontalScollTabhost) findViewById(R.id.tabhost);  
        ivsr.setOnClickListener(this);
        ivcd.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_sr:
                menu.showMenu();
                break;
            case R.id.iv_cd:
                menu.showSecondaryMenu();
                break;
        }
    }
}
