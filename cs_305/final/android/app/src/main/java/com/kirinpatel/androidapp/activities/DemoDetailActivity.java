package com.kirinpatel.androidapp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kirinpatel.androidapp.R;
import com.kirinpatel.androidapp.utils.Photo;
import com.kirinpatel.androidapp.utils.PhotoFetcher;
import com.kirinpatel.androidapp.utils.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DemoDetailActivity extends AppCompatActivity {

    private static final String KEY_PHOTO_ID = "photoId";
    private static final String KEY_USER_ID = "userId";

    private Photo photo;
    private String userId;
    private ImageView imageView;
    private TextView author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_detail);

        if (getIntent().getSerializableExtra(KEY_PHOTO_ID) != null &&
                getIntent().getStringExtra(KEY_USER_ID) != null) {
            photo = (Photo) getIntent().getSerializableExtra(KEY_PHOTO_ID);
            userId = getIntent().getStringExtra(KEY_USER_ID);

            imageView = findViewById(R.id.demo_detail_image);
            TextView title = findViewById(R.id.demo_detail_title);
            title.setText(photo.getTitle());
            author = findViewById(R.id.demo_detail_author);

            new FetchPhoto().execute();
            new FetchUser().execute();
        }
    }

    public static Intent createIntent(Context context, Photo photo) {
        Intent intent = new Intent(context, DemoDetailActivity.class);
        intent.putExtra(KEY_PHOTO_ID, photo);
        intent.putExtra(KEY_USER_ID, photo.getUser().getId());
        return intent;
    }

    private class FetchPhoto extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... voids) {
            try {
                byte[] bytes = new PhotoFetcher().getUrlBytes(photo.getUrl());
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            } catch (IOException e) {
                cancel(true);
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);

            if (bitmap == null) {
                Toast.makeText(DemoDetailActivity.this,
                        "Unable to download image.",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    private class FetchUser extends AsyncTask<Void, Void, User> {

        @Override
        protected User doInBackground(Void... voids) {
            try {
                String url = Uri.parse("https://us-central1-cs-305-final.cloudfunctions.net/getUser")
                        .buildUpon()
                        .appendQueryParameter("id", userId)
                        .build()
                        .toString();
                String json = new PhotoFetcher().getUrlString(url);
                JSONObject jsonObject = new JSONObject(json);
                return new User(jsonObject.getString("id"),
                        jsonObject.getString("name"),
                        jsonObject.getInt("age"));
            } catch (IOException | JSONException e) {
                cancel(true);
                return null;
            }
        }

        @Override
        protected void onPostExecute(User user) {
            author.setText("Uploaded by: " + user.getName());
        }
    }
}
