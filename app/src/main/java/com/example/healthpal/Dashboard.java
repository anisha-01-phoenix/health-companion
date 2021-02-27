package com.example.healthpal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
       ImageButton button= findViewById(R.id.pedometer);
       ImageButton button1= findViewById(R.id.sleep);
       ImageButton button2= findViewById(R.id.water);
       ImageButton button3= findViewById(R.id.exercise);
       Button btn_1=findViewById(R.id.logout);
       Button btn_2=findViewById(R.id.exit);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openPedometer();
           }
       });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensleeping();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWater();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExercise();
            }
        });
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

       

    }
    public void openPedometer(){
        Intent intent=new Intent(this,Pedometer.class);
        startActivity(intent);
    }
    public void opensleeping(){
        Intent intent=new Intent(this,sleeping.class);
        startActivity(intent);
    }
    public void openWater(){
        Intent intent=new Intent(this,Water.class);
        startActivity(intent);
    }
    public void openExercise(){
        Intent intent=new Intent(this,Exercise.class);
        startActivity(intent);
    }
    public void openActivity2(){
        Intent intent=new Intent(this,Activity2.class);
        startActivity(intent);
    }
    public void openMainActivity(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}