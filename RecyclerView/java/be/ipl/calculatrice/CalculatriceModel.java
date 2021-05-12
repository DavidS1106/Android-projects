package be.ipl.calculatrice;

import java.util.ArrayList;

/**
 * Created by Gregg on 03-05-18.
 */

public class CalculatriceModel {

    public enum Etat{
        INIT, OP_G, OP_D, OPERATEUR, RESULTAT
    }

    public Etat getEtat() {
        return etat;
    }

    public String getOpGauche() {
        return opGauche;
    }

    public String getOpDroit() {
        return opDroit;
    }

    public String getOperateur() {
        return operateur;
    }

    private Etat etat;
    private String opGauche, opDroit, operateur;

    interface ModelChangeObserver{

        void notifyModelChange();

    }

    private ArrayList<ModelChangeObserver> observers = new ArrayList<>();

    public void register(ModelChangeObserver observer){
        observers.add(observer);
    }

    public void unregister(ModelChangeObserver observer){
        observers.remove(observer);
    }

    private void notifyAllObserver(){
        for (ModelChangeObserver obs : observers){
            obs.notifyModelChange();
        }
    }




    public CalculatriceModel(){
        etat = Etat.INIT;
        opGauche = "0";
        opDroit = "";
        operateur = "";
    }

    public void resetEtat(){
        etat = Etat.INIT;
        opGauche = "0";
        notifyAllObserver();

    }

    public void ajouterChiffre(String chiffre){

        switch (etat){
            case INIT:
                opGauche = chiffre;
                etat = Etat.OP_G;
                break;
            case OP_G:
                opGauche += chiffre;
                break;
            case OPERATEUR:
                opDroit = chiffre;
                etat = Etat.OP_D;
                break;
            case OP_D:
                opDroit += chiffre;
                break;

            default:;
        }
        notifyAllObserver();


    }

    public void ajoutOperateur(String op){


        switch (etat){
            case OP_G:
            case OP_D:
                operateur = op;
                etat = Etat.OPERATEUR;
                break;
            case OPERATEUR:
                operateur = op;
                break;
            default:;
        }
        notifyAllObserver();

    }

    public void egal(){
        switch (etat){
            case OP_D:
                opGauche = calcResult();
                etat = Etat.RESULTAT;
                break;
            default:;
        }
        notifyAllObserver();
    }



    private String calcResult(){
        int res = 0;
        switch (operateur){
            case "+":
                res =  Integer.parseInt(opGauche) +
                        Integer.parseInt(opDroit);
                return res+"";
            case "-":
                res =  Integer.parseInt(opGauche) -
                        Integer.parseInt(opDroit);
                return res+"";
            default:;
        }

        return "";
    }





}
