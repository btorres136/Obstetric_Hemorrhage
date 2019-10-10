package com.research.obstetric_hemorrhage;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Alarm extends BroadcastReceiver {
    private NotificationManagerCompat mManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Notifications","Notifications", NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            getManager(context).createNotificationChannel(channel);
            //PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 ,intent, PendingIntent.FLAG_UPDATE_CURRENT)
            NotificationCompat.Builder not =  new NotificationCompat.Builder(context, "Notifications")
                    .setContentTitle("Patient: "+intent.getExtras().getString("PATIENT_NAME") + " needs your help!")
                    .setContentText("Enter the app to add patient Info!")
                    .setSmallIcon(R.mipmap.d2icon_round);
            getManager(context).notify(intent.getExtras().getInt("ID"), not.build());
        }
        else{
            //super.onMessageReceived(remoteMessage);
           /* NotificationCompat.Builder not =  new NotificationCompat.Builder(getApplicationContext(), "Notifications")
                    .setContentTitle(remoteMessage.getData().get("title"))
                    .setContentText(remoteMessage.getData().get("body"))
                    .setSmallIcon(R.mipmap.d2icon_round);
            getManager().notify(0, not.build());*/
        }
    }

    private NotificationManagerCompat getManager(Context context) {
        if (mManager == null) {
            mManager = NotificationManagerCompat.from(context
            );
        }
        return mManager;
    }
}
