package com.example.gestiondefete.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "participants")
public class Participant {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id_particpant")
    private Integer idParticipant;

    @NonNull
    @ColumnInfo(name="telephone")
    private String tel;

    @NonNull
    @ColumnInfo(name="nom")
    private String nom;

    @NonNull
    @ColumnInfo(name="prenom")
    private String prenom;

    @NonNull
    @ColumnInfo(name="boisson_favorite")
    private String boissonFavorite;

    public Participant(@NonNull String tel,@NonNull String nom,@NonNull String prenom,@NonNull String boissonFavorite){
        this.tel=tel;
        this.nom= nom;
        this.prenom=prenom;
        this.boissonFavorite= boissonFavorite;
    }


    @NonNull
    public Integer getIdParticipant() {
        return idParticipant;
    }

    @NonNull
    public String getTel() {
        return tel;
    }

    @NonNull
    public String getNom() {
        return nom;
    }

    @NonNull
    public String getPrenom() {
        return prenom;
    }

    @NonNull
    public String getBoissonFavorite() {
        return boissonFavorite;
    }

    public void setIdParticipant(@NonNull Integer idParticipant) {
        this.idParticipant = idParticipant;
    }
}
