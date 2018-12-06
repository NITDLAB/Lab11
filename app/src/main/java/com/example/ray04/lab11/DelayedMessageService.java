package com.example.ray04.lab11;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.util.logging.LogRecord;


public class DelayedMessageService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String MESSAGE = "message";
    public static final String TAG = "DelayedMessageService";
    private static final int NOTIFICATION_ID = 2999 ;
    private long time = 0;

    public DelayedMessageService() {
        super("DelayedMessageService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String text = intent.getStringExtra(MESSAGE);

        synchronized (this)
        {
            if (intent != null) {
                time = intent.getLongExtra("time",5);
                try{
                    Log.v("TAG", String.valueOf(time));
                    wait(time*1000);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        showText(text);
    }

    private void showText(String text) {
        Intent intent  = new Intent(this,MainActivity.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification.Builder(this).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID,notification);

    }
}
