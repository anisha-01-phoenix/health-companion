package com.example.healthpal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Recycler_view_water extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_water);
        recyclerView=findViewById(R.id.waterList);
        floatingActionButton=findViewById(R.id.waterfloat);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy>0)
                {
                    floatingActionButton.hide();
                }
                else{
                    floatingActionButton.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWaterSchedule();
            }
        });
    }

   void openWaterSchedule()
    {
       Intent intent=new Intent(this,WaterSchedule.class);
       startActivity(intent);
    }
}