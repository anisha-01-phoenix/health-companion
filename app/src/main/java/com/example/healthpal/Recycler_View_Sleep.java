package com.example.healthpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class Recycler_View_Sleep extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private RecyclerView recyclerView;
    private DBHelper dbHelper;
    private ArrayList<DateSleepModel>sleepList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view__sleep);
        recyclerView=findViewById(R.id.sleepTime);
        getDataForSleepList();
        RSleepAdapter rSleepAdapter=new RSleepAdapter(this, sleepList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rSleepAdapter);

        Spinner spinner=findViewById(R.id.spinner1);
        Button setAlarm=findViewById(R.id.setAlarm);
        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSleepAlarm();
            }
        });
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.SleepHours, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemClickListener(this);


    }
    private void openSleepAlarm()
    {
        Intent intent=new Intent(this,SleepAlarm.class);
        startActivity(intent);
    }

    private void getDataForSleepList() {
        dbHelper=new DBHelper(this);
        sleepList=dbHelper.readSleepEntries();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text+" hours", Toast.LENGTH_SHORT).show();
    }
}