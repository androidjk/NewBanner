package com.example.newbanner.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newbanner.R;
import com.example.newbanner.TakeActivity;
import com.example.newbanner.entity.TakeMenuList;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by asus1 on 2018/2/22.
 */

public class RecycleviewAdapter extends RecyclerView.Adapter<RecycleviewAdapter.RecouseHolder>{

    List<TakeMenuList>lists;
    Context context;

    public RecycleviewAdapter(Context context,List<TakeMenuList>lists) {
        super();
        this.context=context;
        this.lists=lists;
    }

    @Override
    public RecouseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecouseHolder(LayoutInflater.from(context).inflate(R.layout.take_recycleview,null));
    }

    @Override
    public void onBindViewHolder(RecouseHolder holder, int position) {

        TakeMenuList takeMenuList=lists.get(position);

        holder.circleImageView.setImageResource(takeMenuList.getCircleImageViewId());
        holder.begin.setText(takeMenuList.getBeginTextView());
        holder.end.setText(takeMenuList.getEndTextView());
        holder.goal.setText(takeMenuList.getGoal());
    }

    @Override
    public int getItemCount() {
        return lists==null?0:lists.size();
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
}
