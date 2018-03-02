package com.example.newbanner.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.newbanner.R;
import com.example.newbanner.adapter.ConversationAdapter;
import com.example.newbanner.util.ConversationUtil;

import cn.bmob.newim.BmobIM;

/**
 * Created by Administrator on 2018/3/1.
 */

public class Menu_message_fragment extends Fragment {
    SwipeRefreshLayout srConversation;
    RecyclerView rvconversation;
    ConversationAdapter conversationAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_message_fragment, container, false);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        srConversation.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (ConversationUtil.getInstance().isConnect()){
                    conversationAdapter.setNewData(BmobIM.getInstance().loadAllConversation());
                }else {
                    Toast.makeText(getActivity(), "没有连接", Toast.LENGTH_SHORT).show();
                }
                srConversation.setRefreshing(false);
            }
        });
        if (ConversationUtil.getInstance().isConnect()){
            conversationAdapter = new ConversationAdapter(BmobIM.getInstance().loadAllConversation());
        }else {
            conversationAdapter = new ConversationAdapter(null);
        }
        rvconversation.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvconversation.setItemAnimator(new DefaultItemAnimator());
        rvconversation.setAdapter(conversationAdapter);

    }

    private void initView() {
        srConversation = getView().findViewById(R.id.sr_conversation);
        rvconversation = getView().findViewById(R.id.rv_conversation);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
