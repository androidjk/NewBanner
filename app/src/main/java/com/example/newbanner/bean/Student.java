package com.example.newbanner.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2018/2/7.
 */

public class Student extends BmobUser {
    private String sex;
    private String name;
    private String idNum;
    private Integer age;
    private String telNum;
    private String collega;
    private String className;
    private String nickUrl;
    public int getSum() {
        return sum;
    }

    public Student(){
        sum = 0;
        helpSum= 5;
    }
    public void setSum(int sum) {
        this.sum = sum;
    }

    private int sum;
    public int getHelpSum() {
        return helpSum;
    }

    public void setHelpSum(int helpSum) {
        this.helpSum = helpSum;
    }
    public Student(int sum, int helpSum){
        this.sum =sum;
        this.helpSum = helpSum;
    }
    private int helpSum;
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getCollega() {
        return collega;
    }

    public void setCollega(String collega) {
        this.collega = collega;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getNickUrl() {
        return nickUrl;
    }

    public void setNickUrl(String nickUrl) {
        this.nickUrl = nickUrl;
    }
}
