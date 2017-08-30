package com.bwie.aizhonghui.ri_kaone.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.aizhonghui.ri_kaone.Bean.Mybean;
import com.bwie.aizhonghui.ri_kaone.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by DANGEROUS_HUI on 2017/8/30.
 */

public class MyAdapter extends BaseAdapter{
    private Context context;
    private List<Mybean> list;
    private final int type_num=2;
    private final int typea=0;
    private final int typeb=1;

    public MyAdapter(Context context, List<Mybean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2==1){
            return typea;
        }else{
            return typeb;
        }
    }

    @Override
    public int getViewTypeCount() {
        return type_num;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        ViewHolder holder=null;
        ViewHolder2 holder2=null;
        if(view==null){
            switch (type){
                case typea:
                    holder=new ViewHolder();
                    view=View.inflate(context, R.layout.itemone,null);
                    holder.titleone=view.findViewById(R.id.tv_titleone);
                    holder.dataone=view.findViewById(R.id.tv_dataone);
                    holder.namenoe=view.findViewById(R.id.tv_namenone);
                    holder.imgone=view.findViewById(R.id.iv_imgone);
                    view.setTag(holder);
                    break;
                case typeb:
                    holder2=new ViewHolder2();
                    view=View.inflate(context,R.layout.itemtwo,null);
                    holder2.titletwo=view.findViewById(R.id.tv_titletwo);
                    holder2.datatwo=view.findViewById(R.id.tv_datatwo);
                    holder2.nametwo=view.findViewById(R.id.tv_nametwo);
                    holder2.imgtwo=view.findViewById(R.id.iv_imgtwo);
                    view.setTag(holder2);
                    break;
            }
        }else{
            switch (type){
                case typea:
                    holder= (ViewHolder) view.getTag();
                    break;
                case typeb:
                    holder2= (ViewHolder2) view.getTag();
                    break;
            }
        }
        switch (type){
            case typea:
                holder.titleone.setText(list.get(i).title);
                holder.namenoe.setText(list.get(i).author_name);
                holder.dataone.setText(list.get(i).date);
                ImageLoader.getInstance().displayImage(list.get(i).pics,holder.imgone);
                break;
            case typeb:
                holder2.titletwo.setText(list.get(i).title);
                holder2.nametwo.setText(list.get(i).author_name);
                holder2.datatwo.setText(list.get(i).date);
                ImageLoader.getInstance().displayImage(list.get(i).pics,holder2.imgtwo);
                break;
        }
        return view;
    }
    class ViewHolder{
        public TextView titleone;
        public TextView namenoe;
        public TextView dataone;
        public ImageView imgone;
    }
    class ViewHolder2{
        public TextView titletwo;
        public TextView nametwo;
        public TextView datatwo;
        public ImageView imgtwo;
    }
}
