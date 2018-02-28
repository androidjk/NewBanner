package com.example.newbanner.Bmobentity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by asus1 on 2018/2/27.
 */

public class TakeMenu extends BmobObject {
    private BmobFile userheadImage;//用户头像
    private String userId;//用户Id
    private String menuEnd;//快递要送达的地方
    private String menuBegin;//取快递的快递点
    private String weight;//快递的重量

    public TakeMenu(BmobFile userheadImage,String userId,String menuEnd,String menuBegin,String weight) {
        super();
        this.userheadImage=userheadImage;
        this.userId=userId;
        this.menuEnd=menuEnd;
        this.menuBegin=menuBegin;
        this.weight=weight;
    }

    public BmobFile getUserheadImage() {
        return userheadImage;
    }

    public void setUserheadImage(BmobFile userheadImage) {
        this.userheadImage = userheadImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMenuEnd() {
        return menuEnd;
    }

    public void setMenuEnd(String menuEnd) {
        this.menuEnd = menuEnd;
    }

    public String getMenuBegin() {
        return menuBegin;
    }

    public void setMenuBegin(String menuBegin) {
        this.menuBegin = menuBegin;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }



}
