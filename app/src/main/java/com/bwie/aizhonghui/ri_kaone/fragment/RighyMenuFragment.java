package com.bwie.aizhonghui.ri_kaone.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.aizhonghui.ri_kaone.R;

/**
 * Created by DANGEROUS_HUI on 2017/8/30.
 */

public class RighyMenuFragment extends Fragment{
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView==null){
            mRootView=inflater.inflate(R.layout.right_menu_layout,container,false);
        }
        return mRootView;
    }
}
