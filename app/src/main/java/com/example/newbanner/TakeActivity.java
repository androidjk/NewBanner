package com.example.newbanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TakeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private TextView textView_spinnerfirst,textView_spinnersecond,textView_spinnerthird;
    private Spinner spinnerFirst,spinnerSecond,spinnerThird;
    private ArrayAdapter adapter_first=null;
    private ArrayAdapter adapter_second=null;
    private ArrayAdapter adapter_third=null;
    static   List list_first=new ArrayList();
    static   List list_second=new ArrayList();
    static   List list_third=new ArrayList();

    private static String[] area = {"一食堂","二食堂","五食堂","东门"};
    private static String[] type = {"一食堂","二食堂","五食堂","东门"};
    private static String[] weight = {"一食堂","二食堂","五食堂","东门"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take);

        initViews();
        setListeners();
        setDatas();
        setAdapters();
        setSpinners(adapter_first,spinnerFirst);
        setSpinners(adapter_second,spinnerSecond);
        setSpinners(adapter_third,spinnerThird);




    }

    private void setAdapters() {
       adapter_first = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list_first);
        adapter_second = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list_second);
        adapter_third = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list_third);
    }

    private void setSpinners(final ArrayAdapter adapter,Spinner spinner) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    private void setDatas() {
        for(int i=0;i<area.length;i++){
            list_first.add(area[i]);
        }
        for (int i=0;i<type.length;i++){
            list_second.add(type[i]);
        }
        for (int i=0;i<weight.length;i++){
            list_third.add(weight[i]);
        }
    }

    private void setListeners() {

    }

    private void initViews() {
        textView_spinnerfirst=(TextView)findViewById(R.id.take_spinner_text1);
        textView_spinnersecond=(TextView)findViewById(R.id.take_spinner_text2);
        textView_spinnerthird=(TextView)findViewById(R.id.take_spinner_text3);
        spinnerFirst=(Spinner)findViewById(R.id.take_spinner_first);
        spinnerSecond=(Spinner)findViewById(R.id.take_spinner_second);
        spinnerThird=(Spinner)findViewById(R.id.take_spinner_third);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.take_spinner_first:
                Toast.makeText(this, "aaaaaaaaa", Toast.LENGTH_SHORT).show();
                textView_spinnerfirst.setText((String)adapter_first.getItem(position));
                break;
            case R.id.take_spinner_second:
                textView_spinnersecond.setText((String)adapter_second.getItem(position));
                break;
            case R.id.take_spinner_third:
                textView_spinnerthird.setText((String)adapter_third.getItem(position));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
