package com.example.newbanner.adapter;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newbanner.R;
import com.example.newbanner.entity.Menu_user;

import java.util.List;

/**
 * Created by asus1 on 2018/2/13.
 */

public class ShowPaiHangAdapter extends RecyclerView.Adapter<ShowPaiHangAdapter.ViewHolder> {

    List<Menu_user>menus;

    public ShowPaiHangAdapter(List menus) {
        this.menus=menus;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paihangbang,null));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Menu_user menu_user=menus.get(position);
        holder.user_head.setImageResource(menu_user.getIcon());
        holder.take_num.setText(menu_user.getTakeNum());
        holder.user_name.setText(menu_user.getUser_name());
    }

    @Override
    public int getItemCount() {
        return menus!=null?menus.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView user_head;
        TextView user_name;
        TextView take_num;
        public ViewHolder(View itemView) {
            super(itemView);
            user_head=(ImageView)itemView.findViewById(R.id.imageview_menu_userhead);
            user_name=(TextView)itemView.findViewById(R.id.textview_menu_username);
            take_num=(TextView)itemView.findViewById(R.id.textview_menu_usernum);
        }
    }
}
