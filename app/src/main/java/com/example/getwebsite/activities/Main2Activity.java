package com.example.getwebsite.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.getwebsite.R;
import com.example.getwebsite.adapter.HightTechItemAdapter;
import com.example.getwebsite.models.HightTechitem;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    HightTechItemAdapter hightTechItemAdapter;
    //list of item
    static List<HightTechitem> hightTechitemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);




        hightTechItemAdapter = new HightTechItemAdapter(this,hightTechitemList);
        //get list view
        ListView shopListView = findViewById(R.id.shop_list_view);
        shopListView.setAdapter(hightTechItemAdapter);
    }
}
