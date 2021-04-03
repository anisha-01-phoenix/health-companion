package com.example.healthpal;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

public class BroadcastReceiver  extends android.content.BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Vibrator vibrator=(Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);
        Notification notification=new Notification.Builder(context)
                .setContentTitle("WAKE UP")
                .setContentText("Alarm Ringing")
                .setSmallIcon(R.drawable.sleep)
                .build();

        NotificationManager manager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notification.flags|=Notification.FLAG_AUTO_CANCEL;
        manager.notify(0,notification);
        Uri notif= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone ringtone=RingtoneManager.getRingtone(context, notif);
        ringtone.play();

    }
}
