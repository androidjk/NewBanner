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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.newbanner.adapter.RecycleviewAdapter;
import com.example.newbanner.bean.ExpressHelp;
import com.example.newbanner.bean.Student;


import java.io.File;


import com.example.newbanner.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.handle;
import static android.R.attr.path;
import static android.R.attr.switchMinWidth;
import static com.example.newbanner.fragment.Menu_main_fragment.verifyStoragePermissions;

public class TakeActivity extends BaseActivity implements View.OnClickListener {

    public static final int FLAG_QUERY = 00001;
    public static final int FLAG_LISTALL = 0002;
    private TextView textView_spinnerfirst, textView_spinnersecond, textView_spinnerthird;
    private Spinner spinnerFirst, spinnerSecond, spinnerThird;
    private ArrayAdapter adapter_first = null;
    private ArrayAdapter adapter_second = null;
    private ArrayAdapter adapter_third = null;

    private RecyclerView recyclerView;
    static List<ExpressHelp> lists = new ArrayList<>();//传入RecycleviewAdapter的list数据
    static String path = Environment.getExternalStorageDirectory().getAbsolutePath();
    List list_first = new ArrayList();
    List list_second = new ArrayList();
    List list_third = new ArrayList();

    public String dormitory;
    public String point;
    public String thingsweight;

    private static String[] area = {" ", "东门", "三食堂", "五食堂", "一食堂", "冶金楼", "唯品会", "聚美优品", "其他"};
    private static String[] type = {" ", "1-5栋", "6-10栋", "11-15栋", "16-20栋", "21-23栋", "24-29栋", "30-32栋", "33-35栋"};
    private static String[] weight = {" ", "1-3瓶水", "3", "5"};


    static boolean flag = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take);
        initViews();//初始化布局
        setListeners();//绑定监听
        listQuery(null);
        setDatas();//数据处理
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
                textView_spinnerfirst.setText(point);
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
                textView_spinnersecond.setText(dormitory);
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

        ExpressHelp expressHelp = new ExpressHelp(null, "五食堂", "1-5栋", "3","0101");
        expressHelp.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Log.d("存储", "成功");
                } else {
                    Log.d("失败", e.getMessage());
                }
            }
        });
    }

    public List<ExpressHelp> screenData(List<ExpressHelp> express) {
        List<ExpressHelp> list = new ArrayList<>();
        if (express == null) {
            Log.d("express为空", "true");
        }

        for (ExpressHelp expressHelp : express) {
            if (!TextUtils.isEmpty(dormitory)) {
                if (!expressHelp.getAddressAccuracy().equals(dormitory)) {
                    continue;
                }
            }

            if (!TextUtils.isEmpty(point)) {
                if (!expressHelp.getPointName().equals(point)) {
                    continue;
                }
            }

            if (!TextUtils.isEmpty(thingsweight)) {
                if (!expressHelp.getWeight().equals(thingsweight)) {
                    continue;
                }
            }
            list.add(expressHelp);
        }

        return list;
    }

    private void setList() {

    }

    /**
     * 创建RecycleView
     *
     * @param list
     */
    private void setRecycleView(List<ExpressHelp> list) {
        RecycleviewAdapter recycleviewAdapter = new RecycleviewAdapter(this, list);
        Log.d("NewBanner", String.valueOf(list.size()));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recycleviewAdapter.setOnItemClickListener(new RecycleviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(view.getContext(), position + "", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(recycleviewAdapter);

    }

    /**
     * 设置下拉框样式
     */
    private void setAdapters() {
        adapter_first = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list_first);
        adapter_second = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list_second);
        adapter_third = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list_third);
    }

    private void setSpinners(final ArrayAdapter adapter, Spinner spinner) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    /**
     * 下拉框，订单展示数据处理
     */
    private void setDatas() {
        for (int i = 0; i < area.length; i++) {
            list_first.add(area[i]);
        }
        for (int i = 0; i < type.length; i++) {
            list_second.add(type[i]);
        }
        for (int i = 0; i < weight.length; i++) {
            list_third.add(weight[i]);
        }

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

    /**
     * 订单查找
     *
     * @param userID
     */
    public void listQuery(String userID) {
        Log.d("test ", "test");
//        if (userID == null) {
//            BmobQuery<ExpressHelp> query = new BmobQuery<>();
//            query.setLimit(8).setSkip(1).order("-createdAt")
//                    .findObjects(new FindListener<ExpressHelp>() {
//                        Handler handler = new Handler() {
//                            @Override
//                            public void handleMessage(Message msg) {
//                                super.handleMessage(msg);
//                                switch (msg.what) {
//                                    case FLAG_LISTALL:
//                                        List<ExpressHelp> list = (List) msg.obj;
//                                        for (ExpressHelp expressHelp : list) {
//                                            ExpressHelp express = new ExpressHelp(expressHelp.getUser(), expressHelp.getPointName(), expressHelp.getAddressAccuracy(), "3","0101");
//                                            lists.add(express);
//                                        }
//                                        setRecycleView(lists);
//                                        break;
//                                }
//                            }
//                        };
//
//                        @Override
//                        public void done(List<ExpressHelp> object, BmobException e) {
//                            if (e == null) {
//
//                                Message message = handler.obtainMessage();
//                                message.what = FLAG_LISTALL;
//                                message.obj = object;
//                                handler.sendMessage(message);
//                            } else {
//
//                            }
//                        }
//                    });
//        } else {
            BmobQuery<ExpressHelp> bmobQuery = new BmobQuery();
            bmobQuery.addWhereEqualTo("pickupCode", "0101");
            bmobQuery.setLimit(50);
            bmobQuery.findObjects(new FindListener<ExpressHelp>() {
                Handler handle = new Handler() {

                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case FLAG_QUERY:
                                List<ExpressHelp> list = (List) msg.obj;
                                for (ExpressHelp expressHelp : list) {
                                    ExpressHelp express = new ExpressHelp(expressHelp.getUser(), expressHelp.getPointName(), expressHelp.getAddressAccuracy(), "3","0101");
                                    lists.add(express);
                                }
                                setRecycleView(screenData(lists));
                                break;
                        }

                    }
                };

                @Override
                public void done(List<ExpressHelp> list, BmobException e) {
                    if (e == null) {
                        Log.d("一共查到", list.size() + "条数据");
                        Message message = handle.obtainMessage();
                        message.what = FLAG_QUERY;
                        message.obj = list;
                        handle.sendMessage(message);
                    } else {
                        Log.d("查询失败：", e.getMessage());
                    }
                }
            });
            setRecycleView(screenData(lists));


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.take_back:

                break;
        }
    }
}

