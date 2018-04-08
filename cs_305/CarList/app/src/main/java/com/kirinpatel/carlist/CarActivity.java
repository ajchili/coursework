package com.kirinpatel.carlist;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.kirinpatel.carlist.utils.Car;
import com.kirinpatel.carlist.utils.Cars;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
        System.out.println(uuid);

        if (uuid != null) {
            car = Cars.get(CarActivity.this).getCar(uuid);
        } else {
            car = new Car();
        }

        final EditText make = findViewById(R.id.car_make);
        final EditText model = findViewById(R.id.car_model);
        final EditText type = findViewById(R.id.car_type);

        final Spinner yearSpinner = findViewById(R.id.car_year);
        ArrayList<Integer> years = new ArrayList<>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1908; i <= thisYear; i++) {
            years.add(i);
        }
        yearSpinner.setAdapter(new ArrayAdapter<>(CarActivity.this,
                android.R.layout.simple_spinner_item, years));

        imageView = findViewById(R.id.car_image);

        image = Cars.get(CarActivity.this).getPhotoFile(car);
        updateImageView();

        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = image != null
                && captureImage.resolveActivity(getPackageManager()) != null;

        ImageButton setImage = findViewById(R.id.car_set_image);
        setImage.setEnabled(canTakePhoto);

        setImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = FileProvider.getUriForFile(CarActivity.this,
                        "com.kirinpatel.carlist.FileProvider", image);
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                List<ResolveInfo> cameraActivities =
                        getPackageManager().queryIntentActivities(captureImage,
                                PackageManager.MATCH_DEFAULT_ONLY);

                for (ResolveInfo info : cameraActivities) {
                    grantUriPermission(info.activityInfo.packageName,
                            uri,
                            Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                }

                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });

        Button saveCar = findViewById(R.id.car_save);
        saveCar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                car.setMake(make.getText().toString());
                car.setModel(model.getText().toString());
                car.setType(type.getText().toString());
                car.setYear(Integer.parseInt(yearSpinner.getSelectedItem().toString()));

                if (Cars.get(CarActivity.this).getCar(car.getUuid()) != null) {
                    Cars.get(CarActivity.this).updateCar(car);
                } else {
                    Cars.get(CarActivity.this).addCar(car);
                }

                finish();
            }
        });

        image = Cars.get(CarActivity.this).getPhotoFile(car);
        updateImageView();

        make.setText(car.getMake());
        model.setText(car.getModel());
        type.setText(car.getType());
        yearSpinner.setSelection(years.indexOf(car.getYear()));
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

    public static Intent newIntent(Context context, UUID uuid) {
        Intent intent = new Intent(context, CarActivity.class);
        intent.putExtra(KEY_UUID, uuid);
        return intent;
    }
}
