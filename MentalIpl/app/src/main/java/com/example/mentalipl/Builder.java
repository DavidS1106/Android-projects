package com.example.mentalipl;

import android.app.Application;

import com.example.mentalipl.models.PartieModele;

public class Builder extends Application {
    private PartieModele partieModele;

    @Override
    public void onCreate() {
        super.onCreate();
        partieModele = new PartieModele();
    }

    public PartieModele getPartieModele() {
        return partieModele;
    }
}
