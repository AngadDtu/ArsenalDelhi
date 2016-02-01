package com.example.dell.arsenaldelhi;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.parse.ParseAnalytics;
import com.parse.ParsePushBroadcastReceiver;
import com.parse.PushService;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DELL on 15-01-2016.
 */
public class ParseCustomBroadcastReceiver extends ParsePushBroadcastReceiver {


    private static final String TAG = "TAG";
    @Override
    protected int getSmallIconId(Context context, Intent intent) {
        return R.drawable.logo;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        try {

            JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
            Log.d(TAG, json.getString("alert").toString());

            final String notificationTitle = json.getString("title").toString();
            final String notificationContent = json.getString("alert").toString();

            Intent resultIntent = null;
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);


            resultIntent = new Intent(context, Notifications.class);
            stackBuilder.addParentStack(NotificationManager.class);

            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_ONE_SHOT);

//Customize your notification - sample code
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(this.getSmallIconId(context, intent))
                            .setContentTitle(notificationTitle)
                            .setContentIntent(resultPendingIntent)
                            .setContentText(notificationContent);

            int mNotificationId = 001;
            NotificationManager mNotifyMgr =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            //builder.setContentIntent(resultPendingIntent);
            mNotifyMgr.notify(mNotificationId, builder.build());


        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
        }

    }
    @Override
    protected void onPushOpen(Context context, Intent intent) {
        ParseAnalytics.trackAppOpenedInBackground(intent);
    }
}