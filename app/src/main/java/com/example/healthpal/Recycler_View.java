package com.example.healthpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.healthpal.ProgramAdapter;
import com.example.healthpal.R;

public class Recycler_View extends AppCompatActivity {

    TextView date;
    TextView stepCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view);
        RecyclerView stepsList=(RecyclerView)findViewById(R.id.stepsList);
        stepsList.setLayoutManager((new LinearLayoutManager(this)));
        date= (TextView) findViewById(R.id.rv_date);
        stepCount=(TextView) findViewById(R.id.rv_stepCount);


        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Daily Record of Steps", Context.MODE_PRIVATE);
        String Date=sharedPreferences.getString("Date","");
        String today_steps=sharedPreferences.getString("Steps","");

        date.setText(Date);
        stepCount.setText(today_steps);


    }
}