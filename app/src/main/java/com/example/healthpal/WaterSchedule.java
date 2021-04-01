package com.example.healthpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.allyants.notifyme.NotifyMe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class WaterSchedule extends AppCompatActivity implements View.OnClickListener {
    private int notificationID=1;
    LinearLayout linearLayout;
    Button button;
    Calendar calendar=Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_schedule);
        linearLayout=findViewById(R.id.waterTime);
        button=findViewById(R.id.waterTimeSet);
        findViewById(R.id.btn_set).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView();
            }
        });
    }



    private void addView() {
        final View waterview=getLayoutInflater().inflate(R.layout.row_add,null,false);
        TextView textView=findViewById(R.id.scheduleWater);
        ImageView imageView=findViewById(R.id.crossimage);
        SimpleDateFormat dateFormat=new SimpleDateFormat("k:mm a");
        textView.setText(dateFormat.format(calendar.getTime()));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(waterview);
            }
        });
        linearLayout.addView(waterview);
    }

    private void removeView(View view)
    {
        linearLayout.removeView(view);
    }

    @Override
    public void onClick(View v) {
            TimePicker timePicker=findViewById(R.id.timePicker);
        Intent intent=new Intent(WaterSchedule.this, AlarmReceiver.class);
        intent.putExtra("notificationId",notificationID);
        PendingIntent alarmIntent=PendingIntent.getBroadcast(WaterSchedule.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);

        switch (v.getId())
        {
            case R.id.btn_set:
                int hour=timePicker.getCurrentHour();
                int minute=timePicker.getCurrentMinute();
                calendar.set(Calendar.HOUR_OF_DAY,hour);
                calendar.set(Calendar.MINUTE,minute);
                calendar.set(Calendar.SECOND,0);
                long alarmStartTime=calendar.getTimeInMillis();
                alarmManager.set(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmIntent);
                break;

            case R.id.btn_cancel:
                alarmManager.cancel(alarmIntent);
                break;
        }
    }
}
