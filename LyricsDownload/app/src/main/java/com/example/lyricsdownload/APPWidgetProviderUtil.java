package com.example.lyricsdownload;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * @author wanlijun
 * @description  处理APPWidget的逻辑事务
 * @time 2018/1/31 11:39
 */

public class APPWidgetProviderUtil extends AppWidgetProvider {
    private RemoteViews remoteViews;
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i("wanlijun","action="+action);
        if(action.equals("android.appwidget.action.APPWIDGET_UPDATE")){
            String command = intent.getStringExtra("command");
            if(command != null) {
                if (command.equals("last")) {
                    Log.i("wanlijun", "command=" + command);
                } else {
                    Log.i("wanlijun", "command=" + command);
                }
            }
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_layout);
        Intent intent = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        Intent intent1 = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
        intent1.putExtra("command","last");
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context,1,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        Intent intent2 = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
        intent2.putExtra("command","next");
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(context,2,intent2,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.playBtn,pendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.lastBtn,pendingIntent1);
        remoteViews.setOnClickPendingIntent(R.id.nextBtn,pendingIntent2);
        appWidgetManager.updateAppWidget(appWidgetIds,remoteViews);
    }
}
