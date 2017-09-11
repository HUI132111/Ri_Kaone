package com.bwie.aizhonghui.ri_kaone.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.bwie.aizhonghui.ri_kaone.Bean.CategoryBean;
import com.bwie.aizhonghui.ri_kaone.MainActivity;
import com.bwie.aizhonghui.ri_kaone.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DANGEROUS_HUI on 2017/8/31.
 */

public class HorizontalScollTabhost extends LinearLayout implements ViewPager.OnPageChangeListener {
    private Context mContext;
    private String mhc="NO";
    private int mBgColor;
    private List<CategoryBean> list;
    private List<Fragment> fragmentList;
    private List<TextView> topViews;
    private int count;
    private MyPager myPager;
    private LinearLayout mMenuLayout;
    private HorizontalScrollView hscrollview;
    private ViewPager viewpager;
    public HorizontalScollTabhost(Context context) {
      this(context,null);
    }

    public HorizontalScollTabhost(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public HorizontalScollTabhost(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        init(context,attrs);
    }
     //初始化自定义属性和view
    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.HorizontalScollTabhost);
        mBgColor=typedArray.getColor(R.styleable.HorizontalScollTabhost_top_background,0xfffffff);
        typedArray.recycle();
        initView();
    }

    private void initView() {
        View view= LayoutInflater.from(mContext).inflate(R.layout.horizontal_scroll_tabhost,this,true);
        hscrollview=view.findViewById(R.id.horizontalScrollView);
        viewpager=view.findViewById(R.id.viewpager_fragment);
        viewpager.addOnPageChangeListener(this);
        mMenuLayout=view.findViewById(R.id.layout_menu);


    }
    public void ccc(List<CategoryBean> list,List<Fragment> fragments){
        this.list=list;
        this.count=list.size();
        this.fragmentList=fragments;
        topViews=new ArrayList<>(count);
        drawUi();

    }

    private void drawUi() {
        drawHorizontal();
        drawViewpager();
    }
    //绘制viewpager
    private void drawViewpager() {
        if(myPager==null){
            myPager=new MyPager(((FragmentActivity)mContext).getSupportFragmentManager());
            viewpager.setAdapter(myPager);
        }else{
            mhc="YES";
            myPager.notifyDataSetChanged();
            topViews.get(0).setSelected(false);
            topViews.get(viewpager.getCurrentItem()).setSelected(true);
        }

    }
    //绘制横向滑动菜单
    private void drawHorizontal() {
        mMenuLayout.removeAllViews();
        mMenuLayout.setBackgroundColor(mBgColor);
        for (int i = 0; i <count; i++) {
            CategoryBean bean=(CategoryBean) list.get(i);
            final TextView tv= (TextView) View.inflate(mContext,R.layout.news_top_tv_item,null);
            tv.setText(bean.name);

            final int finalI=i;
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewpager.setCurrentItem(finalI);
                    //moveItemToCenter(tv);
                }
            });
            mMenuLayout.addView(tv);
            topViews.add(tv);

            topViews.get(0).setSelected(true);

        }

    }
    private void moveItemToCenter(TextView tv) {
        DisplayMetrics dm=getResources().getDisplayMetrics();
        int screenWidth=dm.widthPixels;
        int [] locations=new int[2];
        tv.getLocationInWindow(locations);
        int rbWidth=tv.getWidth();
        hscrollview.smoothScrollBy((locations[0]+rbWidth/2-screenWidth/2),0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(mMenuLayout!=null&&mMenuLayout.getChildCount()>0){
            for (int i = 0; i <mMenuLayout.getChildCount(); i++) {
                if(i==position){
                    mMenuLayout.getChildAt(i).setSelected(true);
                }else{
                    mMenuLayout.getChildAt(i).setSelected(false);
                }
            }
        }
        moveItemToCenter(topViews.get(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    class MyPager extends FragmentPagerAdapter{


        public MyPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
    }
}
