package com.example.healthpal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.os.Bundle;

public class Water extends AppCompatActivity {
    int count=0;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_water);
        NotificationManager manager=(NotificationManager)getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        manager.cancelAll();
        if(getIntent().hasExtra("Yes")){
            count++;
            dbHelper.createWaterEntry(count);
        }
    }
}