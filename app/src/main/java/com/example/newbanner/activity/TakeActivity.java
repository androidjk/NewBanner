package com.example.newbanner.activity;

import android.app.Notification;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD:app/src/main/java/com/example/newbanner/TakeActivity.java
import com.example.newbanner.Bmobentity.TakeMenu;
import com.example.newbanner.adapter.RecycleviewAdapter;
import com.example.newbanner.entity.TakeMenuList;

import java.io.File;
=======
import com.example.newbanner.R;

>>>>>>> 1.0:app/src/main/java/com/example/newbanner/activity/TakeActivity.java
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

import static android.R.attr.handle;
import static android.R.attr.path;
import static com.example.newbanner.fragment.Menu_main_fragment.verifyStoragePermissions;

public class TakeActivity extends AppCompatActivity {

    private TextView textView_spinnerfirst, textView_spinnersecond, textView_spinnerthird;
    private Spinner spinnerFirst, spinnerSecond, spinnerThird;
    private ArrayAdapter adapter_first = null;
    private ArrayAdapter adapter_second = null;
    private ArrayAdapter adapter_third = null;

    private RecyclerView recyclerView;
  static List<TakeMenuList> lists = new ArrayList<>();//传入RecycleviewAdapter的list数据
    static List<TakeMenuList> listold;//存储Bmob中查询数据
    static String path = Environment.getExternalStorageDirectory().getAbsolutePath();
    List list_first = new ArrayList();
    List list_second = new ArrayList();
    List list_third = new ArrayList();

    public String dormitory;
    public String point;
    public String thingsweight;

    private static String[] area = {" ", "1-5栋", "6-10栋", "11-15栋", "16-20栋", "21-23栋", "24-29栋", "30-32栋", "33-35栋"};
    private static String[] type = {" ", "东门", "三食堂", "五食堂", "一食堂", "冶金楼", "唯品会", "聚美优品", "其他"};
    private static String[] weight = {" ", "1-3瓶水", "3", "5"};

    static List<String> listEndPlace = new ArrayList<>();
    static List<String> listTakePlace = new ArrayList<>();
    static List<String> listWeight = new ArrayList<>();

    static boolean flag = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take);
        initViews();//初始化布局
        setListeners();//绑定监听
//        listQuery("001");
        setDatas("001");//数据处理
        setAdapters();//设置spinner下拉框样式
        setSpinners(adapter_first, spinnerFirst);
        setSpinners(adapter_second, spinnerSecond);
        setSpinners(adapter_third, spinnerThird);
        setList();

