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

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link CountryListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountryListFragment extends Fragment {
    private CountryViewModel model;
  //  private OnFragmentInteractionListener mListener;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_country_list, container, false);
        RecyclerView rvCountries = view.findViewById(R.id.rv_countries);
        rvCountries.setAdapter(new CountryRecyclerViewAdapter(model));
        return view;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment CountryListFragment.
     */
    // TODO: Rename and change types and number of parameters
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




/*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
*/
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
 /*   public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
