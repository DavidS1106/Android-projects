package com.example.citiesofcountry.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.citiesofcountry.R;
import com.example.citiesofcountry.domaine.Country;
import com.example.citiesofcountry.viewmodel.CountryViewModel;

public class MainActivity extends AppCompatActivity {
    private CountryViewModel model;
    private static String VAL="VAL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(savedInstanceState!=null){
            EditText et= (EditText)findViewById(R.id.et1);

            if(et!=null){
                if(savedInstanceState.getCharSequence(VAL)!=null) {
                    et.setText(savedInstanceState.getCharSequence(VAL));
                    Log.d("val","val est:"+ savedInstanceState.getCharSequence(VAL));
                }
                else{
                    Log.d("val","val est null");
                }
            }
            if(et==null){
                Log.d("val","EditText est null");
            }

        }
        model = ViewModelProviders.of(this).get(CountryViewModel.class);

        CountryListFragment fragmentCountry = CountryListFragment.newInstance();
        Button b=(Button) findViewById(R.id.b_invite);
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        if (findViewById(R.id.global_container) != null){

            getSupportFragmentManager().beginTransaction().replace(R.id.global_container, fragmentCountry).commit();
            model.getSelectedCountry().observe(this, new Observer<Country>() {
                @Override
                public void onChanged(@Nullable Country country) {
                    if (country != null) {
                        ListCitiesFragment fragmentCity = ListCitiesFragment.newInstance();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.global_container, fragmentCity);
                        transaction.commit();
                    }
                }


            });
            b.setOnClickListener(new Button.OnClickListener(){
                public void onClick(View view){
                    BlankFragment bf=BlankFragment.newInstance();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.addToBackStack(null);
                    transaction.replace(R.id.global_container, bf);
                    transaction.commit();
                }
            });
        } else {/


            getSupportFragmentManager().beginTransaction().replace(R.id.countries_container,fragmentCountry).commit();
            ListCitiesFragment fragmentCities = ListCitiesFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.cities_container,fragmentCities).commit();
        }

        ((EditText)findViewById(R.id.et1)).setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                
                if (!hasFocus) {
                    Log.d("val","ca change de focus");
                   EditText et=((EditText)findViewById(R.id.et1));
                  String et_val=String.valueOf(et.getText());


                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        model.setSelectedCountry(null);
        super.onBackPressed();
    }

    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        EditText et= (EditText)findViewById(R.id.et1);
        outState.putCharSequence(VAL, et.getText());
        et.setText("");
        Log.d("val","valeur sauvee");

    }
}
