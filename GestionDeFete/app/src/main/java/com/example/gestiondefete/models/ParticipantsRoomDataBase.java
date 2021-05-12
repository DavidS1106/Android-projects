package com.example.gestiondefete.models;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Participant.class}, version=1)
public abstract class ParticipantsRoomDataBase extends RoomDatabase {
    private static ParticipantsRoomDataBase INSTANCE;

    public static ParticipantsRoomDataBase getINSTANCE(final Context context) {
        if (INSTANCE== null){
            synchronized (ParticipantsRoomDataBase.class){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),ParticipantsRoomDataBase.class,"word_database").build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract ParticipantDao participantDao();
}
