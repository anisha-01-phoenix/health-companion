package com.example.healthpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.DateStepsModel;
import com.StepsService;

import java.util.ArrayList;

public class Recycler_View_Exercise extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DBHelper dbHelper;
    private ArrayList<DateEHoursModel>ETimeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view__exercise);
        recyclerView=findViewById(R.id.etimeList);
        getDataForExerciseList();
        REAdapter reAdapter=new REAdapter(this,ETimeList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(reAdapter);
    }

    public void getDataForExerciseList() {
        dbHelper=new DBHelper(this);
        ETimeList=dbHelper.readEHourEntries();
    }


}