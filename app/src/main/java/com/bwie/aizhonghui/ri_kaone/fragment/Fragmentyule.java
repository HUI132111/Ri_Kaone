package com.bwie.aizhonghui.ri_kaone.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bwie.aizhonghui.ri_kaone.Adapter.MyAdapter;
import com.bwie.aizhonghui.ri_kaone.Bean.LxSj;
import com.bwie.aizhonghui.ri_kaone.Bean.Mybean;
import com.bwie.aizhonghui.ri_kaone.MySqlite.SqliteUtils;
import com.bwie.aizhonghui.ri_kaone.R;
import com.bwie.aizhonghui.ri_kaone.XqActivity;
import com.bwie.aizhonghui.ri_kaone.utils.NetWorkInfoUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import view.xlistview.XListView;

/**
 * Created by DANGEROUS_HUI on 2017/8/31.
 */

public class Fragmentyule extends Fragment implements XListView.IXListViewListener, AdapterView.OnItemClickListener {
    private List<Mybean> mblist ;
    private List<Mybean> newlist;
    private SqliteUtils dao;
    private int nofify=0;
    private MyAdapter ma;
    private View mRootView;
    private XListView lvv;
    private String url="http://v.juhe.cn/toutiao/index";
    private String key="22a108244dbb8d1f49967cd74a0c144d";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView=View.inflate(getActivity(), R.layout.fragment_layout,null);
        dao=new SqliteUtils(getActivity());
        lvv=mRootView.findViewById(R.id.lv_xlv);
        lvv.setXListViewListener(this);
        lvv.setPullLoadEnable(true);
        lvv.setPullRefreshEnable(true);
        loadNewData();
        initpost();
        return mRootView;
    }

    private void loadNewData() {
        new NetWorkInfoUtils().vertify(getActivity(), new NetWorkInfoUtils.NetWork() {
            @Override
            public void netWifiVisible() {
                initpost();
            }

            @Override
            public void netUnVisible() {
                Toast.makeText(getActivity(),"头条无网",Toast.LENGTH_SHORT).show();
                List<LxSj> select = dao.select();
                for (LxSj lxSj : select) {
                    if(lxSj.lxtype.equals("yule")){
                        JSONjie(lxSj.lxcontent);
                    }
                }
            }

            @Override
            public void netMobileVisible() {

              //  Toast.makeText(getActivity(),"头条有网",Toast.LENGTH_SHORT).show();
                initpost();
            }
        });
    }

    private void initpost() {
        nofify=0;
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
            mblist=new ArrayList<>();
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
                    mb.url=js.optString("url");
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
        System.out.println("====总list的====="+mblist.size());
        newlist=new ArrayList<>();
        for (int i = 0; i <nofify+10; i++) {

            newlist.add(mblist.get(i));
        }
        nofify+=10;
        ma=new MyAdapter(getActivity(),newlist);
        lvv.setAdapter(ma);
        lvv.setOnItemClickListener(this);
        lvv.stopRefresh();
    }

    @Override
    public void onRefresh() {
        mblist.clear();
        initpost();
    }

    @Override
    public void onLoadMore() {
        if(nofify+10<mblist.size()){
            for (int i =nofify; i <nofify+10; i++) {
                newlist.add(mblist.get(i));
            }
            nofify+=10;
        }else{
            if(nofify<mblist.size()){
                for (int i =nofify; i <mblist.size(); i++) {
                    newlist.add(mblist.get(i));
                }
                int nk=mblist.size()-nofify;
                nofify+=nk;
            }else{
                Toast.makeText(getActivity(),"已没有更多",Toast.LENGTH_SHORT).show();
            }
        }
        lvv.stopLoadMore();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent in=new Intent(getContext(), XqActivity.class);
        in.putExtra("murl",mblist.get(i-1).url);
        getActivity().startActivity(in);
    }
}
