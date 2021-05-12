package com.example.gestiondefete.models;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ParticipantDao {
    @Insert
    void insertParticipant(Participant participant);

    @Query("SELECT * FROM participants")
    LiveData<List<Participant>> getAllParticipants();
}


