package com.example.newbanner.bean;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;

/**
 * Created by asus1 on 2018/2/9.
 */

public class AdviceMessage extends BmobObject {
    private String adviceId;
    private String adviceContext;

    public void setAdviceId(String adviceName){
        this.adviceId=adviceName;
    }
    public String getAdviceId(){
        return this.adviceId;
    }
    public void setAdviceContext(String adviceContext){
        this.adviceContext=adviceContext;
    }
    public String getAdviceContext(){
        return this.adviceContext;
    }
}
