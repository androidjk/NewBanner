package com.example.newbanner.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newbanner.MainActivity;
import com.example.newbanner.R;
import com.example.newbanner.adapter.ImageAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus1 on 2018/2/8.
 */

public class Menu_main_fragment extends Fragment implements View.OnTouchListener {
    private Menu_main_fragment.ImageHandler mHandler = new Menu_main_fragment.ImageHandler(new WeakReference<Menu_main_fragment>(this));
    private ViewPager mViewPager;
    private TextView text_1,text_2,text_3;
    private LinearLayout menu_main_shouye;

    private String textContent[];
    List<String> list=new ArrayList<>();
    // 自定义轮播图的资源
    private int[] mImageResIds = {R.drawable.back_bg,R.drawable.edit_bg,R.drawable.help};
    // 放轮播图片的ImageView 的list
    private List<ImageView> mImageList = new ArrayList<ImageView>();
    // 放圆点的View的list
    private List<View> mDotList = new ArrayList<View>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_main_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list.add("123456");
        list.add("456789");
        list.add("78915551");
        initViews();
        changeTextContent();

    }
    private void changeTextContent() {
        text_1=(TextView)getView().findViewById(R.id.tv_text1);
        text_2=(TextView)getView().findViewById(R.id.tv_text2);
        text_3=(TextView)getView().findViewById(R.id.tv_text3);
        for (int i=0;i<list.size();i++){
            switch (i){
                case 0:
                    text_1.setText(String.valueOf(list.get(i)));
                    break;
                case 1:
                    text_2.setText(String.valueOf(list.get(i)));
                    break;
                case 2:
                    text_3.setText(String.valueOf(list.get(i)));
                    break;
            }
        }
    }

    private void initViews() {
        // 初始化iewPager的内容
        mViewPager = (ViewPager)getView().findViewById(R.id.view_pager);
        LinearLayout dotLayout = (LinearLayout) getView().findViewById(R.id.dotLayout);
        dotLayout.removeAllViews();
        for (int i = 0; i < mImageResIds.length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            // view.setTag(mImageResId[i]);
            imageView.setImageResource(mImageResIds[i]);
            mImageList.add(imageView);

            View dotView = new View(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( 50,50);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.dot_width),
//                    getResources().getDimensionPixelSize(R.dimen.dot_width));
            params.setMargins(4, 0, 4, 0);
            dotView.setLayoutParams(params);
            dotLayout.addView(dotView);
            mDotList.add(dotView);
        }
        mViewPager.setAdapter(new ImageAdapter(mImageList));
        mViewPager.setOnPageChangeListener(new Menu_main_fragment.PageChangeListener());
        mViewPager.setFocusable(true);
        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2);// 默认在中间，使用户看不到边界
        mViewPager.setOnTouchListener(this);
        // 开始轮播效果
        mHandler.sendEmptyMessageDelayed(Menu_main_fragment.ImageHandler.MSG_UPDATE_IMAGE, Menu_main_fragment.ImageHandler.MSG_DELAY);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mHandler.sendEmptyMessage(Menu_main_fragment.ImageHandler.MSG_KEEP_SILENT);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mHandler.sendEmptyMessageDelayed(Menu_main_fragment.ImageHandler.MSG_UPDATE_IMAGE, Menu_main_fragment.ImageHandler.MSG_DELAY);
                break;
        }
        return false;
    }


    private class PageChangeListener implements ViewPager.OnPageChangeListener {

        // 配合Adapter的currentItem字段进行设置。
        @Override
        public void onPageSelected(int position) {
            mHandler.sendMessage(Message.obtain(mHandler, Menu_main_fragment.ImageHandler.MSG_PAGE_CHANGED, position, 0));
        }

        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {
        }

        // 覆写该方法实现轮播效果的暂停和恢复
        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case ViewPager.SCROLL_STATE_DRAGGING:
                    mHandler.sendEmptyMessage(Menu_main_fragment.ImageHandler.MSG_KEEP_SILENT);
                    break;
                case ViewPager.SCROLL_STATE_IDLE:
                    mHandler.sendEmptyMessageDelayed(Menu_main_fragment.ImageHandler.MSG_UPDATE_IMAGE, Menu_main_fragment.ImageHandler.MSG_DELAY);
                    break;
                default:
                    break;
            }
        }
    }

    private static class ImageHandler extends Handler {
        /**
         * 请求更新显示的View。
         */
        protected static final int MSG_UPDATE_IMAGE = 1;
        /**
         * 请求暂停轮播。
         */
        protected static final int MSG_KEEP_SILENT = 2;
        /**
         * 请求恢复轮播。
         */
        protected static final int MSG_BREAK_SILENT = 3;
        /**
         * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
         * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
         * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
         */
        protected static final int MSG_PAGE_CHANGED = 4;

        // 轮播间隔时间
        protected static final long MSG_DELAY = 3000;

        //         使用弱引用避免Handler泄露.这里的泛型参数可以不是Activity，也可以是Fragment等
        private WeakReference<Menu_main_fragment> weakReference;
        private int currentItem = 0;

        // private boolean isOnce = true;

        protected ImageHandler(WeakReference<Menu_main_fragment> wk) {
            weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final Menu_main_fragment mainActivity = weakReference.get();
            if (mainActivity == null) {
                // Activity已经回收，无需再处理UI了
                return;
            }
            // 第一次不删重复的消息
            if (currentItem != 0) {
                // 检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
                if (mainActivity.mHandler.hasMessages(MSG_UPDATE_IMAGE)) {
                    mainActivity.mHandler.removeMessages(MSG_UPDATE_IMAGE);
                }
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    mainActivity.mViewPager.setCurrentItem(currentItem);
                    // 准备下次播放
                    mainActivity.mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    // 只要不发送消息就暂停了
                    break;
                case MSG_BREAK_SILENT:
                    mainActivity.mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    // 记录当前的页号，避免播放的时候页面显示不正确。
                    int position = msg.arg1;
                    int lastIndex = 0;
                    int index = position % mainActivity.mImageList.size();
                    for (int i = 0; i < mainActivity.mDotList.size(); i++) {
                        mainActivity.mDotList.get(i).setBackgroundResource(R.drawable.raidobox);
                    }
                    if (mainActivity.mDotList.get(index) != null) {
                        mainActivity.mDotList.get(index).setBackgroundResource(R.drawable.raidobox_selectedrai);
                    }
                    lastIndex = index;
                    currentItem = position;
                    break;
                default:
                    break;
            }
        }
    }
}
