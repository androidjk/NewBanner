package com.example.newbanner.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newbanner.R;
import com.example.newbanner.adapter.ImageAdapter;
import com.example.newbanner.fragment.Menu_main_fragment;
import com.example.newbanner.fragment.Menu_person_fragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Fragment mainPerson;
    private Fragment mainHome;
    private LinearLayout shouye,person;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this,"30cf7a949fe1da01b0c798b314a41dad");
        setContentView(R.layout.activity_main);
        initViews();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.menu_container,mainHome)
                .add(R.id.menu_container,mainPerson)
                .hide(mainPerson)
                .commit();
    }

    private void initViews() {
        mainHome=new Menu_main_fragment();
        mainPerson=new Menu_person_fragment();
        shouye = (LinearLayout) findViewById(R.id.menu_main_shouye);
        person = (LinearLayout) findViewById(R.id.menu_main_person);
        shouye.setOnClickListener(this);
        person.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_main_shouye:
                getSupportFragmentManager()
                        .beginTransaction()
                        .show(mainHome)
                        .hide(mainPerson)
                        .commit();
                break;
            case R.id.menu_main_person:
                getSupportFragmentManager()
                        .beginTransaction()
                        .show(mainPerson)
                        .hide(mainHome)
                        .commit();
                break;
        }
    }


}
