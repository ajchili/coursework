package com.kirinpatel.carlist.adapters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kirinpatel.carlist.CarActivity;
import com.kirinpatel.carlist.R;
import com.kirinpatel.carlist.utils.Car;
import com.kirinpatel.carlist.utils.Cars;

import org.w3c.dom.Text;

import java.io.File;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarHolder> {

    private List<Car> cars;

    static class CarHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Car car;
        private View view;
        private File image;
        private ImageView imageView;
        private TextView make;
        private TextView model;
        private TextView type;
        private TextView year;

        CarHolder(View view) {
            super(view);

            itemView.setOnClickListener(this);
            this.view = view;
            imageView = view.findViewById(R.id.list_car_image);
            make = view.findViewById(R.id.list_car_make);
            model = view.findViewById(R.id.list_car_model);
            type = view.findViewById(R.id.list_car_type);
            year = view.findViewById(R.id.list_car_year);
        }

        @Override
        public void onClick(View view) {
            Intent intent = CarActivity.newIntent(view.getContext(), car.getUuid());
            view.getContext().startActivity(intent);
        }

        void bind(Car car) {
            this.car = car;

            image = Cars.get(view.getContext()).getPhotoFile(car);

            if (image != null && image.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(image.getPath());
                imageView.setImageBitmap(bitmap);
            }
            make.setText(car.getMake());
            model.setText(car.getModel());
            type.setText(car.getType());
            year.setText(Integer.toString(car.getYear()));
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
