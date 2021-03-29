package com.example.healthpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.DateStepsModel;
import com.StepsService;

import java.security.Key;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Recycler_View extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DBHelper dbHelper;
    private ArrayList<DateStepsModel>stepCountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view);
        recyclerView=findViewById(R.id.stepsList);
        getDataForList();
        RAdapter rAdapter=new RAdapter(this,stepCountList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rAdapter);
        Intent stepsIntent= new Intent(getApplicationContext(),StepsService.class);
        startService(stepsIntent);



    }

    public void getDataForList() {
        dbHelper=new DBHelper(this);
        stepCountList=dbHelper.readStepsEntries();
    }



}