//        saveDataBmob();

        spinnerFirst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        dormitory = "";
                        break;
                    case 1:
                        dormitory = "1-5栋";
                        break;
                    case 2:
                        dormitory = "6-10栋";
                        break;
                    case 3:
                        dormitory = "11-15栋";
                        break;
                    case 4:
                        dormitory = "16-20栋";
                        break;
                    case 5:
                        dormitory = "21-23栋";
                        break;
                    case 6:
                        dormitory = "24-29栋";
                        break;
                    case 7:
                        dormitory = "30-32栋";
                        break;
                    case 8:
                        dormitory = "33-35栋";
                        break;

                }
                setRecycleView(screenData(lists));
                textView_spinnerfirst.setText(dormitory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSecond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        point = "";
                        break;
                    case 1:
                        point = "东门";
                        break;
                    case 2:
                        point = "三食堂";
                        break;
                    case 3:
                        point = "五食堂";
                        break;
                    case 4:
                        point = "一食堂";
                        break;
                    case 5:
                        point = "冶金楼";
                        break;
                    case 6:
                        point = "唯品会";
                        break;
                    case 7:
                        point = "聚美优品";
                        break;
                    case 8:
                        point = "其他";
                        break;

                }
                if (lists == null) {
                    Log.d("lists未被传值", "true");
                }
                setRecycleView(screenData(lists));
                textView_spinnersecond.setText(point);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerThird.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0:
                        thingsweight = "";
                        break;
                    case 1:
                        thingsweight = "1-3瓶水";
                        break;
                    case 2:
                        thingsweight = "3";
                        break;
                    case 3:
                        thingsweight = "5";
                        break;

                }
                setRecycleView(screenData(lists));
                textView_spinnerthird.setText(thingsweight);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (flag == true) {
            setRecycleView(lists);
            flag = false;
        }

    }

    private void saveDataBmob() {
        verifyStoragePermissions(this);
        String picPath = path + "/newbanner/111.png";//"/ajinkai/jinkai.png"
        final BmobFile file = new BmobFile(new File(picPath));
        file.upload(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    TakeMenu takeMenu = new TakeMenu(file, "001", "1-4栋", "五食堂", "2瓶水");//头像，用户ID,送达地点end，初始快递点begin，快递重量
                    takeMenu.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                Log.d("存储", "成功");
                            } else {
                                Log.d("失败2", e.getMessage());
                            }
                        }
                    });
                } else {
                    Log.d("失败1", e.getMessage());
                }
            }
        });
    }

    public List<TakeMenuList> screenData(List<TakeMenuList> express) {
        List<TakeMenuList> list = new ArrayList<>();
        if (express==null){
            Log.d("express为空", "true");
        }

        for (TakeMenuList takeMenuList : express) {
            if (!TextUtils.isEmpty(dormitory)) {
                if (!takeMenuList.getEndTextView().equals(dormitory)) {
                    continue;
                }
            }

            if (!TextUtils.isEmpty(point)) {
                if (!takeMenuList.getBeginTextView().equals(point)) {
                    continue;
                }
            }

            if (!TextUtils.isEmpty(thingsweight)) {
                if (!takeMenuList.getGoal().equals(thingsweight)) {
                    continue;
                }
            }
            list.add(takeMenuList);
        }

        return list;
    }

    private void setList() {

    }

    private void setRecycleView(List<TakeMenuList> list) {
        RecycleviewAdapter recycleviewAdapter = new RecycleviewAdapter(this, list);
        Log.d("NewBanner", String.valueOf(list.size()));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recycleviewAdapter);
    }

    /**
     * 设置下拉框样式
     */
    private void setAdapters() {
        adapter_first = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list_first);
        adapter_second = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list_second);
        adapter_third = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list_third);
    }

    private void setSpinners(final ArrayAdapter adapter, Spinner spinner) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    /**
     * 下拉框，订单展示数据处理
     */
    private void setDatas(String userID) {
        for (int i = 0; i < area.length; i++) {
            list_first.add(area[i]);
        }
        for (int i = 0; i < type.length; i++) {
            list_second.add(type[i]);
        }
        for (int i = 0; i < weight.length; i++) {
            list_third.add(weight[i]);
        }
        BmobQuery<TakeMenu> bmobQuery = new BmobQuery();
        bmobQuery.addWhereEqualTo("userId", "001");
        bmobQuery.setLimit(50);
        bmobQuery.findObjects(new FindListener<TakeMenu>() {
            Handler handle=new Handler(){

                public void handleMessage(Message msg) {
                    switch (msg.what){
                        case 00001:
                       List<TakeMenu>list=(List)msg.obj;
                            for (TakeMenu takemenu:list){
                                TakeMenuList takeMenuList = new TakeMenuList(R.drawable.back_bg, takemenu.getMenuBegin(), takemenu.getMenuEnd(), "3");
                                lists.add(takeMenuList);
                            }
                            setRecycleView(screenData(lists));
                            break;
                    }

                }
            };
            @Override
            public void done(List<TakeMenu> list, BmobException e) {
                if (e == null) {
                    Log.d("一共查到", list.size() + "条数据");
                    Message message=handle.obtainMessage();
                    message.what= 00001;
                    message.obj=list;
                    handle.sendMessage(message);
                } else {
                    Log.d("查询失败：", e.getMessage());
                }
            }
        });
        setRecycleView(screenData(lists));
    }

    private void setListeners() {

    }

    /**
     * 初始化布局
     */

    private void initViews() {
        textView_spinnerfirst = (TextView) findViewById(R.id.take_spinner_text1);
        textView_spinnersecond = (TextView) findViewById(R.id.take_spinner_text2);
        textView_spinnerthird = (TextView) findViewById(R.id.take_spinner_text3);
        spinnerFirst = (Spinner) findViewById(R.id.take_spinner_first);
        spinnerSecond = (Spinner) findViewById(R.id.take_spinner_second);
        spinnerThird = (Spinner) findViewById(R.id.take_spinner_third);
        recyclerView = (RecyclerView) findViewById(R.id.take_recycleView);
    }


    public void listQuery(String userID) {

        BmobQuery<TakeMenu> bmobQuery = new BmobQuery();
        bmobQuery.addWhereEqualTo("userId", userID);
        bmobQuery.setLimit(50);
        bmobQuery.findObjects(new FindListener<TakeMenu>() {
            @Override
            public void done(List<TakeMenu> list, BmobException e) {
                if (e == null) {
                    Log.d("一共查到", list.size() + "条数据");
                    for (TakeMenu takeMenu : list) {
//                        listEndPlace.add(takeMenu.getMenuEnd());
//                        listTakePlace.add(takeMenu.getMenuBegin());
//                        listWeight.add(takeMenu.getWeight());
//                        Log.d("listEndPlace",listEndPlace.get(0));
                    }
                } else {
                    Log.d("查询失败：", e.getMessage());
                }
            }
        });

    }

}
