package com.example.newbanner.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by asus1 on 2018/2/10.
 */

public class Person extends BmobObject {
    private String name;
    private BmobFile bmobFile;
    public Person(String name,BmobFile file){
        this.bmobFile=file;
        this.name=name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public void setBmobFile(BmobFile file){
        this.bmobFile=file;
    }
    public BmobFile getBmobFile(){
        return this.bmobFile;
    }
}
