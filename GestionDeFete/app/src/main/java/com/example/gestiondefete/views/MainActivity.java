package com.example.gestiondefete.views;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gestiondefete.R;
import com.example.gestiondefete.models.Participant;
import com.example.gestiondefete.presenter.ParticipantListAdapter;
import com.example.gestiondefete.viewmodels.GestionDeFeteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_SMS = 3;
    private GestionDeFeteViewModel gestionDeFeteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        demanderPermissionLecture();


        RecyclerView rvParticipants = findViewById(R.id.rv_participants);
        final ParticipantListAdapter adapter = new ParticipantListAdapter(this);
        rvParticipants.setAdapter(adapter);
        gestionDeFeteViewModel = ViewModelProviders.of(this).get(GestionDeFeteViewModel.class);
        gestionDeFeteViewModel.getAllParticipants().observe(this,new Observer<List<Participant>>(){
            @Override
            public void onChanged(@Nullable List<Participant> participants) {
                adapter.setParticipants(participants);
            }
        });


    }

    private void demanderPermissionLecture(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED ){
            if  (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECEIVE_SMS) || ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_SMS) ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.expli_permission_sms).setTitle("Permission exigée");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, MY_PERMISSIONS_REQUEST_READ_SMS);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, MY_PERMISSIONS_REQUEST_READ_SMS
                );
            }
        }
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
}
