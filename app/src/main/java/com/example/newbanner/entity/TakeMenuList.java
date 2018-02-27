package com.example.newbanner.entity;

/**
 * Created by asus1 on 2018/2/22.
 */

public class TakeMenuList {

    private int circleImageViewId;
    private String beginTextView;
    private String endTextView;
    private String goal;

    public TakeMenuList(int circleImageViewId, String beginTextView, String endTextView, String goal) {
        super();
        this.beginTextView = beginTextView;
        this.circleImageViewId = circleImageViewId;
        this.endTextView = endTextView;
        this.goal = goal;
    }

    public int getCircleImageViewId() {
        return this.circleImageViewId;
    }

    public void setCircleImageViewId(int circleImageViewId) {
        this.circleImageViewId = circleImageViewId;
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
