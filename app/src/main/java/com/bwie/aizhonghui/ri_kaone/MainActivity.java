package com.bwie.aizhonghui.ri_kaone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.bwie.aizhonghui.ri_kaone.Adapter.MyAdapter;
import com.bwie.aizhonghui.ri_kaone.Bean.CategoryBean;
import com.bwie.aizhonghui.ri_kaone.Bean.Mybean;
import com.bwie.aizhonghui.ri_kaone.Bean.TVtitle;
import com.bwie.aizhonghui.ri_kaone.MySqlite.SqliteUtilsheng;
import com.bwie.aizhonghui.ri_kaone.fragment.Fragmentcaijing;
import com.bwie.aizhonghui.ri_kaone.fragment.Fragmentguoji;
import com.bwie.aizhonghui.ri_kaone.fragment.Fragmentguonei;
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
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import view.xlistview.XListView;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
@ViewInject(R.id.iv_sr)
    ImageView ivsr;
@ViewInject(R.id.iv_cd)
    ImageView  ivcd;
    private HorizontalScollTabhost mTabhost;
    private SqliteUtilsheng sdao;
    public String jsonstr;
    private SharedPreferences sp;
    private List<ChannelBean> chanlist;
    private List<ChannelBean> mychanlist;
    private List<CategoryBean> beans;
    private List<Fragment> fragmentList;
    private String url="http://v.juhe.cn/toutiao/index";
    private String key="22a108244dbb8d1f49967cd74a0c144d";
    private Map<String,Fragment> map;
    private SlidingMenu menu;
    private ImageView ivjia;
    private String ctv="NO";
    public void onCreate(Bundle state) {
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(state);
        //setContentView(R.layout.activity_main);
        sendBroadcast(new Intent("kson.test"));
        x.view().inject(this);
        initSliding();
        initView();



    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("走了OnResume方法");
        initData();
    }

    private void initData() {
        map=new HashMap<>();
        map.put("头条",new Fragmenttop());
        map.put("娱乐",new Fragmentyule());
        map.put("社会",new Fragmentshehui());
        map.put("体育",new Fragmenttiyu());
        map.put("科技",new Fragmentkeji());
        map.put("财经",new Fragmentcaijing());
        map.put("时尚",new Fragmentshishang());
        map.put("军事",new Fragmentjunshi());
        map.put("国际",new Fragmentguoji());
        map.put("国内",new Fragmentguonei());
        fragmentList=new ArrayList<>();
        beans=new ArrayList<>();
        CategoryBean bean=new CategoryBean();
        String select = sdao.select();
        if(select!=null){
            System.out.println("====读取==="+select);
            List<ChannelBean> channelBeen = JSONlist(select);
            for (ChannelBean channelBean : channelBeen) {
               if(channelBean.isSelect()){
                   bean=new CategoryBean();
                   bean.name=channelBean.getName();
                   System.out.println("========飞起========"+bean.name);
                   beans.add(bean);
                   fragmentList.add(map.get(bean.name));
               }
            }
            mTabhost.ccc(beans,fragmentList);
            System.out.println("====存入成功sdfsdfdfdfdsf===");
        }
         else{
            System.out.println("====空数据===");
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
            bean = new CategoryBean();
            bean.name = "国际";
            bean.id = "guoji";
            beans.add(bean);
            bean = new CategoryBean();
            bean.name = "国内";
            bean.id = "guonei";
            beans.add(bean);
            fragmentList.add(new Fragmenttop());
            fragmentList.add(new Fragmentyule());
            fragmentList.add(new Fragmentshehui());
            fragmentList.add(new Fragmenttiyu());
            fragmentList.add(new Fragmentkeji());
            fragmentList.add( new Fragmentcaijing());
            fragmentList.add(new Fragmentcaijing());
            fragmentList.add(new Fragmentshishang());
            fragmentList.add(new Fragmentjunshi());
            fragmentList.add(new Fragmentguonei());
            mTabhost.ccc(beans,fragmentList);
        }
    }


    private void initSliding() {
        //添加左菜单
        //setBehindContentView(R.layout.left_menu_content);
        //getSupportFragmentManager().beginTransaction().replace(R.id.left_menu_content, new leftFragment()).commit();
        //设置slidingmenu相关属性
        menu =new SlidingMenu(this);
        menu.setMenu(R.layout.left_menu_layout);
        getSupportFragmentManager().beginTransaction().replace(R.id.left_menu_layout, new leftFragment()).commit();
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setBehindOffsetRes(R.dimen.BehindOffsetRes);
        //设置右菜单
        menu.setSecondaryMenu(R.layout.right_menu_layout);
        getSupportFragmentManager().beginTransaction().replace(R.id.right_menu_layout,new RighyMenuFragment()).commit();
        menu.attachToActivity(MainActivity.this,SlidingMenu.SLIDING_CONTENT);
    }



    private void initView() {
        mTabhost= (HorizontalScollTabhost) findViewById(R.id.tabhost);
        sdao=new SqliteUtilsheng(MainActivity.this);
        ivjia= (ImageView) findViewById(R.id.iv_jia);
        sp=getSharedPreferences("MYHUI",MODE_PRIVATE);
        System.out.println("====千===");
        String mctv = sp.getString("mctv", null);
         if(mctv!=null){
             ctv=mctv;
             System.out.println("====存入成功sdfsdfdfdfdsf===");
        }
        System.out.println("====后===");
        ivjia.setOnClickListener(this);
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
            case R.id.iv_jia:
                //jsonstr=sp.getString("user_setting",null);
                //if(jsonstr==null){
                if(ctv.equals("YES")){
                    String select = sdao.select();

                        System.out.println("====读取==="+select);
                        List<ChannelBean> channelBeen = JSONlist(select);
                        ChannelActivity.startChannelActivity(MainActivity.this,channelBeen);
                        System.out.println("====存入成功sdfsdfdfdfdsf===");


                }else if(ctv.equals("NO")){
                    chanlist=new ArrayList<ChannelBean>();
                    ChannelBean channelBean;
                    for (int i = 0; i < 10; i++) {
                        if(i<3){
                            channelBean=new ChannelBean(beans.get(i).name,true);
                        }else{
                            channelBean=new ChannelBean(beans.get(i).name,false);
                        }
                        chanlist.add(channelBean);
                    }
                    ChannelActivity.startChannelActivity(MainActivity.this,chanlist);
                }

                break;
        }
    }


    //解析数组类型Json
    private List<ChannelBean> JSONlist(String result) {
        try {
            JSONArray obj = new JSONArray(result);
            if(obj!=null&&obj.length()>0){
                mychanlist=new ArrayList<ChannelBean>();
                for (int i = 0; i <obj.length(); i++) {
                    JSONObject js = obj.getJSONObject(i);
                    String name = js.getString("name");
                    boolean isSelect = js.getBoolean("isSelect");
                    ChannelBean mcb=new ChannelBean(name,isSelect);
                    mychanlist.add(mcb);
                }
                return mychanlist;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         if(requestCode==ChannelActivity.REQUEST_CODE&&resultCode==ChannelActivity.RESULT_CODE){
             jsonstr=data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);
             sdao.delete();
             sdao.add(jsonstr);
             System.out.println("====存入成功==="+jsonstr);
             ctv="YES";
             sp.edit().putString("mctv",ctv).commit();
         }
//            jsonstr=data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);
//            System.out.println("======="+jsonstr);
//            sp.edit().putString("user_setting",jsonstr).commit();

    }
}
