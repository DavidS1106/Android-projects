package com.example.mentalipl.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mentalipl.Builder;
import com.example.mentalipl.R;
import com.example.mentalipl.models.PartieModele;

public class MainActivity extends AppCompatActivity {
    private PartieModele partieModele;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        partieModele = ((Builder)getApplication()).getPartieModele();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_configurer) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onNouvellePartie(View view){
         EditText edNumber= findViewById(R.id.editTextNbOp);
        String nbText = edNumber.getText().toString();
        if (nbText.equals("")){
            Toast.makeText(this,getString(R.string.manque_nombre_op),Toast.LENGTH_LONG).show();
            return;
        }
        else {
            CheckBox cbAdd = findViewById(R.id.checkBoxAddition);
            CheckBox cbSous = findViewById(R.id.checkBoxSoustraction);
            if (!cbAdd.isChecked() &&!cbSous.isChecked()){
                Toast.makeText(this,getString(R.string.manque_choix_op),Toast.LENGTH_LONG).show();
                return;
            }
            int nbOperations = Integer.parseInt(edNumber.getText().toString());
            EditText etMail = findViewById(R.id.editTextMail);
            String email = etMail.getText().toString();
            partieModele.commencer(cbAdd.isChecked(),cbSous.isChecked(),nbOperations,email);
            Intent intent = new Intent(this,CalculActivity.class);
            startActivity(intent);
        }
    }
}
