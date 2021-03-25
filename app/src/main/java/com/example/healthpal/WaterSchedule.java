package com.example.healthpal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class WaterSchedule extends AppCompatActivity implements View.OnClickListener {
    LinearLayout linearLayout;
    Button button;

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
        TextView textView=(TextView)findViewById(R.id.scheduleWater);
        ImageView imageView=(ImageView)findViewById(R.id.crossimage);
        Calendar calendar=Calendar.getInstance();
        int hours=calendar.get(Calendar.HOUR_OF_DAY);
        int mins=calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(WaterSchedule.this, R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar1=Calendar.getInstance();
                calendar1.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar1.set(Calendar.MINUTE,minute);
                calendar.setTimeZone(TimeZone.getDefault());
                SimpleDateFormat dateFormat=new SimpleDateFormat("k:mm a");
                String time=dateFormat.format(calendar1.getTime());
                textView.setText(time);
            }
        },hours,mins,false);
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
}