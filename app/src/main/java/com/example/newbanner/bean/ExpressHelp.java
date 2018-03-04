package com.example.newbanner.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;


/**
 * 快递订单
 */
public class ExpressHelp extends BmobObject implements Serializable {
    /**
     *发布者
     */
    private Student user;
    /**
     * 取货码
     */
    private String pickupCode;
    /**
     * 收件人
     */
    private String addressName;
    /**
     * 电话号码
     */
    private String addressTelephone;
    /**
     *收货地址
     */
    private String addressAccuracy;
    /**
     * 快递点名称
     */
    private String pointName;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 取货短信
     */
    private String expressSms;
    /**
     * 宿舍
     */
    private String dormitory;
    /**
     * 快递点
     */
    private String expressPoint;
    /**
     * 重量
     */
    private String weight;
    /**
     * 帮助用户
     */
    private Student HelpUser;
    /**
     * 发布时间
     */
    private long publishTime;

    public ExpressHelp(){

    }

    /**
     * 快递订单信息初始化
     * @param student
     * @param pointName
     * @param addressAccuracy
     * @param weight
     * @param pickupCode
     */
    public ExpressHelp(Student student,String pointName,String addressAccuracy,String weight,String pickupCode){
        this.user=student;
        this.pointName=pointName;
        this.addressAccuracy=addressAccuracy;
        this.weight=weight;
        this.pickupCode=pickupCode;
    }
    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
    /**
     * 是否完成帮助
     */
    private boolean state;

    public Student getUser(){return user;}

    public void setUser(Student user){this.user=user;}

    public String getPickupCode(){return pickupCode;}

    public void setPickupCode(String pickupCode){this.pickupCode = pickupCode;}

    public String getAddressName(){return addressName;}

    public void setAddressName(String addressName){this.addressName = addressName;}

    public String getAddressTelephone(){return  addressTelephone;}

    public void setAddressTelephone(String addressTelephone){this.addressTelephone = addressTelephone;}

    public String getAddressAccuracy(){return addressAccuracy;}

    public void setAddressAccuracy(String addressAccuracy){this.addressAccuracy = addressAccuracy;}

    public String getPointName(){return pointName;}

    public void setPointName(String pointName){this.pointName = pointName;}

    public String getRemarks(){return remarks;}

    public void setRemarks(String remarks){this.remarks = remarks;}

    public String getExpressSms(){return expressSms;}

    public void setExpressSms(String expressSms){this.expressSms = expressSms;}

    public String getDormitory(){return dormitory;}

    public void setDormitory(String dormitory){this.dormitory = dormitory;}

    public String getExpressPoint(){return expressPoint;}

    public void setExpressPoint(String expressPoint){this.expressPoint = expressPoint;}

    public String getWeight(){return weight;}

    public void setWeight(String weight){this.weight = weight;}

    public Student getHelpUser(){return HelpUser;}

    public void setHelpUser(Student HelpUser) {this.HelpUser = HelpUser;}

    public long getPublishTime() {return publishTime;}

    public void setPublishTime(long publishTime) {this.publishTime = publishTime;}
}
