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
    private RecyclerView.LayoutManager layoutManager;
    private DBHelper dbHelper;
    private ArrayList<DateEHoursModel> ETimeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view__exercise);
        recyclerView=(RecyclerView)findViewById(R.id.etimeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getDataForExerciseList();
        REAdapter reAdapter=new REAdapter(ETimeList);
        recyclerView.setAdapter(reAdapter);
    }

    public void getDataForExerciseList() {
        dbHelper=new DBHelper(this);
        ETimeList=dbHelper.readEHourEntries();
    }


}