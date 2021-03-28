package com.example.healthpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.TimePickerDialog;
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

public class WaterSchedule extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {
    LinearLayout linearLayout;
    Button button;
    Calendar calendar=Calendar.getInstance();
    TimePickerDialog timePickerDialog;
    public int c=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_schedule);
        linearLayout=findViewById(R.id.waterTime);
        button=findViewById(R.id.waterTimeSet);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        addView();
    }

    private void addView() {
        final View waterview=getLayoutInflater().inflate(R.layout.row_add,null,false);
        TextView textView=findViewById(R.id.scheduleWater);
        ImageView imageView=findViewById(R.id.crossimage);
        SimpleDateFormat dateFormat=new SimpleDateFormat("k:mm a");
        String time=dateFormat.format(calendar.getTime());
        textView.setText(time);
        timePickerDialog.show();



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
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
        calendar.set(Calendar.MINUTE,minute);

        NotifyMe notifyMe=new NotifyMe.Builder(getApplicationContext())
                .title("TIME TO DRINK WATER")
                .content("Did you drink Water now?")
                .time(calendar)
                .addAction(new Intent(),"Dismiss",true,false)
                .addAction(new Intent(),"Done", true)
                .large_icon(R.mipmap.ic_launcher_round)
                .build();

    }
}