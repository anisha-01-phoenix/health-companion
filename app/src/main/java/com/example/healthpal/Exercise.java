package com.example.healthpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Exercise extends AppCompatActivity {

    DBHelper dbHelper;
    SwitchCompat startExercise;
    double time=0.0;
    boolean isExercise=false;
    Timer timer;
    TimerTask timerTask;
    String time_exercise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view__exercise);

        startExercise=findViewById(R.id.switch1);
        timer=new Timer();
        startExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(startExercise.isChecked())
                {
                        isExercise=true;
                        startTimer();
                }
                else {
                    timerTask.cancel();
                    dbHelper.createEHoursEntry(time_exercise);
                }
            }
        });

    }

    private void startTimer()
    {
        timerTask=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        time_exercise=getTime();
                    }
                });

            }
        };
        timer.scheduleAtFixedRate(timerTask,0,1000);
    }
    private String getTime()
    {
        int rounded=(int)Math.round(time);
        int seconds=((rounded%86400)%3600)%60;
        int minutes=((rounded%86400)%3600)/60;
        int hours=((rounded%86400)/3600);
        return formatTime(seconds,minutes,hours);
    }
    private String formatTime(int seconds, int minutes, int hours)
    {
        return String.format("%02d",hours)+":"+String.format("%02d",minutes)+":"+String.format("%02d",seconds);
    }

}
