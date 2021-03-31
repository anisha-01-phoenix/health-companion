package com.example.healthpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

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
        TextView textView=findViewById(R.id.scheduleWater);
        ImageView imageView=findViewById(R.id.crossimage);
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
        setNotification();



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(waterview);
            }
        });
        linearLayout.addView(waterview);
    }

    private void setNotification() {
        NotificationManager manager=(NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1=new Intent(WaterSchedule.this,Water.class);
        intent1.putExtra("Yes",true);
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent1=PendingIntent.getActivity(WaterSchedule.this,0,intent1,PendingIntent.FLAG_ONE_SHOT);
        Intent intent=new Intent(WaterSchedule.this,Water.class);
        intent.putExtra("No",false);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent=PendingIntent.getActivity(WaterSchedule.this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(WaterSchedule.this,getString(R.string.app_name));
        builder.setContentTitle("DRINK WATER");
        builder.setContentText("Did you drink Water?");
        builder.setSmallIcon(R.drawable.water);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.addAction(R.drawable.ic_launcher_foreground,"Yes",pendingIntent1);
        builder.addAction(R.drawable.ic_launcher_foreground,"No",pendingIntent);
        manager.notify(1,builder.build());


    }

    private void removeView(View view)
    {
        linearLayout.removeView(view);
    }

    }
