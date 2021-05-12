package com.example.mentalipl.models.domaine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Partie {
    private Map<GenerateurDeCalculs, Boolean> operationMap = new HashMap<>();
    private GenerateurDeCalculs addition, soustraction;
    private Calcul calculCourant;
    private int nbOperationsTotales, nbOperationsEffectuees;

    public boolean isAdditionPermis() {
        return operationMap.get(addition);
    }

    public boolean isSoustractionPermis() {
        return operationMap.get(soustraction);
    }

    public Partie() {
        addition = new GenerateurDAddition(new Configuration(0, 20));
        soustraction = new GenerateurDeSoustraction(new Configuration(0, 20));
        this.operationMap.put(addition, true);
        this.operationMap.put(soustraction, true);
    }

    public Configuration getConfigurationAddition() {
        return addition.getConfiguration();
    }

    public Configuration getConfigurationSoustraction() {
        return soustraction.getConfiguration();
    }


    public Calcul getCourante() {

        return this.calculCourant;
    }

    public void passerAuCalculSuivant() {
        nbOperationsEffectuees++;
        if(nbOperationsEffectuees==nbOperationsTotales)
            this.calculCourant = null;
        else
            this.calculCourant = genererCalcul();
    }

    private Calcul genererCalcul() {
        List<GenerateurDeCalculs> operationList = operationMap.entrySet().stream().filter(e -> e.getValue() == true)
                .map(e -> e.getKey()).collect(Collectors.toList());
        int randOP = (int) (Math.random() * operationList.size());
        return operationList.get(randOP).fournirCalcul();
    }

    public boolean isFini() {
        return nbOperationsTotales == nbOperationsEffectuees;
    }


    public int getNbOperationsTotales() {
        return nbOperationsTotales;
    }

    public int getNbOperationsEffectuees() {
        return nbOperationsEffectuees;
    }

    public void commencer(boolean choixAdd, boolean choixSous, int nbOp) {
        enregistrerAddition(choixAdd);
        enregistrerSoustraction(choixSous);
        this.nbOperationsTotales = nbOp;
        nbOperationsEffectuees=0;
        this.calculCourant = genererCalcul();
    }


    private void enregistrerAddition(boolean add) {
        this.operationMap.put(addition, add);
    }

    private void enregistrerSoustraction(boolean sous) {
        this.operationMap.put(soustraction, sous);
    }

}
