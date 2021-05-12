package com.example.mentalipl.models;

import com.example.mentalipl.models.domaine.Calcul;
import com.example.mentalipl.models.domaine.Partie;

import java.util.ArrayList;

public class PartieModele {
    private static final String DEFAULT_EMAIL = "emmeline.leconte@vinci.be";
    private String rapport="";
    private int nbBonneReponse=0;
    private Partie partie;
    private ArrayList<PartieObserver> observers = new ArrayList<PartieObserver>();
    private String calculPrecedent = "";
    private boolean bienRepondu = true;
    private String email;

    public PartieModele(){
        partie = new Partie();
    }

    public void addObserver(PartieObserver observer){
        if (!observers.contains(observer)) observers.add(observer);
    }


    public void removeObserver(PartieObserver observer){
        observers.remove(observer);
    }

    public void notifyChange(){
        for (PartieObserver po : observers){
            po.notifyChange();
        }
    }


    public void commencer(boolean choixAdd, boolean choixSous, int nbOperation,String email ) {
        partie.commencer(choixAdd,choixSous,nbOperation);
        nbBonneReponse=0;
        rapport ="";
        calculPrecedent="";
        this.email=email;
    }

    public Calcul fournirCalcul(){
        return  partie.getCourante();
    }

    public void verifierResultat(int proposition){
        Calcul calcul = partie.getCourante();
        calculPrecedent = calcul.getCalcul()  + proposition;
        if (proposition == partie.getCourante().getRes()){
            bienRepondu = true;
            nbBonneReponse++;
        } else {
            bienRepondu = false;
            calculPrecedent += " (" + calcul.getRes() + ")";
            rapport += "\n" + calculPrecedent;
        }
        partie.passerAuCalculSuivant();
        notifyChange();
    }

    public boolean aBienRepondu(){
        return bienRepondu;
    }

    public String getCalculPrecedent() {
        return calculPrecedent;
    }

    public boolean estTerminee(){
        return partie.isFini();
    }

    public int nombreOperationFaite(){
        return partie.getNbOperationsEffectuees();
    }

    public void changerMinAddition(int min){
        partie.getConfigurationAddition().setMin(min);
    }

    public void changerMaxAddition(int max){
        partie.getConfigurationAddition().setMax(max);
    }

    public void changerMinSoustraction(int min){
        partie.getConfigurationSoustraction().setMin(min);
    }

    public void changerMaxSoustraction(int max){
        partie.getConfigurationSoustraction().setMax(max);
    }

    public String getEmailRapport(){
        if (email.equals("")) return DEFAULT_EMAIL;
        return email;
    }

    public String getRapport(){
        double pourcentage = Math.round(((double)nbBonneReponse)/partie.getNbOperationsTotales()*10000)/100;
        String rapportFinal = "Résultat : "+ pourcentage+"%";
        if (!rapport.equals(""))
             rapportFinal +=    "\nListe des calculs erronés : " + rapport;
        return rapportFinal;
    }

    public int getNbOperationsTotales(){
        return partie.getNbOperationsTotales();
    }
}
