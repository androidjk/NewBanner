package com.example.newbanner.im;

import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;

/**
 * Created by Administrator on 2018/3/1.
 */

public class ImMessageHandler extends BmobIMMessageHandler {
    @Override
    public void onMessageReceive(MessageEvent messageEvent) {
        super.onMessageReceive(messageEvent);
        //在线消息
    }

    @Override
    public void onOfflineReceive(OfflineMessageEvent offlineMessageEvent) {
        super.onOfflineReceive(offlineMessageEvent);
        //离线消息，每次connect的时候会查询离线消息，如果有，此方法会被调用
    }
}
