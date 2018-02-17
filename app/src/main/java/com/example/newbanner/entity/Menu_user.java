package com.example.newbanner.entity;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by asus1 on 2018/2/13.
 */

public class Menu_user {
    int icon;
    String user_name;
    String takeNum;

    public Menu_user(int icon,String user_name,String takeNum) {
        this.icon=icon;
        this.user_name=user_name;
        this.takeNum=takeNum;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setTakeNum(String takeNum) {
        this.takeNum = takeNum;
    }

    public void setUser_name (String user_name) {
        this.user_name = user_name;
    }

    public int getIcon() {
        return icon;
    }

    public String getTakeNum() {
        return takeNum;
    }

    public String getUser_name() {
        return user_name;
    }
}
