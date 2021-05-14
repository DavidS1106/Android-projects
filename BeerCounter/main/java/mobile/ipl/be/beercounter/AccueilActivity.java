package mobile.ipl.be.beercounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AccueilActivity extends AppCompatActivity {

    public static final String NB_BEERS = "nbBeers";
    public static final String HIGH_SCORE = "highScore";
    public static final int HIGH_SCORE_REQUEST_CODE = 34;
    private int nbBeers;
    private int highScore;
    private TextView textViewNbBeers;
    private TextView textViewStatusParty;
    private boolean isHighScoreReached;
    private int maxBeer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textViewNbBeers = (TextView)findViewById(R.id.textView_nb_beers);
        textViewStatusParty = (TextView)findViewById(R.id.textViewstatus_party);

        
        Button buttonAddBeer = (Button)findViewById(R.id.button_add_beer);
        buttonAddBeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbBeers++;

                if (isHighScoreReached){
                    maxBeer--;
                    if (maxBeer == 0){
                        Toast.makeText(AccueilActivity.this,R.string.send_sms, Toast.LENGTH_LONG).show();
                        /
                    }
                }


                if (highScore<nbBeers){ 
                    highScore = nbBeers;
                    updateMessage(true);
                    if (!isHighScoreReached){
                        isHighScoreReached = true;
                        lauchHighScoreActivity();
                    }
                }
                else{
                    updateMessage(false);
                }



            }
        });

        
        if (savedInstanceState != null){
            nbBeers = savedInstanceState.getInt(NB_BEERS);
            highScore = savedInstanceState.getInt(HIGH_SCORE);
        }
        updateMessage(highScore<nbBeers);
    }

   
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HIGH_SCORE_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            maxBeer = data.getIntExtra(NewHighScoreActivity.MAX_BEER, 1);
            
            textViewStatusParty.setText("Il vous reste " + maxBeer + " bière(s) à boire");
        }
    }

 
    private void lauchHighScoreActivity(){
        Intent hsIntent = new Intent(this, NewHighScoreActivity.class);
        startActivityForResult(hsIntent, HIGH_SCORE_REQUEST_CODE);
    }

    private void updateMessage(boolean newHighScore){

        textViewNbBeers.setText(""+nbBeers);

        String [] messages = getResources().getStringArray(R.array.party_status);
        int [] limits = getResources().getIntArray(R.array.beer_limits);

        int nbLimits=0;
        for (int limit: limits){
            if (nbBeers>=limit)
                nbLimits++;
        }

        String newHighscoreMessage = "";
        if (newHighScore)
            newHighscoreMessage = getResources().getString(R.string.new_highscore);

        if(nbLimits<messages.length)
            textViewStatusParty.setText(messages[nbLimits] + " " + newHighscoreMessage);
    }



  
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NB_BEERS, nbBeers);
        outState.putInt(HIGH_SCORE, highScore);
    }

   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accueil, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        int id = item.getItemId();

        if (id == R.id.action_quit) {
            finish();
            return true;
        }

        if (id == R.id.action_reset){
            nbBeers = 0;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
