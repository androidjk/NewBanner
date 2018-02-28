package com.example.newbanner.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.newbanner.R;
import com.example.newbanner.bean.Address;
import com.example.newbanner.bean.ExpressHelp;
import com.example.newbanner.bean.Student;

import org.litepal.crud.DataSupport;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class PublishActivity extends BaseActivity {
    @Bind(R.id.tb_publish)
    Toolbar tbPublish;
    @Bind(R.id.et_pickup_code)
    EditText etPickupCode;
    @Bind(R.id.et_address_name)
    EditText etAddressName;
    @Bind(R.id.et_address_telephone)
    EditText etAddressTelephone;
    @Bind(R.id.et_address_accuracy)
    EditText etAddressAccuracy;
    @Bind(R.id.et_point_name)
    EditText etPointName;
    @Bind(R.id.et_remarks)
    EditText etRemarks;
    @Bind(R.id.et_express_sms)
    EditText etExpressSms;
    @Bind(R.id.btn_publish)
    Button btnPublish;
    @Bind(R.id.sp_address_dormitory)
    Spinner spAddressDormitory;
    @Bind(R.id.ll_address_dormitory)
    LinearLayout llAddressDormitory;
    @Bind(R.id.sp_address_weight)
    Spinner spAddressWeight;
    @Bind(R.id.ll_address_weight)
    LinearLayout llAddressWeight;
    @Bind(R.id.sp_address_point)
    Spinner spAddressPoint;
    @Bind(R.id.ll_address_point)
    LinearLayout llAddressPoint;


    private ExpressHelp expressHelp = new ExpressHelp();
    Student user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ButterKnife.bind(this);
        setToolBar(R.id.tb_publish);
        initHome();

        try {
            for (Address address : DataSupport.findAll(Address.class)) {
                if (address.isDefault()){
                    etAddressTelephone.setText(address.getPhoneNumber());
                    etAddressAccuracy.setText(address.getAddress());
                    etAddressName.setText(address.getReceiver());
                    break;
                }
            }
        } finally {

        }
    }

    public void getSpinnerData() {
        spAddressDormitory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                expressHelp.setDormitory(spAddressDormitory.getSelectedItem().toString());
            }
        });
        spAddressPoint.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                expressHelp.setExpressPoint(spAddressPoint.getSelectedItem().toString());
            }
        });
        spAddressWeight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                expressHelp.setWeight(spAddressWeight.getSelectedItem().toString());
            }
        });
    }

    @OnClick({R.id.ll_address_dormitory, R.id.ll_address_weight, R.id.ll_address_point, R.id.btn_publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_address_dormitory:

                break;
            case R.id.ll_address_weight:

                break;
            case R.id.ll_address_point:

                break;
            case R.id.btn_publish:
                getSpinnerData();
                if (TextUtils.isEmpty(etPickupCode.getText().toString())) {
                    Toast.makeText(mActivity, "取货码不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etPointName.getText().toString())) {
                    Toast.makeText(mActivity, "快递点名称不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etAddressAccuracy.getText().toString())) {
                    Toast.makeText(mActivity, "收货地址不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etAddressName.getText().toString())) {
                    Toast.makeText(mActivity, "收件人不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etAddressTelephone.getText().toString())) {
                    Toast.makeText(mActivity, "电话号码不能为空", Toast.LENGTH_SHORT).show();

                } else if (expressHelp.getDormitory().equals("宿舍")) {
                    Toast.makeText(mActivity, "请选择宿舍", Toast.LENGTH_SHORT).show();

                } else if (expressHelp.getWeight().equals("重量")) {
                    Toast.makeText(mActivity, "请选择重量", Toast.LENGTH_SHORT).show();

                } else if (expressHelp.getExpressPoint().equals("快递点")) {
                    Toast.makeText(mActivity, "请选择快递点", Toast.LENGTH_SHORT).show();

                } else {
                    expressHelp.setState(false);
                    expressHelp.setUser(BmobUser.getCurrentUser(Student.class));
                    expressHelp.setAddressAccuracy(etAddressAccuracy.getText().toString());
                    expressHelp.setExpressSms(etExpressSms.getText().toString());
                    expressHelp.setAddressTelephone(etAddressTelephone.getText().toString());
                    expressHelp.setPickupCode(etPickupCode.getText().toString());
                    expressHelp.setAddressName(etAddressName.getText().toString());
                    expressHelp.setPointName(etPointName.getText().toString());
                    expressHelp.setRemarks(etRemarks.getText().toString());
                    expressHelp.setHelpUser(null);
                    expressHelp.setPublishTime(System.currentTimeMillis());
                    user = BmobUser.getCurrentUser(Student.class);
                    if (user.getHelpSum() > 0) {
//                        showProgressDialog();
                        expressHelp.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
                                    Student newUser = new Student(user.getSum(), user.getHelpSum() - 1);
                                    newUser.update(user.getObjectId(), new UpdateListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            dissmiss();
                                            if (e == null) {
                                                Toast.makeText(mActivity, "发布成功", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(PublishActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                    finish();
                                } else {
                                    dissmiss();
                                    Toast.makeText(mActivity, e.getErrorCode() + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(mActivity, "你的可请求帮助次数不够", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    break;
                }
        }
    }
}
