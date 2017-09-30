package com.sd.style.common.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.sd.style.R;
import com.sd.style.ui.TestActivity;


/**
 * Author: HeLei on 2017/9/15 17:29
 */

public class NotificationHelper {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void notification(Context context){
        Intent intent = new Intent(context, TestActivity.class);
        intent.putExtra("params", "notification");
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = new Notification.Builder(context)
                .setTicker("你有一条新的通知")
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(contentIntent)
                .setContentText("妹子给你发来了消息")
                .setContentTitle("新的私信")
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();

        manager.notify(1, notification);
    }
}
