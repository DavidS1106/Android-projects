package com.example.citiesofcountry.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;


import com.example.citiesofcountry.domaine.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryViewModel extends AndroidViewModel {
    private MutableLiveData<Country> selectedCountry = new MutableLiveData<Country>();
    private List<Country> countries = new ArrayList<>();

    public CountryViewModel(@NonNull Application application) {
        super(application);

        countries.add(new Country("BE","Belgique"));
        countries.add(new Country("FR","France"));
        countries.add(new Country("IT","Italie"));
        countries.add(new Country("CA","Canada"));
        countries.add(new Country("NL","Pays-Bas"));
        countries.add(new Country("DE","Allemagne"));

    }

    public List<Country> getCountries() {
        return countries;
    }

    public LiveData<Country> getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(Country country){
        selectedCountry.setValue(country);
    }
}
