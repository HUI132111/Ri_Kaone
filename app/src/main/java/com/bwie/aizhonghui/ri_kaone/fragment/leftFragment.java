package com.bwie.aizhonghui.ri_kaone.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.aizhonghui.ri_kaone.Otherdeng;
import com.bwie.aizhonghui.ri_kaone.R;

/**
 * Created by DANGEROUS_HUI on 2017/8/30.
 */

public class leftFragment extends Fragment implements View.OnClickListener{
    private View mRootView;
    private Button btnother;
    private ImageView ivimg;
    private SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(mRootView==null){
            mRootView=inflater.inflate(R.layout.left_menu_content,container,false);
            sp=getActivity().getSharedPreferences("HIU",getActivity().MODE_PRIVATE);
            if(sp.getBoolean("reason",false)){
                ((AppCompatActivity)getActivity()).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }else{
                ((AppCompatActivity)getActivity()).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            initView();
            btnother.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().startActivity(new Intent(getContext(), Otherdeng.class));
                }
            });
        }
        return mRootView;
    }

    private void initView() {
        btnother=mRootView.findViewById(R.id.btn_other);
        ivimg=mRootView.findViewById(R.id.iv_yj);
        ivimg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_yj:
                if(sp.getBoolean("reason",false)){
                    ((AppCompatActivity)getActivity()).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    sp.edit().putBoolean("reason",false).commit();
                }else{
                    ((AppCompatActivity)getActivity()).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    sp.edit().putBoolean("reason",true).commit();
                }

                break;
        }
    }
}
