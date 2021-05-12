package be.ipl.calculatrice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class CalculatriceActivity extends AppCompatActivity {

    TextView resultatTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculatrice);


        resultatTV = (TextView)findViewById(R.id.textViewResultat);

        etat = Etat.INIT;
        opGauche = "0";
        opDroit = "";
        operateur = "";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculatrice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /*
     *
     * Gestion évènements
     */

    private enum Etat{
        INIT, OP_G, OP_D, OPERATEUR, RESULTAT
    }

    private Etat etat;
    private String opGauche, opDroit, operateur;

    public void onChiffre(View v){
        String chiffre = (String)v.getTag();

        switch (etat){
            case INIT:
                opGauche = chiffre;
                resultatTV.setText(opGauche);
                etat = Etat.OP_G;
                break;
            case OP_G:
                opGauche += chiffre;
                resultatTV.setText(opGauche);
                break;
            case OPERATEUR:
                opDroit = chiffre;
                etat = Etat.OP_D;
                resultatTV.setText(opDroit);
                break;
            case OP_D:
                opDroit += chiffre;
                resultatTV.setText(opDroit);
                break;

            default:;
        }


    }

    public void onOperateur(View v){
        String op = (String)v.getTag();

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

    }

    public void onEgal(View v){
        switch (etat){
            case OP_D:
                opGauche = calcResult();
                resultatTV.setText(opGauche);
                break;
            default:;
        }
    }

    public void onReset(View v){
        etat = Etat.INIT;
        opGauche = "0";
        resultatTV.setText(opGauche);
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

        return "Operation not implemented";
    }
}
