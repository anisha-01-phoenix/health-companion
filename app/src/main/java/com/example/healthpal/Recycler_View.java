package com.example.healthpal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.healthpal.ProgramAdapter;
import com.example.healthpal.R;

import java.util.ArrayList;

public class Recycler_View extends AppCompatActivity {

    private ArrayList<ClipData.Item>items=new ArrayList<>();
    private int lastposition;
        Pedometer pedometer=new Pedometer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view);
        RecyclerView stepsList=(RecyclerView)findViewById(R.id.stepsList);
        stepsList.setHasFixedSize(true);
        stepsList.setAdapter(new ProgramAdapter(items,this));
        LinearLayoutManager layoutManager=new LinearLayoutManager((this));
        stepsList.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getBaseContext()) ;
        lastposition=sharedPreferences.getInt("Last Position",lastposition);
        stepsList.scrollToPosition(lastposition);
        stepsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                lastposition=layoutManager.findFirstVisibleItemPosition();

            }
        });
        items.add(new ClipData.Item(pedometer.Date));
        items.add(new ClipData.Item(pedometer.today_steps));




    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor e=sharedPreferences.edit();
        e.putInt("Last Position",lastposition);
        e.apply();

    }
}