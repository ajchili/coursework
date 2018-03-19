package com.kirinpatel.criminalintent;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kirinpatel.criminalintent.util.Crime;
import com.kirinpatel.criminalintent.util.CrimeLab;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public class CrimeActivity extends AppCompatActivity {

    private static final int REQUEST_PHOTO = 2;

    private Crime crime;
    private File photo;
    private ImageView image;
    private static final String KEY_UUID = "uuid";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        UUID uuid = (UUID) getIntent().getSerializableExtra(KEY_UUID);

        final TextView title = findViewById(R.id.crime_detail_title);
        final Button date = findViewById(R.id.crime_detail_date);
        final CheckBox solved = findViewById(R.id.crime_detail_solved);
        image = findViewById(R.id.crime_detail_image);
        final ImageButton setImage = findViewById(R.id.crime_detail_set_image);
        Button save = findViewById(R.id.crime_detail_save);

        if (uuid != null) {
            crime = CrimeLab.get(CrimeActivity.this).getCrime(uuid);
        } else {
            crime = new Crime();
            crime.setDate(new Date(System.currentTimeMillis()));
        }

        photo = CrimeLab.get(CrimeActivity.this).getPhotoFile(crime);
        updatePhotoView();

        title.setText(crime.getTitle());
        date.setText(crime.getDate().toString());
        solved.setChecked(crime.isSolved());

        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = photo != null
                && captureImage.resolveActivity(getPackageManager()) != null;
        setImage.setEnabled(canTakePhoto);

        setImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Uri uri = FileProvider.getUriForFile(CrimeActivity.this,
                        "com.kirinpatel.criminalintent.FileProvider", photo);
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

        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final View dialogView = LayoutInflater.from(CrimeActivity.this)
                        .inflate(R.layout.dialog_date_picker, null);
                final DatePicker datePicker = dialogView.findViewById(R.id.dialog_picker);
                datePicker.init(1900 + crime.getDate().getYear(),
                        crime.getDate().getMonth(),
                        crime.getDate().getDate(),
                        new DatePicker.OnDateChangedListener() {
                            @Override
                            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                                int year = datePicker.getYear();
                                int month = datePicker.getMonth();
                                int day = datePicker.getDayOfMonth();

                                Date selectedDate = new GregorianCalendar(year, month, day).getTime();
                                crime.setDate(selectedDate);
                                date.setText(crime.getDate().toString());
                            }
                        });

                Dialog dialog = new AlertDialog.Builder(CrimeActivity.this)
                        .setTitle(R.string.crime_date)
                        .setView(dialogView)
                        .setPositiveButton(android.R.string.ok, null)
                        .setNeutralButton(android.R.string.cancel, null)
                        .create();
                dialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                crime.setTitle(title.getText().toString());
                crime.setSolved(solved.isChecked());
                if (CrimeLab.get(CrimeActivity.this).getCrime(crime.getUid()) != null) {
                    CrimeLab.get(CrimeActivity.this).updateCrime(crime);
                } else {
                    CrimeLab.get(CrimeActivity.this).addCrime(crime);
                }
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updatePhotoView();
    }

    private void updatePhotoView() {
        if (photo == null || !photo.exists()) {
            image.setImageDrawable(null);
        } else {
            Bitmap bitmap = BitmapFactory.decodeFile(photo.getPath());
            image.setImageBitmap(bitmap);
        }
    }

    public static Intent newIntent(Context context, UUID uid) {
        Intent intent = new Intent(context, CrimeActivity.class);
        intent.putExtra(KEY_UUID, uid);
        return intent;
    }
}
