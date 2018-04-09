package com.example.nandhu.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MainActivity extends BroadcastReceiver {

    Context cont;
    @Override
    public void onReceive(Context context, Intent intent) {
        cont = context;
        if("android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())){
            Bundle bundle = intent.getExtras();
            Object msg[] = (Object[]) bundle.get("pdus");
            SmsMessage sms[] = new SmsMessage[msg.length];
            for(int i=0; i< msg.length; i++){
                sms[i] = SmsMessage.createFromPdu((byte[])msg[i]);
            }
            String smsmsg = sms[0].getMessageBody();
            String from = sms[0].getOriginatingAddress();
            System.out.println(from+": "+smsmsg);
            if (smsmsg.equalsIgnoreCase("lakshmi silent")){
                AudioManager am;
                am= (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
/*

//For Normal mode
            am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
*/

//For Silent mode
                am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
/*
//For Vibrate mode
            am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);*/
                Toast.makeText(context, "Phone silent", Toast.LENGTH_SHORT).show();
            }


        }
    }
}
