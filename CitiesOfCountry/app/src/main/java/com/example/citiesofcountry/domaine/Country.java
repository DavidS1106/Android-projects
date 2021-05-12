package com.example.citiesofcountry.domaine;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private String code;
    private String name;
    private List<String> cities;
    public Country(String code, String name){
        this.code = code;
        this.name = name;
    }


    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public List<String> getCities() {
        if (cities == null){
            cities = new ArrayList<String>();
            for (int i = 1; i <= 15 ; i++){
                cities.add( name + "ville" + i);
            }
        }
        return cities;
    }
    
    public String getCitiesToString(){
      String cities ="";
      for (String city : getCities()){
         cities += city +",";
      }
      
      return cities.endsWith(",")?cities.substring(0,cities.length()-1):cities;
      
    }

}
