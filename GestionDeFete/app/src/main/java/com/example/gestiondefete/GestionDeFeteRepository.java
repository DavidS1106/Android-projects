package com.example.gestiondefete;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;


import com.example.gestiondefete.models.Participant;
import com.example.gestiondefete.models.ParticipantDao;
import com.example.gestiondefete.models.ParticipantsRoomDataBase;

import java.util.List;

public  class GestionDeFeteRepository {
    private ParticipantDao participantDao;
    private LiveData<List<Participant>> participants;
    public GestionDeFeteRepository(Application app){
        ParticipantsRoomDataBase db = ParticipantsRoomDataBase.getINSTANCE(app);
        participantDao =db.participantDao();
        participants = participantDao.getAllParticipants();
    }

    public LiveData<List<Participant>> getAllParticipants(){
        return participants;
    }

    public void insertParticipant(Participant participant){
        new InsertAsyncTask(participantDao).execute(participant);
    }

    private static class InsertAsyncTask extends AsyncTask<Participant,Void,Void>{
        private ParticipantDao participantDao;
        public InsertAsyncTask(ParticipantDao participantDao){
            this.participantDao= participantDao;
        }

        @Override
        protected Void doInBackground(Participant... participants) {
            participantDao.insertParticipant(participants[0]);
            return null;
        }
    }
}
