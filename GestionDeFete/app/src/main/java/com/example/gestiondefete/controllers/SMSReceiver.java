package com.example.gestiondefete.controllers;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.example.gestiondefete.GestionDeFeteRepository;
import com.example.gestiondefete.models.Participant;


public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("GestionDeFete","dans le receiver");
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++)
            {
                // Convertir les PDUs en messages
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
            // Enfin, traiter les messages
            for (SmsMessage message : messages) {
                String tel = message.getOriginatingAddress();
                String msg = message.getMessageBody();
                String[] decoupe = msg.split(";");
                Log.d("GestionDeFete",msg);
                if (decoupe.length ==4 && decoupe[0].equalsIgnoreCase("#laFete")) {
                    Log.d("GestionDeFete","creation");
                    Participant participant = new Participant(tel,decoupe[1],decoupe[2],decoupe[3]);
                    GestionDeFeteRepository gestionDeFeteRepository = new GestionDeFeteRepository((Application)context.getApplicationContext());
                    Log.d("GestionDeFete","ajout");
                    gestionDeFeteRepository.insertParticipant(participant);
                }
            }
        }
    }

}
