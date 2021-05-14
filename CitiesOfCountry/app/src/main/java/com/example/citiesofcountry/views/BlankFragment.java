package com.example.citiesofcountry.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.citiesofcountry.R;
import com.example.citiesofcountry.domaine.Invite;

import java.util.ArrayList;
import java.util.List;


public class BlankFragment extends Fragment {
    
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    public BlankFragment() {
        
    }

  
    public static BlankFragment newInstance() {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_recyclerview, container, false);
        Log.d("test","Oncreate fragment");
        RecyclerView rv=view.findViewById(R.id.rv_test);
        Invite inv1=new Invite("David","David","Eau");
        Invite inv2=new Invite("Michelin","Michelin","Coca");
        List<Invite> liste;
        liste = new ArrayList<Invite>();
        liste.add(inv1);
        liste.add(inv2);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setAdapter(new TestAdapter(liste));
        
        return view;
    }

   

    public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder>{

        List<Invite> liste;
        public TestAdapter(List<Invite> l){
            liste=l;
        }
        @NonNull
        @Override
        public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view=LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.a_row,viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TestAdapter.ViewHolder viewHolder, int i) {
            viewHolder.nom.setText(liste.get(i).getNom());
            viewHolder.boisson.setText(liste.get(i).getBoisson());
            Log.d("viewHolder","valeurs: "+viewHolder.nom.getText()+" "+viewHolder.boisson.getText());
            Log.d("taille","t: "+String.valueOf(liste.size()));

        }

        @Override
        public int getItemCount() {
            return liste.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView nom;
            private TextView boisson;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                nom=(TextView)itemView.findViewById(R.id.tv_test1);
                boisson=(TextView)itemView.findViewById(R.id.tv_test2);
            }
        }
    }
}
