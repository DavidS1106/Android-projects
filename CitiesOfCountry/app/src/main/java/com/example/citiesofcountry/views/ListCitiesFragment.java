package com.example.citiesofcountry.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.citiesofcountry.R;
import com.example.citiesofcountry.domaine.Country;
import com.example.citiesofcountry.viewmodel.CountryViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.

 * to handle interaction events.
 * Use the {@link ListCitiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListCitiesFragment extends Fragment {
    private CountryViewModel model;

    public ListCitiesFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment ListCitiesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListCitiesFragment newInstance() {
        ListCitiesFragment fragment = new ListCitiesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(getActivity()).get(CountryViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_cities, container, false);
        final TextView rvCities = view.findViewById(R.id.tv_cities);

        model.getSelectedCountry().observe(this, new Observer<Country>() {
            @Override
            public void onChanged(@Nullable Country country) {
                if (country != null){
                    rvCities.setText(country.getCitiesToString());
                }
                else rvCities.setText("");
            }
        });
        return view;
    }

}
