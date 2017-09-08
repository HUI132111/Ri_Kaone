package com.bwie.aizhonghui.ri_kaone.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.bwie.aizhonghui.ri_kaone.LxActivity;
import com.bwie.aizhonghui.ri_kaone.R;
import com.example.city_picker.CityListActivity;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by DANGEROUS_HUI on 2017/8/30.
 */

public class RighyMenuFragment extends Fragment implements View.OnClickListener{
    private View mRootView;
    private RelativeLayout rewifi;
    private RelativeLayout lixian;
    private RelativeLayout banben;
    private Switch aSwitch;
//    private   AlertDialog.Builder b;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView==null){
            mRootView=inflater.inflate(R.layout.right_menu_content,container,false);
        }
        initView();
        return mRootView;
    }

    private void initView() {
        rewifi=mRootView.findViewById(R.id.re_wifi);
        lixian=mRootView.findViewById(R.id.re_lixian);
        banben=mRootView.findViewById(R.id.re_banben);
        aSwitch=mRootView.findViewById(R.id.st_switch);
        banben.setOnClickListener(this);
        rewifi.setOnClickListener(this);
        lixian.setOnClickListener(this);
        //Switch的监听事件
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getActivity(),"打开",Toast.LENGTH_SHORT).show();
                    JPushInterface.resumePush(getActivity());
                }else{
                    Toast.makeText(getActivity(),"关闭",Toast.LENGTH_SHORT).show();
                    JPushInterface.stopPush(getActivity());
                }
            }
        });
//        b=new AlertDialog.Builder(getActivity());
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.re_wifi:
                new AlertDialog.Builder(getActivity()).setSingleChoiceItems(new String[]{"大图", "无图"}, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i==0){
                            Toast.makeText(getActivity(),"大图",Toast.LENGTH_SHORT).show();

                        }else if(i==1){
                            Toast.makeText(getActivity(),"无图",Toast.LENGTH_SHORT).show();
                        }
                        dialogInterface.dismiss();
                    }
                }).show();
                break;
            case R.id.re_lixian:
                startActivity(new Intent(getActivity(), LxActivity.class));
                break;
            case R.id.re_banben:
                getActivity().startActivity(new Intent(getActivity(), CityListActivity.class));
                break;

        }
    }
}
