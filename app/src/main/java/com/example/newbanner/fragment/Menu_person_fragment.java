package com.example.newbanner.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.newbanner.R;
import com.example.newbanner.activity.AddressActivity;

/**
 * Created by Administrator on 2018/2/26.
 */

public class Menu_person_fragment extends Fragment implements View.OnClickListener {
    LinearLayout addressLinearLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_person_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setListener();
    }

    private void setListener() {
        addressLinearLayout.setOnClickListener(this);
    }

    public void initView() {
        addressLinearLayout = getView().findViewById(R.id.ll_person_address);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_person_address:
                Intent intent = new Intent(getActivity().getApplicationContext(), AddressActivity.class);
                startActivity(intent);
                break;
        }
    }

}
