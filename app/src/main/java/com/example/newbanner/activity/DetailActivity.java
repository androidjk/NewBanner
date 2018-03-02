package com.example.newbanner.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.newbanner.R;
import com.example.newbanner.bean.Student;
import com.example.newbanner.other.CacheManager;
import com.imnjh.imagepicker.SImagePicker;
import com.imnjh.imagepicker.activity.PhotoPickerActivity;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileCallback;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends BaseActivity {

    @Bind(R.id.cm_person)
    CircleImageView cmPerson;
    @Bind(R.id.et_student_age)
    EditText etStudentAge;
    @Bind(R.id.et_student_telNum)
    EditText etStudentTelNum;
    @Bind(R.id.et_student_collega)
    EditText etStudentCollega;
    @Bind(R.id.et_student_className)
    EditText etStudentClassName;
    @Bind(R.id.tb_personal)
    Toolbar tbPersonal;
    @Bind(R.id.btn_student_ensure)
    Button btnStudentEnsure;
    public static final int REQUEST_CODE_AVATAR = 100;
    @Bind(R.id.tv_true_name)
    TextView tvTrueName;
    @Bind(R.id.tv_student_sex)
    TextView tvStudentSex;
    @Bind(R.id.ll_information)
    LinearLayout llInformation;
    private String cmUrl;
    public static final String AVATAR_FILE_NAME = "avatar.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detali);
        ButterKnife.bind(this);
        setToolBar(R.id.tb_personal);
        Intent intent = getIntent();
        tvStudentSex.setText(intent.getStringExtra("idInfo_sex"));
        tvTrueName.setText(intent.getStringExtra("idInfo_name"));
    }

    @OnClick({R.id.cm_person, R.id.btn_student_ensure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cm_person:
                if (getCcamra() && getStorage()) {
                    SImagePicker
                            .from(this)
                            .pickMode(SImagePicker.MODE_AVATAR)
                            .showCamera(true)
                            .cropFilePath(
                                    CacheManager.getInstance().getImageInnerCache()
                                            .getAbsolutePath(AVATAR_FILE_NAME))
                            .forResult(REQUEST_CODE_AVATAR);
                } else {
                    Toast.makeText(mActivity, "请给予权限", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_student_ensure:
                initSave();
                break;
        }
    }

    private void initSave() {
        if (!etStudentAge.getText().toString().isEmpty() && !etStudentClassName.getText().toString().isEmpty()
                && !etStudentCollega.getText().toString().isEmpty() && !tvStudentSex.getText().toString().isEmpty()
                && !etStudentTelNum.getText().toString().isEmpty() && !tvTrueName.getText().toString().isEmpty()) {
            final Student student = Student.getCurrentUser(Student.class);
            student.setName(tvStudentSex.getText().toString());
            student.setSex(tvTrueName.getText().toString());
            student.setCollega(etStudentCollega.getText().toString());
            student.setClassName(etStudentClassName.getText().toString());
            student.setAge(Integer.parseInt(etStudentAge.getText().toString()));
            student.setTelNum(etStudentTelNum.getText().toString());
            student.update(student.getObjectId(), new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        //finish
                    } else if (e.getErrorCode() == 9016) {
                        Toast.makeText(mActivity, "网络无连接( ▼-▼ )", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mActivity, "更新用户信息失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(mActivity, "您有信息没有填完", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_AVATAR) {
            final ArrayList<String> pathList =
                    data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT_SELECTION);
            Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
            options.width = 200;
            options.height = 200;
            Tiny.getInstance().source(pathList.get(0)).asFile().withOptions(options).compress(new FileCallback() {
                @Override
                public void callback(boolean isSuccess, String outfile) {
                    if (isSuccess) {
                        final BmobFile bmobFile = new BmobFile(new File(outfile));
                        bmobFile.uploadblock(new UploadFileListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    cmUrl = bmobFile.getUrl();
                                    updateHead();

                                    Toast.makeText(DetailActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    dissmiss();
                                    Toast.makeText(DetailActivity.this, "更新失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        dissmiss();
                        Toast.makeText(DetailActivity.this, "压缩失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void updateHead() {
        Student student = Student.getCurrentUser(Student.class);
        student.setNickUrl(cmUrl);
        student.update(student.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    dissmiss();
                    Toast.makeText(DetailActivity.this, "更改成功", Toast.LENGTH_SHORT).show();
                    Glide.with(DetailActivity.this)
                            .load(cmUrl)
                            .into(cmPerson);
                } else {
                    dissmiss();
                    Toast.makeText(DetailActivity.this, "更改失败", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
}
