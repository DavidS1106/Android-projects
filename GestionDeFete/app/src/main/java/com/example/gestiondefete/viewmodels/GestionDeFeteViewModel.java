package com.example.gestiondefete.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;


import com.example.gestiondefete.GestionDeFeteRepository;
import com.example.gestiondefete.models.Participant;

import java.util.List;

public class GestionDeFeteViewModel extends AndroidViewModel {
    private LiveData<List<Participant>> participants;
    private GestionDeFeteRepository repository;

    public GestionDeFeteViewModel(Application app){
        super(app);
        repository = new GestionDeFeteRepository(app);
        this.participants = repository.getAllParticipants();
    }

    public LiveData<List<Participant>> getAllParticipants(){
        return participants;
    }

    public void insertParticipant(Participant participant){
        repository.insertParticipant(participant);
    }
}
