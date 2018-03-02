package com.example.newbanner.entity;

/**
 * Created by asus1 on 2018/2/22.
 */

public class TakeMenuList {

    private String beginTextView;//快递点
    private String endTextView;//快递送达地点
    private String goal;//积分
    private String nickUrl;//头像URL地址

    public TakeMenuList(String nickUrl, String beginTextView, String endTextView, String goal) {
        super();
        this.beginTextView = beginTextView;
        this.endTextView = endTextView;
        this.goal = goal;
        this.nickUrl=nickUrl;
    }
    public String getNickUrl() {
        return nickUrl;
    }

    public void setNickUrl(String nickUrl) {
        this.nickUrl = nickUrl;
    }

    public String getBeginTextView() {
        return this.beginTextView;
    }

    public void setBeginTextView(String beginTextView) {
        this.beginTextView = beginTextView;
    }

    public String getEndTextView() {
        return this.endTextView;
    }

    public void setEndTextView(String endTextView) {
        this.endTextView = endTextView;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

}
