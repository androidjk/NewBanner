package com.example.newbanner.bean;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;

/**
 * Created by asus1 on 2018/2/9.
 */

public class AdviceMessage extends BmobObject {
    private String adviceName;
    private String adviceContext;

    public void setAdviceName(String adviceName){
        this.adviceName=adviceName;
    }
    public String getAdviceName(){
        return this.adviceName;
    }
    public void setAdviceContext(String adviceContext){
        this.adviceContext=adviceContext;
    }
    public String getAdviceContext(){
        return this.adviceContext;
    }
}
