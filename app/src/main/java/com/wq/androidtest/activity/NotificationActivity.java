package com.wq.androidtest.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;

/**
 * Created by wangqi on 15/9/17.
 */
public class NotificationActivity extends BaseActivity implements View.OnClickListener {

    Button defaultBtn;
    Button customBtn;
    NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        defaultBtn = (Button) findViewById(R.id.defaultBtn);
        customBtn = (Button) findViewById(R.id.customBtn);
        defaultBtn.setOnClickListener(this);
        customBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.defaultBtn:
                Notification defaultNotif = new Notification();
                defaultNotif.icon = R.drawable.star_yellow;
                defaultNotif.tickerText = "hello";
                defaultNotif.when = System.currentTimeMillis();
                defaultNotif.flags = Notification.FLAG_AUTO_CANCEL;
                Intent intent = new Intent(this, TextActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                defaultNotif.setLatestEventInfo(this, "hellowrold", "this is a notification", pendingIntent);
                manager.notify(1, defaultNotif);


//                Notification noti = new Notification.Builder(mCtx)
//                        .setContentTitle("New mail from")
//                        .setContentText("test")
//                        .setSmallIcon(R.drawable.icon1)
//                        .setLargeIcon(BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.icon1)).getNotification();
//                manager.notify(1, noti);

                break;
            case R.id.customBtn:

                Notification customNotif = new Notification();
                customNotif.icon = R.drawable.star_yellow;
                customNotif.tickerText = "custom notification";
                customNotif.when = System.currentTimeMillis();
                customNotif.flags = Notification.FLAG_AUTO_CANCEL;
                Intent customIntent = new Intent(this, TextActivity.class);
                PendingIntent customPendingIntent = PendingIntent.getActivity(this, 0, customIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);
                remoteViews.setTextViewText(R.id.msg, "custom title");
                remoteViews.setTextViewText(R.id.open, "click to open");
                remoteViews.setImageViewResource(R.id.icon, R.drawable.icon1);
                PendingIntent clickPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, DemoTableActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
                remoteViews.setOnClickPendingIntent(R.id.open, clickPendingIntent);
                customNotif.contentView = remoteViews;
                customNotif.contentIntent = customPendingIntent;
                manager.notify(2, customNotif);
                break;
        }
    }
}
