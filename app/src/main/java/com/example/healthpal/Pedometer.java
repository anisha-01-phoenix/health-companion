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

import java.text.DateFormat;
import java.util.Calendar;

public class Pedometer extends AppCompatActivity implements SensorEventListener {



     SensorManager sensorManager;
     Sensor stepCounter, stepDetector;
     TextView steps;
     boolean stepCounterRun,stepDetectorRun;
     int stepCount=0, stepDetect=0;
     SharedPreferences sharedPreferences;
     String today_steps,Date;
     Button pedo_button;
    long time,startTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);
        Calendar calendar=Calendar.getInstance();
        Date= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        time=System.currentTimeMillis();
        steps=(TextView)findViewById(R.id.steps);

        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);

        startTime=calendar.getTimeInMillis();
        sharedPreferences=getSharedPreferences("Daily Record of Steps", Context.MODE_PRIVATE);
        pedo_button= findViewById(R.id.pedo_record);

        today_steps=steps.getText().toString();
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Date",Date);
        editor.putString("Steps",today_steps);
        editor.commit();


        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        this.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
        {
            stepCounter=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            stepCounterRun=true;
        }
        else
        {
            stepCounterRun=false;
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)!=null)
        {
            stepDetector=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
            stepDetectorRun=true;
        }
        else
        {
            stepDetectorRun=false;
        }
        pedo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Pedometer.this,Recycler_View.class);
                startActivity(intent);

            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        if(stepCounterRun)
        {
            sensorManager.registerListener(this,stepCounter,SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(stepDetectorRun)
        {
            sensorManager.registerListener(this,stepDetector,SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(stepCounterRun)
        {
            sensorManager.unregisterListener(this);
        }
        if(stepDetectorRun)
        {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(time>startTime) {

            if (event.sensor == stepCounter) {
                stepCount = (int) event.values[0];
                steps.setText(String.valueOf(stepCount));

            } else if (event.sensor == stepDetector) {
                stepDetect = (int) (stepDetect + event.values[0]);
                steps.setText(String.valueOf(stepDetect));
            }
        }
        else
            steps.setText(("0"));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}