package com.example.newbanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.LinearLayout;

import com.example.newbanner.adapter.ShowPaiHangAdapter;
import com.example.newbanner.entity.Menu_user;

import java.util.ArrayList;
import java.util.List;

public class Paihangbang extends AppCompatActivity {

    RecyclerView recyclerView_paihangbang;

    List<Menu_user>list=new ArrayList<>();
    int icons[]={R.drawable.back_bg};
    String user_name[]={"jinkai"};
    String user_num[]={"2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paihangbang);
        for (int i=0;i<10;i++){
            recycleViewList();
        }

    }

    private void recycleViewList() {
        recyclerView_paihangbang=(RecyclerView)findViewById(R.id.paihangbang_list);
        for (int i=0;i<icons.length;i++){
            Menu_user menu_user=new Menu_user(icons[i],user_name[i],user_num[i]);
            list.add(menu_user);
        }

        ShowPaiHangAdapter showPaiHangAdapter=new ShowPaiHangAdapter(list);
        recyclerView_paihangbang.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_paihangbang.setAdapter(showPaiHangAdapter);
    }


}
