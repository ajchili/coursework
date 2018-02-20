package com.kirinpatel.criminalintent.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kirinpatel.criminalintent.R;
import com.kirinpatel.criminalintent.util.Crime;

import java.util.List;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.CrimeHolder> {

    private List<Crime> crimes;

    static class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Crime crime;
        private TextView crimeTitle;
        private TextView crimeDate;

        CrimeHolder(View view) {
            super(view);

            itemView.setOnClickListener(this);

            crimeTitle = itemView.findViewById(R.id.crime_title);
            crimeDate = itemView.findViewById(R.id.crime_date);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(
                    view.getContext(),
                    crime.getTitle() + " clicked.",
                    Toast.LENGTH_SHORT).show();
        }

        void bind(Crime crime) {
            this.crime = crime;

            crimeTitle.setText(crime.getTitle());
            crimeDate.setText(crime.getDate().toString());
        }
    }

    public CrimeAdapter(List<Crime> crimes) {
        this.crimes = crimes;
    }

    @Override
    public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item_crime, parent,false);
        return new CrimeHolder(view);
    }

    @Override
    public void onBindViewHolder(CrimeHolder holder, int position) {
        Crime crime = crimes.get(position);
        holder.bind(crime);
    }

    @Override
    public int getItemCount() {
        return crimes.size();
    }
}
