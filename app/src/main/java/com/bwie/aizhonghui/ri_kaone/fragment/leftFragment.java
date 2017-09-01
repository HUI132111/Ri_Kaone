package com.bwie.aizhonghui.ri_kaone.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bwie.aizhonghui.ri_kaone.Otherdeng;
import com.bwie.aizhonghui.ri_kaone.R;

/**
 * Created by DANGEROUS_HUI on 2017/8/30.
 */

public class leftFragment extends Fragment{
    private View mRootView;
    private Button btnother;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(mRootView==null){
            mRootView=inflater.inflate(R.layout.left_menu_content,container,false);
            btnother=mRootView.findViewById(R.id.btn_other);
            btnother.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().startActivity(new Intent(getContext(), Otherdeng.class));
                }
            });
        }
        return mRootView;
    }
}
