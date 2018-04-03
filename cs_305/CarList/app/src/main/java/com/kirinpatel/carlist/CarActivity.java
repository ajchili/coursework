package com.kirinpatel.carlist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.kirinpatel.carlist.utils.Car;
import com.kirinpatel.carlist.utils.Cars;

import java.io.File;
import java.util.UUID;

public class CarActivity extends AppCompatActivity {

    private static final int REQUEST_PHOTO = 2;

    private Car car;
    private File image;
    private ImageView imageView;
    private static final String KEY_UUID = "uuid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        UUID uuid = (UUID) getIntent().getSerializableExtra(KEY_UUID);

        if (uuid != null) {
            car = Cars.get(CarActivity.this).getCar(uuid);
        } else {
            car = new Car();
        }

        image = Cars.get(CarActivity.this).getPhotoFile(car);
        updateImageView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateImageView();
    }

    public void updateImageView() {
        if (image != null || !image.exists()) {
            imageView.setImageDrawable(null);
        } else {
            Bitmap bitmap = BitmapFactory.decodeFile(image.getPath());
            imageView.setImageBitmap(bitmap);
        }
    }

    public static Intent newIntent(Context context, UUID uid) {
        Intent intent = new Intent(context, CarActivity.class);
        intent.putExtra(KEY_UUID, uid);
        return intent;
    }
}
