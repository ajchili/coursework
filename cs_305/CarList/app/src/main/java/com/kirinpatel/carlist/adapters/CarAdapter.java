package com.kirinpatel.carlist.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kirinpatel.carlist.CarActivity;
import com.kirinpatel.carlist.R;
import com.kirinpatel.carlist.utils.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarHolder> {

    private List<Car> cars;

    static class CarHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Car car;

        CarHolder(View view) {
            super(view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = CarActivity.newIntent(view.getContext(), car.getUuid());
            view.getContext().startActivity(intent);
        }

        void bind(Car car) {
            this.car = car;
        }
    }

    public CarAdapter(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public CarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item_car, parent,false);
        return new CarHolder(view);
    }

    @Override
    public void onBindViewHolder(CarHolder holder, int position) {
        Car car = cars.get(position);
        holder.bind(car);
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }
}
