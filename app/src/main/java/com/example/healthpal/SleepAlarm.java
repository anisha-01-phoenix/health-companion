package com.example.healthpal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class SleepAlarm extends AppCompatActivity {
    AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
    int hour, min,hr,m;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_alarm);
        TimePicker timePicker=findViewById(R.id.sleepTimepicker);
        TextView textView=findViewById(R.id.sleepTV);
        Button StopAlarm=findViewById(R.id.AlarmStop);
        StopAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,hr);
                calendar.set(Calendar.MINUTE,m);
                if(hr<hour)
                    dbHelper.createSleepEntry(-1);
                else if(m<min+10)
                    dbHelper.createSleepEntry(0);
                else
                    dbHelper.createSleepEntry(1);

            }

        });
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
               hour=hourOfDay;
               min=minute;
               textView.setText(textView.getText().toString()+" "+hour+" : "+min);

            }
        });
    }
    public void setTime(View v)
    {

        Date date=new Date();
        Calendar calendar=Calendar.getInstance();
        Calendar now=Calendar.getInstance();
        calendar.setTime(date);
        now.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE,min);
        calendar.set(Calendar.SECOND,0);
        if(calendar.before(now))
        {
            calendar.add(Calendar.DATE,1);
        }
        Intent intent=new Intent(SleepAlarm.this, BroadcastReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(SleepAlarm.this,24444,intent,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
    }

}