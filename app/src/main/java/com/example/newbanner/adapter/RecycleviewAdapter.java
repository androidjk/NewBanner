package com.example.newbanner.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newbanner.R;
import com.example.newbanner.bean.ExpressHelp;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by asus1 on 2018/2/22.
 */

public class RecycleviewAdapter extends RecyclerView.Adapter<RecycleviewAdapter.RecouseHolder> implements View.OnClickListener{

    List<ExpressHelp>lists;
    Context context;

    public RecycleviewAdapter(Context context,List<ExpressHelp>lists) {
        super();
        this.context=context;
        this.lists=lists;
    }

    @Override
    public RecouseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.take_recycleview,null);
        RecouseHolder recouseHolder=new RecouseHolder(view);
        view.setOnClickListener(this);
        return recouseHolder;
    }

    @Override
    public void onBindViewHolder(final RecouseHolder holder, int position) {

        ExpressHelp expressHelp=lists.get(position);

//        Glide.with(context)
//                .load(expressHelp)
//                .into(holder.circleImageView);
        holder.begin.setText(expressHelp.getPointName());
        holder.end.setText(expressHelp.getAddressAccuracy());
        holder.goal.setText(expressHelp.getWeight());
        holder.itemView.setTag(position);//将position保存在itemView的Tag中，以便点击时进行获取
    }

    @Override
    public int getItemCount() {
        return lists==null?0:lists.size();
    }

    @Override
    public void onClick(View v) {
        if (clickListener!=null){
           clickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public class RecouseHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView begin,end,goal;
        public RecouseHolder(View itemView) {
            super(itemView);
            circleImageView=(CircleImageView)itemView.findViewById(R.id.take_menu_head);
            begin=(TextView)itemView.findViewById(R.id.take_menu_home);
            end=(TextView)itemView.findViewById(R.id.take_menu_end);
            goal=(TextView)itemView.findViewById(R.id.take_menu_jifen);
        }
    }
    private OnItemClickListener clickListener=null;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        clickListener = onItemClickListener;
    }
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
}

