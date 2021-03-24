package com;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;


import com.example.healthpal.StepsDBHelper;

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
