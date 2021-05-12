package com.example.mentalipl.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mentalipl.Builder;
import com.example.mentalipl.R;
import com.example.mentalipl.models.PartieModele;

public class SettingActivity extends AppCompatActivity {
    private PartieModele modele;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        modele = ((Builder)getApplication()).getPartieModele();
    }

    public void onMAJBorneMinA(View view){
        EditText etMinAdd = findViewById(R.id.editText_min_add);
        String minimum = etMinAdd.getText().toString();
        if (minimum.equals("")){
            Toast.makeText(this,getString(R.string.bmAdd), Toast.LENGTH_LONG);
        }
        else {
            modele.changerMinAddition(Integer.parseInt(minimum));
        }
    }

    public void onMAJBorneMaxA(View view){
        EditText etMaxAdd = findViewById(R.id.editText_max_add);
        String maximum = etMaxAdd.getText().toString();
        if (maximum.equals("")){
            Toast.makeText(this,getString(R.string.bmaxadd), Toast.LENGTH_LONG).show();
        }
        else {
            modele.changerMaxAddition(Integer.parseInt(maximum));
        }
    }

    public void onMAJBorneMinS(View view){
        EditText etMinSous = findViewById(R.id.editText_min_sous);
        String minimum = etMinSous.getText().toString();
        if (minimum.equals("")){
            Toast.makeText(this,getString(R.string.bminsous), Toast.LENGTH_LONG).show();
        }
        else {
            modele.changerMinSoustraction(Integer.parseInt(minimum));
        }
    }

    public void onMAJBorneMaxS(View view){
        EditText etMaxSous = findViewById(R.id.editText_max_sous);
        String maximum = etMaxSous.getText().toString();
        if (maximum.equals("")){
            Toast.makeText(this,getString(R.string.bmaxsous), Toast.LENGTH_LONG).show();
        }
        else {
            modele.changerMaxSoustraction(Integer.parseInt(maximum));
        }
    }

}
