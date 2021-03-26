package com.example.healthpal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Recycler_view_water extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private RecyclerView.LayoutManager layoutManager;
    private DBHelper dbHelper;
    private ArrayList<DateWaterModel> waterCountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_water);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.waterfloat);
        recyclerView=(RecyclerView)findViewById(R.id.waterList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getDataForWaterList();
        RWaterAdapter rwaterAdapter=new RWaterAdapter(waterCountList);
        recyclerView.setAdapter(rwaterAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWaterSchedule();
            }
        });
    }

    private void getDataForWaterList() {
        dbHelper=new DBHelper(this);
        waterCountList=dbHelper.readWaterEntries();
    }

    void openWaterSchedule()
    {
       Intent intent=new Intent(this,WaterSchedule.class);
       startActivity(intent);
    }
}