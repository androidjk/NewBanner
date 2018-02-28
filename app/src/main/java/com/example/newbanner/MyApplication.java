package com.example.newbanner;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;

import com.example.newbanner.other.GlideImageLoader;
import com.imnjh.imagepicker.PickerConfig;
import com.imnjh.imagepicker.SImagePicker;
import com.zxy.tiny.Tiny;

import org.litepal.LitePalApplication;

import cn.bmob.v3.Bmob;


/**
 * Created by Administrator on 2017/12/3.
 */

public class MyApplication extends LitePalApplication {
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this,"30cf7a949fe1da01b0c798b314a41dad");
        mContext=getApplicationContext();
        Tiny.getInstance().init(this);
        SImagePicker.init(new PickerConfig.Builder().setAppContext(this)
                .setImageLoader(new GlideImageLoader())
                .setToolbaseColor(Color.parseColor("#108de8"))
                .build());
    }

}
