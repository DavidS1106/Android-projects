package com.example.citiesofcountry.domaine;


import android.support.annotation.NonNull;


public class Invite {

    private int id;


    private String nom;

    private String prenom;

    private String boisson;

    public Invite(String nom,String prenom,String boisson) {
        this.nom = nom;
        this.prenom = prenom;
        this.boisson = boisson;
    }
    @NonNull
    public String getNom() {
        return nom;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setNom(@NonNull String nom) {
        this.nom = nom;
    }

    @NonNull
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(@NonNull String prenom) {
        this.prenom = prenom;
    }

    @NonNull
    public String getBoisson() {
        return boisson;
    }

    public void setBoisson(@NonNull String boisson) {
        this.boisson = boisson;
    }
}
