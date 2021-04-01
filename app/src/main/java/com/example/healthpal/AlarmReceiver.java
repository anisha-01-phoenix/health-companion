package com.example.healthpal;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int notificationId=intent.getIntExtra("notificationId",0);
        Intent mainIntent=new Intent(context, WaterSchedule.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,mainIntent,0);
        NotificationManager manager=(NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1=new Intent(context,Water.class);
        intent1.putExtra("Yes",true);
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent1=PendingIntent.getActivity(context,0,intent1,PendingIntent.FLAG_ONE_SHOT);
        Intent intent2=new Intent(context,Water.class);
        intent2.putExtra("No",false);
        intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent2=PendingIntent.getActivity(context,0,intent2,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,"Water");
        builder.setContentTitle("DRINK WATER");
        builder.setContentText("Did you drink Water?");
        builder.setSmallIcon(R.drawable.water);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.addAction(R.drawable.ic_launcher_foreground,"Yes",pendingIntent1);
        builder.addAction(R.drawable.ic_launcher_foreground,"No",pendingIntent2);
        manager.notify(1,builder.build());

    }
}
