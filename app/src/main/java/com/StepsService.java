package com;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.widget.TextView;


import com.example.healthpal.Pedometer;
import com.example.healthpal.Recycler_View;
import com.example.healthpal.StepsDBHelper;

import java.util.Calendar;

public class StepsService extends Service implements SensorEventListener  {
    private SensorManager sensorManager;
    private Sensor stepDetectorSensor;
    private StepsDBHelper stepsDBHelper;




    @Override
    public void onCreate() {
        super.onCreate();
        sensorManager=(SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)!=null)
        {
            stepDetectorSensor=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
            sensorManager.registerListener(this,stepDetectorSensor,SensorManager.SENSOR_DELAY_NORMAL);
            stepsDBHelper=new StepsDBHelper(this);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

            stepsDBHelper.createStepsEntry();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
