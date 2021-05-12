package com.example.mentalipl.views;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mentalipl.Builder;
import com.example.mentalipl.R;
import com.example.mentalipl.models.PartieModele;
import com.example.mentalipl.models.PartieObserver;

public class CalculActivity extends AppCompatActivity implements PartieObserver {
    private PartieModele partieModele;
    private TextView tvCalcul;
    private TextView tvCalculPrec;
    private ProgressBar progressBarNbOp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvCalcul = findViewById(R.id.textViewOpCour);
        tvCalculPrec = findViewById(R.id.textViewPrec);
        progressBarNbOp = findViewById(R.id.progressBarEff);


        partieModele = ((Builder)getApplication()).getPartieModele();
        progressBarNbOp.setMax(partieModele.getNbOperationsTotales());
    }

    @Override
    public void notifyChange() {
        mettreAJour();

    }

    @Override
    protected void onStart() {
        super.onStart();
        partieModele.addObserver(this);
        mettreAJour();

    }

    @Override
    protected void onStop() {
        super.onStop();
        partieModele.removeObserver(this);
    }

    private void mettreAJour(){
        EditText etProp = findViewById(R.id.editTextRepDonnee);
        etProp.setText("");
        if (partieModele.aBienRepondu()){
            tvCalculPrec.setTextColor(Color.GREEN);
        }
        else {
            tvCalculPrec.setTextColor(Color.RED);
        }
        tvCalculPrec.setText(partieModele.getCalculPrecedent());
        progressBarNbOp.setProgress(partieModele.nombreOperationFaite());
        if (!partieModele.estTerminee()) tvCalcul.setText(partieModele.fournirCalcul().getCalcul());
        else {
            TextView tvC= findViewById(R.id.textViewCalc);
            tvC.setText(getString(R.string.fini));
            etProp.setVisibility(View.INVISIBLE);
            tvCalcul.setVisibility(View.INVISIBLE);
        }


    }

    public void onOK(View view){
        if (partieModele.estTerminee()){
            String email = partieModele.getEmailRapport();
            String rapport = partieModele.getRapport();
            Intent mailIntent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse("mailto:?subject=" + "Rapport de partie"+ "&body=" + rapport + "&to=" + email);
            mailIntent.setData(data);
            startActivity(mailIntent);
            finish();
        }
        else {
            EditText etProp = findViewById(R.id.editTextRepDonnee);
            String rep = etProp.getText().toString();
            if (rep.equals("")){
                Toast.makeText(this,getString(R.string.encode_rep),Toast.LENGTH_LONG);
                return;
            }
            int proposition = Integer.parseInt(rep);
            partieModele.verifierResultat(proposition);
        }
    }

}
