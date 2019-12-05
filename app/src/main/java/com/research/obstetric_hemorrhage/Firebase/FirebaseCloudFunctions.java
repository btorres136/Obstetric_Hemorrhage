package com.research.obstetric_hemorrhage.Firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.research.obstetric_hemorrhage.R;

public class FirebaseCloudFunctions extends FirebaseMessagingService {
    private NotificationManagerCompat mManager;

    public FirebaseCloudFunctions() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Notifications","Notifications", NotificationManager.IMPORTANCE_HIGH);
           channel.enableLights(true);
           channel.enableVibration(true);
           channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
           getManager().createNotificationChannel(channel);
            //PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 ,intent, PendingIntent.FLAG_UPDATE_CURRENT)
           NotificationCompat.Builder not =  new NotificationCompat.Builder(getApplicationContext(), "Notifications")
                   .setContentTitle(remoteMessage.getData().get("title"))
                   .setContentText(remoteMessage.getData().get("body"))
                   .setSmallIcon(R.mipmap.d2icon_round);
           getManager().notify(0, not.build());
        }
        else{
            //super.onMessageReceived(remoteMessage);
            NotificationCompat.Builder not =  new NotificationCompat.Builder(getApplicationContext(), "Notifications")
                    .setContentTitle(remoteMessage.getData().get("title"))
                    .setContentText(remoteMessage.getData().get("body"))
                    .setSmallIcon(R.mipmap.d2icon_round);
            getManager().notify(0, not.build());
        }
    }
    private NotificationManagerCompat getManager() {
        if (mManager == null) {
            mManager = NotificationManagerCompat.from(this
            );
        }
        return mManager;
    }

    @Override
    public void onNewToken(String s) {
        //super.onNewToken(s);
    }

}
