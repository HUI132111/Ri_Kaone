package com.bwie.aizhonghui.ri_kaone.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwie.aizhonghui.ri_kaone.Adapter.MyAdapter;
import com.bwie.aizhonghui.ri_kaone.Bean.Mybean;
import com.bwie.aizhonghui.ri_kaone.MainActivity;
import com.bwie.aizhonghui.ri_kaone.R;

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

public class Fragmenttop extends Fragment implements XListView.IXListViewListener {
    private List<Mybean> mblist=new ArrayList<>();
    private List<Mybean> newlist;
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
        lvv=mRootView.findViewById(R.id.lv_xlv);
        lvv.setXListViewListener(this);
        lvv.setPullLoadEnable(true);
        lvv.setPullRefreshEnable(true);
        initpost();
        return mRootView;
    }
    private void initpost() {
        RequestParams params=new RequestParams(url);
        params.addBodyParameter("key",key);
        params.addBodyParameter("type","top");
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
        newlist=new ArrayList<>();
        for (int i = 0; i <nofify+10; i++) {
         newlist.add(mblist.get(i));
        }
        nofify+=10;
        ma=new MyAdapter(getActivity(),newlist);
        lvv.setAdapter(ma);
        lvv.stopRefresh();
    }

    @Override
    public void onRefresh() {
        mblist.clear();
        initpost();
    }

    @Override
    public void onLoadMore() {
        Toast.makeText(getActivity(),"已没有更多",Toast.LENGTH_SHORT).show();
        lvv.stopLoadMore();
    }
}
