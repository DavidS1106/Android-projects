package com.example.citiesofcountry.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.citiesofcountry.R;
import com.example.citiesofcountry.domaine.Country;
import com.example.citiesofcountry.viewmodel.CountryViewModel;

import java.util.List;


public class CountryListFragment extends Fragment {
    private CountryViewModel model;

    public CountryListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(getActivity()).get(CountryViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        View view = inflater.inflate(R.layout.fragment_country_list, container, false);
        RecyclerView rvCountries = view.findViewById(R.id.rv_countries);
        rvCountries.setAdapter(new CountryRecyclerViewAdapter(model));
        return view;
    }

    
    public static CountryListFragment newInstance() {
        CountryListFragment fragment = new CountryListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static class CountryRecyclerViewAdapter
            extends RecyclerView.Adapter<CountryRecyclerViewAdapter.ViewHolder> {

        private final CountryViewModel model;
        private final List<Country> countries;

        CountryRecyclerViewAdapter(CountryViewModel model) {
            this.model = model;
            this.countries = model.getCountries();
        }

        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CitiesOfCountry","on a cliqué");
                Country country = (Country) view.getTag();
                model.setSelectedCountry(country);
            }
        };



        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.country_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.tvCode.setText(countries.get(position).getCode());
            holder.tvName.setText(countries.get(position).getName());
            holder.itemView.setTag(countries.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return countries.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView tvCode;
            final TextView tvName;

            ViewHolder(View view) {
                super(view);
                tvCode = (TextView) view.findViewById(R.id.tv_code);
                tvName = (TextView) view.findViewById(R.id.tv_name);
            }
        }
    }




}
