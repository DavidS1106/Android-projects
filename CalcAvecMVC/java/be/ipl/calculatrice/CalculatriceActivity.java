package be.ipl.calculatrice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class CalculatriceActivity extends AppCompatActivity implements CalculatriceModel.ModelChangeObserver {

    TextView resultatTV;
    CalculatriceModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculatrice);
        model = ((Builder)getApplication()).getModel();

        resultatTV = (TextView)findViewById(R.id.textViewResultat);

        notifyModelChange();
    }

    @Override
    protected void onResume() {
        super.onResume();
        model.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        model.unregister(this);
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
     * Partie controle
     */



    public void onChiffre(View v){
        String chiffre = (String)v.getTag();

        model.ajouterChiffre(chiffre);


    }

    public void onOperateur(View v){
        String op = (String)v.getTag();

       model.ajoutOperateur(op);

    }

    public void onEgal(View v){
        model.egal();
    }

    public void onReset(View v){
        model.resetEtat();
    }


    @Override
    public void notifyModelChange() {
        CalculatriceModel.Etat etat = model.getEtat();
        String affiche ="";

        if (etat == CalculatriceModel.Etat.OP_D){
            affiche = model.getOpDroit();
        } else{
            affiche = model.getOpGauche();
        }

        resultatTV.setText(affiche);
    }
}
