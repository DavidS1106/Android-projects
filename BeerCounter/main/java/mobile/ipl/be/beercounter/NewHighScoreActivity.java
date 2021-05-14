package mobile.ipl.be.beercounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


public class NewHighScoreActivity extends AppCompatActivity {

    public static final String MAX_BEER = "maxBeer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_high_score);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    }

  
    public void onSave(View view) {
        int nbBeerMax = 0;
        int ok = Activity.RESULT_OK;
        try {
            TextView textViewNbBeersMax = findViewById(R.id.editText_nb_beer_max);
            nbBeerMax = Integer.parseInt(textViewNbBeersMax.getText().toString());
        } catch (Exception e) {
            ok = Activity.RESULT_CANCELED;
        }

        Intent returnIntent = new Intent();
        returnIntent.putExtra(MAX_BEER, nbBeerMax);
        setResult(ok, returnIntent);
        finish();
    }
}
