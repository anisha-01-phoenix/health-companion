package com.example.healthpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.StepsService;

import java.text.DateFormat;
import java.util.Calendar;

public class Pedometer extends AppCompatActivity implements SensorEventListener {



     private SensorManager sensorManager;
     private Sensor sensor;
     private boolean isSensorPresent= false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view);

        sensorManager=(SensorManager)this.getSystemService(Context.SENSOR_SERVICE);


        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
        {
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isSensorPresent=true;
        }
        else
        {
            isSensorPresent=false;
        }


    }



    @Override
    protected void onResume() {
        super.onResume();
        if(isSensorPresent){
            sensorManager.registerListener(this,sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isSensorPresent)
        {
            sensorManager.unregisterListener(this);
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String steps=String.valueOf(event.values[0]);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}