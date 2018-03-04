package com.example.newbanner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
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

//        final ImageView imageView = (ImageView) findViewById(R.id.paihangbang_firstshow);
//
//        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                R.drawable.back_bg);
//
//        imageView.getViewTreeObserver().addOnPreDrawListener(
//                new ViewTreeObserver.OnPreDrawListener() {
//
//                    @Override
//                    public boolean onPreDraw() {
//                        blur(bitmap, imageView);
//                        return true;
//                    }
//                });


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
//    private void blur(Bitmap bkg, View view) {
//        long startMs = System.currentTimeMillis();
//        float scaleFactor = 8;
//        float radius = 2;
//
//        Bitmap overlay = Bitmap.createBitmap(
//                (int) (view.getMeasuredWidth() / scaleFactor),
//                (int) (view.getMeasuredHeight() / scaleFactor),
//                Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(overlay);
//        canvas.translate(-view.getLeft() / scaleFactor, -view.getTop()
//                / scaleFactor);
//        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
//        Paint paint = new Paint();
//        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
//        canvas.drawBitmap(bkg, 0, 0, paint);
//
//        overlay = FastBlurUtil.doBlur(overlay, (int) radius, true);
//
//        view.setBackgroundDrawable(new BitmapDrawable(getResources(), overlay));
//        System.out.println(System.currentTimeMillis() - startMs + "ms");
//    }

}
