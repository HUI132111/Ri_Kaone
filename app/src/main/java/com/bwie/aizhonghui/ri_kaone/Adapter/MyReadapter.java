package com.bwie.aizhonghui.ri_kaone.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.aizhonghui.ri_kaone.Bean.Catogray;
import com.bwie.aizhonghui.ri_kaone.R;

import java.util.List;

/**
 * Created by DANGEROUS_HUI on 2017/9/5.
 */

public class MyReadapter extends RecyclerView.Adapter<MyReadapter.MyViewHolder> {
    private Context context;
    private List<Catogray> list;
    private OnItemClickListener onItemClickListener;

    public MyReadapter(Context context, List<Catogray> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_recycle,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.lxtou.setText(list.get(position).name);
            //holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClickListener(position,view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
         private TextView lxtou;
         private CheckBox checkBox;
         public MyViewHolder(View itemView) {
             super(itemView);
             lxtou=itemView.findViewById(R.id.tv_lxtou);
             checkBox=itemView.findViewById(R.id.lx_ck);
         }
     }
     public void setOnItemClickListener(OnItemClickListener onItemClickListener){
         this.onItemClickListener=onItemClickListener;
     }

     public interface OnItemClickListener{
         void onItemClickListener(int pos,View view);
     }
}
