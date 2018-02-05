package com.example.lyricsdownload.design;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wanlijun
 * @description
 * @time 2018/2/5 14:46
 */

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i("wanlijun","action="+action);
        if(action.equals("android.provider.Telephony.SMS_RECEIVED")){
//            abortBroadcast();
            Bundle bundle = intent.getExtras();
            if(bundle != null){
                Object[] obj = (Object[]) bundle.get("pdus");
                for(int i=0;i<obj.length;i++){
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj[i]);
                    Log.i("wanlijun",smsMessage.getDisplayMessageBody());
                    Log.i("wanlijun",smsMessage.getDisplayOriginatingAddress());
//                    Log.i("wanlijun",smsMessage.getMessageBody());
//                    Log.i("wanlijun",smsMessage.getOriginatingAddress());
                    Log.i("wanlijun",getTime(smsMessage.getTimestampMillis()));
                }
            }
        }
    }
    private String getTime(long time){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
        Date date = new Date(time);
        return sdf.format(date);
    }
}
